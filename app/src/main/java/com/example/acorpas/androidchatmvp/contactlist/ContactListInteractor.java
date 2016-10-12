package com.example.acorpas.androidchatmvp.contactlist;

/**
 * Created by a.corpas on 26/07/2016.
 */
public interface ContactListInteractor {
    void subscribeForContactEvents();
    void unSubscribeForContactEvents();
    void destroyContactListListener();
    void removeContact(String email);
}
