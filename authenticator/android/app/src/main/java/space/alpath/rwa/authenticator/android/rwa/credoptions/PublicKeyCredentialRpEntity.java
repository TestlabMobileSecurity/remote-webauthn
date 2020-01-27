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
 * Represents WebAuthn PublicKeyCredentialRpEntity.
 * https://www.w3.org/TR/webauthn/#dictdef-publickeycredentialrpentity
 */
public final class PublicKeyCredentialRpEntity {
    private final String id, name, icon;

    /**
     * Creates WebAuthn PublicKeyCredentialRpEntity object. See WebAuthn reference on top for params.
     */
    public PublicKeyCredentialRpEntity(@JsonProperty(value = "id", required = true) final String id,
                                       @JsonProperty(value = "name", required = true) final String name,
                                       @JsonProperty(value = "icon") final String icon) {
        this.id = id;
        this.name = name;
        this.icon = icon;
    }

    /**
     * Transforms to WebAuthnKit equivalent of PublicKeyCredentialRpEntity.
     *
     * @return WebAuthnKit PublicKeyCredentialRpEntity
     */
    public webauthnkit.core.data.PublicKeyCredentialRpEntity toWebAuthnKit() {
        return new webauthnkit.core.data.PublicKeyCredentialRpEntity(id, name, icon);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }
}
