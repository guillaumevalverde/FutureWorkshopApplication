package com.gve.futureworkshopapplication.core.app;

import android.content.Context;
import android.net.Uri;

import com.gve.futureworkshopapplication.BuildConfig;
import com.gve.futureworkshopapplication.core.injection.qualifiers.ForApplication;
import com.gve.futureworkshopapplication.loginuser.data.MockUserProvider;
import com.gve.futureworkshopapplication.loginuser.data.UserAPI;
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
    UserAPI provideUserAPi() {
        return new MockUserProvider();
    }

}
