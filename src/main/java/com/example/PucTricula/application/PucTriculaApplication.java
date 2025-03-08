package main.java.com.example.PucTricula.application;

import javax.swing.SwingUtilities;

import main.java.com.example.PucTricula.controller.LoginController;
import main.java.com.example.PucTricula.model.Disciplina;
import main.java.com.example.PucTricula.model.SistemaMatricula;
import main.java.com.example.PucTricula.view.LoginView;
import main.java.com.example.PucTricula.model.Aluno;

public class PucTriculaApplication {
    public static void main(String[] args) {
        SistemaMatricula sistema = new SistemaMatricula();
        sistema.cadastrarAluno(new Aluno("Carlos", "12345", "123"));
        sistema.cadastrarAluno(new Aluno("Mariana", "67890", "456"));
        sistema.cadastrarDisciplina(new Disciplina("Matemática", 10));
        sistema.cadastrarDisciplina(new Disciplina("Portugues", 5));
        sistema.cadastrarDisciplina(new Disciplina("Historia", 2));
        
        SwingUtilities.invokeLater(() -> new LoginView(sistema).setVisible(true));
    }
}
