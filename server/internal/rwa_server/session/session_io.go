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
)

// Reads and executes incoming RP-client requests
func (sess *session) ReadClientRequest() (*message.Request, error) {
	var request message.Request
	err := sess.clientConn.ReadJSON(&request)
	if err != nil {
		return nil, err
	}

	return &request, nil
}

// Reads and executes incoming Auth-client requests
func (sess *session) ReadAuthenticatorRequest() (*message.Request, error) {
	if sess.authenticatorConn == nil {
		return nil, errors.New("No authenticator set ")
	}

	var request message.Request
	err := sess.authenticatorConn.ReadJSON(&request)
	if err != nil {
		return nil, err
	}

	return &request, nil
}

// Sends event to RP-client
func (sess *session) sendClientEvent(event message.Event) error {
	return sess.clientConn.WriteJSON(event)
}

// Sends event to Auth-client
func (sess *session) sendAuthenticatorEvent(event message.Event) error {
	if sess.authenticatorConn == nil {
		return errors.New("No authenticator set ")
	}

	return sess.authenticatorConn.WriteJSON(event)
}
