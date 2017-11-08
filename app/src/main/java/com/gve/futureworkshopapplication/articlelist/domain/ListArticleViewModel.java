package com.gve.futureworkshopapplication.articlelist.domain;


import android.support.annotation.NonNull;

import com.gve.futureworkshopapplication.core.recyclerview.DisplayableItem;
import com.gve.futureworkshopapplication.articlelist.data.ArticleRepo;
import com.gve.futureworkshopapplication.articlelist.presentation.ArticleDisplayableMapper;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;


/**
 * Created by gve on 26/10/2017.
 */

public class ListArticleViewModel {

    private ArticleDisplayableMapper articleDisplayableMapper;
    private ArticleRepo articleRepo;


    @Inject
    public ListArticleViewModel(@NonNull ArticleDisplayableMapper imageDisplayableMapper,
                                @NonNull ArticleRepo articleRepo) {

        this.articleDisplayableMapper = imageDisplayableMapper;
        this.articleRepo = articleRepo;
    }

    public Flowable<List<DisplayableItem>> getDisplayable() {
        return articleRepo.getStream()
                .map(articleDisplayableMapper);
    }
}
