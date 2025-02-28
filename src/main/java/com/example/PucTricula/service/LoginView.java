package main.java.com.example.PucTricula.service;

import main.java.com.example.PucTricula.controller.LoginController;
import main.java.com.example.PucTricula.model.Administrador;
import main.java.com.example.PucTricula.model.Aluno;
import main.java.com.example.PucTricula.model.SistemaMatricula;

import javax.swing.*;
import java.awt.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class LoginView extends JFrame {
    private SistemaMatricula sistema;
    
    public LoginView(SistemaMatricula sistema) {
        this.sistema = sistema;
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2));
        
        JLabel userLabel = new JLabel("Usuário:");
        JTextField userField = new JTextField();
        JLabel passLabel = new JLabel("Senha:");
        JPasswordField passField = new JPasswordField();
        JButton loginButton = new JButton("Login");
        
        loginButton.addActionListener(e -> {
            String usuario = userField.getText();
            String senha = new String(passField.getPassword());
            
            Aluno alunoLogado = null;
        for (Aluno aluno : sistema.getAlunos()) {
            if (aluno.getNome().equals(usuario) && aluno.autenticar(senha)) {
                alunoLogado = aluno;
                break;
            }
        }       

        if (alunoLogado != null) {
            new AlunoView(alunoLogado, sistema).setVisible(true);
            dispose();
        }else if (new Administrador().autenticar(senha)) {
                new AdminView(sistema).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Login inválido!");
            }
        });
        
        add(userLabel);
        add(userField);
        add(passLabel);
        add(passField);
        add(new JLabel());
        add(loginButton);
    }
    }
    
    /*public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginController controller = new LoginController();
            new LoginView(controller).setVisible(true);
        });
    }*/

        


