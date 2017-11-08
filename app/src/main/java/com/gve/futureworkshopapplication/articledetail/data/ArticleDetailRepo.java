package com.gve.futureworkshopapplication.articledetail.data;

import android.database.Observable;

import com.gve.futureworkshopapplication.userarticle.data.Article;
import com.gve.futureworkshopapplication.userarticle.data.MapperArticle;
import com.gve.futureworkshopapplication.userarticle.data.RetrofitApiService;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Created by gve on 07/11/2017.
 */

public class ArticleDetailRepo {

    RetrofitApiService retrofitApiService;
    ArticleStore articleStore;

    @Inject
    public ArticleDetailRepo(RetrofitApiService retrofitApiService, ArticleStore articleStore) {
        this.articleStore = articleStore;
        this.retrofitApiService = retrofitApiService;
    }

    public Flowable<Article> getArticle(int id) {
        return articleStore.getArticleFlowable(id);
    }

    public Completable fetchArticle(int id) {
        return  Single.just(id)
                .flatMap(idA -> articleStore.getArticleSingle(idA)
                                .onErrorResumeNext(ee -> retrofitApiService.getArticle("" + id)
                                    .map(MapperArticle.mapperArticleDetailRawToArticle)
                                    .doOnSuccess(this::storeArticle))
                )
                .toCompletable();
    }

    public void storeArticle(Article article) {
        articleStore.saveArticle(article);
    }

    public void deleteArticle(Article article) {
        articleStore.deleteArticle(article);
    }

    public Single<Article> getArticleSingle(int id) {
        return articleStore.getArticleSingle(id);
    }
}
