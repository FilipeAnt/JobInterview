package org.academiadecodigo.thunderstructs;

import java.net.Socket;

public class Player {


    private String playerName;
    private int totalJobs;
    private int totalPoints;
    private int pointsLastGame;
    private int pointsLastSession;
    private int roundPoints;
    private Socket playerSocket;


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

    public void setRoundPoints(int roundPoints) {
        this.roundPoints = roundPoints;
    }
}
