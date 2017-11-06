package com.gve.futureworkshopapplication.userarticle.presentation;

import com.gve.futureworkshopapplication.core.app.SubcomponentBuilder;
import com.gve.futureworkshopapplication.core.injection.scopes.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by gve on 26/10/2017.
 */

@ActivityScope
@Subcomponent(modules = {ListArticleActivityModule.class, RecyclerViewActivityModule.class})
public interface ListArticleActivityComponent {

    @Subcomponent.Builder
    interface Builder extends SubcomponentBuilder<ListArticleActivityComponent> {
        Builder activityModule(ListArticleActivityModule module);
    }

    void inject(ListArticleActivity activity);
}
