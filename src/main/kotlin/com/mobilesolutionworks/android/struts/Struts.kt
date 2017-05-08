package com.mobilesolutionworks.android.struts

import kotlin.reflect.KClass

/**
 * Created by yunarta on 13/5/17.
 */

public interface Struts {

    fun <T : com.mobilesolutionworks.android.struts.EndPoint.Contract> endPoint(name: Class<T>): T?

    operator fun <T : com.mobilesolutionworks.android.struts.EndPoint.Contract> get(name: KClass<T>): T? = endPoint(name.java)


    val context: android.content.Context

    /**
     * Created by yunarta on 9/5/17.
     */

    interface BackEnd : com.mobilesolutionworks.android.struts.Struts {

        fun <L : EntityLocator.Contract> locator(name: Class<L>): L?
    }

    /**
     * Created by yunarta on 9/5/17.
     */

    interface Install {

        fun <T : com.mobilesolutionworks.android.struts.EndPoint.Contract, M : EndPoint<T>> addEndPoint(name: Class<T>, endPoint: M)

        fun <L : EntityLocator.Contract, M : EntityLocator<L>> addLocator(name: Class<L>, locator: M)
    }

    /**
     * Created by yunarta on 9/5/17.
     */

    interface Setup {

        fun installPlugin(plugin: Plugin)
    }
}