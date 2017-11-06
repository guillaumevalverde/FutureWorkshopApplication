package com.gve.futureworkshopapplication.loginuser.injection;

import android.content.Context;

import com.gve.futureworkshopapplication.core.app.DaggerUtil;
import com.gve.futureworkshopapplication.core.injection.qualifiers.ForActivity;

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
    @DaggerUtil.ActivityScope
    @ForActivity
    public Context getContext() {
        return context;
    }
}
