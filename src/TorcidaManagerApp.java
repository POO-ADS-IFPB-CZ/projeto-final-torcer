package controller;

import view.LoginView;

public class TorcidaManagerApp {
    public static void main(String[] args) {
        LoginView loginView = new LoginView();
        new LoginController(loginView);
    }
}