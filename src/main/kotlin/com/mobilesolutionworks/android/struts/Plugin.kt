package com.mobilesolutionworks.android.struts

/**
 * Created by yunarta on 13/5/17.
 */

abstract class Plugin {

    protected var context: android.content.Context? = null
        private set

    internal fun setContext(context: android.content.Context) {
        this.context = context
    }

    internal fun dispatchStartInstallation(struts: Struts.Install) = startInstallation(struts)

    protected abstract fun startInstallation(struts: Struts.Install)
}