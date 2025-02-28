package main.java.com.example.PucTricula.controller;

import main.java.com.example.PucTricula.model.Usuario;
import javax.swing.*;

public class LoginController {
    private Usuario usuario;
    
    public LoginController() {
        this.usuario = new Usuario();
    }
    
    public void handleLogin(String login, String password) {
        String role = usuario.realizarLogin(login, password);
        if (role != null) {
            JOptionPane.showMessageDialog(null, "Login bem-sucedido! Tipo de usuário: " + role);
        } else {
            JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}





