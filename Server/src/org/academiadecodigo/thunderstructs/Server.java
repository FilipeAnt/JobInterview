package org.academiadecodigo.thunderstructs;

import org.academiadecodigo.thunderstructs.Utilitary.ServerConfiguration;
import org.academiadecodigo.thunderstructs.Utilitary.ServerMessage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {


    private ServerSocket serverSocket;
    private Socket[] playerSockets;
    private ExecutorService clientsThreadPool;
    private int playerCounter;
    private int playersPerGame;


    public Server() {

        try {

            clientsThreadPool = Executors.newCachedThreadPool();
            playersPerGame = ServerConfiguration.PLAYERS_PER_GAME;
            serverSocket = new ServerSocket(ServerConfiguration.PORT_NUMBER);
            this.playerSockets = new Socket[ServerConfiguration.PLAYERS_PER_GAME];

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {

        try {

            while (serverSocket.isBound()) {

                System.out.println(ServerMessage.AWAITING_CLIENT_CONNECTION);

                //playerSockets[playerCounter] = serverSocket.accept();

                Socket clientSocket = serverSocket.accept();
                System.out.println(getPlayerIPMessage());

                clientsThreadPool.submit(new ClientHandler(clientSocket));//TODO: ClientHandler isn't runnable
                playerCounter++;


                if (playerCounter == playersPerGame) {
                    clientsThreadPool.submit(new PlayerHandler(playerSockets)); //TODO: Method
                    System.out.println(ServerMessage.NEW_GAME_STARTED);
                    playerCounter = 0;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPlayerIPMessage () {

        return ServerMessage.CONNECTION_WITH + playerSockets[playerCounter].getInetAddress() + ".\n";
    }
}
