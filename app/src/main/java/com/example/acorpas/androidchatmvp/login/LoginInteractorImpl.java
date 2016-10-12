package com.example.acorpas.androidchatmvp.login;

/**
 * Created by a.corpas on 26/07/2016.
 */
public class LoginInteractorImpl implements LoginInteractor{

    private LoginRepository loginRepository;

    public LoginInteractorImpl() {
        this.loginRepository = new LoginRepositoryImpl();
    }

    @Override
    public void doSignUp(final String email, final String password) {
        loginRepository.signUp(email, password);
    }

    @Override
    public void doSignIn(String email, String password) {
        loginRepository.signIn(email, password);
    }

    @Override
    public void checkSession() {

    }

    @Override
    public void checkAlreadyAuthenticated() {
        loginRepository.checkAlreadyAuthenticated();
    }
}
