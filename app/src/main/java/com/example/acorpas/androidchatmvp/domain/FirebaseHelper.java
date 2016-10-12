package com.example.acorpas.androidchatmvp.domain;

import com.example.acorpas.androidchatmvp.entities.User;
import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by a.corpas on 26/07/2016.
 */
public class FirebaseHelper {
    private Firebase dataReference;
    private final static String SEPARATOR = "___";
    private final static String CHATS_PATH = "chats";
    private final static String USERS_PATH = "users";
    public final static String CONTACTS_PATH = "contacts";
    private final static String FIREBASE_URL = "https://android-chat-example.firebaseio.com";

    //region Singleton
    // Singleton 2
    private static class SingletonHolder {
        private static final FirebaseHelper INSTANCE = new FirebaseHelper();
    }

    // Singleton 1 (llamaremos a esta funcion para tener una instancia unicamente)
    public static FirebaseHelper getInstance() {
        return SingletonHolder.INSTANCE;
    }

    // Singleton 3
    public FirebaseHelper(){
        dataReference = new Firebase(FIREBASE_URL);
    }

    // Singleton 4
    public Firebase getDataReference() {
        return dataReference;
    }
    //endregion

    // Obtenemos el correo del usuario atutenticado en este momento
    public String getAuthUserEmail() {
        AuthData authData = dataReference.getAuth();
        String email = null;
        // Ha iniciado sesion?
        if (authData != null) {
            Map<String, Object> providerData = authData.getProviderData();
            email = providerData.get("email").toString();
        }
        return email;
    }

    // Obtenemos la referencia de un usuario a partir de su email
    public Firebase getUserReference(String email){
        Firebase userReference = null;
        if (email != null) {
            String emailKey = email.replace(".", "_"); // Firebase no acepta puntos
            userReference = dataReference.getRoot().child(USERS_PATH).child(emailKey);
        }
        return userReference;
    }

    /**
     * Obtenemos la referencia de nuestro usuario
     * @return referencia de nuestro usuario
     */
    public Firebase getMyUserReference() {
        return getUserReference(getAuthUserEmail());
    }

    /**
     * Obtenemos los contactos a partir de su email
     * @param email
     * @return
     */
    public Firebase getContactsReference(String email){
        return getUserReference(email).child(CONTACTS_PATH);
    }

    /***
     * Obtenemos nuestros contactos
     * @return
     */
    public Firebase getMyContactsReference(){
        return getContactsReference(getAuthUserEmail());
    }

    /***
     * Obtenemos la referencia de un contacto a partir del correo del usuario y del correo del contacto
     * Primero obtenemos la referencia del correo principal y luego un child de los contactos y del correo secundario
     * @param mainEmail correo del usuario
     * @param childEmail correo del contacto
     * @return Referencia del contacto
     */
    public Firebase getOneContactReference(String mainEmail, String childEmail){
        String childKey = childEmail.replace(".","_");
        return getUserReference(mainEmail).child(CONTACTS_PATH).child(childKey);
    }

    /***
     * Contiene un separador y ademas necesitamos comparar para obtenerlo en orden alfabetico. Esto lo
     * hacemos mediante la raiz de la referencia de datos, la referencia chats y el hijo de la referencia
     * @param receiver
     * @return referencia de los chats
     */
    public Firebase getChatsReference(String receiver){
        String keySender = getAuthUserEmail().replace(".","_");
        String keyReceiver = receiver.replace(".","_");

        String keyChat = keySender + SEPARATOR + keyReceiver;
        if (keySender.compareTo(keyReceiver) > 0) {
            keyChat = keyReceiver + SEPARATOR + keySender;
        }
        return dataReference.getRoot().child(CHATS_PATH).child(keyChat);
    }

    /***
     * Cambiamos el status de la conexion de un usuario.
     * Verificamos que tiene una referencia valida y si es asi entonces defino un mapa y y lo modifico
     * mediante la KEY.
     * @param online indica si esta online o offline
     */
    public void changeUserConnectionStatus(boolean online) {
        if (getMyUserReference() != null) {
            Map<String, Object> updates = new HashMap<String, Object>();
            updates.put("online", online);
            getMyUserReference().updateChildren(updates);

            notifyContactsOfConnectionChange(online);
        }
    }

    /***
     * Nos permite cambiar nuestro estado y cerrar la sesion de firebase.
     * @param online statuc actual
     * @param signoff cerrando sesion
     */
    public void notifyContactsOfConnectionChange(final boolean online, final boolean signoff) {
        final String myEmail = getAuthUserEmail();
        getMyContactsReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    String email = child.getKey();
                    Firebase reference = getOneContactReference(email, myEmail);
                    reference.setValue(online);
                }
                if (signoff){
                    dataReference.unauth();
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }

    /***
     * Notificamos a los contactos que mi estado a cambiado.
     * @param online estado nuevo
     */
    public void notifyContactsOfConnectionChange(boolean online) {
        notifyContactsOfConnectionChange(online, false);
    }

    public void signOff(){
        notifyContactsOfConnectionChange(User.OFFLINE, true);
    }
}
