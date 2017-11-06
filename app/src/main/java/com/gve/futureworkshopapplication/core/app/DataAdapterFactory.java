package com.gve.futureworkshopapplication.core.app;

import com.google.gson.TypeAdapterFactory;
import com.ryanharter.auto.value.gson.GsonTypeAdapterFactory;

/**
 * Created by gve on 26/10/2017.
 */

@GsonTypeAdapterFactory
public abstract class DataAdapterFactory implements TypeAdapterFactory {
    public static TypeAdapterFactory create() {
        return new AutoValueGson_DataAdapterFactory();
    }
}
