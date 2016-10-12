package com.example.acorpas.androidchatmvp.addcontact.ui;

/**
 * Created by a.corpas on 27/07/2016.
 */
public interface AddContactView {
    void showInput();
    void hideInput();
    void showProgress();
    void hideProgress();

    void contactAdded();
    void contactNotAdded();
}
