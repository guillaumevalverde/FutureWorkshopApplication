package com.gve.futureworkshopapplication.userarticle.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

import com.gve.futureworkshopapplication.R;
import com.gve.futureworkshopapplication.core.app.BootCampApp;
import com.gve.futureworkshopapplication.core.recyclerview.RecyclerViewAdapter;
import com.gve.futureworkshopapplication.userarticle.domain.ListArticleViewModel;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by gve on 31/10/2017.
 */

public class ListArticleActivity extends AppCompatActivity {
    public static final String TAG = ListArticleActivity.class.getSimpleName();

    @Inject
    ListArticleViewModel listArticleViewModel;

    @Inject
    RecyclerViewAdapter adapter;

    private CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ListArticleActivityComponent.Builder builder = (ListArticleActivityComponent.Builder)
                (((BootCampApp) getApplication()).getComponent())
                        .subComponentBuilders()
                        .get(ListArticleActivityComponent.Builder.class)
                        .get();
        builder.activityModule(new ListArticleActivityModule(this)).build().inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_article);

        String textToolBar = this.getResources().getString(R.string.user_articles_news_feed, "username");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        TextView titleToolBar = toolbar.findViewById(R.id.toolbar_title);
        titleToolBar.setText(textToolBar);

        toolbar.setOnMenuItemClickListener(item -> {
            Log.v(TAG, "log clickclick");
            switch (item.getItemId()) {
                case R.id.action_log_out:
                    Log.v(TAG, "log out");
                    return true;

                default:
                    // If we got here, the user's action was not recognized.
                    // Invoke the superclass to handle it.
                    return false;

            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        //logoutToolBar.setOnClickListener(click ->  Log.v(TAG, "log click"));

        RecyclerView.LayoutManager mLayoutManager
                = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        disposable.add(
                listArticleViewModel.getDisplayable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(users -> adapter.update(users), e -> Log.e(TAG, e.getMessage())));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.v(TAG, "create menu");
        getMenuInflater().inflate(R.menu.user_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }
}
