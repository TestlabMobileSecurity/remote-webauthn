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
 * Represents a request message sent from client to server.
 */
public final class RequestMessage {
    private final String op, payload;

    /**
     * Creates a new request message.
     *
     * @param op      request message opcode
     * @param payload encrypted payload as base64
     */
    public RequestMessage(@JsonProperty(value = "op", required = true) final String op,
                          @JsonProperty(value = "payload") final String payload) {
        this.op = op;
        this.payload = payload;
    }

    /**
     * Returns opcode.
     *
     * @return opcode
     */
    public String getOp() {
        return op;
    }

    /**
     * Returns encrypted payload as base64.
     *
     * @return encrypted payload as base64
     */
    public String getPayload() {
        return payload;
    }
}
