package com.mobilesolutionworks.android.zero;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by yunarta on 9/5/17.
 */

public abstract class Plugin {

    private Context context;

    void setContext(@NonNull Context context) {
        this.context = context;
    }

    @NonNull
    protected Context context() {
        return context;
    }

    protected abstract void startInstallation(@NonNull Struts.Install struts);
}
