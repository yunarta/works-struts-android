package com.mobilesolutionworks.android.viper;

import com.mobilesolutionworks.android.app.controller.WorksController;

/**
 * Created by yunarta on 5/5/17.
 */

public class VIPERPresenter<V extends VIPERView> extends WorksController {

    private V view;

    public void setView(V view) {
        this.view = view;
        onAttached();
    }

    protected V view() {
        return view;
    }

    protected void onAttached() {

    }
}
