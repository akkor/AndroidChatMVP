package com.example.acorpas.androidchatmvp.login;

/**
 * Created by a.corpas on 26/07/2016.
 */

/***
 * Trabaja los casos de uso
 */
public interface LoginInteractor {
    void checkSession();
    void checkAlreadyAuthenticated();
    void doSignUp(String email, String password);
    void doSignIn(String email, String password);
}
