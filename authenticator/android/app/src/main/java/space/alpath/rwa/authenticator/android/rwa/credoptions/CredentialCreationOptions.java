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

/**
 * Represents WebAuthn CredentialCreationOptions.
 * https://www.w3.org/TR/webauthn/#credentialcreationoptions-extension
 */
public final class CredentialCreationOptions {
    private final PublicKeyCredentialCreationOptions publicKey;

    /**
     * Creates WebAuthn CredentialCreationOptions object. See WebAuthn reference on top for params.
     */
    public CredentialCreationOptions(@JsonProperty("publicKey") final PublicKeyCredentialCreationOptions publicKey) {
        this.publicKey = publicKey;
    }

    public PublicKeyCredentialCreationOptions getPublicKey() {
        return publicKey;
    }
}
