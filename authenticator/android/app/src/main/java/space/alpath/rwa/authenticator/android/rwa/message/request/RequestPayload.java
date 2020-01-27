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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Represents decrypted event message payload from server to client.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "action")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CreateRequestPayload.class, name = "create"),
        @JsonSubTypes.Type(value = RequestRequestPayload.class, name = "request")
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestPayload {
    private final String action;

    /**
     * Creates new decrypted event message payload.
     *
     * @param action action to perform (create / get)
     */
    public RequestPayload(@JsonProperty(value = "action", required = true) final String action) {
        this.action = action;
    }

    /**
     * Returns the action to perform (create / get).
     *
     * @return action to perform (create / get)
     */
    public String getAction() {
        return action;
    }
}
