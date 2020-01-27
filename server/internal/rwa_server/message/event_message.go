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

package message

type eventType string

const (
	sessionAssignmentEventType  eventType = "session_assignment"
	authenticatorJoinEventType  eventType = "authenticator_join"
	requestSubmissionEventType  eventType = "request_submission"
	responseSubmissionEventType eventType = "response_submission"
)

type Event interface{}

// Represents an event message
type event struct {
	Type eventType `json:"type"`
}

// Represents a session assignment event message
type sessionAssignmentEvent struct {
	event
	SessionId string `json:"session_id"`
}

// Represents a request submisssion event message
type requestSubmissionEvent struct {
	event
	Payload string `json:"payload"`
}

// Represents a response submission event message
type responseSubmissionEvent struct {
	event
	Payload string `json:"payload"`
}

// Initializes a new session assignment event message
func NewSessionAssignmentEvent(sessionId string) Event {
	return &sessionAssignmentEvent{
		event{sessionAssignmentEventType},
		sessionId,
	}
}

// Initializes a new authenticator join event message
func NewAuthenticatorJoinEvent() Event {
	return &event{authenticatorJoinEventType}
}

// Initializes a new request submisssion event message
func NewRequestSubmissionEvent(payload string) Event {
	return &requestSubmissionEvent{
		event{requestSubmissionEventType},
		payload,
	}
}

// Initializes a new response submisssion event message
func NewResponseSubmissionEvent(payload string) Event {
	return &responseSubmissionEvent{
		event{responseSubmissionEventType},
		payload,
	}
}
