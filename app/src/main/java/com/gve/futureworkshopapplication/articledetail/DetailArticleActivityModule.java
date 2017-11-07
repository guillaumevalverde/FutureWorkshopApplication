package com.gve.futureworkshopapplication.articledetail;

import android.content.Context;

import com.gve.futureworkshopapplication.core.injection.qualifiers.ForActivity;
import com.gve.futureworkshopapplication.core.injection.scopes.ActivityScope;
import com.gve.futureworkshopapplication.userarticle.data.RetrofitApiService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by gve on 26/10/2017.
 */

@Module
public class DetailArticleActivityModule {

    @ForActivity
    private Context context;

    public DetailArticleActivityModule(Context context) {
        this.context = context;
    }

    @Provides
    @ActivityScope
    @ForActivity
    public Context getContext() {
        return context;
    }

    @Provides
    @ActivityScope
    public DetailArticleViewModel provide(RetrofitApiService retrofitApiService) {
        return new DetailArticleViewModel(retrofitApiService);
    }
}
