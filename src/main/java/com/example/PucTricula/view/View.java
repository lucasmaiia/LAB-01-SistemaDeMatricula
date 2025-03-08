package main.java.com.example.PucTricula.view;

import java.io.FileInputStream;
import java.io.FileOutputStream;
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
    private static final String FILE_NAME = "usuarios.txt";
    private static final String DISCIPLINAS_FILE = "disciplinas.txt";

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
                    System.out.println("\nDisciplinas cadastradas:");
                    for (Disciplina d : disciplinas) {
                        System.out.println("- " + d.getNome() + " (Créditos: " + d.getCreditos() + ")");
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
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(usuarios);
        } catch (IOException e) {
            System.out.println("Erro ao salvar usuários: " + e.getMessage());
        }
    }

    private static List<Usuario> carregarUsuarios() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<Usuario>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    private static void salvarDisciplinas(List<Disciplina> disciplinas) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DISCIPLINAS_FILE))) {
            out.writeObject(disciplinas);
        } catch (IOException e) {
            System.out.println("Erro ao salvar disciplinas: " + e.getMessage());
        }
    }

    private static List<Disciplina> carregarDisciplinas() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(DISCIPLINAS_FILE))) {
            return (List<Disciplina>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
}
