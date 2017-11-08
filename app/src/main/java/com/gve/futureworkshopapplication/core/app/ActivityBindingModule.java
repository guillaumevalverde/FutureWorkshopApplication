package com.gve.futureworkshopapplication.core.app;

import com.gve.futureworkshopapplication.articledetail.presentation.DetailArticleActivityComponent;
import com.gve.futureworkshopapplication.core.launch.LaunchActivityComponent;
import com.gve.futureworkshopapplication.loginuser.presentation.LoginActivityComponent;
import com.gve.futureworkshopapplication.articlelist.presentation.ListArticleActivityComponent;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by gve.
 */

@Module(subcomponents = {
        LoginActivityComponent.class,
        ListArticleActivityComponent.class,
        LaunchActivityComponent.class,
        DetailArticleActivityComponent.class
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

    @Binds @IntoMap
    @DaggerUtil.SubcomponentKey(DetailArticleActivityComponent.Builder.class)
    public abstract SubcomponentBuilder detailArticleActivity(DetailArticleActivityComponent.Builder impl);

}
