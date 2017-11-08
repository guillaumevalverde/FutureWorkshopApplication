package com.gve.futureworkshopapplication.loginuser.presentation;

import android.content.Context;

import com.gve.futureworkshopapplication.core.injection.qualifiers.ForActivity;
import com.gve.futureworkshopapplication.core.injection.scopes.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by gve on 26/10/2017.
 */

@Module
public class LoginActivityModule {

    @ForActivity
    private Context context;

    public LoginActivityModule(Context context) {
        this.context = context;
    }

    @Provides
    @ActivityScope
    @ForActivity
    public Context getContext() {
        return context;
    }
}
