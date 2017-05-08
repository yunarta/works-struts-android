package com.mobilesolutionworks.android.zero;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by yunarta on 9/5/17.
 */

class StrutsImpl implements Struts, Struts.Setup, Struts.Install, Struts.BackEnd {

    private Context context;

    private Set<Plugin> plugins;

    private ObjectLocator objectLocator;

    StrutsImpl(@NonNull Context context) {
        this.context = context;
        this.objectLocator = new ObjectLocator();
    }

    @Override
    public void installPlugin(@NonNull Plugin plugin) {
        plugins.add(plugin);
    }

    @Override
    public Context context() {
        return context;
    }

    void start() {
        // configuration phase
        for (Plugin plugin : plugins) {
            plugin.setContext(context);
        }

        // installation phase
        for (Plugin plugin : plugins) {
            plugin.startInstallation(this);
        }

        final Collection<EndPoint> points = objectLocator.endPoints();
        for (EndPoint point : points) {
            point.setup(this);
        }

        final Collection<EntityLocator> locators = objectLocator.locators();
        for (EntityLocator locator : locators) {
            locator.setup(this);
        }

        // start up phase
        for (EntityLocator locator : locators) {
            locator.setup(this);
        }

        for (EndPoint point : points) {
            point.onStart();
        }
    }

    @Override
    public <T extends EndPoint.Contract, M extends EndPoint<T>> void addEndPoint(@NonNull Class<T> name, @NonNull M endPoint) {
        objectLocator.addEndPoint(name, endPoint);
    }

    @Override
    public <L extends EntityLocator.Contract, M extends EntityLocator<L>> void addLocator(@NonNull Class<L> name, @NonNull M locator) {
        objectLocator.addLocator(name, locator);
    }

    @Override
    @NonNull
    public <T extends EndPoint.Contract> T endPoint(Class<T> name) {
        return objectLocator.endPoint(name);
    }

    @Override
    @NonNull
    public <L extends EntityLocator.Contract> L locator(Class<L> name) {
        return objectLocator.locator(name);
    }

    static class ObjectLocator {

        private Map<Class<? extends EndPoint.Contract>, EndPoint> moduleMap = new HashMap<>();

        private Map<Class<? extends EntityLocator.Contract>, EntityLocator> locatorMap = new HashMap<>();

        <T extends EndPoint.Contract, M extends EndPoint<T>> void addEndPoint(@NonNull Class<T> name, @NonNull M endPoint) {
            moduleMap.put(name, endPoint);
        }

        <L extends EntityLocator.Contract, M extends EntityLocator<L>> void addLocator(@NonNull Class<L> name, @NonNull M module) {
            locatorMap.put(name, module);
        }

        @NonNull
        <T extends EndPoint.Contract> T endPoint(@NonNull Class<T> name) {
            return (T) moduleMap.get(name);
        }

        @NonNull
        <L extends EntityLocator.Contract> L locator(@NonNull Class<L> name) {
            return (L) locatorMap.get(name);
        }

        @NonNull
        Collection<EndPoint> endPoints() {
            return moduleMap.values();
        }

        @NonNull
        Collection<EntityLocator> locators() {
            return locatorMap.values();
        }
    }
}
