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

package space.alpath.rwa.authenticator.android.rwa.message.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import space.alpath.rwa.authenticator.android.rwa.credoptions.CredentialCreationOptions;

/**
 * Represents request (request message) payload for credentials create operation.
 */
public final class CreateRequestPayload extends RequestPayload {
    private final CredentialCreationOptions options;

    /**
     * Creates new credentials create request payload.
     *
     * @param options credentials create options
     */
    public CreateRequestPayload(@JsonProperty(value = "options", required = true) CredentialCreationOptions options) {
        super("create");
        this.options = options;
    }

    /**
     * Returns credentials create options.
     *
     * @return credentials create options
     */
    public CredentialCreationOptions getOptions() {
        return options;
    }
}
