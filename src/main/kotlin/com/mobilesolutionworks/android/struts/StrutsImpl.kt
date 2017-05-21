package com.mobilesolutionworks.android.struts

import android.content.Context
import android.os.PersistableBundle
import com.mobilesolutionworks.android.struts.jobmanager.Job

/**
 * Created by yunarta on 13/5/17.
 */

internal class StrutsImpl(val name: String, val _context: Context, plugins: Collection<Plugin>?) :
      Struts {

    val scheduler = SchedulerImpl(name, _context)

    val plugins = HashSet<Plugin>(plugins)

    val objectLocator: StrutsImpl.ObjectLocator

    init {
        this.objectLocator = StrutsImpl.ObjectLocator()
    }

    override val context: android.content.Context
        get() = _context

    fun start() {
        plugins.map { it.setContext(context); it }
              .map {
                  it.dispatchStartInstallation(object : Struts.Install {

                      override fun <T : EndPoint.Contract, M : EndPoint<T>> addEndPoint(name: Class<T>, endPoint: M) = this@StrutsImpl.addEndPoint(name, endPoint)

                      override fun <L : EntityLocator.Contract, M : EntityLocator<L>> addLocator(name: Class<L>, locator: M) = this@StrutsImpl.addLocator(name, locator)

                  })
              }
        objectLocator.endPoints().map {
            it.setup(object : Struts.BackEnd<Scheduler.BackEnd> {
                override val scheduler: Scheduler.BackEnd
                    get() = object : Scheduler.BackEnd {
                        override fun cancel(name: String) {
                            this@StrutsImpl.scheduler.cancel(it, name)
                        }

                        override fun schedule(name: String, job: Job, bundle: PersistableBundle?) {
                            this@StrutsImpl.scheduler.schedule(it, name, job, bundle)
                        }
                    }

                override fun <T : EndPoint.Contract> endPoint(name: Class<T>): T? = this@StrutsImpl.endPoint(name)

                override val context: Context
                    get() = this@StrutsImpl.context

                override fun <L : EntityLocator.Contract> locator(name: Class<L>): L? = this@StrutsImpl.locator(name)
            })
        }

        objectLocator.endPoints().map {
            it.dispatchCreate(object : Struts.Mutable {
                override val scheduler: Scheduler.Install
                    get() = object : Scheduler.Install {

                        override fun register(name: String, receiver: (PersistableBundle) -> Boolean) {
                            this@StrutsImpl.scheduler.register(it, name, receiver)
                        }
                    }
            })
            it
        }.map { it.dispatchStart() }
    }

    fun <T : EndPoint.Contract, M : EndPoint<T>> addEndPoint(name: Class<T>, endPoint: M) {
        objectLocator.addEndPoint(name, endPoint)
    }

    fun <L : EntityLocator.Contract, M : EntityLocator<L>> addLocator(name: Class<L>, locator: M) {
        objectLocator.addLocator(name, locator)
    }

    override fun <T : EndPoint.Contract> endPoint(name: Class<T>): T? {
        return objectLocator.endPoint(name)
    }

    fun <L : EntityLocator.Contract> locator(name: Class<L>): L? {
        return objectLocator.locator(name)
    }

    @Suppress("UNCHECKED_CAST")
    class ObjectLocator {

        private val moduleMap = java.util.HashMap<Class<out EndPoint.Contract>, EndPoint<*>>()

        private val locatorMap = java.util.HashMap<Class<out EntityLocator.Contract>, EntityLocator<*>>()

        fun <T : EndPoint.Contract, M : EndPoint<T>> addEndPoint(name: Class<T>, endPoint: M) {
            moduleMap.put(name, endPoint)
        }

        fun <L : EntityLocator.Contract, M : EntityLocator<L>> addLocator(name: Class<L>, module: M) {
            locatorMap.put(name, module)
        }

        fun <T : EndPoint.Contract> endPoint(name: Class<T>): T? {
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