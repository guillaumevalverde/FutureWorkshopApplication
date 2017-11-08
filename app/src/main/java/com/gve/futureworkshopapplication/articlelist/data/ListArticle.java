package com.gve.futureworkshopapplication.articlelist.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by gve on 06/11/2017.
 */

public class ListArticle {

    @SerializedName("articles")
    private List<ArticleRaw> articleRaws;

    public List<ArticleRaw> getArticleList() {
        return articleRaws;
    }
}
