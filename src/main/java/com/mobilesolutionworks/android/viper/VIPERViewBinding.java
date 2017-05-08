package com.mobilesolutionworks.android.viper;

import android.databinding.ViewDataBinding;

/**
 * Created by yunarta on 5/5/17.
 */

public class VIPERViewBinding<V extends VIPERView, B extends ViewDataBinding> {

    private V view;

    private B binding;

    protected B binding() {
        return binding;
    }

    protected V view() {
        return view;
    }
}
