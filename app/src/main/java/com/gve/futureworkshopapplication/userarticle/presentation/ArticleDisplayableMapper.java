package com.gve.futureworkshopapplication.userarticle.presentation;

import android.support.annotation.NonNull;

import com.gve.futureworkshopapplication.core.recyclerview.DisplayableItem;
import com.gve.futureworkshopapplication.userarticle.data.Article;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

import static com.gve.futureworkshopapplication.core.recyclerview.DisplayableItem.toDisplayableItem;

/**
 * Created by gve on 27/10/2017.
 */

public class ArticleDisplayableMapper implements Function<List<Article>, List<DisplayableItem>> {

    @Inject
    ArticleDisplayableMapper() { }

    @Override
    public List<DisplayableItem> apply(@NonNull final List<Article> articles) throws Exception {
        return Observable.fromIterable(articles)
                .map(this::wrapInDisplayableItem)
                .toList()
                .blockingGet();
    }

    private DisplayableItem wrapInDisplayableItem(Article viewEntity) {
        return toDisplayableItem(viewEntity, DataImageConstant.ARTICLE_CARD_TYPE);
    }
}
