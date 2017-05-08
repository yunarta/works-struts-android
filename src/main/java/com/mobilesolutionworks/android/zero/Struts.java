package com.mobilesolutionworks.android.zero;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by yunarta on 9/5/17.
 */

public interface Struts {

    <T extends EndPoint.Contract> T endPoint(@NonNull Class<T> name);

    @NonNull
    Context context();

    /**
     * Created by yunarta on 9/5/17.
     */

    interface BackEnd extends Struts {

        @NonNull
        Context context();

        @NonNull
        <L extends EntityLocator.Contract> L locator(@NonNull Class<L> name);
    }

    /**
     * Created by yunarta on 9/5/17.
     */

    interface Install {

        <T extends EndPoint.Contract, M extends EndPoint<T>> void addEndPoint(@NonNull Class<T> name, @NonNull M module);

        <L extends EntityLocator.Contract, M extends EntityLocator<L>> void addLocator(@NonNull Class<L> name, @NonNull M module);
    }

    /**
     * Created by yunarta on 9/5/17.
     */

    interface Setup {

        void installPlugin(@NonNull Plugin plugin);
    }
}
