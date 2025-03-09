package main.java.com.example.PucTricula.model;

import java.util.ArrayList;
import java.util.List;

public class Disciplina {
    private String nome;
    private int creditos;
    private Professor professor;

    private List<Aluno> alunosMatriculados;

    public Disciplina(String nome, int creditos) {
        this.nome = nome;
        this.creditos = creditos;
        this.alunosMatriculados = new ArrayList<>();
    }

    
    public int getCreditos() {
        return creditos;
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

    public void atribuirProfessor(Professor professor) {
        this.professor = professor;
    }

    public Professor getProfessor() {
        return professor;
    }

}