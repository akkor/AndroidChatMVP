package com.example.acorpas.androidchatmvp.addcontact;

/**
 * Created by a.corpas on 27/07/2016.
 */
public class AddContactInteractorImpl implements AddContactInteractor {
    AddContactRepositoryImpl addContactRepository;

    public AddContactInteractorImpl(AddContactRepositoryImpl addContactRepository) {
        this.addContactRepository = addContactRepository;
    }

    @Override
    public void addContact(String email) {
        addContactRepository.addContact(email);
    }
}
