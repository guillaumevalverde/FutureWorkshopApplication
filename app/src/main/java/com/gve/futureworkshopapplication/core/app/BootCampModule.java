package com.gve.futureworkshopapplication.core.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.gve.futureworkshopapplication.core.injection.qualifiers.ForApplication;
import com.gve.futureworkshopapplication.loginuser.UserManager;
import com.gve.futureworkshopapplication.loginuser.data.UserAPI;
import com.gve.futureworkshopapplication.loginuser.data.UserComponent;
import com.gve.futureworkshopapplication.loginuser.data.UserDataStore;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by gve.
 */

@Module
public class BootCampModule {

    @ForApplication
    @Provides
    @Singleton
    Context provideApplicationContext(Application app) {
        return app.getApplicationContext();
    }

    @Provides
    @Singleton
    static SharedPreferences provideSharedPreference(@ForApplication Context context) {
        return context.getSharedPreferences("SharedPref", Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    public UserManager provideUserManager(UserAPI userAPi, UserDataStore userDataStore,
                                          UserComponent.Builder userComponentBuilder) {
        return new UserManager(userAPi, userDataStore, userComponentBuilder);
    }

    @Provides
    @Singleton
    UserDataStore provideUserDataStore(SharedPreferences sharedPreferences, Gson gson) {
        return new UserDataStore(sharedPreferences, gson);
    }

}
