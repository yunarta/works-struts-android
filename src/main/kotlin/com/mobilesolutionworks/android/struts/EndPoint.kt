package com.mobilesolutionworks.android.struts

/**
 * Created by yunarta on 13/5/17.
 */

abstract class EndPoint<out T : com.mobilesolutionworks.android.struts.EndPoint.Contract> {

    internal var _struts: Struts.BackEnd? = null

    internal val _contract: T
        get() = contract

    protected abstract val contract: T

    internal fun setup(struts: Struts.BackEnd) {
        this._struts = struts
    }

    protected val context: android.content.Context
        get() = _struts!!.context

    protected val struts: Struts.BackEnd?
        get() = _struts

    internal fun dispatchStart() = onStart()

    protected abstract fun onStart()

    interface Contract
}
