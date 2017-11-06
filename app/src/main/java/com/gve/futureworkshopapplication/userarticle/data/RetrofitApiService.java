package com.gve.futureworkshopapplication.userarticle.data;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by gve on 02/11/2017.
 */

public interface RetrofitApiService {

    @GET("fw-coding-test.json")
    Single<ListArticle> getListArticles();

    @GET("{id}.json")
    Single<ArticleDetailRaw> getArticle(@Path("id") String id);
}
