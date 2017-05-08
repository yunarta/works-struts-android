package com.mobilesolutionworks.android.struts

/**
 * Created by yunarta on 13/5/17.
 */

internal class StrutsImpl(private val _context: android.content.Context) : Struts, com.mobilesolutionworks.android.struts.Struts.Setup, com.mobilesolutionworks.android.struts.Struts.Install, com.mobilesolutionworks.android.struts.Struts.BackEnd {

    private val plugins: MutableSet<Plugin>

    private val objectLocator: com.mobilesolutionworks.android.struts.StrutsImpl.ObjectLocator

    init {
        this.plugins = java.util.HashSet<Plugin>()
        this.objectLocator = com.mobilesolutionworks.android.struts.StrutsImpl.ObjectLocator()
    }

    override fun installPlugin(plugin: Plugin) {
        plugins.add(plugin)
    }

    override val context: android.content.Context
        get() = _context

    fun start() {
        // configuration phase
        for (plugin in plugins) {
            plugin.setContext(context)
        }

        // installation phase
        for (plugin in plugins) {
            plugin.dispatchStartInstallation(this)
        }

        val points = objectLocator.endPoints()
        for (point in points) {
            point.setup(this)
        }

        val locators = objectLocator.locators()
        for (locator in locators) {
            locator.setup(this)
        }

        // start up phase
        for (locator in locators) {
            locator.setup(this)
        }

        for (point in points) {
            point.dispatchStart()
        }
    }

    override fun <T : com.mobilesolutionworks.android.struts.EndPoint.Contract, M : EndPoint<T>> addEndPoint(name: Class<T>, endPoint: M) {
        objectLocator.addEndPoint(name, endPoint)
    }

    override fun <L : EntityLocator.Contract, M : EntityLocator<L>> addLocator(name: Class<L>, locator: M) {
        objectLocator.addLocator(name, locator)
    }

    override fun <T : com.mobilesolutionworks.android.struts.EndPoint.Contract> endPoint(name: Class<T>): T? {
        return objectLocator.endPoint(name)
    }

    override fun <L : EntityLocator.Contract> locator(name: Class<L>): L? {
        return objectLocator.locator(name)
    }

    @Suppress("UNCHECKED_CAST")
    internal class ObjectLocator {

        private val moduleMap = java.util.HashMap<Class<out EndPoint.Contract>, EndPoint<*>>()

        private val locatorMap = java.util.HashMap<Class<out EntityLocator.Contract>, EntityLocator<*>>()

        fun <T : com.mobilesolutionworks.android.struts.EndPoint.Contract, M : EndPoint<T>> addEndPoint(name: Class<T>, endPoint: M) {
            moduleMap.put(name, endPoint)
        }

        fun <L : EntityLocator.Contract, M : EntityLocator<L>> addLocator(name: Class<L>, module: M) {
            locatorMap.put(name, module)
        }

        fun <T : com.mobilesolutionworks.android.struts.EndPoint.Contract> endPoint(name: Class<T>): T? {
            return moduleMap[name]?._contract as? T
        }

        fun <L : EntityLocator.Contract> locator(name: Class<L>): L? {
            return locatorMap[name]?._contract as? L
        }

        fun endPoints(): Collection<EndPoint<*>> {
            return moduleMap.values
        }

        fun locators(): Collection<EntityLocator<*>> {
            return locatorMap.values
        }
    }
}