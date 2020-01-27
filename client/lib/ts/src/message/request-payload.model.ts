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

/**
 * Represents the request payload type.
 */
export enum RequestAction {
    create = 'create',
    request = 'request'
}

/**
 * Represents decrypted payload from either RequestMessage or ResponseSubmissionEvent.
 */
export interface RequestPayload {
    action: RequestAction;
}

/**
 * Represents decrypted payload shipped in RequestMessage from client to proxy.
 */
export interface CreateRequestPayload extends RequestPayload {
    options: CredentialCreationOptions;
}

/**
 * Represents decrypted payload shipped in ResponseSubmissionEvent from proxy to client.
 */
export interface RequestRequestPayload extends RequestPayload {
    options: CredentialRequestOptions;
}