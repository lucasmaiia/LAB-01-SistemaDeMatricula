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

import main.java.com.example.PucTricula.model.Administrador;
import main.java.com.example.PucTricula.model.Aluno;
import main.java.com.example.PucTricula.model.Disciplina;
import main.java.com.example.PucTricula.model.Professor;
import main.java.com.example.PucTricula.model.Usuario;

class View {

    private static final String FILE_USUARIOS = "usuarios.csv";
    private static final String FILE_DISCIPLINAS = "disciplinas.csv";
    private static final String FILE_MATRICULAS = "matriculas.csv";
    
    private static List<Usuario> carregarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_USUARIOS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                String tipo = dados[0]; 
                String nome = dados[1];
                String email = dados[2];
                String senha = dados[3];
    
                switch (tipo.toLowerCase()) {
                    case "aluno":
                        usuarios.add(new Aluno(nome, email, senha));
                        break;
                    case "professor":
                        usuarios.add(new Professor(nome, email, senha));
                        break;
                    case "administrador":
                        usuarios.add(new Administrador(nome, email, senha));
                        break;
                    default:
                        System.out.println("Tipo de usuário desconhecido: " + tipo);
                }
            }
        } catch (IOException e) {
            System.out.println("Nenhum usuário encontrado, iniciando lista vazia.");
        }
        return usuarios;
    }
        
    
        public static List<Disciplina> carregarDisciplinas() {
            List<Disciplina> disciplinas = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_DISCIPLINAS))) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String linha;
                while ((linha = reader.readLine()) != null) {
                    String[] dados = linha.split(",");
                    disciplinas.add(
                            new Disciplina(dados[0], Integer.parseInt(dados[1]), LocalDate.parse(dados[3], formatter), 0));
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
    
        public static void cancelarMatricula(List<Usuario> usuarios, List<Disciplina> disciplinas, Scanner scanner) {
            System.out.println("\n--- Cancelamento de Matrícula ---");
            System.out.println(">> Selecione um aluno:");
            List<Aluno> alunos = new ArrayList<>();
            for (Usuario u : usuarios) {
                if (u instanceof Aluno) {
                    alunos.add((Aluno) u);
                }
            }
            for (int i = 0; i < alunos.size(); i++) {
                System.out.println(i + ". " + alunos.get(i).getNome());
            }
            int alunoIndex = scanner.nextInt();
            scanner.nextLine();
            
            if (alunoIndex >= 0 && alunoIndex < alunos.size()) {
                Aluno alunoSelecionado = alunos.get(alunoIndex);
                System.out.println(">> Selecione uma disciplina:");
                for (int i = 0; i < disciplinas.size(); i++) {
                    System.out.println(i + ". " + disciplinas.get(i).getNome());
                }
                int disciplinaIndex = scanner.nextInt();
                scanner.nextLine();
                
                if (disciplinaIndex >= 0 && disciplinaIndex < disciplinas.size()) {
                    disciplinas.get(disciplinaIndex).cancelarMatricula(alunoSelecionado);
                }
            }
        }
    
        public static void visualizarDisciplinasProfessor(List<Usuario> usuarios, List<Disciplina> disciplinas, Scanner scanner) {
            System.out.println("\n--- Grade do Professor ---");
            System.out.println(">> Selecione um professor:");
            List<Professor> professores = new ArrayList<>();
            for (Usuario u : usuarios) {
                if (u instanceof Professor) {
                    professores.add((Professor) u);
                }
            }
            for (int i = 0; i < professores.size(); i++) {
                System.out.println(i + ". " + professores.get(i).getNome());
            }
            int professorIndex = scanner.nextInt();
            scanner.nextLine();
            
            if (professorIndex >= 0 && professorIndex < professores.size()) {
                Professor professorSelecionado = professores.get(professorIndex);
                System.out.println(">> Disciplinas ministradas por " + professorSelecionado.getNome() + ":\n");
                boolean encontrouDisciplina = false;
                for (Disciplina d : disciplinas) {
                    if (d.getProfessor() != null && d.getProfessor().equals(professorSelecionado)) {
                        System.out.println("- " + d.getNome());
                        encontrouDisciplina = true;
                    }
                }
                if (!encontrouDisciplina) {
                    System.out.println(">> Este professor não ministra nenhuma disciplina.\n");
                }
            }
        }
         private static Usuario realizarLogin(List<Usuario> usuarios, Scanner scanner) {
            System.out.print(">> Email: ");
            String email = scanner.nextLine();
            System.out.print(">> Senha: ");
            String senha = scanner.nextLine();
    
            for (Usuario usuario : usuarios) {
                if (usuario.getEmail().equals(email) && usuario.validarSenha(senha)) {
                    return usuario;
                }
            }
            System.out.println("Ops! Login inválido!");
            return null;
        }
}