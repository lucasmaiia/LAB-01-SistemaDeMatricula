package main.java.com.example.PucTricula.model;

import java.util.ArrayList;
import java.util.List;

public class Curso {
    private String nome;
    private int creditos;
    private List<Disciplina> disciplinas;

    public Curso(String nome, int creditos){

        this.nome = nome;
        this.creditos = creditos;
        this.disciplinas = new ArrayList<>();

    }

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
