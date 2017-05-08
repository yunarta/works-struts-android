package com.mobilesolutionworks.android.zero;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yunarta on 2/5/17.
 */

public class Foundation {

    private static Foundation instance;

    public static void create(Context context) {
        instance = new Foundation(context);
    }

    public static Foundation single() {
        return instance;
    }

    private final Context context;

    private Map<String, Struts> strutsMap;

    private CredentialService credentialService;

    Foundation(Context context) {
        this.context = context.getApplicationContext();

        strutsMap = new HashMap<>();
        credentialService = new CredentialService();
    }

    @NonNull
    Context context() {
        return context;
    }

    public void configure(@NonNull ConfigureInterface setupInterface) {
        setupInterface.setup(new Configurable(this));
    }

    public void setup(@NonNull BuildInterface buildInterface) {
        final StrutsImpl struts = new StrutsImpl(context());
        strutsMap.put(buildInterface.name(), struts);

        buildInterface.setup(struts);
        struts.start();
    }

    public @NonNull
    Struts getStruts(@NonNull String name) {
        return strutsMap.get(name);
    }

    public CredentialService credentialManager() {
        return credentialService;
    }

    /**
     * Created by yunarta on 5/5/17.
     */

    public interface BuildInterface {

        String name();

        void setup(Struts.Setup struts);
    }

    /**
     * Created by yunarta on 9/5/17.
     */

    public interface ConfigureInterface {

        void setup(Configurable foundation);
    }

    /**
     * Created by yunarta on 9/5/17.
     */

    public static class Configurable {

        private Foundation foundation;

        Configurable(Foundation foundation) {
            this.foundation = foundation;
        }

        public Context context() {
            return foundation.context();
        }

        public <T extends CredentialManager> void installCredentialInterface(Class<T> name, T credentialInterface) {
            foundation.credentialManager().installCredentialManager(name, credentialInterface);
        }
    }
}
