package org.cms.view;

import org.cms.entity.User;
import org.cms.enums.Role;
import org.cms.service.contract.ArticleService;
import org.cms.service.contract.UserService;
import org.cms.service.dto.CreateArticleDto;
import org.cms.service.dto.UpdateArticle;

import java.util.Scanner;

public class TerminalView {
    private final  Scanner scanner;
    private final UserService usersService;
    private final ArticleService articleService;
    private  User loggedUser;


    public boolean isLoggedIn() {
        return this.loggedUser != null;
    }

    public TerminalView(ArticleService articleService, UserService usersService) {
        this.usersService = usersService;
        this.articleService = articleService;
        this.scanner = new Scanner(System.in);
    }

    public void init() {
        while (true) {
            if (!isLoggedIn()) {
                showInitialMenu();
            } else {
                showLoggedMenu();
            }
        }
    }

    private void makeLogin(String email, String password) {
       loggedUser = usersService.login(email, password);
         if (loggedUser == null) {
              System.out.println("Usuário ou senha inválidos.");
         } else {
              System.out.println("Login efetuado com sucesso.");
         }
    }

    private void showContentList() {
        if(articleService.listArticles().isEmpty()) {
            System.out.println("Nenhum artigo encontrado.");
            return;
        }

        articleService.listArticles().forEach(article -> {
            System.out.println("ID: " + article.getId());
            System.out.println("Título: " + article.getTitle());
            System.out.println("Conteúdo: " + article.getContent());
            System.out.println("Autor: " + usersService.getUser(article.getAuthorId()).getEmail());
            System.out.println();
        });
    }

    private void showLoggedMenu() {
        System.out.println("Menu Principal:");
        System.out.println("1. Criar Conteúdo");
        System.out.println("2. Listar Conteúdos");
        System.out.println("3. Atualizar Conteúdo");
        System.out.println("4. Excluir Conteúdo");
        System.out.println("5. Logout");
        System.out.print("Escolha uma opção: ");

        int opcao = scanner.nextInt();

        scanner.nextLine();

        switch (opcao) {
            case 1:
                addArticle();
                break;
            case 2:
               showContentList();
                break;
            case 3:
                updateArticle();
                break;
            case 4:
                deleteArticle();
                break;
            case 5:
                loggedUser = null;
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private void updateArticle() {
       this.showContentList();
        System.out.println("Digite o ID do artigo que deseja atualizar:");
        String articleId = scanner.nextLine();

        var article = articleService.getArticle(articleId);

        if(article == null) {
            System.out.println("Artigo não encontrado.");
             this.updateArticle();
             return;
        }

        System.out.println("Digite o novo título: ");
        String title = scanner.nextLine();
        System.out.println("Digite o novo conteúdo: ");
        String content = scanner.nextLine();

        articleService.updateArticle(new UpdateArticle(articleId, title, content));
        System.out.println("Artigo atualizado com sucesso.");
    }

    private void addArticle() {
        System.out.println("Digite o título: ");
        String title = scanner.nextLine();
        System.out.println("Digite o conteúdo: ");
        String content = scanner.nextLine();
        articleService.createArticle(new CreateArticleDto(title, content, loggedUser.getId()));
        System.out.println("Artigo criado com sucesso.");
    }

    private void deleteArticle() {
        this.showContentList();
        System.out.println("Digite o ID do artigo que deseja excluir: ");
        String articleId = scanner.nextLine();
        articleService.deleteArticle(articleId);
        System.out.println("Artigo excluído com sucesso.");
    }


    private void showInitialMenu() {
        System.out.println("Menu Inicial:");
        System.out.println("1. Login");
        System.out.println("2. Listar Conteúdos");
        System.out.println("3. Sair");
        System.out.print("Escolha uma opção: ");

        int opcao = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        switch (opcao) {
            case 1:
                System.out.println("Digite o email: ");
                String email = scanner.nextLine();
                System.out.println("Digite a senha: ");
                String password = scanner.nextLine();
                makeLogin(email, password);
                break;
            case 2:
                showContentList();
                break;
            case 3:
                System.exit(0);
                scanner.close();
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }




}
