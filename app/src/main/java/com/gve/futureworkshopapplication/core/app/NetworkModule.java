package com.gve.futureworkshopapplication.core.app;

import com.google.gson.Gson;
import com.gve.futureworkshopapplication.BuildConfig;
import com.gve.futureworkshopapplication.InstrumentationModule;
import com.gve.futureworkshopapplication.userarticle.data.ApiNetworkService;
import com.gve.futureworkshopapplication.userarticle.data.RetrofitApiService;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Set;

import javax.inject.Named;
import javax.inject.Qualifier;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = {GsonModule.class, InstrumentationModule.class})
public final class NetworkModule {

    private static final String API_URL = "API_URL";
    private static final String IMAGES_URL = "IMAGES_URL";

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface AppInterceptor {
    }

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface NetworkInterceptor {
    }

    @Provides
    @Singleton
    static Retrofit provideApi(@Named(API_URL) String baseUrl, Gson gson, OkHttpClient client) {
        return new Retrofit.Builder().addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                     .addConverterFactory(GsonConverterFactory.create(gson))
                                     .client(client)
                                     .baseUrl(baseUrl)
                                     .build();
    }

    @Provides
    @Named(API_URL)
    static String provideFutureWorkshopUrl() {
        return BuildConfig.API_URL;
    }

    @Provides
    @Singleton
    static OkHttpClient provideApiOkHttpClient(@AppInterceptor Set<Interceptor> appInterceptor,
                                               @NetworkInterceptor Set<Interceptor>
                                                       networkInterceptor) {
        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
        okBuilder.interceptors().addAll(appInterceptor);
        okBuilder.networkInterceptors().addAll(networkInterceptor);

        return okBuilder.build();
    }

    @Provides
    @Singleton
    static RetrofitApiService provideApiNetworkService(Retrofit retrofit){
        return retrofit.create(RetrofitApiService.class);
    }

}
