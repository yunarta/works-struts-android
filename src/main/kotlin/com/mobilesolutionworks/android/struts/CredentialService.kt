package com.mobilesolutionworks.android.struts

/**
 * Created by yunarta on 13/5/17.
 */

public class CredentialService internal constructor() {

    private val credentialManagerMap: MutableMap<Class<out CredentialManager<*>>, CredentialManager<*>>

    init {
        this.credentialManagerMap = java.util.HashMap<Class<out CredentialManager<*>>, CredentialManager<*>>()
    }

    internal fun <T : CredentialManager<*>> installCredentialManager(name: Class<T>, credentialInterface: T) {
        credentialManagerMap.put(name, credentialInterface)
    }

    @Suppress("UNCHECKED_CAST")
    operator fun <T : CredentialManager<*>> get(name: Class<T>): T? = credentialManagerMap[name] as? T
}
