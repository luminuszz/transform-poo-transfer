package cms;

import java.util.ArrayList;
import java.util.Scanner;

public class cms {
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";
    
    private static boolean isLoggedIn = false;
    private static ArrayList<String> articles = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            if (!isLoggedIn) {
                exibirMenuInicial();
            } else {
                exibirMenuPrincipal();
            }
        }
    }

    private static void exibirMenuInicial() {
        System.out.println("Menu Inicial:");
        System.out.println("1. Login");
        System.out.println("2. Listar Conteúdos");
        System.out.println("3. Sair");
        System.out.print("Escolha uma opção: ");
        
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        switch (opcao) {
            case 1:
                fazerLogin();
                break;
            case 2:
                listarConteudos();
                break;
            case 3:
                System.exit(0);
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private static void exibirMenuPrincipal() {
        System.out.println("Menu Principal:");
        System.out.println("1. Criar Conteúdo");
        System.out.println("2. Listar Conteúdos");
        System.out.println("3. Atualizar Conteúdo");
        System.out.println("4. Excluir Conteúdo");
        System.out.println("5. Logout");
        System.out.print("Escolha uma opção: ");
        
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        switch (opcao) {
            case 1:
                criarConteudo();
                break;
            case 2:
                listarConteudos();
                break;
            case 3:
                atualizarConteudo();
                break;
            case 4:
                excluirConteudo();
                break;
            case 5:
                isLoggedIn = false;
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private static void fazerLogin() {
        System.out.print("Usuário: ");
        String username = scanner.nextLine();
        System.out.print("Senha: ");
        String password = scanner.nextLine();

        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            isLoggedIn = true;
            System.out.println("Login realizado com sucesso!");
        } else {
            System.out.println("Usuário ou senha inválidos.");
        }
    }

    private static void criarConteudo() {
        System.out.print("Título do conteúdo: ");
        String titulo = scanner.nextLine();
        articles.add(titulo);
        System.out.println("Conteúdo criado com sucesso!");
    }

    private static void listarConteudos() {
        if (articles.isEmpty()) {
            System.out.println("Nenhum conteúdo disponível.");
        } else {
            System.out.println("Conteúdos:");
            for (int i = 0; i < articles.size(); i++) {
                System.out.println((i + 1) + ". " + articles.get(i));
            }
        }
    }

    private static void atualizarConteudo() {
        listarConteudos();
        if (articles.isEmpty()) return;

        System.out.print("Escolha o número do conteúdo a ser atualizado: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine(); // Consumir a quebra de linha

        if (index >= 0 && index < articles.size()) {
            System.out.print("Novo título do conteúdo: ");
            String novoTitulo = scanner.nextLine();
            articles.set(index, novoTitulo);
            System.out.println("Conteúdo atualizado com sucesso!");
        } else {
            System.out.println("Índice inválido.");
        }
    }

    private static void excluirConteudo() {
        listarConteudos();
        if (articles.isEmpty()) return;

        System.out.print("Escolha o número do conteúdo a ser excluído: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine(); // Consumir a quebra de linha

        if (index >= 0 && index < articles.size()) {
            articles.remove(index);
            System.out.println("Conteúdo excluído com sucesso!");
        } else {
            System.out.println("Índice inválido.");
        }
    }
}
