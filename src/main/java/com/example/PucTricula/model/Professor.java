package main.java.com.example.PucTricula.model;

import java.util.ArrayList;
import java.util.List;

public class Professor extends Usuario {
    private List<Disciplina> disciplinasLecionadas;

    public Professor(String nome, String email, String senha) {
        super(tipo = "professor", nome, email, senha);
        this.disciplinasLecionadas = new ArrayList<>();
    }

    public void adicionarDisciplina(Disciplina disciplina) {
        disciplinasLecionadas.add(disciplina);
    }

    public void consultarHorarios() {
        System.out.println("Horários das disciplinas lecionadas por " + getNome() + ":");
        for (Disciplina d : disciplinasLecionadas) {
            System.out.println("- " + d.getNome());
        }
    }

    public void visualizarAlunos() {
        System.out.println("Alunos matriculados nas disciplinas lecionadas por " + getNome() + ":");
        for (Disciplina d : disciplinasLecionadas) {
            System.out.println("Disciplina: " + d.getNome());
            for (Aluno aluno : d.getAlunosMatriculados()) {
                System.out.println("- " + aluno.getNome());
            }
        }
    }
}
