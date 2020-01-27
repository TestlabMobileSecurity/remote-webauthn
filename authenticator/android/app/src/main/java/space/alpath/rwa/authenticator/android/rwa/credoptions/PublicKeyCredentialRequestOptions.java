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

import android.util.Base64;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import webauthnkit.core.data.UserVerificationRequirement;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents WebAuthn PublicKeyCredentialRequestOptions.
 * https://www.w3.org/TR/webauthn/#dictdef-publickeycredentialrequestoptions
 */
public final class PublicKeyCredentialRequestOptions {
    private final byte[] challenge;
    private final Long timeout;
    private final String rpId;
    private final List<PublicKeyCredentialDescriptor> allowCredentials;
    private final String userVerification;
    // Extensions currently not supported

    /**
     * Creates WebAuthn PublicKeyCredentialRequestOptions object. See WebAuthn reference on top for params.
     */
    public PublicKeyCredentialRequestOptions(
            @JsonProperty(value = "challenge", required = true) final String b64Challenge,
            @JsonProperty(value = "timeout") final Long timeout,
            @JsonProperty(value = "rpId") final String rpId,
            @JsonProperty(value = "allowCredentials") final List<PublicKeyCredentialDescriptor> allowCredentials,
            @JsonProperty(value = "userVerification") final String userVerification) {
        this.challenge = Base64.decode(b64Challenge, Base64.DEFAULT);
        this.timeout = timeout;
        this.rpId = rpId;
        this.allowCredentials = allowCredentials;
        this.userVerification = userVerification;
    }

    public PublicKeyCredentialRequestOptions(final webauthnkit.core.data.PublicKeyCredentialRequestOptions wakOptions) {
        this.challenge = wakOptions.getChallenge();
        this.timeout = wakOptions.getTimeout();
        this.rpId = wakOptions.getRpId();

        this.allowCredentials = new ArrayList<>();
        for (final webauthnkit.core.data.PublicKeyCredentialDescriptor wakCredential : wakOptions.getAllowCredential()) {
            this.allowCredentials.add(new PublicKeyCredentialDescriptor(wakCredential));
        }

        this.userVerification = wakOptions.getUserVerification().toString();
    }

    /**
     * Transforms to WebAuthnKit equivalent of PublicKeyCredentialRequestOptions.
     *
     * @return WebAuthnKit PublicKeyCredentialRequestOptions
     */
    public webauthnkit.core.data.PublicKeyCredentialRequestOptions toWebAuthnKit() {
        final List<webauthnkit.core.data.PublicKeyCredentialDescriptor> wakAllowCredentials = new ArrayList<>();
        if (allowCredentials != null) {
            for (final PublicKeyCredentialDescriptor credential : allowCredentials) {
                wakAllowCredentials.add(credential.toWebAuthnKit());
            }
        }

        UserVerificationRequirement wakUserVerification;
        if (userVerification != null) {
            switch (userVerification) {
                case "discouraged":
                    wakUserVerification = UserVerificationRequirement.Discouraged;
                    break;
                case "preferred":
                    wakUserVerification = UserVerificationRequirement.Preferred;
                    break;
                case "required":
                    wakUserVerification = UserVerificationRequirement.Required;
                    break;
                default:
                    throw new RuntimeException("Type not supported");
            }
        } else {
            wakUserVerification = UserVerificationRequirement.Preferred;
        }

        return new webauthnkit.core.data.PublicKeyCredentialRequestOptions(
                challenge,
                rpId,
                wakAllowCredentials,
                wakUserVerification,
                timeout);
    }

    @JsonIgnore
    public byte[] getChallenge() {
        return challenge;
    }

    @JsonProperty("challenge")
    public String getB64Challenge() {
        return Base64.encodeToString(challenge, Base64.DEFAULT);
    }

    public Long getTimeout() {
        return timeout;
    }

    public String getRpId() {
        return rpId;
    }

    public List<PublicKeyCredentialDescriptor> getAllowCredentials() {
        return allowCredentials;
    }

    public String getUserVerification() {
        return userVerification;
    }
}
