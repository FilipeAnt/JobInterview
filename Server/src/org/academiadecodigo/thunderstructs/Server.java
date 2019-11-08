package org.academiadecodigo.thunderstructs;

import org.academiadecodigo.thunderstructs.Game.PlayerHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {


    private final int PLAYERS_PER_GAME = 2;
    private ServerSocket serverSocket;
    private Socket[] playerSockets;
    private ExecutorService clientsThreads;

    public Server() {

        try {
            clientsThreads = Executors.newCachedThreadPool();
            serverSocket = new ServerSocket(6565);
            this.playerSockets = new Socket[PLAYERS_PER_GAME];

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {

        try {
            int playersCounter = 0;
            while (true) {
                System.out.println("Waiting for players...");
                playerSockets[playersCounter] = serverSocket.accept();
                System.out.println(playerSockets[playersCounter].getInetAddress().getAddress() + "connected");
                playersCounter++;
                if (playersCounter % 2 == 0) {
                    clientsThreads.submit(new PlayerHandler(playerSockets));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
