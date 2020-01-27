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

package space.alpath.rwa.authenticator.android.rwa.message;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an event message sent from server to client.
 */
public final class EventMessage {
    private final String type, payload;

    /**
     * Creates new event message.
     *
     * @param type    event message type
     * @param payload encrypted event message payload as base64
     */
    public EventMessage(@JsonProperty(value = "type", required = true) final String type,
                        @JsonProperty(value = "payload") final String payload) {
        this.type = type;
        this.payload = payload;
    }

    /**
     * Returns event message type.
     *
     * @return event message type
     */
    public String getType() {
        return type;
    }

    /**
     * Returns encrypted event message payload as base64.
     *
     * @return encrypted event message payload as base64
     */
    public String getPayload() {
        return payload;
    }
}
