package main.java.com.example.PucTricula.service;

import main.java.com.example.PucTricula.controller.LoginController;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {
    private JTextField userField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton cancelButton;
    private LoginController controller;

    public LoginView(LoginController controller) {
        this.controller = controller;
        
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2));
        
        JLabel userLabel = new JLabel("Usuário:");
        userField = new JTextField();
        JLabel passwordLabel = new JLabel("Senha:");
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        cancelButton = new JButton("Cancelar");
        
        add(userLabel);
        add(userField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
        add(cancelButton);
        
        loginButton.addActionListener(e -> controller.handleLogin(userField.getText(), new String(passwordField.getPassword())));
        cancelButton.addActionListener(e -> System.exit(0));
    }
    
    /*public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginController controller = new LoginController();
            new LoginView(controller).setVisible(true);
        });
    }*/
}
        


