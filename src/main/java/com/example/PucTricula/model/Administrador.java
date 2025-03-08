package main.java.com.example.PucTricula.model;

public class Administrador extends Usuario {
    public Administrador(String nome, String email, String senha) {
        super(nome, email, senha);
    }

    public void gerarCurriculoSemestral() {
        System.out.println("Gerando currículo para o semestre...");
    }

    public void atualizarInformacoes() {
        System.out.println("Atualizando informações do sistema...");
    }
}
