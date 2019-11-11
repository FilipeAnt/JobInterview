package org.academiadecodigo.thunderstructs.Menus;

import org.academiadecodigo.thunderstructs.ClientAppMenu;
import org.academiadecodigo.thunderstructs.Utility.Messages;

public class ClientQuizMenu extends ClientAppMenu {


    public int promptQuizAnswer(String question, String[] answerOptions) {
        return this.menuPrompt(question, answerOptions);
    }
}
