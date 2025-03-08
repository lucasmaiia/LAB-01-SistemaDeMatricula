package main.java.com.example.PucTricula.model;

import java.util.ArrayList;
import java.util.List;

class Disciplina {
    private String nome;
    private int creditos;
    private List<Aluno> alunosMatriculados;

    public Disciplina(String nome, int creditos) {
        this.nome = nome;
        this.creditos = creditos;
        this.alunosMatriculados = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public List<Aluno> getAlunosMatriculados() {
        return alunosMatriculados;
    }

    public boolean matricularAluno(Aluno aluno) {
        if (alunosMatriculados.size() < 60) {
            alunosMatriculados.add(aluno);
            return true;
        }
        return false;
    }

    public void cancelarMatricula(Aluno aluno) {
        alunosMatriculados.remove(aluno);
    }

    public boolean verificarAtivacao() {
        return alunosMatriculados.size() >= 3;
    }
}