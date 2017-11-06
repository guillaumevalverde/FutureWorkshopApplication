package com.gve.futureworkshopapplication.core.app;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Scope;

import dagger.MapKey;

/**
 * Created by gve.
 */

public class DaggerUtil {

    @Scope
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ActivityScope {
    }

    // Needed only to create the above mapping
    @MapKey
    @Target({ElementType.METHOD}) @Retention(RetentionPolicy.RUNTIME)
    public @interface SubcomponentKey {
        Class<?> value();
    }
}
