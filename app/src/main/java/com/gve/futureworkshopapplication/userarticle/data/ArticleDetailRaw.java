package com.gve.futureworkshopapplication.userarticle.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

/**
 * Created by gve on 31/10/2017.
 */

@AutoValue
public abstract class ArticleDetailRaw {

    @NonNull
    public abstract int id();

    @Nullable
    public abstract String title();

    @Nullable
    @SerializedName("image_url")
    public abstract String imageUrl();

    @Nullable
    public abstract String source();

    @Nullable
    public abstract String content();

    @Nullable
    public abstract String date();

    @NonNull
    public static TypeAdapter<ArticleDetailRaw> typeAdapter(@NonNull final Gson gson) {
        return new AutoValue_ArticleDetailRaw.GsonTypeAdapter(gson);
    }

    @NonNull
    public static Builder builder() {
        return new AutoValue_ArticleDetailRaw.Builder();
    }

    @AutoValue.Builder
    public interface Builder {

        Builder id(final int title);

        Builder title(final String title);

        Builder imageUrl(final String imageUrl);

        Builder source(final String source);

        Builder content(final String content);

        Builder date(final String date);

        ArticleDetailRaw build();
    }

    public String toString(){
        return "Article " + id() + ", " + title() + ", " + source() + ", " + content() + "\n"
                + date() + ", " + imageUrl();
    }
}
