package main.java.com.example.PucTricula.service;

import main.java.com.example.PucTricula.controller.AlunoController;
import main.java.com.example.PucTricula.model.Aluno;
import main.java.com.example.PucTricula.model.SistemaMatricula;
import main.java.com.example.PucTricula.service.LoginView;

import javax.swing.*;
import java.awt.*;

public class AlunoView extends JFrame {
    private AlunoController controller;

    public AlunoView(Aluno aluno, SistemaMatricula sistema) {
        this.controller = new AlunoController(aluno, sistema);

        setTitle("Área do Aluno");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JButton matricularButton = new JButton("Matricular-se em uma Disciplina");
        JButton verDisciplinasButton = new JButton("Minhas Disciplinas");
        JButton sairButton = new JButton("Sair");

        matricularButton.addActionListener(e -> controller.matricularEmDisciplina(this));
        verDisciplinasButton.addActionListener(e -> controller.mostrarDisciplinas(this));

        sairButton.addActionListener(e -> {
            new LoginView(sistema).setVisible(true);
            dispose();
        });

        add(matricularButton);
        add(verDisciplinasButton);
        add(sairButton);
    }
}
