package com.example.acorpas.androidchatmvp.login;

/**
 * Created by a.corpas on 26/07/2016.
 */

/**
 * Tiene interaccion con el backend (firebase)
 */
public interface LoginRepository {
    void signUp(final String email, final String password);
    void signIn(String email, String password);
    void checkAlreadyAuthenticated();
    void checkSession();
}
