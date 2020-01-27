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

package space.alpath.rwa.authenticator.android.rwa.credoptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import webauthnkit.core.data.PublicKeyCredentialType;

/**
 * Represents WebAuthn PublicKeyCredentialParameters.
 * https://www.w3.org/TR/webauthn/#credential-params
 */
public final class PublicKeyCredentialParameters {
    private final String type;
    private final Long alg;

    /**
     * Creates WebAuthn PublicKeyCredentialParameters object. See WebAuthn reference on top for params.
     */
    public PublicKeyCredentialParameters(@JsonProperty(value = "type", required = true) final String type,
                                         @JsonProperty(value = "alg", required = true) final Long alg) {
        this.type = type;
        this.alg = alg;
    }

    /**
     * Transforms to WebAuthnKit equivalent of PublicKeyCredentialParameters.
     *
     * @return WebAuthnKit PublicKeyCredentialParameters
     */
    public webauthnkit.core.data.PublicKeyCredentialParameters toWebAuthnKit() {
        PublicKeyCredentialType wakType;
        switch (type) {
            case "public-key":
                wakType = PublicKeyCredentialType.PublicKey;
                break;
            default:
                throw new RuntimeException("Type not supported");
        }

        return new webauthnkit.core.data.PublicKeyCredentialParameters(
                wakType,
                alg.intValue()
        );
    }

    public String getType() {
        return type;
    }

    public long getAlg() {
        return alg;
    }
}
