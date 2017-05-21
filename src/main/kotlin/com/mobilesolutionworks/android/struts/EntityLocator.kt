package com.mobilesolutionworks.android.struts

/**
 * Created by yunarta on 13/5/17.
 */

abstract class EntityLocator<out T : EntityLocator.Contract> {

    internal val _contract: T
        get() = contract

    protected abstract val contract: T

//    protected var struts: ImmutableStruts? = null
//        private set
//
//    internal fun setup(struts: ImmutableStruts) {
//        this.struts = struts
//    }

    /**
     * Created by yunarta on 9/5/17.
     */

    interface Contract
}