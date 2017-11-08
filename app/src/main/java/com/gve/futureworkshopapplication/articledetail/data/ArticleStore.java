package com.gve.futureworkshopapplication.articledetail.data;

import android.content.Context;

import com.gve.futureworkshopapplication.core.data.AppDataBase;
import com.gve.futureworkshopapplication.core.injection.qualifiers.ForApplication;
import com.gve.futureworkshopapplication.articlelist.data.Article;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Created by gve on 07/11/2017.
 */

public class ArticleStore {

    private AppDataBase appDataBase;

    @Inject
    public ArticleStore(@ForApplication Context context) {
        this.appDataBase = AppDataBase.getDatabase(context);
    }

    public Flowable<Article> getArticleFlowable(int id) {
        return appDataBase.articleModel().getItembyId(id);
    }

    public void saveArticle(Article article) {
        appDataBase.articleModel().addArticle(article);
    }

    public void deleteArticle(Article article) {
        appDataBase.articleModel().deleteArticle(article);
    }

    public Single<Article> getArticleSingle(int id) {
        return appDataBase.articleModel().getItembyIdSingle(id);
    }
}
