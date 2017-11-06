package com.gve.futureworkshopapplication.userarticle.data;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by gve on 01/11/2017.
 */

public class ApiNetworkService {

    private RetrofitApiService service;

    @Inject
    public ApiNetworkService(RetrofitApiService service) {
        this.service = service;
     }

    public Single<List<ArticleRaw>> fetchArticleRawListData() {
        return service.getListArticles()
                .subscribeOn(Schedulers.io())
                .map(file -> file.getArticleList());
    }
}
