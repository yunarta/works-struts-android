package com.mobilesolutionworks.android.zero;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by yunarta on 2/5/17.
 */

public abstract class EndPoint<T extends EndPoint.Contract> {

    private Struts.BackEnd struts;

    protected abstract T getInterface();

    protected void setup(@NonNull Struts.BackEnd struts) {
        this.struts = struts;
    }

    protected Context context() {
        return struts.context();
    }

    protected Struts.BackEnd struts() {
        return struts;
    }

    protected abstract void onStart();

    public interface Contract {
    }
}
