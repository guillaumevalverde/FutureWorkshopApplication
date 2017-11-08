package com.gve.futureworkshopapplication.articlelist.data;

import android.support.annotation.NonNull;
import android.util.Log;

import com.gve.futureworkshopapplication.core.data.ReactiveStore;
import com.gve.futureworkshopapplication.core.data.Repo;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by gve on 01/11/2017.
 */

public class ArticleRepo implements Repo<Article> {

    private ApiNetworkService fetcher;
    private ReactiveStore<Article> sharedPreferenceStore;
    private static final long TIME_AMOUNT_VALIDATE = 1 * 60 * 60 * 1000;
    private CompositeDisposable disposable = new CompositeDisposable();

    @Inject
    public ArticleRepo(@NonNull ApiNetworkService fetcher,
                       @NonNull ReactiveStore<Article> sharedPreferenceStore) {
        this.fetcher = fetcher;
        this.sharedPreferenceStore = sharedPreferenceStore;
    }

    @Override
    public Completable fetch() {
        Log.v("GVE", "call fetch");
        return fetcher.fetchArticleRawListData()
                .flatMapObservable(Observable::fromIterable)
                // map from raw to safe
                .map(MapperArticle.mapperArticleRawToArticle)
                .toList()
                // put mapped objects in store
                .doOnSuccess(sharedPreferenceStore::storeAll)
                .toCompletable();
    }

    @Override
    public Flowable<List<Article>> getStream() {
        return sharedPreferenceStore.<ArticleRaw, Long>getAll()
                .doOnNext(pair -> {
                    if (isDataDeprecated(pair.second)) {
                        disposable.add(fetch().subscribe(() -> System.out.println("fetch ListArticle"),
                                        error -> System.out.println("error fetch ListArticle"
                                                + error.getMessage())));
                    }
                })
                .map(p -> p.first);
    }

    public static boolean isDataDeprecated(long time) {
        return ((new Date()).getTime() - time) > TIME_AMOUNT_VALIDATE;
    }
}
