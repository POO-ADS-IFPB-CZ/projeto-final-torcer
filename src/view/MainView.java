package view;

import model.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainView extends JFrame {
    private JButton userButton;
    private JButton teamButton;
    private JButton eventButton;
    private JButton logoutButton;
    private JLabel welcomeLabel;

    public MainView(User user) {
        setTitle("Menu Principal - Torcida Manager");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        welcomeLabel = new JLabel("Bem-vindo, " + user.getName() + "!");
        add(welcomeLabel);

        userButton = new JButton("Gerenciar Usuários");
        add(userButton);

        teamButton = new JButton("Gerenciar Times");
        add(teamButton);

        eventButton = new JButton("Gerenciar Eventos");
        add(eventButton);

        logoutButton = new JButton("Logout");
        add(logoutButton);

        setVisible(true);
    }

    public void addUserListener(ActionListener listener) { userButton.addActionListener(listener); }
    public void addTeamListener(ActionListener listener) { teamButton.addActionListener(listener); }
    public void addEventListener(ActionListener listener) { eventButton.addActionListener(listener); }
    public void addLogoutListener(ActionListener listener) { logoutButton.addActionListener(listener); }
}