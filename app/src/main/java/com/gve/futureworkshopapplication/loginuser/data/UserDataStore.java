package com.gve.futureworkshopapplication.loginuser.data;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

import javax.inject.Inject;

public class UserDataStore {
    private static final String KEY_SERIALIZED_USER = "serialized_user";

    private SharedPreferences sharedPreferences;
    private Gson gson;

    @Inject
    public UserDataStore(SharedPreferences sharedPreferences, Gson gson) {
        this.sharedPreferences = sharedPreferences;
        this.gson = gson;
    }

    public void createUser(User user) {
        String serializedUser = gson.toJson(user);
        sharedPreferences.edit().putString(KEY_SERIALIZED_USER, serializedUser).apply();
    }

    public void clearUser() {
        sharedPreferences.edit().remove(KEY_SERIALIZED_USER).apply();
    }

    public User getUser() {
        String serializedUser = sharedPreferences.getString(KEY_SERIALIZED_USER, null);
        if (!TextUtils.isEmpty(serializedUser)) {
            return gson.fromJson(serializedUser, User.class);
        }
        return null;
    }
}
