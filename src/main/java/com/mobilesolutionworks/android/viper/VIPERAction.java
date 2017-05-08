package com.mobilesolutionworks.android.viper;

/**
 * Created by yunarta on 5/5/17.
 */

public interface VIPERAction<V extends VIPERView> {

    void perform(V view);
}
