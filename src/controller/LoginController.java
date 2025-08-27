package controller;

import model.DataPersistence;
import model.User;
import view.LoginView;
import view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LoginController {
    private LoginView view;
    private List<User> users;

    public LoginController(LoginView view) {
        this.view = view;
        this.users = DataPersistence.loadUsers();

        view.addLoginListener(new LoginListener());
        view.addRegisterListener(new RegisterListener());
    }

    class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = view.getUsername();
            String password = User.hashPassword(view.getPassword());

            for (User user : users) {
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    view.dispose();
                    MainView mainView = new MainView(user);
                    new MainController(mainView, users, user);
                    return;
                }
            }
            view.showMessage("Login inválido!");
        }
    }

    class RegisterListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = view.getUsername();
            String password = view.getPassword();

            for (User user : users) {
                if (user.getUsername().equals(username)) {
                    view.showMessage("Usuário já existe!");
                    return;
                }
            }

            User newUser = new User(username, password, "Nome Padrão", "email@exemplo.com", "Time Padrão");
            users.add(newUser);

            try {
                DataPersistence.saveUsers(users);
                view.showMessage("Registrado com sucesso! Faça login.");
            } catch (Exception ex) {
                view.showMessage("Erro ao salvar!");
            }
        }
    }
}