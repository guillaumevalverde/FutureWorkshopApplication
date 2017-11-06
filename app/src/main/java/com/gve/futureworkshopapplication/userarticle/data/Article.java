package com.gve.futureworkshopapplication.userarticle.data;

import android.support.annotation.NonNull;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;


/**
 * Created by gve on 07/11/2017.
 */

@AutoValue
public abstract class Article {

    @NonNull
    public abstract int id();

    @NonNull
    public abstract String title();

    @NonNull
    public abstract String imageUrl();

    @NonNull
    public abstract String source();

    @NonNull
    public abstract String summary();

    @NonNull
    public abstract String content();

    @NonNull
    public abstract String date();

    @NonNull
    public static TypeAdapter<Article> typeAdapter(@NonNull final Gson gson) {
        return new AutoValue_Article.GsonTypeAdapter(gson);
    }

    @NonNull
    public static Builder builder() {
        return new AutoValue_Article.Builder();
    }

    @AutoValue.Builder
    public interface Builder {

        Builder id(int title);

        Builder title(String title);

        Builder imageUrl(String imageUrl);

        Builder source(String source);

        Builder summary(String source);

        Builder content(String content);

        Builder date(String date);

        Article build();
    }

    public String toString() {
        return "Article " + id() + ", " + title() + ", " + source()
                + ", " + summary() + ", " + content() + "\n"
                + date() + ", " + imageUrl();
    }
}
