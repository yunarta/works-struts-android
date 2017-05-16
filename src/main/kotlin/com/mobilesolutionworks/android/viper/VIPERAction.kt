package com.mobilesolutionworks.android.viper

/**
 * Created by yunarta on 14/5/17.
 */

public interface VIPERAction<in V : VIPERView> {

    fun perform(view: V)
}
