package main.java.com.example.PucTricula.controller;

import main.java.com.example.PucTricula.model.Disciplina;

import main.java.com.example.PucTricula.model.Curso;

class CursoController {
    public void adicionarDisciplina(Curso curso, Disciplina disciplina) {
        curso.adicionarDisciplina(disciplina);
    }
}
