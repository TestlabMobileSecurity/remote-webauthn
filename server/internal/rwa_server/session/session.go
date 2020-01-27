/*
Copyright 2020 Paul Wagner

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package session

import (
	"errors"
	"server/internal/rwa_server/message"

	"github.com/google/uuid"
)
import "github.com/gorilla/websocket"

type sessionState uint8

// Represents the states of the proxy state machine
const (
	awaitRequestState  sessionState = 0
	awaitResponseState sessionState = 1
	finalState         sessionState = 2
)

// Represents a session, which basically consists of two RP- and Auth-client connections
type session struct {
	clientConn, authenticatorConn *websocket.Conn
	state                         sessionState
	deferredRequestPayload        string
}

type Session interface {
	SetAuthenticator(conn *websocket.Conn) error
	ReadClientRequest() (*message.Request, error)
	ReadAuthenticatorRequest() (*message.Request, error)
	ForwardRequest(requestPayload string) error
	ForwardResponse(responsePayload string) error
	Terminate()
	sendClientEvent(event message.Event) error
	sendAuthenticatorEvent(event message.Event) error
}

// Initializes a new session with given RP-client connection
func New(clientConn *websocket.Conn) (string, Session, error) {
	sessionId := uuid.New().String()

	session := &session{
		clientConn: clientConn,
		state:      awaitRequestState,
	}

	err := session.sendClientEvent(message.NewSessionAssignmentEvent(sessionId))
	return sessionId, session, err
}

// Sets remaining Auth-client
func (sess *session) SetAuthenticator(conn *websocket.Conn) error {
	if sess.authenticatorConn != nil {
		return errors.New("Authenticator already set ")
	}
	sess.authenticatorConn = conn

	if len(sess.deferredRequestPayload) > 0 {
		err := sess.sendAuthenticatorEvent(message.NewRequestSubmissionEvent(sess.deferredRequestPayload))
		if err != nil {
			return err
		}

		sess.deferredRequestPayload = ""
	}

	return sess.sendClientEvent(message.NewAuthenticatorJoinEvent())
}

// Forwards request with given payload to Auth-client
func (sess *session) ForwardRequest(requestPayload string) error {
	if sess.state != awaitRequestState {
		return errors.New("Invalid operation for current state ")
	}
	sess.state = awaitResponseState

	if sess.authenticatorConn != nil {
		return sess.sendAuthenticatorEvent(message.NewRequestSubmissionEvent(requestPayload))
	}

	sess.deferredRequestPayload = requestPayload
	return nil
}

// Forwards request with given payload to RP-client
func (sess *session) ForwardResponse(responsePayload string) error {
	if sess.state != awaitResponseState {
		return errors.New("Forward response operation not permitted for current state ")
	}

	sess.state = finalState

	return sess.sendClientEvent(message.NewResponseSubmissionEvent(responsePayload))
}

// Terminates session
func (sess *session) Terminate() {
	// Closing is allowed to fail if connection is already closed
	_ = sess.clientConn.Close()
	if sess.authenticatorConn != nil {
		_ = sess.authenticatorConn.Close()
	}
}
