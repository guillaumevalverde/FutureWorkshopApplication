package com.gve.futureworkshopapplication.core.data;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.util.Pair;

import com.google.gson.Gson;
import com.gve.futureworkshopapplication.articlelist.data.Article;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by gve on 31/10/2017.
 */

public class SharedPreferenceStore implements ReactiveStore<Article> {

    public static final String JSON_EMPTY = "{\"time\":0,\"data\":[]}";
    private SharedPreferences sharedPreferences;
    private Gson gson;
    private BehaviorSubject<String> jsonPublish = BehaviorSubject.create();
    private final String keyInSharedPref;

    public SharedPreferenceStore(SharedPreferences sharedPreference,
                                 final String keyInSharedPred,
                                 Gson gson) {
        this.sharedPreferences = sharedPreference;
        this.gson = gson;
        this.keyInSharedPref = keyInSharedPred;
        SharedPreferences.OnSharedPreferenceChangeListener listener = (prefs, key) -> jsonPublish
                .onNext(sharedPreferences.getString(keyInSharedPred, JSON_EMPTY));
        jsonPublish.onNext(sharedPreferences.getString(keyInSharedPred, JSON_EMPTY));
        sharedPreference.registerOnSharedPreferenceChangeListener(listener);
    }

    @Override
    public void storeAll(@NonNull List<Article> modelList) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        try {
            editor.putString(keyInSharedPref, getJsonFromList(modelList, gson, getTime()));
            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Flowable<Pair<List<Article>, Long>> getAll() {
        return jsonPublish.toFlowable(BackpressureStrategy.LATEST)
                .map(ims -> {
                    JsonPojo jsonPojo = getListFromJson(ims, gson);
                    return new Pair(jsonPojo.data(), jsonPojo.time());
                });
    }

    public static String getJsonFromIm(Article article,
                                       Gson gson) {
        return gson.toJson(article);
    }

    public static String getJsonFromList(List<Article> articleList,
                                         Gson gson,
                                         Callable<Long> getTime) throws Exception {
        return gson.toJson(new AutoValue_JsonPojo(getTime.call(), articleList));
    }

    public static JsonPojo getListFromJson(String json,
                                           Gson gson) {
        return gson.fromJson(json, JsonPojo.class);
    }

    public static Callable<Long> getTime() {
        Date date = new Date();
        return date::getTime;
    }

}
