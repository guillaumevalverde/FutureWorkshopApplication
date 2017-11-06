package com.gve.futureworkshopapplication.core.data;

import android.support.annotation.NonNull;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.gve.futureworkshopapplication.userarticle.data.Article;

import java.util.List;

/**
 * Created by gve on 01/11/2017.
 */

@AutoValue
public abstract class JsonPojo {

    public abstract long time();

    public abstract List<Article> data();

    public static JsonPojo create(long time, List<Article> articleList) {
        return new AutoValue_JsonPojo(time, articleList);
    }

    @NonNull
    public static TypeAdapter<JsonPojo> typeAdapter(@NonNull final Gson gson) {
        return new AutoValue_JsonPojo.GsonTypeAdapter(gson);
    }
}
