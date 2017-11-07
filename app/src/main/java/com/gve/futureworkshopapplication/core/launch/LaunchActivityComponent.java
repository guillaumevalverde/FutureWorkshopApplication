package com.gve.futureworkshopapplication.core.launch;

import com.gve.futureworkshopapplication.core.app.SubcomponentBuilder;
import com.gve.futureworkshopapplication.core.injection.scopes.ActivityScope;
import com.gve.futureworkshopapplication.userarticle.presentation.ListArticleActivity;
import com.gve.futureworkshopapplication.userarticle.presentation.RecyclerViewActivityModule;

import dagger.Subcomponent;

/**
 * Created by gve on 26/10/2017.
 */

@ActivityScope
@Subcomponent(modules = {LaunchActivityModule.class})
public interface LaunchActivityComponent {

    @Subcomponent.Builder
    interface Builder extends SubcomponentBuilder<LaunchActivityComponent> {
        Builder activityModule(LaunchActivityModule module);
    }

    void inject(LaunchActivity activity);
}
