package com.example.acorpas.androidchatmvp.contactlist.ui;

import com.example.acorpas.androidchatmvp.entities.User;

/**
 * Created by a.corpas on 26/07/2016.
 */
public interface ContactListView {
    void onContactAdded(User user);
    void onContactChanged(User user);
    void onContactRemoved(User user);
}
