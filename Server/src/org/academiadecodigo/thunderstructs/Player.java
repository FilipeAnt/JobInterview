package org.academiadecodigo.thunderstructs;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class Player {


    private String playerName;
    private int totalJobs;
    private int totalPoints;
    private int pointsLastGame;
    private int pointsLastSession;
    private int roundPoints;
    private Socket playerSocket;
    private boolean isReady = false;


    public Player (String playerName, Socket socket) {

        this.playerName = playerName;
        this.totalJobs = 0;
        this.totalPoints = 0;
        this.pointsLastGame = 0;
        this.pointsLastSession = 0;
        this.playerSocket = socket;
    }

    public Player (String[] playerData) {

        this.playerName = playerData[0];
        addRegisterData(playerData);
    }

    public void addRegisterData (String[] playerData) {

        int dataIndex = 1;

        totalJobs = Integer.parseInt(playerData[dataIndex++]);
        pointsLastGame = Integer.parseInt(playerData[dataIndex++]);
        pointsLastSession = Integer.parseInt(playerData[dataIndex++]);
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getTotalJobs() {
        return totalJobs;
    }

    public int getPointsLastGame() {
        return pointsLastGame;
    }

    public int getPointsLastSession() {
        return pointsLastSession;
    }

    public Socket getPlayerSocket() {
        return playerSocket;
    }

    public boolean isPlayerConnected() {

        return playerSocket.isClosed();
    }

    public void setRoundPoints(int roundPoints) {
        this.roundPoints = roundPoints;
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
}
