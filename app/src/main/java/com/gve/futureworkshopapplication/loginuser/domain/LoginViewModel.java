package com.gve.futureworkshopapplication.loginuser.domain;

import com.gve.futureworkshopapplication.loginuser.data.User;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Function;

/**
 * Created by gve on 06/11/2017.
 */

public class LoginViewModel {

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
                                .onErrorResumeNext(Observable
                                        .just(UserStateView.getStateNoExistingUser()));
                    }
                });
    }
}
