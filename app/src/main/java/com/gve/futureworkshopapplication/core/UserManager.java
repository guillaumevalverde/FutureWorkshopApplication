package com.gve.futureworkshopapplication.core;

import com.gve.futureworkshopapplication.loginuser.data.User;
import com.gve.futureworkshopapplication.loginuser.data.UserAPI;
import com.gve.futureworkshopapplication.loginuser.data.UserComponent;
import com.gve.futureworkshopapplication.loginuser.data.UserDataStore;
import com.gve.futureworkshopapplication.loginuser.data.UserModule;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import polanski.option.Option;
import timber.log.Timber;

/**
 * Created by gve on 06/11/2017.
 */

public class UserManager {

    private UserAPI userAPI;
    private UserComponent.Builder builder;
    private UserDataStore userDataStore;
    private UserComponent userComponent;
    private User user;

    public UserManager(UserAPI userAPI, UserDataStore userDataStore,
                       UserComponent.Builder builder) {
        this.userAPI = userAPI;
        this.builder = builder;
        this.userDataStore = userDataStore;
    }

    public Single<User> startSessionForUser(String username) {
        return userAPI.getUser(username)
                .doOnSuccess(user -> {
                    userDataStore.createUser(user);
                    userComponent = builder.userModule(new UserModule(user)).build();
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public boolean startUserSession() {
        if (user != null)
            return true;

        user = userDataStore.getUser();
        if (user != null) {
            Timber.i("Session started, user: %s", user);
            userComponent = builder.userModule(new UserModule(user)).build();
            return true;
        }
        return false;
    }

    public Option<User> getUser() {
        return Option.ofObj(user);
    }

    public UserComponent getUserComponent() {
        return userComponent;
    }

    public void closeUserSession() {
        Timber.i("Close session for user: %s", userDataStore.getUser());
        user = null;
        userDataStore.clearUser();
        userComponent = null;
    }
}
