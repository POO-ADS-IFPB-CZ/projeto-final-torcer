package controller;

import model.DataPersistence;
import model.User;
import model.Team;
import view.UserView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserController {
    private UserView view;
    private List<User> users;
    private List<Team> teams;

    public UserController(UserView view, List<User> users, List<Team> teams) {
        this.view = view;
        this.users = users;
        this.teams = teams;

        view.addCreateListener(new CreateListener());
        view.addUpdateListener(new UpdateListener());
        view.addDeleteListener(new DeleteListener());
    }

    class CreateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = view.getName();
            String email = view.getEmail();
            String favoriteTeam = view.getFavoriteTeam();
            String username = name.toLowerCase();
            User newUser = new User(username, "senha123", name, email, favoriteTeam);
            users.add(newUser);
            saveAndRefresh();
        }
    }

    class UpdateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selected = view.getSelectedUser();
            if (selected == null) return;
            for (User user : users) {
                if (user.getUsername().equals(selected)) {
                    user.setName(view.getName());
                    user.setEmail(view.getEmail());
                    user.setFavoriteTeam(view.getFavoriteTeam());
                    saveAndRefresh();
                    return;
                }
            }
        }
    }

    class DeleteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selected = view.getSelectedUser();
            if (selected == null) return;
            users.removeIf(user -> user.getUsername().equals(selected));
            saveAndRefresh();
        }
    }

    private void saveAndRefresh() {
        try {
            DataPersistence.saveUsers(users);
            view.refreshList(users);
            view.showMessage("Operação realizada!");
        } catch (Exception ex) {
            view.showMessage("Erro ao salvar!");
        }
    }
}