package com.gve.futureworkshopapplication.loginuser.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import com.gve.futureworkshopapplication.R;
import com.gve.futureworkshopapplication.core.app.BootCampApp;
import com.gve.futureworkshopapplication.core.UserManager;
import com.gve.futureworkshopapplication.loginuser.domain.LoginViewModel;
import com.gve.futureworkshopapplication.loginuser.domain.UserStateView;
import com.gve.futureworkshopapplication.articlelist.presentation.ListArticleActivity;
import com.jakewharton.rxbinding2.view.RxView;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

import static com.gve.futureworkshopapplication.loginuser.domain.UserStateView.ERROR_EDIT_TEXT_EMPTY;
import static com.gve.futureworkshopapplication.loginuser.domain.UserStateView.ERROR_NO_EXISTING_USER;
import static com.gve.futureworkshopapplication.loginuser.domain.UserStateView.USER_OK;

/**
 * Created by gve on 06/11/2017.
 */

public class LoginUserActivity  extends AppCompatActivity {

    public static final String TAG = LoginUserActivity.class.getSimpleName();
    private static final String IMAGE_URL =
            "https://s3.amazonaws.com/future-workshops/fw-coding-login-image.jpg";

    private CompositeDisposable disposable = new CompositeDisposable();
    private TextInputEditText textInputEditText;

    @Inject
    Picasso picasso;

    @Inject
    UserManager userManager;

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
        Button loginB = findViewById(R.id.user_login_button);

        textInputEditText = findViewById(R.id.user_login_edit_text);

        disposable.add(LoginViewModel.getUserStateViewObs(RxView.clicks(loginB),
                () -> textInputEditText.getText().toString(),
                username -> userManager.startSessionForUser(username))
            .subscribe(userStateView -> handleUserState(userStateView),
                    error -> Log.e(TAG, "error : " + error.getLocalizedMessage()),
                    () -> Log.v(TAG, "complete")));


        ImageView imageView = findViewById(R.id.user_login_image);
        picasso.load(IMAGE_URL)
                .placeholder(R.color.background_card)
                .error(R.color.background_card)
                .fit()
                .centerCrop()
                .into(imageView);

    }

    private void handleUserState(UserStateView userState) {
        if (userState.state == USER_OK) {
            goToNextActivity();
        } else if (userState.state == ERROR_EDIT_TEXT_EMPTY) {
            textInputEditText.setError(this.getResources()
                    .getString(R.string.user_login_error_empty));
        } else if (userState.state == ERROR_NO_EXISTING_USER) {
            textInputEditText.setError(this.getResources()
                    .getString(R.string.user_login_error_no_user));
        }
    }

    private void goToNextActivity() {
        Log.v(TAG, "go to next activity");
        Intent intent = new Intent(this, ListArticleActivity.class);
        this.startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }
}
