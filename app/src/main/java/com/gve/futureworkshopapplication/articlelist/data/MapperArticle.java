package com.gve.futureworkshopapplication.articlelist.data;


import io.reactivex.functions.Function;

/**
 * Created by gve on 07/11/2017.
 */

public class MapperArticle {

    public static Function<ArticleRaw, Article> mapperArticleRawToArticle =
            articleRaw -> Article.builder().id(articleRaw.id())
                    .content(articleRaw.content() == null ? "" : articleRaw.content())
                    .date(articleRaw.date() == null ? "" : formatDate(articleRaw.date()))
                    .imageUrl(articleRaw.iconUrl() == null ? "" : articleRaw.iconUrl())
                    .summary(articleRaw.summary() == null ? "" : articleRaw.summary())
                    .source("")
                    .title(articleRaw.title() == null ? "" : articleRaw.title())
                    .build();

    public static Function<ArticleDetailRaw, Article>  mapperArticleDetailRawToArticle =
            articleDetailRaw -> Article.builder().id(articleDetailRaw.id())
                    .content(articleDetailRaw.content() == null ? "" : articleDetailRaw.content())
                    .date(articleDetailRaw.date() == null ? "" : formatDate(articleDetailRaw.date()))
                    .imageUrl(articleDetailRaw.imageUrl() == null ? "" : articleDetailRaw.imageUrl())
                    .summary("")
                    .source(articleDetailRaw.source() == null ? "" : articleDetailRaw.source())
                    .title(articleDetailRaw.title() == null ? "" : articleDetailRaw.title())
                    .build();

    public static String formatDate(String date) {
        return date.replace("/", ".");
    }
}
