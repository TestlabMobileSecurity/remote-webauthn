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

export enum EventType {
    sessionAssignmentEvent = 'session_assignment',
    authenticatorJoinEvent = 'authenticator_join',
    responseSubmissionEvent = 'response_submission'
}

/**
 * Represents an event message sent from proxy server to client.
 */
export interface EventMessage {
    type: EventType;
}

/**
 * Represents an event fired by proxy when new session was assigned.
 */
export interface SessionAssignmentEvent extends EventMessage {
    session_id: string;
}

/**
 * Represents an event fired by proxy when response was submitted.
 */
export interface ResponseSubmissionEvent extends EventMessage {
    payload: string;
}
