package main.java.com.example.PucTricula.service;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;



import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import main.java.com.example.PucTricula.model.Aluno;
import main.java.com.example.PucTricula.model.SistemaMatricula;
import main.java.com.example.PucTricula.model.Disciplina;


class AdminView extends JFrame {
    public AdminView(SistemaMatricula sistema) {
        setTitle("Área do Administrador");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        
        JButton listarAlunosButton = new JButton("Listar Alunos");
        JButton listarDisciplinasButton = new JButton("Listar Disciplinas");
        JButton sairButton = new JButton("Sair");
        
        listarAlunosButton.addActionListener(e -> {
            List<Aluno> alunos = sistema.getAlunos();
            StringBuilder lista = new StringBuilder("Alunos:\n");
            for (Aluno aluno : alunos) {
                lista.append(aluno.getNome()).append("\n");
            }
            JOptionPane.showMessageDialog(this, lista.toString());
        });
        
        listarDisciplinasButton.addActionListener(e -> {
            List<Disciplina> disciplinas = sistema.getDisciplinas();
            if (disciplinas.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nenhuma disciplina cadastrada!");
                return;
            }
        
            // Criando um array de nomes de disciplinas para exibição
            String[] nomesDisciplinas = disciplinas.stream()
                                                   .map(Disciplina::getNome)
                                                   .toArray(String[]::new);
        
            // Exibindo um diálogo para seleção da disciplina
            String disciplinaSelecionada = (String) JOptionPane.showInputDialog(
                this,
                "Escolha uma disciplina:",
                "Disciplinas",
                JOptionPane.PLAIN_MESSAGE,
                null,
                nomesDisciplinas,
                nomesDisciplinas[0]
            );
        
            if (disciplinaSelecionada != null) {
                for (Disciplina disciplina : disciplinas) {
                    if (disciplina.getNome().equals(disciplinaSelecionada)) {
                        JOptionPane.showMessageDialog(this, disciplina.listarAlunos());
                        break;
                    }
                }
            }
        });
        
        
        sairButton.addActionListener(e -> {
            new LoginView(sistema).setVisible(true);
            dispose();
        });
        
        add(listarAlunosButton);
        add(listarDisciplinasButton);
        add(sairButton);
    }
}
