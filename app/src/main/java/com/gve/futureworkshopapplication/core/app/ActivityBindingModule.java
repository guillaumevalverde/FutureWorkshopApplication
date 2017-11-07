package com.gve.futureworkshopapplication.core.app;

import com.gve.futureworkshopapplication.core.launch.LaunchActivityComponent;
import com.gve.futureworkshopapplication.loginuser.injection.LoginActivityComponent;
import com.gve.futureworkshopapplication.userarticle.presentation.ListArticleActivityComponent;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by gve.
 */

@Module(subcomponents = {
        LoginActivityComponent.class,
        ListArticleActivityComponent.class,
        LaunchActivityComponent.class
})
public abstract class ActivityBindingModule {

    @Binds @IntoMap
    @DaggerUtil.SubcomponentKey(LoginActivityComponent.Builder.class)
    public abstract SubcomponentBuilder loginActivity(LoginActivityComponent.Builder impl);


    @Binds @IntoMap
    @DaggerUtil.SubcomponentKey(ListArticleActivityComponent.Builder.class)
    public abstract SubcomponentBuilder listArticleActivity(ListArticleActivityComponent.Builder impl);

    @Binds @IntoMap
    @DaggerUtil.SubcomponentKey(LaunchActivityComponent.Builder.class)
    public abstract SubcomponentBuilder listLaunchActivity(LaunchActivityComponent.Builder impl);
}

