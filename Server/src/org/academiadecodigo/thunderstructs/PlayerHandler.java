package org.academiadecodigo.thunderstructs;

import org.academiadecodigo.thunderstructs.Operations.OperationFactory;

import java.net.Socket;

public class PlayerHandler implements Runnable {

    private Player[] players;
    private int actions = 0;
    private OperationFactory operations;

    public PlayerHandler(Player[] players) {
        this.players = players;
        this.operations = new OperationFactory();
    }

    @Override
    public void run() {
        actions++;
        operations.execute(actions, players);
    }
}
