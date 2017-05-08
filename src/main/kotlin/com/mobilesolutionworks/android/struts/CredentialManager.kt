package com.mobilesolutionworks.android.struts

/**
 * Created by yunarta on 13/5/17.
 */

public interface CredentialManager<C : Credential> {

    fun add(name: String, credential: C)

    fun update(name: String, credential: C)

    operator fun get(name: String): C

    fun list(): List<C>
}