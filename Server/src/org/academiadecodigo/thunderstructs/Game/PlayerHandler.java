package org.academiadecodigo.thunderstructs.Game;

import java.net.Socket;

public class PlayerHandler {

    private Socket[] players;
    private int actions = 0;
    private OperationFactory operations;

    public PlayerHandler(Socket[] players) {
        this.players = players;
        this.operations = new OperationFactory();
    }

    @Override
    public void run() {
        actions++;
        operations.execute(actions, players);
    }
}
