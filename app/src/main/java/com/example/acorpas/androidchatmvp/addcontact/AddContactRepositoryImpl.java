package com.example.acorpas.androidchatmvp.addcontact;

import com.example.acorpas.androidchatmvp.addcontact.events.AddContactEvent;
import com.example.acorpas.androidchatmvp.domain.FirebaseHelper;
import com.example.acorpas.androidchatmvp.entities.User;
import com.example.acorpas.androidchatmvp.lib.EventBus;
import com.example.acorpas.androidchatmvp.lib.GreenRobotEventBus;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by a.corpas on 27/07/2016.
 */
public class AddContactRepositoryImpl implements AddContactRepository{
    @Override
    public void addContact(final String email) {
        final String key = email.replace(".","_");

        FirebaseHelper helper = FirebaseHelper.getInstance();
        final Firebase userReference = helper.getUserReference(email);
        userReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                AddContactEvent event = new AddContactEvent();
                if (user != null) {
                    boolean online = user.isOnline();
                    FirebaseHelper helper = FirebaseHelper.getInstance();

                    Firebase userContactsReference = helper.getMyContactsReference();
                    userContactsReference.child(key).setValue(online);

                    String currentUserEmailKey = helper.getAuthUserEmail();
                    currentUserEmailKey = currentUserEmailKey.replace(".","_");
                    Firebase reverseUserContactsReference = helper.getContactsReference(email);
                    reverseUserContactsReference.child(currentUserEmailKey).setValue(true);
                } else {
                    event.setError(true);
                }
                EventBus eventBus = GreenRobotEventBus.getInstance();
                eventBus.post(event);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {}
        });
    }
}
