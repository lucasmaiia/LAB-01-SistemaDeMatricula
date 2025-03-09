package main.java.com.example.PucTricula.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Disciplina {
    private String nome;
    private int creditos;
    private Professor professor;
    private double custo;


    private LocalDate dataLimiteMatricula;

    private List<Aluno> alunosMatriculados;

    public Disciplina(String nome, int creditos, LocalDate dataLimiteMatricula, double custo) {
        this.nome = nome;
        this.creditos = creditos;
        this.alunosMatriculados = new ArrayList<>();
        this.dataLimiteMatricula = dataLimiteMatricula;
        this.custo = custo;
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
        if (alunosMatriculados.remove(aluno)) {
            System.out.println("Matrícula de " + aluno.getNome() + " na disciplina " + nome + " foi cancelada com sucesso.");
        } else {
            System.out.println("O aluno " + aluno.getNome() + " não está matriculado nesta disciplina.");
        }
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

    public double getCusto() {
        return custo;
    }


    public void setCusto(double custo) {
        this.custo = custo;
    }

}