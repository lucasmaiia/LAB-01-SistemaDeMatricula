package main.java.com.example.PucTricula.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Disciplina {
    private String nome;
    private int creditos;
    private Professor professor;
    private LocalDate dataLimiteMatricula;

    private List<Aluno> alunosMatriculados;

    public Disciplina(String nome, int creditos, LocalDate dataLimiteMatricula) {
        this.nome = nome;
        this.creditos = creditos;
        this.alunosMatriculados = new ArrayList<>();
        this.dataLimiteMatricula = dataLimiteMatricula;
    }

    
    public int getCreditos() {
        return creditos;
    }

    public String getNome() {
        return nome;
    }

    public List<Aluno> getAlunosMatriculados() {
        return alunosMatriculados;
    }

    public boolean matricularAluno(Aluno aluno) {
        if(LocalDate.now().isAfter(dataLimiteMatricula) || alunosMatriculados.size() > 60){
            System.out.println("Erro. O período de matrícula já foi encerrado!");
            return false;
        }else{
            alunosMatriculados.add(aluno);
            System.out.println("Aluno "+aluno.getNome() + " matriculado com sucesso na disciplina " +nome);
            return true;
        }
    }

    public void cancelarMatricula(Aluno aluno) {
        alunosMatriculados.remove(aluno);
    }

    public boolean verificarAtivacao() {
        return alunosMatriculados.size() >= 3;
    }

    public void atribuirProfessor(Professor professor) {
        this.professor = professor;
    }

    public Professor getProfessor() {
        return professor;
    }

    public LocalDate getDataLimiteMatricula() {
        return dataLimiteMatricula;
    }

}