package main.java.com.example.PucTricula.model;

import java.util.HashMap;
import java.util.Map;

public class Usuario {

    private Map<String, String[]> usuarios;

    public Usuario(){
        usuarios = new HashMap<>();
        usuarios.put("aluno", new String[]{"123", "aluno"});
        usuarios.put("prof", new String[]{"123", "professor"});
        usuarios.put("admin", new String[]{"123", "admin"});
    }


    public String realizarLogin(String login, String password){
        if (usuarios.containsKey(login) && usuarios.get(login)[0].equals(password)) {
            return usuarios.get(login)[1];
        }
        return null;
    }
    }


