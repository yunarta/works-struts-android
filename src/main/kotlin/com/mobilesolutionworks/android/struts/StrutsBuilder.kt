package com.mobilesolutionworks.android.struts

/**
 * Created by yunarta on 21/5/17.
 */

class StrutsBuilder {

    private val _plugins = mutableSetOf<Plugin>()
    val plugins: Collection<Plugin>?
        get() = _plugins.toSet()

    fun addPlugin(plugin: Plugin) {
        _plugins.add(plugin)
    }
}