package main.java.com.example.PucTricula.model;

import java.util.ArrayList;
import java.util.List;

public class SistemaMatricula {
    private List<Aluno> alunos = new ArrayList<>();
    private List<Disciplina> disciplinas = new ArrayList<>();
    
    public void cadastrarAluno(Aluno aluno) {
        alunos.add(aluno);
    }
    
    public void cadastrarDisciplina(Disciplina disciplina) {
        disciplinas.add(disciplina);
    }
    
    public boolean autenticarAluno(String matricula, String senha) {
        for (Aluno aluno : alunos) {
            if (aluno.getNome().equals(matricula) && aluno.autenticar(senha)) {
                return true;
            }
        }
        return false;
    }
    
    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }
    
    public List<Aluno> getAlunos() {
        return alunos;
    }
}
