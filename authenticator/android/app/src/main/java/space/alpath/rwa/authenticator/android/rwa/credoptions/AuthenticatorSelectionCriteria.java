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
import webauthnkit.core.data.AuthenticatorAttachment;
import webauthnkit.core.data.UserVerificationRequirement;

/**
 * Represents WebAuthn AuthenticatorSelectionCriteria.
 * https://www.w3.org/TR/webauthn/#authenticatorSelection
 */
public final class AuthenticatorSelectionCriteria {
    private final String authenticatorAttachment, userVerification;

    private final boolean requireResidentKey;

    /**
     * Creates WebAuthn AuthenticatorSelectionCriteria object. See WebAuthn reference on top for params.
     */
    public AuthenticatorSelectionCriteria(
            @JsonProperty(value = "authenticatorAttachment") final String authenticatorAttachment,
            @JsonProperty(value = "userVerification") final String userVerification,
            @JsonProperty(value = "requireResidentKey") final boolean requireResidentKey) {
        this.authenticatorAttachment = authenticatorAttachment;
        this.userVerification = userVerification;
        this.requireResidentKey = requireResidentKey;
    }

    /**
     * Transforms to WebAuthnKit equivalent of AuthenticatorSelectionCriteria.
     *
     * @return WebAuthnKit AuthenticatorSelectionCriteria
     */
    public webauthnkit.core.data.AuthenticatorSelectionCriteria toWebAuthnKit() {
        AuthenticatorAttachment wakAuthenticatorAttachment = null;
        if (authenticatorAttachment != null) {
            switch (authenticatorAttachment) {
                case "platform":
                    wakAuthenticatorAttachment = AuthenticatorAttachment.Platform;
                    break;
                case "cross-platform":
                    wakAuthenticatorAttachment = AuthenticatorAttachment.CrossPlatform;
                    break;
                default:
                    throw new RuntimeException("Type not supported");
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

        return new webauthnkit.core.data.AuthenticatorSelectionCriteria(
                wakAuthenticatorAttachment,
                requireResidentKey,
                wakUserVerification
        );
    }

    public String getAuthenticatorAttachment() {
        return authenticatorAttachment;
    }

    public String getUserVerification() {
        return userVerification != null ? userVerification : "preferred";
    }

    public boolean isRequireResidentKey() {
        // Will return false (as specified) if not set
        return requireResidentKey;
    }
}
