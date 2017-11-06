package com.gve.futureworkshopapplication.loginuser.data;

import android.support.annotation.NonNull;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Created by gve on 06/11/2017.
 */

@AutoValue
public abstract class User {

    @NonNull
    public abstract String name();

    @NonNull
    public static TypeAdapter<User> typeAdapter(@NonNull final Gson gson) {
        return new AutoValue_User.GsonTypeAdapter(gson);
    }

    @NonNull
    public static Builder builder() {
        return new AutoValue_User.Builder();
    }

    @AutoValue.Builder
    public interface Builder {

        Builder name(final String title);

        User build();
    }

    public String toString(){
        return name();
    }
}
