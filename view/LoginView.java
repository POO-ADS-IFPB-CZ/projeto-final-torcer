package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    public LoginView() {
        setTitle("Login - Torcida Manager");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        loginButton = new JButton("Login");
        add(loginButton);

        registerButton = new JButton("Registrar");
        add(registerButton);

        setVisible(true);
    }

    public String getUsername() { return usernameField.getText(); }
    public String getPassword() { return new String(passwordField.getPassword()); }

    public void addLoginListener(ActionListener listener) { loginButton.addActionListener(listener); }
    public void addRegisterListener(ActionListener listener) { registerButton.addActionListener(listener); }

    public void showMessage(String message) { JOptionPane.showMessageDialog(this, message); }
}