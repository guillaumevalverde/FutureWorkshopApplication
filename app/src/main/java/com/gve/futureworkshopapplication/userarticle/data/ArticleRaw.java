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
public abstract class ArticleRaw {

    @NonNull
    public abstract int id();

    @Nullable
    public abstract String title();

    @Nullable
    @SerializedName("icon_url")
    public abstract String iconUrl();

    @Nullable
    public abstract String summary();

    @Nullable
    public abstract String content();

    @Nullable
    public abstract String date();

    @NonNull
    public static TypeAdapter<ArticleRaw> typeAdapter(@NonNull final Gson gson) {
        return new AutoValue_ArticleRaw.GsonTypeAdapter(gson);
    }

    @NonNull
    public static Builder builder() {
        return new AutoValue_ArticleRaw.Builder();
    }

    @AutoValue.Builder
    public interface Builder {

        Builder id(final int id);

        Builder title(final String title);

        Builder iconUrl(final String url);

        Builder summary(final String summary);

        Builder content(final String content);

        Builder date(final String date);

        ArticleRaw build();
    }

    public String toString(){
        return "Article " + id() + ", " + title() + ", " + summary() + ", " + content() + "\n"
                + date() + ", " + iconUrl();
    }
}
