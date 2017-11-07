package com.gve.futureworkshopapplication.articledetail;

import com.gve.futureworkshopapplication.core.app.SubcomponentBuilder;
import com.gve.futureworkshopapplication.core.injection.scopes.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by gve on 26/10/2017.
 */

@ActivityScope
@Subcomponent(modules = {DetailArticleActivityModule.class})
public interface DetailArticleActivityComponent {

    @Subcomponent.Builder
    interface Builder extends SubcomponentBuilder<DetailArticleActivityComponent> {
        Builder activityModule(DetailArticleActivityModule module);
    }

    void inject(DetailArticleActivity activity);
}
