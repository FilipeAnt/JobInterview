package org.academiadecodigo.thunderstructs;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

public class ClientAppMenu {

    private Prompt prompt;

    public ClientAppMenu () {

        this.prompt = new Prompt(System.in, System.out);
    }

    public int integerPrompt(String message) {

        IntegerInputScanner question = new IntegerInputScanner();
        question.setMessage(message);

        return prompt.getUserInput(question);
    }

    public String stringPrompt(String message) {

        return "a";
    }

    public int menuPrompt(String message, String[] menuOptions) {

        MenuInputScanner scanner = new MenuInputScanner(menuOptions);
        scanner.setMessage(message);

        return prompt.getUserInput(scanner);
    }

}
