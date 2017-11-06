package com.gve.futureworkshopapplication;

import com.gve.futureworkshopapplication.core.app.NetworkModule.NetworkInterceptor;
import com.gve.futureworkshopapplication.core.app.NetworkModule.AppInterceptor;

import java.util.Collections;
import java.util.Set;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ElementsIntoSet;
import okhttp3.Interceptor;


/**
 * Created by gve on 02/11/2017.
 */

@Module
public final class InstrumentationModule {

    @Provides
    @Singleton
    @NetworkInterceptor
    @ElementsIntoSet
    static Set<Interceptor> provideNetworkInterceptors() {
        return Collections.emptySet();
    }

    @Provides
    @Singleton
    @AppInterceptor
    @ElementsIntoSet
    static Set<Interceptor> provideAppInterceptors() {
        return Collections.emptySet();
    }
}
