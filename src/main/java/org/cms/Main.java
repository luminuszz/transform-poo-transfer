package org.cms;

import org.cms.enums.Role;
import org.cms.service.contract.ArticleService;
import org.cms.service.contract.UserService;
import org.cms.service.impl.ArticleServiceImpl;
import org.cms.service.impl.UserServiceImpl;
import org.cms.view.TerminalView;

public class Main {
    public static void main(String[] args) {

        try {
            ArticleService articleService = new ArticleServiceImpl();
            UserService userService = new UserServiceImpl();

            userService.createUser("admin", "admin", Role.ADMIN);

            TerminalView terminalView = new TerminalView(articleService, userService);

            terminalView.init();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
