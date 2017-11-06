package com.gve.futureworkshopapplication.core.app;

import com.gve.futureworkshopapplication.loginuser.injection.LoginActivityComponent;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by gve.
 */

@Module(subcomponents = {LoginActivityComponent.class})
public abstract class ActivityBindingModule {

    @Binds @IntoMap @DaggerUtil.SubcomponentKey(LoginActivityComponent.Builder.class)
    public abstract SubcomponentBuilder myActivity(LoginActivityComponent.Builder impl);
}

