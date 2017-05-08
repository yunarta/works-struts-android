package com.mobilesolutionworks.android.viper

import android.databinding.ViewDataBinding

/**
 * Created by yunarta on 14/5/17.
 */

public class VIPERViewBinding<V : VIPERView, B : ViewDataBinding> {

    private val view: V? = null

    private val binding: B? = null

    protected fun binding(): B? {
        return binding
    }

    protected fun view(): V? {
        return view
    }
}
