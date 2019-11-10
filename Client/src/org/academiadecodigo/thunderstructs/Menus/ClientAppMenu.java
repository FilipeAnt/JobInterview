package org.academiadecodigo.thunderstructs.Menus;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.thunderstructs.Utility.Messages;

public class ClientAppMenu {

    private Prompt prompt;

    public ClientAppMenu () {

        this.prompt = new Prompt(System.in, System.out);
    }

    public int menuPrompt(String message, String[] menuOptions) {
        System.out.println(Messages.BAR + "\n" + Messages.BAR);

        MenuInputScanner scanner = new MenuInputScanner(menuOptions);
        scanner.setMessage(message);

        return prompt.getUserInput(scanner) ;
    }
}
