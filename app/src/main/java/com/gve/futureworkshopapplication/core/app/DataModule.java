package com.gve.futureworkshopapplication.core.app;

import android.content.Context;
import com.gve.futureworkshopapplication.BuildConfig;
import com.gve.futureworkshopapplication.core.injection.qualifiers.ForApplication;
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
                .build();
        Picasso.setSingletonInstance(picasso);
        return picasso;
    }

}
