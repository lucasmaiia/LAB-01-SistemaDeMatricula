package main.java.com.example.PucTricula.controller;

import main.java.com.example.PucTricula.model.Administrador;

class AdministradorController {
    public void gerarCurriculoSemestral(Administrador administrador) {
        administrador.gerarCurriculoSemestral();
    }

    public void atualizarInformacoes(Administrador administrador) {
        administrador.atualizarInformacoes();
    }
}