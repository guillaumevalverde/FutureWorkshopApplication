package com.gve.futureworkshopapplication;

import com.gve.futureworkshopapplication.loginuser.LoginUserActivity;
import com.gve.futureworkshopapplication.loginuser.data.MockUserProvider;
import com.gve.futureworkshopapplication.loginuser.data.User;
import com.gve.futureworkshopapplication.test_common.BaseTest;

import org.junit.Before;
import org.junit.Test;

import io.reactivex.observers.TestObserver;
import io.reactivex.subjects.BehaviorSubject;
import polanski.option.Option;

/**
 * Created by gve on 06/11/2017.
 */

public class LogInTest extends BaseTest {

    private BehaviorSubject<Object> clickObservable;
    private User guillaume = User.builder().name("guillaume").build();


    @Before
    public void setUp() {
        clickObservable = BehaviorSubject.create();
    }

    @Test
    public void clickUserNameTest() {
        TestObserver testObserver = LoginUserActivity.getUserStateViewObs(clickObservable,
                () -> "guillaume",
                (username) -> (new MockUserProvider()).getUser(username)).test();
        clickObservable.onNext(null);
        testObserver.assertNotComplete();
    }

    @Test
    public void clickWithRightUserNameTest() {
        TestObserver<LoginUserActivity.UserStateView> testObserver = LoginUserActivity.getUserStateViewObs(clickObservable,
                () -> "guillaume",
                (username) -> (new MockUserProvider()).getUser(username)).test();
        clickObservable.onNext(Option.none());
        testObserver.assertNotComplete();

        testObserver.assertValueAt(0, user -> {
            System.out.println("user: " + user.toString());
            return user.state == LoginUserActivity.UserStateView.USER_OK;
        });
        testObserver.assertValueAt(0, user -> {
            System.out.println("user: " + user.toString());
            User userTest = user.user.orDefault(() -> User.builder().name("default").build());
            return userTest.name().equals("guillaume");
        });
    }

    @Test
    public void clickWithWrongUserNameTest() {
        TestObserver<LoginUserActivity.UserStateView> testObserver = LoginUserActivity.getUserStateViewObs(clickObservable,
                () -> "gerard",
                (username) -> (new MockUserProvider()).getUser(username)).test();
        clickObservable.onNext(Option.none());
        clickObservable.onNext(Option.none());
        testObserver.assertNotComplete();
        testObserver.assertValueAt(0, user -> {
            System.out.println("user: " + user.toString());
            return user.state == LoginUserActivity.UserStateView.ERROR_NO_EXISTING_USER;
        });
    }

    @Test
    public void mockUserProviderValidateUserTest() {
        TestObserver testObserver = (new MockUserProvider()).getUser("guillaume").test();
        testObserver.assertComplete();
    }

    @Test
    public void mockUserProviderUnvalidateUserTest() {
        TestObserver testObserver = (new MockUserProvider()).getUser("gerard").test();
        testObserver.assertNotComplete();
    }

}
