package com.gve.futureworkshopapplication.articledetail.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.gve.futureworkshopapplication.articlelist.data.Article;

import io.reactivex.Flowable;
import io.reactivex.Single;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by gve on 07/11/2017.
 */

@Dao
public interface ArticleModelDao {

    @Query("select * from articles where id = :id")
    Flowable<Article> getItembyId(int id);

    @Insert(onConflict = REPLACE)
    void addArticle(Article article);

    @Delete
    void deleteArticle(Article article);

    @Query("select * from articles where id = :id")
    Single<Article> getItembyIdSingle(int id);

}
