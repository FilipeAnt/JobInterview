package org.academiadecodigo.thunderstructs;

public class ClientQuizMenu extends ClientAppMenu {

    Decryptor decryptor;

    public ClientQuizMenu () {

        this.decryptor = new Decryptor();
    }

    public void update (String serverMessage) {

        decryptor.update(serverMessage);
    }

    public int promptQuizAnswer () {

        String question = decryptor.getMessage();
        String[] answerOptions = decryptor.getOptions();

        return this.menuPrompt(question, answerOptions);
    }
}
