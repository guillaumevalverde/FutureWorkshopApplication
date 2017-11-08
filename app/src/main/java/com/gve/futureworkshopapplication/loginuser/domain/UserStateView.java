package com.gve.futureworkshopapplication.loginuser.domain;

import com.gve.futureworkshopapplication.loginuser.data.User;

import polanski.option.Option;

/**
 * Created by gve on 08/11/2017.
 */

public  class UserStateView {
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
