package com.mobilesolutionworks.android.struts

import android.util.Log

/**
 * Created by yunarta on 13/5/17.
 */

abstract class EndPoint<out T : EndPoint.Contract> {

    interface Contract

    internal var _struts: Struts.BackEnd<Scheduler.BackEnd>? = null
    protected val struts: Struts.BackEnd<Scheduler.BackEnd>?
        get() = _struts

    protected abstract val contract: T
    internal val _contract: T
        get() = contract

    internal fun setup(struts: Struts.BackEnd<Scheduler.BackEnd>) {
        this._struts = struts
    }

    protected val context: android.content.Context?
        get() = _struts?.context

    internal fun dispatchCreate(struts: Struts.Mutable) {
        onCreate(struts)
    }

    protected abstract fun onCreate(struts: Struts.Mutable)

    internal fun dispatchStart() {
        onStart()
    }

    protected abstract fun onStart()
}
