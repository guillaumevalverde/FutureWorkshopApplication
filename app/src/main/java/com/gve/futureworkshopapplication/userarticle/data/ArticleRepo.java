package com.gve.futureworkshopapplication.userarticle.data;

import android.support.annotation.NonNull;
import android.util.Log;

import com.gve.futureworkshopapplication.core.data.ReactiveStore;
import com.gve.futureworkshopapplication.core.data.Repo;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * Created by gve on 01/11/2017.
 */

public class ArticleRepo implements Repo<Article> {

    private ApiNetworkService fetcher;
    private ReactiveStore<Article> sharedPreferenceStore;
    private static final long TIME_AMOUNT_VALIDATE = 1 * 60 * 60 * 1000;

    @Inject
    public ArticleRepo(@NonNull ApiNetworkService fetcher,
                       @NonNull ReactiveStore<Article> sharedPreferenceStore) {
        this.fetcher = fetcher;
        this.sharedPreferenceStore = sharedPreferenceStore;
    }

    @Override
    public void fetch() {
        Log.v("GVE", "call fetch");
        fetcher.fetchArticleRawListData()
                .flatMapObservable(Observable::fromIterable)
                // map from raw to safe
                .map(MapperArticle.mapperArticleRawToArticle)
                .toList()
                // put mapped objects in store
                .doOnSuccess(sharedPreferenceStore::storeAll)
                .subscribe();
    }

    @Override
    public Flowable<List<Article>> getStream() {
        return sharedPreferenceStore.<ArticleRaw, Long>getAll()
                .doOnNext(pair -> {
                    if (isDataDeprecated(pair.second)) {
                        fetch();
                    }
                })
                .map(p -> p.first);
    }

    public static boolean isDataDeprecated(long time) {
        return ((new Date()).getTime() - time) > TIME_AMOUNT_VALIDATE;
    }
}
