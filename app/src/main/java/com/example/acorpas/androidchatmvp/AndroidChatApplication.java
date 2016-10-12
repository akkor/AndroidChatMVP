package com.example.acorpas.androidchatmvp;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by a.corpas on 26/07/2016.
 */
public class AndroidChatApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        // Inicializamos Firebase
        setupFirebase();
    }

    private void setupFirebase() {
        Firebase.setAndroidContext(this);
        // Nos interesa que tenga soporte para caracteristicas offline
        Firebase.getDefaultConfig().setPersistenceEnabled(true);
    }
}
