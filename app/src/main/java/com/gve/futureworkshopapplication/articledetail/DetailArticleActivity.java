package com.gve.futureworkshopapplication.articledetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.gve.futureworkshopapplication.R;
import com.gve.futureworkshopapplication.core.app.BootCampApp;
import com.gve.futureworkshopapplication.core.injection.qualifiers.ForActivity;
import com.gve.futureworkshopapplication.loginuser.LoginUserActivity;
import com.gve.futureworkshopapplication.loginuser.UserManager;
import com.gve.futureworkshopapplication.loginuser.data.User;
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
        String userName = userManager.getUser().orDefault(()
                -> User.builder().name("userName").build()).name();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        titleToolBar = toolbar.findViewById(R.id.toolbar_title);

        toolbar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_log_out:
                    userManager.closeUserSession();
                    Intent intent = new Intent(this, LoginUserActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(intent);
                    return true;

                default:
                    return false;

            }
        });

        disposable.add(viewModel.fetchArticle("" + articleId)
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

        picasso.load(article.imageUrl())
                .placeholder(R.color.background_card)
                .fit()
                .centerCrop()
                .into(imageArticleIV);
    }
}
