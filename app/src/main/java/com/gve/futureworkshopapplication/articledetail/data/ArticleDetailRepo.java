package com.gve.futureworkshopapplication.articledetail.data;

import com.gve.futureworkshopapplication.articlelist.data.Article;
import com.gve.futureworkshopapplication.articlelist.data.MapperArticle;
import com.gve.futureworkshopapplication.articlelist.data.RetrofitApiService;
import com.gve.futureworkshopapplication.core.data.Repo;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Created by gve on 07/11/2017.
 */

public class ArticleDetailRepo implements Repo<Article> {

    RetrofitApiService retrofitApiService;
    ArticleStore articleStore;

    @Inject
    public ArticleDetailRepo(RetrofitApiService retrofitApiService, ArticleStore articleStore) {
        this.articleStore = articleStore;
        this.retrofitApiService = retrofitApiService;
    }

    @Override
    public Flowable<Article> getStream(int id) {
        return articleStore.getArticleFlowable(id);
    }

    @Override
    public Completable fetch(int id) {
        return  Single.just(id)
                .flatMap(idA -> articleStore.getArticleSingle(idA)
                                .onErrorResumeNext(ee -> retrofitApiService.getArticle("" + id)
                                    .map(MapperArticle.mapperArticleDetailRawToArticle)
                                    .doOnSuccess(this::store))
                )
                .toCompletable();
    }

    @Override
    public void store(Article article) {
        articleStore.saveArticle(article);
    }

    @Override
    public void delete(Article article) {
        articleStore.deleteArticle(article);
    }

    @Override
    public Single<Article> getSingle(int id) {
        return articleStore.getArticleSingle(id);
    }

}
