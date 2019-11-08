package org.academiadecodigo.thunderstructs.Operations;

import org.academiadecodigo.thunderstructs.Game.QuizPreparator;

import java.net.Socket;

public class OperationFactory {

    public void execute(int action, Socket[] playerSockets) {
        switch (action) {
            case 1:
                new QuizPreparator(playerSockets).execute();
                break;
            case 2:
                new SaveScore();
                break;
            case 3:
                new ShowScore();
                break;
        }
    }
}
