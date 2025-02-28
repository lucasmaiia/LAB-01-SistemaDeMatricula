package main.java.com.example.PucTricula.application;

import javax.swing.SwingUtilities;

import main.java.com.example.PucTricula.controller.LoginController;
import main.java.com.example.PucTricula.service.LoginView;

public class PucTriculaApplication {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginController controller = new LoginController();
            new LoginView(controller).setVisible(true);
        });
    }
    }
