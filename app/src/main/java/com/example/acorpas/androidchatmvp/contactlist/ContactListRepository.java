package com.example.acorpas.androidchatmvp.contactlist;

/**
 * Created by a.corpas on 26/07/2016.
 */
public interface ContactListRepository {
    void signOff();
    String getCurrentEmail();
    void removeContact(String email);
    void destroyContactListListener();
    void subscribeForContactListUpdates();
    void unSubscribeForContactListUpdates();
    void changeUserConnectionStatus(boolean online);

}
