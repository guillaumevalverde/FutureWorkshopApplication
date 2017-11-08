package com.gve.futureworkshopapplication.core.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.gve.futureworkshopapplication.articledetail.data.ArticleModelDao;
import com.gve.futureworkshopapplication.articlelist.data.Article;

/**
 * Created by gve on 07/11/2017.
 */

@Database(entities = {Article.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase instance;

    public static AppDataBase getDatabase(Context context) {
        if (instance == null) {
            instance =
                    Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "article_db")
                            .build();
        }
        return instance;
    }

    public abstract ArticleModelDao articleModel();

}
