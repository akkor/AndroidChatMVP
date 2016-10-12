package com.example.acorpas.androidchatmvp.addcontact;

import com.example.acorpas.androidchatmvp.addcontact.events.AddContactEvent;
import com.example.acorpas.androidchatmvp.addcontact.ui.AddContactView;
import com.example.acorpas.androidchatmvp.lib.EventBus;
import com.example.acorpas.androidchatmvp.lib.GreenRobotEventBus;

/**
 * Created by a.corpas on 27/07/2016.
 */
public interface AddContactPresenter {
    void onShow();
    void onDestroy();

    void addContact(String email);
    void onEventMainThread(AddContactEvent event);


}
