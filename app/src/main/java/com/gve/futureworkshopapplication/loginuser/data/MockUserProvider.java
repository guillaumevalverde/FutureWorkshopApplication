package com.gve.futureworkshopapplication.loginuser.data;

import io.reactivex.Single;

/**
 * Created by gve on 06/11/2017.
 */

public class MockUserProvider implements UserAPI {
    private static final String EXISTING_USER = "guillaume";

    @Override
    public Single<User> getUser(String username) {
        if (username.contentEquals(EXISTING_USER)) {
            return Single.just(User.builder().name(username).build());
        } else {
            return Single.error(new Exception("no user"));
        }
    }
}
