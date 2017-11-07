package com.gve.futureworkshopapplication.loginuser;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.gve.futureworkshopapplication.R;
import com.gve.futureworkshopapplication.core.app.BootCampApp;
import com.gve.futureworkshopapplication.loginuser.data.User;
import com.gve.futureworkshopapplication.loginuser.injection.LoginActivityComponent;
import com.gve.futureworkshopapplication.loginuser.injection.LoginActivityModule;
import com.gve.futureworkshopapplication.userarticle.presentation.ListArticleActivity;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.squareup.picasso.Picasso;

import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import polanski.option.Option;

import static com.gve.futureworkshopapplication.loginuser.LoginUserActivity.UserStateView.ERROR_EDIT_TEXT_EMPTY;
import static com.gve.futureworkshopapplication.loginuser.LoginUserActivity.UserStateView.ERROR_NO_EXISTING_USER;
import static com.gve.futureworkshopapplication.loginuser.LoginUserActivity.UserStateView.USER_OK;

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

    @SuppressLint({"RxLeakedSubscription", "RxSubscribeOnError"})
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

        getUserStateViewObs(RxView.clicks(loginB),
                            () -> textInputEditText.getText().toString(),
                            username -> userManager.startSessionForUser(username))
                .subscribe(this::handleUserState,
                           error -> Log.e(TAG, "error : " + error.getLocalizedMessage()),
                           () -> Log.v(TAG, "complete"));


        ImageView imageView = findViewById(R.id.user_login_image);
        picasso.load(IMAGE_URL)
                .placeholder(R.color.background_card)
                .fit()
                .centerCrop()
                .into(imageView);

    }

    public static Observable<UserStateView>
                        getUserStateViewObs(Observable<Object> clickObservable,
                                            Callable<String> userNameObservable,
                                            Function<String, Single<User>> fetchUser) {
        return clickObservable
                .map(click -> userNameObservable.call())
                .concatMap(userName -> {
                        if (userName == null || userName.isEmpty()) {
                            return Observable.just(UserStateView.getStateEditTextEmpty());
                        }
                        else {
                            return fetchUser.apply(userName)
                                .map(UserStateView::getStateUserOk)
                                .toObservable()
                                .onErrorResumeNext(Observable.just(UserStateView.getStateNoExistingUser()));
                        }
                    });
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
    }

    public static class UserStateView {
        public static final int ERROR_NO_EXISTING_USER = 0;
        public static final int ERROR_EDIT_TEXT_EMPTY = 1;
        public static final int USER_OK = 2;

        public int state;
        public Option<User> user;

        private UserStateView(int state, Option<User> user) {
            this.state = state;
            this.user = user;
        }

        public static UserStateView getStateEditTextEmpty() {
            return new UserStateView(ERROR_EDIT_TEXT_EMPTY, Option.none());
        }

        public static UserStateView getStateNoExistingUser() {
            return new UserStateView(ERROR_NO_EXISTING_USER, Option.none());
        }

        public static UserStateView getStateUserOk(User user) {
            System.out.println("user created: " + user.toString());
            return new UserStateView(USER_OK, Option.ofObj(user));
        }

        @Override
        public String toString() {
            return "state: " + state + ", username:" + (user.isNone() ? "none" : user.toString());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }
}
