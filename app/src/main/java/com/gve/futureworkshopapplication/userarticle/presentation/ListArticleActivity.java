package com.gve.futureworkshopapplication.userarticle.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

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
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }
}
