package com.example.acorpas.androidchatmvp.contactlist;

import com.example.acorpas.androidchatmvp.contactlist.events.ContactListEvent;

/**
 * Created by a.corpas on 26/07/2016.
 */
public interface ContactListPresenter {
    void onPause();
    void onResume();
    void onCreate();
    void onDestroy();

    void signOff();
    String getCurrentUserEmail();
    void removeContact(String email);
    void onEventMainThread(ContactListEvent event);
}
