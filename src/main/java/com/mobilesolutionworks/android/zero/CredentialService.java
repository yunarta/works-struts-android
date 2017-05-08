package com.mobilesolutionworks.android.zero;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yunarta on 5/5/17.
 */

public class CredentialService {

    private Map<Class<? extends CredentialManager>, CredentialManager> credentialManagerMap;

    CredentialService() {
        this.credentialManagerMap = new HashMap<>();
    }

    <T extends CredentialManager> void installCredentialManager(Class<T> name, T credentialInterface) {
        credentialManagerMap.put(name, credentialInterface);
    }

    public <T extends CredentialManager> T get(Class<T> name) {
        return (T) credentialManagerMap.get(name);
    }
}
