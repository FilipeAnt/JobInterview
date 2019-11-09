package org.academiadecodigo.thunderstructs;

public class ClientQuizMenu extends ClientAppMenu {

    private Decryptor decryptor;

    public ClientQuizMenu () {

        this.decryptor = new Decryptor();
    }

    public int promptQuizAnswer (String question, String[] answerOptions) {

        String finalQuestion = question + "\n\n" + Messages.BAR + Messages.BAR;
        return this.menuPrompt(question, answerOptions);
    }
}
