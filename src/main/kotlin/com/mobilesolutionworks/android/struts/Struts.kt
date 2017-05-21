package com.mobilesolutionworks.android.struts

import kotlin.reflect.KClass

/**
 * Created by yunarta on 13/5/17.
 */

public interface Struts {

    fun <T : EndPoint.Contract> endPoint(name: Class<T>): T?

    operator fun <T : EndPoint.Contract> get(name: KClass<T>): T? = endPoint(name.java)

    val context: android.content.Context

    interface BackEnd<out S : Scheduler> : Struts {

        val scheduler: S

        fun <L : EntityLocator.Contract> locator(name: Class<L>): L?
    }

    interface Install {

        fun <T : EndPoint.Contract, M : EndPoint<T>> addEndPoint(name: Class<T>, endPoint: M)

        fun <L : EntityLocator.Contract, M : EntityLocator<L>> addLocator(name: Class<L>, locator: M)
    }

    interface Mutable {

        val scheduler: Scheduler.Install
    }
}
