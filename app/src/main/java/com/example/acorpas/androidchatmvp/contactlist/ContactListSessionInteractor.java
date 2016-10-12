package com.example.acorpas.androidchatmvp.contactlist;

/**
 * Created by a.corpas on 26/07/2016.
 */
public interface ContactListSessionInteractor {
    void signOff();
    String getCurrentUserEmail();
    void changeConnectionStatus(boolean online);
}
