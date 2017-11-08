package com.gve.futureworkshopapplication.userarticle.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


/**
 * Created by gve on 07/11/2017.
 */


@Entity(tableName = "articles")
public class Article {

    @PrimaryKey
    private int id;

    private String title;
    private String imageUrl;
    private String source;
    private String summary;
    private String content;
    private String date;
    private Boolean favourite;

    @NonNull
    public int id() {
        return id;
    }

    @NonNull
    public String title() {
        return title;
    }

    @NonNull
    public String imageUrl() {
        return imageUrl;
    }

    @NonNull
    public String source() {
        return source;
    }

    @NonNull
    public String summary() {
        return summary;
    }

    @NonNull
    public String content() {
        return content;
    }

    @NonNull
    public String date() {
        return date;
    }

    public Boolean isFavourite() {
        return favourite;
    }

    public Article(int id, String title, String imageUrl, String source, String summary, String content, String date, Boolean favourite) {
        this.id= id;
        this.title = title != null ? title : "";
        this.imageUrl = imageUrl != null ? imageUrl : "";
        this.source = source != null ? source : "";
        this.summary = summary != null ? summary : "";
        this.content = content != null ? content : "";
        this.date = date != null ? date : "";
        this.favourite = favourite;
    }

    public static Article getArticleFavoChanged(Article article) {
        return new Article(article.id, article.title, article.imageUrl,
                article.source, article.summary, article.content, article.date, !article.favourite);
    }
    @NonNull
    public static Builder builder() {
        return new Builder();
    }


    public static class Builder {
        private int id;
        private String title;
        private String imageUrl;
        private String source;
        private String summary;
        private String content;
        private String date;
        private Boolean favourite = false;

        Builder id(int id) {
            this.id = id;
            return this;
        }

        Builder title(String title) {
            this.title = title;
            return this;
        }

        Builder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        Builder source(String source) {
            this.source = source;
            return this;
        }

        Builder summary(String summary) {
            this.summary = summary;
            return this;
        }

        Builder content(String content) {
            this.content = content;
            return this;
        }

        Builder date(String date) {
            this.date = date;
            return this;
        }

        Article build() {
            return new Article(id, title, imageUrl, source, summary, content, date, favourite);
        }
    }

    public String toString() {
        return "Article " + id() + ", " + title() + ", " + source()
                + ", " + summary() + ", " + content() + "\n"
                + date() + ", " + imageUrl();
    }
}
