package main.java.com.example.PucTricula.controller;

import main.java.com.example.PucTricula.model.Professor;

class ProfessorController {
    public void visualizarAlunos(Professor professor) {
        professor.visualizarAlunos();
    }

    public void consultarHorarios(Professor professor) {
        professor.consultarHorarios();
    }
}