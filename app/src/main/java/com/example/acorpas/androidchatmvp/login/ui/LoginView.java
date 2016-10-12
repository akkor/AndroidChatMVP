package com.example.acorpas.androidchatmvp.login.ui;

/**
 * Created by a.corpas on 26/07/2016.
 */

public interface LoginView {
    void enableInputs();
    void disableInputs();
    void showProgress();
    void hideProgress();

    void handleSignUp();
    void handleSignIn();

    void navigateToMainScreen();
    void loginError(String error);

    void newUserSuccess();
    void newUserError(String error);
}
