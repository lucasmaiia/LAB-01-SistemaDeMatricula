package main.java.com.example.PucTricula.controller;

import main.java.com.example.PucTricula.model.Aluno;
import main.java.com.example.PucTricula.model.Disciplina;


class AlunoController {
    public void matricularAluno(Aluno aluno, Disciplina disciplina) {
        if (aluno.realizarMatricula(disciplina)) {
            System.out.println("Matrícula realizada com sucesso para " + aluno.getNome());
        } else {
            System.out.println("Falha na matrícula.");
        }
    }

    public void cancelarMatricula(Aluno aluno, Disciplina disciplina) {
        aluno.cancelarMatricula(disciplina);
    }
}