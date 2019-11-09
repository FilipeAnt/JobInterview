package org.academiadecodigo.thunderstructs.Operations;

import org.academiadecodigo.thunderstructs.Game.QuizPreparator;
import org.academiadecodigo.thunderstructs.Player;

import java.net.Socket;

public class OperationFactory {

    public void execute(int action, Player[] players) {
        switch (action) {
            case 1:
                System.out.println("Operation Factory");
                System.out.println(players[1].getPlayerName());
                System.out.println(players[0].getPlayerName());
                new QuizPreparator(players).execute();
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
