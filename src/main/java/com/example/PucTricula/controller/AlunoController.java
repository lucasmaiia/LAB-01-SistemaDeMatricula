package main.java.com.example.PucTricula.controller;

import main.java.com.example.PucTricula.model.Aluno;
import main.java.com.example.PucTricula.model.Disciplina;
import main.java.com.example.PucTricula.model.SistemaMatricula;

import javax.swing.*;
import java.util.List;

public class AlunoController {
    private Aluno aluno;
    private SistemaMatricula sistema;

    public AlunoController(Aluno aluno, SistemaMatricula sistema) {
        this.aluno = aluno;
        this.sistema = sistema;
    }

    public void matricularEmDisciplina(JFrame parent) {
        List<Disciplina> disciplinasDisponiveis = sistema.getDisciplinas();

        if (disciplinasDisponiveis.isEmpty()) {
            JOptionPane.showMessageDialog(parent, "Nenhuma disciplina disponível para matrícula.");
            return;
        }

        String[] nomesDisciplinas = disciplinasDisponiveis.stream()
                .map(Disciplina::getNome)
                .toArray(String[]::new);

        String disciplinaSelecionada = (String) JOptionPane.showInputDialog(
                parent,
                "Escolha uma disciplina para se matricular:",
                "Matrícula",
                JOptionPane.PLAIN_MESSAGE,
                null,
                nomesDisciplinas,
                nomesDisciplinas[0]
        );

        if (disciplinaSelecionada != null) {
            for (Disciplina disciplina : disciplinasDisponiveis) {
                if (disciplina.getNome().equals(disciplinaSelecionada)) {
                    if (aluno.adicionarDisciplina(disciplina)) {
                        JOptionPane.showMessageDialog(parent, "Matrícula realizada com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(parent, "Não foi possível se matricular nesta disciplina.");
                    }
                    break;
                }
            }
        }
    }

    public void mostrarDisciplinas(JFrame parent) {
        List<Disciplina> minhasDisciplinas = aluno.getDisciplinas();
        if (minhasDisciplinas.isEmpty()) {
            JOptionPane.showMessageDialog(parent, "Você não está matriculado em nenhuma disciplina.");
        } else {
            StringBuilder lista = new StringBuilder("Suas disciplinas:\n");
            for (Disciplina disciplina : minhasDisciplinas) {
                lista.append(disciplina.getNome()).append("\n");
            }
            JOptionPane.showMessageDialog(parent, lista.toString());
        }
    }
}
