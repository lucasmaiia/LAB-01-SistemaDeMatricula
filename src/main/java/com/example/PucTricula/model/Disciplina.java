package main.java.com.example.PucTricula.model;

import java.util.ArrayList;
import java.util.List;

public class Disciplina {
    private String nome;
    private int creditos;
    private int maxAlunos = 60;
    private List<Aluno> alunosMatriculados = new ArrayList<>();

    public Disciplina(String nome, int creditos){
        this.nome = nome;
        this.creditos = creditos;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Aluno> getAlunosMatriculados() {
        return alunosMatriculados;
    }

    public boolean matricularAluno(Aluno aluno) {
        if (alunosMatriculados.size() < maxAlunos) {
            alunosMatriculados.add(aluno);
            return true;
        }
        return false;
    }

    public String listarAlunos() {
        StringBuilder lista = new StringBuilder("Alunos matriculados em " + nome + ":\n");
        for (Aluno aluno : alunosMatriculados) {
            lista.append(aluno.getNome()).append("\n");
        }
        return lista.toString();
    }

}
