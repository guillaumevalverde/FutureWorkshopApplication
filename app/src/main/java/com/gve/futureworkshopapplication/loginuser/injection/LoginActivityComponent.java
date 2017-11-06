package com.gve.futureworkshopapplication.loginuser.injection;

import com.gve.futureworkshopapplication.core.app.SubcomponentBuilder;
import com.gve.futureworkshopapplication.core.injection.scopes.ActivityScope;
import com.gve.futureworkshopapplication.loginuser.LoginUserActivity;

import dagger.Subcomponent;

/**
 * Created by gve on 26/10/2017.
 */

@ActivityScope
@Subcomponent(modules = {LoginActivityModule.class})
public interface LoginActivityComponent {

    @Subcomponent.Builder
    interface Builder extends SubcomponentBuilder<LoginActivityComponent> {
        Builder activityModule(LoginActivityModule module);
    }

    void inject(LoginUserActivity activity);
}
