package main.java.com.example.PucTricula.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class Aluno extends Usuario{
    private int matricula;
    private List<Disciplina> disciplinas = new ArrayList<>();

    

    public Aluno(String string, String string2, String string3) {
        //TODO Auto-generated constructor stub
    }

    

    public Aluno(Aluno alunoLogado, SistemaMatricula sistema) {
        //TODO Auto-generated constructor stub
    }



    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public int getMatricula() {
        return matricula;
    }

    public boolean autenticar(String senha) {
        return this.senha.equals(senha);
    }

    public boolean adicionarDisciplina(Disciplina disciplina) {
        if (disciplinas.size() < 6) {
            disciplinas.add(disciplina);
            return disciplina.matricularAluno(this);
        }
        return false;
    }


}
 