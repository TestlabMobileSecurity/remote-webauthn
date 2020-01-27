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

package space.alpath.rwa.authenticator.android.rwa.message.response;

/**
 * Represents answer (event message) payload for credentials create operation.
 */
public final class CreateResponsePayload extends ResponsePayload {
    private final Object response;

    /**
     * Creates new credentials create answer payload.
     *
     * @param response WebAuthn credentials create answer object
     */
    public CreateResponsePayload(final Object response) {
        super("create");
        this.response = response;
    }

    /**
     * Returns WebAuthn credentials create answer object.
     *
     * @return WebAuthn credentials create answer object
     */
    public Object getResponse() {
        return response;
    }
}
