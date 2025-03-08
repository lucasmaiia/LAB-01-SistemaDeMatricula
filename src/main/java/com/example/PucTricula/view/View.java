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
import main.java.com.example.PucTricula.model.Usuario;

class View {
    private static final String FILE_NAME = "usuarios.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Usuario> usuarios = carregarUsuarios();
        
        while (true) {
            System.out.println("\n--- Sistema de Matrículas Universitário ---");
            System.out.println("1. Cadastrar Aluno");
            System.out.println("2. Listar Usuários");
            System.out.println("3. Sair");
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
}