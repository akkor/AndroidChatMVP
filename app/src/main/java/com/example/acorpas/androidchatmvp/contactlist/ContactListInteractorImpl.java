package com.example.acorpas.androidchatmvp.contactlist;

/**
 * Created by a.corpas on 27/07/2016.
 */
public class ContactListInteractorImpl implements ContactListInteractor {
    ContactListRepositoryImpl contactListRepository;

    public ContactListInteractorImpl() {
        this.contactListRepository = new ContactListRepositoryImpl();
    }

    @Override
    public void subscribeForContactEvents() {
        contactListRepository.subscribeForContactListUpdates();
    }

    @Override
    public void unSubscribeForContactEvents() {
        contactListRepository.unSubscribeForContactListUpdates();
    }

    @Override
    public void destroyContactListListener() {
        contactListRepository.destroyContactListListener();
    }

    @Override
    public void removeContact(String email) {
        contactListRepository.removeContact(email);
    }
}
