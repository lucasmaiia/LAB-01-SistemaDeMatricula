package main.java.com.example.PucTricula.model;

public class Administrador {
    
    private String senhaAdmin = "admin123";
    
    public Administrador(){

    }

    public boolean autenticar(String senha) {
        return senha.equals(senhaAdmin);
    }


    public void gerarCurriculoTrimestral(){

    }

    void atualizarInformacoes(){

    }

}
