package main.java.com.example.PucTricula.model;

import java.util.HashMap;
import java.util.Map;

public class Usuario {

    protected String nome;
    protected String senha;
    private Map<String, String[]> usuarios;

    public Usuario(){
        usuarios = new HashMap<>();
        usuarios.put(nome, new String[]{senha});
        
    }


    public String realizarLogin(String login, String password){
        if (usuarios.containsKey(login) && usuarios.get(login)[0].equals(password)) {
            return usuarios.get(login)[1];
        }
        return null;
    }

    public String getNome() {
        return nome;
    }

    }




