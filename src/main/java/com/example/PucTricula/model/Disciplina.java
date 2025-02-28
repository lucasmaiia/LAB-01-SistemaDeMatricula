package main.java.com.example.PucTricula.model;

public class Disciplina {
    private String nome;
    private int creditos;

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
}
