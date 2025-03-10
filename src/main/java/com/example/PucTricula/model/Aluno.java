package main.java.com.example.PucTricula.model;

import main.java.com.example.PucTricula.model.Disciplina;

import java.util.ArrayList;
import java.util.List;

public class Aluno extends Usuario {
    private List<Disciplina> disciplinasMatriculadas;

    public Aluno(String nome, String email, String senha) {
        super(tipo = "aluno",nome, email, senha);
        this.disciplinasMatriculadas = new ArrayList<>();
    }

    public boolean realizarMatricula(Disciplina disciplina) {
        if (disciplinasMatriculadas.size() < 6) {
            disciplinasMatriculadas.add(disciplina);
            System.out.println("Matrícula realizada na disciplina: " + disciplina.getNome());
            return true; 
        } else {
            System.out.println("Limite de disciplinas atingido!");
            return false; 
        }
    }
    

    public void cancelarMatricula(Disciplina disciplina) {
        if (disciplinasMatriculadas.remove(disciplina)) {
            System.out.println("Matrícula cancelada na disciplina: " + disciplina.getNome());
        } else {
            System.out.println("Você não está matriculado nessa disciplina.");
        }
    }

    public void visualizarDisciplinas() {
        System.out.println("Disciplinas matriculadas:");
        for (Disciplina d : disciplinasMatriculadas) {
            System.out.println("- " + d.getNome());
        }
    }
}