package main.java.com.example.PucTricula.controller;

import main.java.com.example.PucTricula.model.Aluno;
import main.java.com.example.PucTricula.model.Disciplina;

class DisciplinaController {
    public void matricularAluno(Disciplina disciplina, Aluno aluno) {
        if (disciplina.matricularAluno(aluno)) {
            System.out.println(aluno.getNome() + " matriculado na disciplina " + disciplina.getNome());
        } else {
            System.out.println("Disciplina cheia ou erro na matrícula.");
        }
    }

    public void cancelarMatricula(Disciplina disciplina, Aluno aluno) {
        disciplina.cancelarMatricula(aluno);
    }

    public boolean verificarAtivacao(Disciplina disciplina) {
        return disciplina.verificarAtivacao();
    }
}