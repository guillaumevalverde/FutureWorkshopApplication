package com.gve.futureworkshopapplication.articlelist.presentation;

import com.gve.futureworkshopapplication.core.recyclerview.DisplayableItem;
import com.gve.futureworkshopapplication.core.recyclerview.ItemComparator;

/**
 * Created by gve on 27/10/2017.
 */

public final class ArticleItemComparator
        implements ItemComparator {

    @Override
    public boolean areItemsTheSame(final DisplayableItem item1, final DisplayableItem item2) {
        return item1.equals(item2);
    }

    @Override
    public boolean areContentsTheSame(final DisplayableItem item1, final DisplayableItem item2) {
        return item1.equals(item2);
    }
}
