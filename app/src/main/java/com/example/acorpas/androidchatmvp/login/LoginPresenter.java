package com.example.acorpas.androidchatmvp.login;

/**
 * Created by a.corpas on 26/07/2016.
 */

import com.example.acorpas.androidchatmvp.login.events.LoginEvent;

/**
 * El presentador esta vinculado a la vista para usar los metodos definidos en esta
 */
public interface LoginPresenter {
    void onCreate();
    void onDestroy();
    void checkForAuthenticatedUser();
    void onEventMainThread(LoginEvent event);
    void validateLogin(String email, String password);
    void registerNewUser(String email, String password);
}
