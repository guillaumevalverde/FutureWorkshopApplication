package com.gve.futureworkshopapplication.articlelist.presentation;

import com.gve.futureworkshopapplication.core.preconditions.AndroidPreconditions;
import com.gve.futureworkshopapplication.core.recyclerview.ItemComparator;
import com.gve.futureworkshopapplication.core.recyclerview.RecyclerViewAdapter;
import com.gve.futureworkshopapplication.core.recyclerview.ViewHolderBinder;
import com.gve.futureworkshopapplication.core.recyclerview.ViewHolderFactory;

import java.util.Map;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntKey;
import dagger.multibindings.IntoMap;

import static com.gve.futureworkshopapplication.articlelist.presentation.ArticleConstant.ARTICLE_CARD_TYPE;

/**
 * Created by gve on 26/10/2017.
 */

@Module
public abstract class RecyclerViewActivityModule {

    @Provides
    static RecyclerViewAdapter provideRecyclerAdapter(ItemComparator itemComparator,
                                                      Map<Integer, ViewHolderFactory> factoryMap,
                                                      Map<Integer, ViewHolderBinder> binderMap,
                                                      AndroidPreconditions androidPreconditions) {
        return new RecyclerViewAdapter(itemComparator, factoryMap, binderMap, androidPreconditions);
    }

    @Provides
    static ItemComparator provideComparator() {
        return new ArticleItemComparator();
    }

    @Binds
    @IntoMap
    @IntKey(ARTICLE_CARD_TYPE)
    abstract ViewHolderFactory
        provideUserCardHolderFactory(ArticleViewHolder.ArticleCardHolderFactory factory);

    @Binds
    @IntoMap
    @IntKey(ARTICLE_CARD_TYPE)
    abstract ViewHolderBinder
        provideUserCardHolderBinder(ArticleViewHolder.ArticleCardHolderBinder binder);
}
