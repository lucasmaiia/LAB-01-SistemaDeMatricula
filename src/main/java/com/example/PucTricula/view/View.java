package main.java.com.example.PucTricula.view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.java.com.example.PucTricula.model.Aluno;
import main.java.com.example.PucTricula.model.Disciplina;
import main.java.com.example.PucTricula.model.Usuario;

class View {
    private static final String FILE_USUARIOS = "usuarios.csv";
    private static final String FILE_DISCIPLINAS = "disciplinas.csv";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Usuario> usuarios = carregarUsuarios();
        List<Disciplina> disciplinas = carregarDisciplinas();
        
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
                    for (int i = 0; i < usuarios.size(); i++) {
                        if (usuarios.get(i) instanceof Aluno) {
                            System.out.println(i + ". " + usuarios.get(i).getNome());
                        }
                    }
                    int alunoIndex = scanner.nextInt();
                    scanner.nextLine();
                    if (alunoIndex >= 0 && alunoIndex < usuarios.size() && usuarios.get(alunoIndex) instanceof Aluno) {
                        Aluno alunoSelecionado = (Aluno) usuarios.get(alunoIndex);
                        System.out.println("Selecione uma disciplina:");
                        for (int i = 0; i < disciplinas.size(); i++) {
                            System.out.println(i + ". " + disciplinas.get(i).getNome());
                        }
                        int disciplinaIndex = scanner.nextInt();
                        scanner.nextLine();
                        if (disciplinaIndex >= 0 && disciplinaIndex < disciplinas.size()) {
                            alunoSelecionado.realizarMatricula(disciplinas.get(disciplinaIndex));
                            salvarUsuarios(usuarios);
                        }
                    }
                    break;
                    case 5:
                    System.out.println("\nDisciplinas cadastradas e seus alunos:");
                    for (Disciplina d : disciplinas) {
                        System.out.println("- " + d.getNome() + " (Créditos: " + d.getCreditos() + ")");
                        if (d.getAlunosMatriculados().isEmpty()) {
                            System.out.println("  Nenhum aluno matriculado.");
                        } else {
                            System.out.println("  Alunos matriculados:");
                            for (Aluno alunoMatriculado : d.getAlunosMatriculados()) {
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
}
