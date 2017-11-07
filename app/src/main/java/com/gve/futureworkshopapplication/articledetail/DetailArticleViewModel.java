package com.gve.futureworkshopapplication.articledetail;

import com.gve.futureworkshopapplication.userarticle.data.Article;
import com.gve.futureworkshopapplication.userarticle.data.MapperArticle;
import com.gve.futureworkshopapplication.userarticle.data.RetrofitApiService;

import io.reactivex.Single;

/**
 * Created by gve on 07/11/2017.
 */

class DetailArticleViewModel {

    private RetrofitApiService retrofitApiService;

    DetailArticleViewModel(RetrofitApiService retrofitApiService) {
        this.retrofitApiService = retrofitApiService;
    }


    public Single<Article> fetchArticle(String id) {
        return retrofitApiService.getArticle(id)
                .map(MapperArticle.mapperArticleDetailRawToArticle);
    }
}
