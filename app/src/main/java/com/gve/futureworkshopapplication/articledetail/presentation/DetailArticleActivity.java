package com.gve.futureworkshopapplication.articledetail.presentation;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.gve.futureworkshopapplication.R;
import com.gve.futureworkshopapplication.articledetail.domain.DetailArticleViewModel;
import com.gve.futureworkshopapplication.core.app.BootCampApp;
import com.gve.futureworkshopapplication.core.injection.qualifiers.ForActivity;
import com.gve.futureworkshopapplication.loginuser.UserManager;
import com.gve.futureworkshopapplication.userarticle.data.Article;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by gve on 07/11/2017.
 */

public class DetailArticleActivity extends AppCompatActivity {
    public static final String TAG = DetailArticleActivity.class.getSimpleName();
    public static final String ARTICLE_ID = "ARTICLE_ID";

    @Inject
    UserManager userManager;

    @Inject
    @ForActivity
    Context context;

    private CompositeDisposable disposable = new CompositeDisposable();

    @Inject
    DetailArticleViewModel viewModel;

    @Inject
    Picasso picasso;

    private int articleId;

    private TextView titleTV;
    private ImageView imageArticleIV;
    private TextView sourceDateTV;
    private TextView contentTV;
    private TextView titleToolBar;
    private ImageView buttonToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DetailArticleActivityComponent.Builder builder = (DetailArticleActivityComponent.Builder)
                (((BootCampApp) getApplication()).getComponent())
                        .subComponentBuilders()
                        .get(DetailArticleActivityComponent.Builder.class)
                        .get();
        builder.activityModule(new DetailArticleActivityModule(this)).build().inject(this);

        super.onCreate(savedInstanceState);
        userManager.startUserSession();
        setContentView(R.layout.activity_article);

        titleTV = findViewById(R.id.article_detail_title);
        imageArticleIV = findViewById(R.id.article_detail_image);
        sourceDateTV = findViewById(R.id.article_detail_source_date);
        contentTV = findViewById(R.id.article_detail_content);

        articleId = getIntent().getExtras().getInt(ARTICLE_ID);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        titleToolBar = toolbar.findViewById(R.id.toolbar_article_detail_title);
        buttonToolBar = toolbar.findViewById(R.id.toolbar_article_detail_button);

        buttonToolBar.setOnClickListener(click ->
                disposable.add(viewModel.changeFavourite(articleId)
                        .subscribe(() -> Log.v(TAG, "changeFavourite complete"),
                                error -> Log.e(TAG, "changeFavourite error: "
                                        + error.getMessage()))));

        disposable.add(viewModel.fetchArticle(articleId)
                .subscribe(() -> Log.v(TAG, "fectch complete"),
                        error -> Log.e(TAG, "fetch error: " + error.getMessage())));
        disposable.add(viewModel.getArticleFlowable(articleId)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(this::setUpUi,
                                        error -> Log.e(TAG, error.getMessage())));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }

    public void setUpUi(Article article) {
        titleTV.setText(article.title());
        sourceDateTV.setText(this.getResources()
                .getString(R.string.article_source_date, article.source(), article.date()));
        contentTV.setText(article.content());
        titleToolBar.setText(article.title());
        buttonToolBar.setImageDrawable(this.getResources().getDrawable(article.isFavourite()
                ? R.drawable.star_selected
                : R.drawable.star_unselected, null));

        picasso.load(article.imageUrl())
                .placeholder(R.color.background_card)
                .fit()
                .centerCrop()
                .into(imageArticleIV);
    }
}
