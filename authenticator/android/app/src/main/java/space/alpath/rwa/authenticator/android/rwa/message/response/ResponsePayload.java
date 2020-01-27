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
 * Represents decrypted request message payload from client to server.
 */
public class ResponsePayload {
    private final String action;

    /**
     * Creates new decrypted request message payload.
     *
     * @param action action to perform on server
     */
    public ResponsePayload(final String action) {
        this.action = action;
    }

    /**
     * Returns the action to perform on server
     *
     * @return action to perform on server
     */
    public String getAction() {
        return action;
    }
}
