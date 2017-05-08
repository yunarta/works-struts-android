package com.mobilesolutionworks.android.viper

import com.mobilesolutionworks.android.app.controller.WorksController

/**
 * Created by yunarta on 14/5/17.
 */

open class VIPERPresenter<V : VIPERView> : WorksController() {

    private var view: V? = null

    fun setView(view: V) {
        this.view = view
        onAttached()
    }

    protected fun view(): V? {
        return view
    }

    open protected fun onAttached() {

    }
}