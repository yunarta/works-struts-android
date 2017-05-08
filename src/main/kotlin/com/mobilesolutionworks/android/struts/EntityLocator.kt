package com.mobilesolutionworks.android.struts

/**
 * Created by yunarta on 13/5/17.
 */

abstract class EntityLocator<out T : com.mobilesolutionworks.android.struts.EntityLocator.Contract> {

    internal val _contract: T
        get() = contract

    protected abstract val contract: T

    protected var struts: com.mobilesolutionworks.android.struts.Struts.BackEnd? = null
        private set

    internal fun setup(struts: com.mobilesolutionworks.android.struts.Struts.BackEnd) {
        this.struts = struts
    }

    /**
     * Created by yunarta on 9/5/17.
     */

    interface Contract
}