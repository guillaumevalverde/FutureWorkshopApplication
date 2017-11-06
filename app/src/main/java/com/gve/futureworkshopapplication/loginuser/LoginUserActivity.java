package com.gve.futureworkshopapplication.loginuser;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.gve.futureworkshopapplication.R;
import com.gve.futureworkshopapplication.core.app.BootCampApp;
import com.gve.futureworkshopapplication.loginuser.injection.LoginActivityComponent;
import com.gve.futureworkshopapplication.loginuser.injection.LoginActivityModule;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by gve on 06/11/2017.
 */

public class LoginUserActivity  extends AppCompatActivity {
    public static String tag = LoginUserActivity.class.getSimpleName();

    private CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LoginActivityComponent.Builder builder = (LoginActivityComponent.Builder)
                (((BootCampApp) getApplication()).getComponent())
                        .subComponentBuilders()
                        .get(LoginActivityComponent.Builder.class)
                        .get();
        builder.activityModule(new LoginActivityModule(this)).build().inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }
}