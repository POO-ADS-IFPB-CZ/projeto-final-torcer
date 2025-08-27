package view;

import model.User;
import model.Team;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class UserView extends CrudView {
    private JTextField nameField, emailField;
    private JComboBox<String> favoriteTeamComboBox;

    public UserView(List<User> users, List<Team> teams) {
        super("Gerenciar Usuários", 400, 400);

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Nome:"));
        nameField = new JTextField();
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        inputPanel.add(emailField);
        inputPanel.add(new JLabel("Time Favorito:"));

        favoriteTeamComboBox = new JComboBox<>();
        for (Team team : teams) {
            favoriteTeamComboBox.addItem(team.getName());
        }
        inputPanel.add(favoriteTeamComboBox);

        add(inputPanel, BorderLayout.NORTH);

        refreshList(users);
    }

    public String getSelectedUser() {
        String selected = itemList.getSelectedValue();
        if (selected != null) {
            return selected.split(" - ")[0];
        }
        return null;
    }

    public String getName() { return nameField.getText(); }
    public String getEmail() { return emailField.getText(); }

    public String getFavoriteTeam() { return (String) favoriteTeamComboBox.getSelectedItem(); }

    @Override
    public void refreshList(List<?> users) {
        listModel.clear();
        for (Object userObj : users) {
            User user = (User) userObj;
            listModel.addElement(user.getUsername() + " - " + user.getFavoriteTeam());
        }
    }
}