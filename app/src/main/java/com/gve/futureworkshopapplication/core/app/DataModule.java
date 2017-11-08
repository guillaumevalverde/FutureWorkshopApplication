package com.gve.futureworkshopapplication.core.app;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

import com.google.gson.Gson;
import com.gve.futureworkshopapplication.BuildConfig;
import com.gve.futureworkshopapplication.core.data.AppDataBase;
import com.gve.futureworkshopapplication.core.data.ReactiveStore;
import com.gve.futureworkshopapplication.core.data.SharedPreferenceStore;
import com.gve.futureworkshopapplication.core.injection.qualifiers.ForApplication;
import com.gve.futureworkshopapplication.loginuser.data.MockUserProvider;
import com.gve.futureworkshopapplication.loginuser.data.UserAPI;
import com.gve.futureworkshopapplication.userarticle.data.Article;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by gve.
 */

@Module
final class DataModule {

    //Picasso library handle the caching of the image in the disk or in the memory.
    @Provides
    @Singleton
    Picasso providePicasso(@ForApplication Context context) {
        Picasso picasso = new Picasso.Builder(context)
                .indicatorsEnabled(BuildConfig.DEBUG)
                .loggingEnabled(BuildConfig.DEBUG)
                .listener(new Picasso.Listener() {
                    @Override
                    public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                        exception.printStackTrace();
                    }
                })
                .build();
        Picasso.setSingletonInstance(picasso);
        return picasso;
    }

    @Provides
    @Singleton
    ReactiveStore<Article> provideSharedPrefStore(SharedPreferences sharedPreference,
                                                  Gson gson) {
        return new SharedPreferenceStore(sharedPreference,
                "ARTICLE_LIST",
                gson);
    }

    @Provides
    @Singleton
    UserAPI provideUserAPi() {
        return new MockUserProvider();
    }

}
