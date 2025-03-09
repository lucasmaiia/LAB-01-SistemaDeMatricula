package main.java.com.example.PucTricula.view;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.java.com.example.PucTricula.model.Aluno;
import main.java.com.example.PucTricula.model.Disciplina;
import main.java.com.example.PucTricula.model.Professor;
import main.java.com.example.PucTricula.model.Usuario;

class View {
    private static final String FILE_USUARIOS = "usuarios.csv";
    private static final String FILE_DISCIPLINAS = "disciplinas.csv";
    private static final String FILE_MATRICULAS = "matriculas.csv";

    private static void salvarUsuarios(List<Usuario> usuarios) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_USUARIOS))) {
            for (Usuario u : usuarios) {
                writer.write(u.getNome() + "," + u.getEmail() + "," + u.getSenha());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar usuários: " + e.getMessage());
        }
    }

    private static List<Usuario> carregarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_USUARIOS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                usuarios.add(new Aluno(dados[0], dados[1], dados[2]));
            }
        } catch (IOException e) {
            System.out.println("Nenhum usuário encontrado, iniciando lista vazia.");
        }
        return usuarios;
    }

    public static void salvarDisciplinas(List<Disciplina> disciplinas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_DISCIPLINAS))) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            for (Disciplina d : disciplinas) {
                writer.write(d.getNome() + "," + d.getCreditos() + "," + d.getCusto() + "," + d.getDataLimiteMatricula().format(formatter));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar disciplinas: " + e.getMessage());
        }
    }

    public static List<Disciplina> carregarDisciplinas() {
        List<Disciplina> disciplinas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_DISCIPLINAS))) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                disciplinas.add(
                        new Disciplina(dados[0], Integer.parseInt(dados[1]), LocalDate.parse(dados[2], formatter), 0));
            }
        } catch (IOException e) {
            System.out.println("Nenhuma disciplina encontrada, iniciando lista vazia.");
        }
        return disciplinas;
    }

    private static void carregarMatriculas(List<Usuario> usuarios, List<Disciplina> disciplinas) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_MATRICULAS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                String nomeAluno = dados[0];
                String nomeDisciplina = dados[1];

                Aluno aluno = (Aluno) usuarios.stream()
                        .filter(u -> u instanceof Aluno && u.getNome().equals(nomeAluno))
                        .findFirst()
                        .orElse(null);

                Disciplina disciplina = disciplinas.stream()
                        .filter(d -> d.getNome().equals(nomeDisciplina))
                        .findFirst()
                        .orElse(null);

                if (aluno != null && disciplina != null) {
                    disciplina.matricularAluno(aluno);
                }
            }
        } catch (IOException e) {
            System.out.println("Nenhuma matrícula encontrada, iniciando lista vazia.");
        }
    }

    private static void salvarMatricula(String nomeAluno, String nomeDisciplina) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_MATRICULAS, true))) {
            writer.write(nomeAluno + "," + nomeDisciplina);
            writer.newLine();
        } catch (IOException e) {

            System.out.println("Erro ao salvar matrícula: " + e.getMessage());
        }
    }
    public static void calcularMensalidades(List<Usuario> usuarios, List<Disciplina> disciplinas) {
        System.out.println("\n--- Sistema de Cobrança ---");
        for (Usuario u : usuarios) {
            if (u instanceof Aluno) {
                Aluno aluno = (Aluno) u;
                double totalMensalidade = 0;
                for (Disciplina d : disciplinas) {
                    if (d.getAlunosMatriculados().contains(aluno)) {
                        totalMensalidade += d.getCusto();
                    }
                }
                System.out.println("Aluno: " + aluno.getNome() + " - Mensalidade: R$ " + totalMensalidade);
            }
        }
    }
}