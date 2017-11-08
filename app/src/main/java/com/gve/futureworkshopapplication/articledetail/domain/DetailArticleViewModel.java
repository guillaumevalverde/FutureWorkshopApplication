package com.gve.futureworkshopapplication.articledetail.domain;

import com.gve.futureworkshopapplication.articledetail.data.ArticleDetailRepo;
import com.gve.futureworkshopapplication.articlelist.data.Article;
import com.gve.futureworkshopapplication.articlelist.data.ArticleRepo;
import com.gve.futureworkshopapplication.articlelist.data.MapperArticle;
import com.gve.futureworkshopapplication.articlelist.data.RetrofitApiService;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by gve on 07/11/2017.
 */

public class DetailArticleViewModel {

    private ArticleDetailRepo articleRepo;

    public DetailArticleViewModel(ArticleDetailRepo articleRepo) {
        this.articleRepo = articleRepo;
    }

    public Completable fetchArticle(int id) {
        return articleRepo.fetchArticle(id)
                .subscribeOn(Schedulers.io());
    }

    public Flowable<Article> getArticleFlowable(int id) {
        return articleRepo.getArticle(id);
    }

    public Single<Article> getArticleSingle(int id) {
        return articleRepo.getArticleSingle(id);
    }


    public Completable changeFavourite(int id) {
        return articleRepo.getArticleSingle(id)
                .map(Article::getArticleFavoChanged)
                .doOnSuccess(articleRepo::storeArticle)
                .subscribeOn(Schedulers.computation())
                .toCompletable();
    }
}
