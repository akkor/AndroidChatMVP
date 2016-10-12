package com.example.acorpas.androidchatmvp.addcontact.events;

/**
 * Created by a.corpas on 27/07/2016.
 */
public class AddContactEvent {
    boolean error = false;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
