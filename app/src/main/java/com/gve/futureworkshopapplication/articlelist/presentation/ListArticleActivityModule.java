package com.gve.futureworkshopapplication.articlelist.presentation;

import android.content.Context;

import com.gve.futureworkshopapplication.core.injection.qualifiers.ForActivity;
import com.gve.futureworkshopapplication.core.injection.scopes.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by gve on 26/10/2017.
 */

@Module
public class ListArticleActivityModule {

    @ForActivity
    private Context context;

    public ListArticleActivityModule(Context context) {
        this.context = context;
    }

    @Provides
    @ActivityScope
    @ForActivity
    public Context getContext() {
        return context;
    }
}
