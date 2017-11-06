package com.gve.futureworkshopapplication.core.app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapterFactory;

import java.util.Set;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;

/**
 * Created by gve.
 */

@Module
public class GsonModule {

    @Provides
    @Singleton
    static Gson provideGson(Set<TypeAdapterFactory> typeAdapters) {
        final GsonBuilder builder = new GsonBuilder();

        for (TypeAdapterFactory factory : typeAdapters) {
            builder.registerTypeAdapterFactory(factory);
        }

        return builder.create();
    }

    @Provides
    @IntoSet
    TypeAdapterFactory provideTypeAdapterFactory() {
        return DataAdapterFactory.create();
    }

}
