package com.mobilesolutionworks.android.zero;

import android.support.annotation.NonNull;

/**
 * Created by yunarta on 7/5/17.
 */

public abstract class EntityLocator<T extends EntityLocator.Contract> {

    abstract T getInterface();

    private Struts.BackEnd struts;

    void setup(@NonNull Struts.BackEnd struts) {
        this.struts = struts;
    }

    /**
     * Created by yunarta on 9/5/17.
     */

    interface Contract {

    }
}
