package org.academiadecodigo.thunderstructs;

import java.net.Socket;

public class Player {


    private String playerName;
    private Socket playerSocket;
    private boolean isReady = false;
    private int roundPoints;


    public Player (String playerName, Socket socket) {

        this.playerName = playerName;
        this.playerSocket = socket;
        this.roundPoints = -1;
    }


    public String getPlayerName() {
        return playerName;
    }


    public Socket getPlayerSocket() {
        return playerSocket;
    }

    public boolean isPlayerConnected() {

        return playerSocket.isClosed();
    }


    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setIsReady(boolean setter){
        this.isReady = setter;
    }

    public boolean getIsReady(){
        return this.isReady;
    }

    public int getRoundPoints() {
        return roundPoints;
    }

    public void setRoundPoints(int roundPoints) {
        this.roundPoints = roundPoints;
    }
}
