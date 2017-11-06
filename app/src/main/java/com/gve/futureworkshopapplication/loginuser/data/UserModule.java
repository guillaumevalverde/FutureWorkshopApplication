package com.gve.futureworkshopapplication.loginuser.data;

import com.gve.futureworkshopapplication.core.injection.scopes.UserScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by gve on 06/11/2017.
 */

@Module
public class UserModule {

    private User user;

    public UserModule(User user) {
        this.user = user;
    }

    @Provides
    @UserScope
    User provideUser() {
        return user;
    }
}
