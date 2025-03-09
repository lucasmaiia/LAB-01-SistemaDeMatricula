package main.java.com.example.PucTricula.application;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.java.com.example.PucTricula.model.Aluno;
import main.java.com.example.PucTricula.model.Disciplina;
import main.java.com.example.PucTricula.model.Usuario;

class PucTriculaApplication {
    private static final String FILE_USUARIOS = "usuarios.csv";
    private static final String FILE_DISCIPLINAS = "disciplinas.csv";
    private static final String FILE_MATRICULAS = "matriculas.csv";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Usuario> usuarios = carregarUsuarios();
        List<Disciplina> disciplinas = carregarDisciplinas();
        carregarMatriculas(usuarios, disciplinas);
        
        while (true) {
            System.out.println("\n--- Sistema de Matrículas Universitário ---");
            System.out.println("1. Cadastrar Aluno");
            System.out.println("2. Listar Usuários");
            System.out.println("3. Cadastrar Disciplina");
            System.out.println("4. Matricular Aluno em Disciplina");
            System.out.println("5. Listar Disciplinas e Alunos Matriculados");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            System.out.println();
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Senha: ");
                    String senha = scanner.nextLine();
                    Usuario aluno = new Aluno(nome, email, senha);
                    usuarios.add(aluno);
                    salvarUsuarios(usuarios);
                    System.out.println("Aluno cadastrado com sucesso!");
                    break;
                case 2:
                    System.out.println("\nUsuários cadastrados:");
                    for (Usuario u : usuarios) {
                        System.out.println("- " + u.getNome() + " (" + u.getEmail() + ")");
                    }
                    break;
                case 3:
                    System.out.print("Nome da Disciplina: ");
                    String nomeDisciplina = scanner.nextLine();
                    System.out.print("Créditos: ");
                    int creditos = scanner.nextInt();
                    scanner.nextLine();
                    disciplinas.add(new Disciplina(nomeDisciplina, creditos));
                    salvarDisciplinas(disciplinas);
                    System.out.println("Disciplina cadastrada com sucesso!");
                    break;
                    case 4:
                    System.out.println("Selecione um aluno:");
                    List<Aluno> listaAlunos = new ArrayList<>();
                    for (Usuario u : usuarios) {
                        if (u instanceof Aluno) {
                            listaAlunos.add((Aluno) u);
                        }
                    }
                    for (int i = 0; i < listaAlunos.size(); i++) {
                        System.out.println(i + ". " + listaAlunos.get(i).getNome());
                    }
                    int alunoIndex = scanner.nextInt();
                    scanner.nextLine();
                    if (alunoIndex >= 0 && alunoIndex < listaAlunos.size()) {
                        Aluno alunoSelecionado = listaAlunos.get(alunoIndex);
                        System.out.println("Selecione uma disciplina:");
                        for (int i = 0; i < disciplinas.size(); i++) {
                            System.out.println(i + ". " + disciplinas.get(i).getNome());
                        }
                        int disciplinaIndex = scanner.nextInt();
                        scanner.nextLine();
                        if (disciplinaIndex >= 0 && disciplinaIndex < disciplinas.size()) {
                            Disciplina disciplinaSelecionada = disciplinas.get(disciplinaIndex);
                            disciplinaSelecionada.matricularAluno(alunoSelecionado);
                            salvarMatricula(alunoSelecionado.getNome(), disciplinaSelecionada.getNome());
                            System.out.println("Matrícula realizada com sucesso!");
                        }
                    }
                    break;
                    case 5:
                    System.out.println("\nDisciplinas cadastradas e seus alunos:");
                    for (Disciplina d : disciplinas) {
                        System.out.println("- " + d.getNome() + " (Créditos: " + d.getCreditos() + ")");
                        List<Aluno> alunosMatriculados = d.getAlunosMatriculados();
                        if (alunosMatriculados.isEmpty()) {
                            System.out.println("  Nenhum aluno matriculado.");
                        } else {
                            System.out.println("  Alunos matriculados:");
                            for (Aluno alunoMatriculado : alunosMatriculados) {
                                System.out.println("    - " + alunoMatriculado.getNome());
                            }
                        }
                    }
                    break;
                case 6:
                    System.out.println("Encerrando sistema...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

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

    private static void salvarDisciplinas(List<Disciplina> disciplinas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_DISCIPLINAS))) {
            for (Disciplina d : disciplinas) {
                writer.write(d.getNome() + "," + d.getCreditos());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar disciplinas: " + e.getMessage());
        }
    }

    private static List<Disciplina> carregarDisciplinas() {
        List<Disciplina> disciplinas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_DISCIPLINAS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                disciplinas.add(new Disciplina(dados[0], Integer.parseInt(dados[1])));
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
}
