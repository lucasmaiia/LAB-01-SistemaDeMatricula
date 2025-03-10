package main.java.com.example.PucTricula.model;

import java.io.Serializable;

public abstract class Usuario implements Serializable {
    protected static String tipo;
    private String nome;
    private String email;
    private String senha;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Usuario(String tipo, String nome, String email, String senha) {
        this.tipo = tipo;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean validarSenha(String senhaDigitada) {
        return this.senha.equals(senhaDigitada);
    }
}