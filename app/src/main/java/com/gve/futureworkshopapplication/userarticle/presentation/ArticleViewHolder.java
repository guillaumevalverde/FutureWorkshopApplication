package com.gve.futureworkshopapplication.userarticle.presentation;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gve.futureworkshopapplication.R;
import com.gve.futureworkshopapplication.articledetail.presentation.DetailArticleActivity;
import com.gve.futureworkshopapplication.core.injection.qualifiers.ForActivity;
import com.gve.futureworkshopapplication.core.recyclerview.DisplayableItem;
import com.gve.futureworkshopapplication.core.recyclerview.ViewHolderBinder;
import com.gve.futureworkshopapplication.core.recyclerview.ViewHolderFactory;
import com.gve.futureworkshopapplication.userarticle.data.Article;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import javax.inject.Inject;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class ArticleViewHolder extends RecyclerView.ViewHolder {

    private TextView titleTV;
    private TextView descriptionTV;
    private TextView dateTV;
    private ImageView imageIV;
    private ProgressBar progressBar;
    private Picasso picasso;
    private View card;

    private static final int RADIUS = 10;
    private static final int MARGIN = 0;
    private static final Transformation TRANSFORMATION = new RoundedCornersTransformation(RADIUS, MARGIN);

    // We also create a constructor that accepts the entire item row
    // and does the view lookups to find each subview
    public ArticleViewHolder(final View itemView, Picasso picasso) {
        // Stores the itemView in a public final member variable that can be used
        // to access the context from any ViewHolder instance.
        super(itemView);
        card = itemView;
        titleTV = itemView.findViewById(R.id.article_card_title);
        dateTV = itemView.findViewById(R.id.article_card_date);
        descriptionTV = itemView.findViewById(R.id.article_card_description);
        imageIV = itemView.findViewById(R.id.article_card_image);
        progressBar = itemView.findViewById(R.id.article_card_progress_bar);
        this.picasso = picasso;
    }

    private void bind(@NonNull final Article article) {
        titleTV.setText(article.title());
        dateTV.setText(article.date());
        descriptionTV.setText(article.summary());
        if (!article.imageUrl().isEmpty()) {
            progressBar.setVisibility(View.VISIBLE);
            picasso.load(article.imageUrl())
                    .placeholder(R.drawable.rounded)
                    .fit()
                    .transform(TRANSFORMATION)
                    .centerCrop()
                    .error(R.drawable.rounded)
                    .into(imageIV,  new Callback() {

                        @Override
                        public void onSuccess() {
                            progressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError() {
                            progressBar.setVisibility(View.GONE);
                        }
                    });
        } else {
            progressBar.setVisibility(View.GONE);
            imageIV.setImageDrawable(imageIV.getResources()
                    .getDrawable(R.drawable.rounded));
        }

        card.setOnClickListener(click -> startNextActivity(article.id()));
    }

    private void startNextActivity(int id) {
        Intent intent = new Intent(itemView.getContext(), DetailArticleActivity.class);
        intent.putExtra(DetailArticleActivity.ARTICLE_ID, id);
        itemView.getContext().startActivity(intent);
    }

    static class ArticleCardHolderFactory extends ViewHolderFactory {

        private Picasso picasso;

        @Inject
        ArticleCardHolderFactory(@NonNull @ForActivity final Context context, Picasso picasso) {
            super(context);
            this.picasso = picasso;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder createViewHolder(@NonNull final ViewGroup parent) {
            return new ArticleViewHolder(LayoutInflater.from(context)
                    .inflate(R.layout.article_recycler_row, parent, false), picasso);
        }
    }

    static class ArticleCardHolderBinder implements ViewHolderBinder {

        @Inject
        ArticleCardHolderBinder() { }

        @Override
        public void bind(@NonNull final RecyclerView.ViewHolder viewHolder,
                         @NonNull final DisplayableItem item) {
            ArticleViewHolder castedViewHolder = ArticleViewHolder.class.cast(viewHolder);
            Article viewEntity = Article.class.cast(item.model());
            castedViewHolder.bind(viewEntity);
        }
    }
}
