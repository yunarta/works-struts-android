package com.mobilesolutionworks.android.zero;

import java.util.List;

/**
 * Created by yunarta on 5/5/17.
 */

public interface CredentialManager<C extends Credential> {

    void add(String name, C credential);

    void update(String name, C credential);

    C get(String name);

    List<C> list();
}
