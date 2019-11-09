package org.academiadecodigo.thunderstructs;

import java.net.Socket;

public class Player {


    private String playerName;
    private int totalJobs;
    private int pointsLastGame;
    private int pointsLastSession;

    private Socket playerSocket;


    public Player (String playerName) {

        this.playerName = playerName;
        totalJobs = 0;
        pointsLastGame = 0;
        pointsLastSession = 0;
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
}
