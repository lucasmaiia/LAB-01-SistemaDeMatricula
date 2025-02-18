package main.java.model;

import java.util.List;

public class Curso {
    private String nome;
    private int creditos;
    private List<Disciplina> disciplinas;

    public int getCreditos() {
        return creditos;
    }
    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }
    public String getNome() {
        return nome;
    }
    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }
    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
}
