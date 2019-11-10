package org.academiadecodigo.thunderstructs;

import org.academiadecodigo.thunderstructs.Utilitary.ServerConfiguration;
import org.academiadecodigo.thunderstructs.Utilitary.ServerMessage;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {


    private int playerCounter;
    private ServerSocket serverSocket;
    private Socket[] playerSockets;
    private ExecutorService clientsThreadPool;
    private int playersPerGame;
    private Player[] fancyPlayers = new Player[2];

    private LinkedList<Player> onlinePlayers;


    public Server() {

        try {

            clientsThreadPool = Executors.newCachedThreadPool();
            playersPerGame = ServerConfiguration.PLAYERS_PER_GAME;
            serverSocket = new ServerSocket(ServerConfiguration.PORT_NUMBER);
            this.playerSockets = new Socket[ServerConfiguration.PLAYERS_PER_GAME];
            this.onlinePlayers = new LinkedList<>();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {

        try {

            clientsThreadPool.submit(new RoomManager(onlinePlayers));

            while (serverSocket.isBound()) {

                System.out.println(ServerMessage.AWAITING_CLIENT_CONNECTION);
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connection accepted");
                String playerName = "Temp" + playerCounter;
                onlinePlayers.add( new Player(playerName, clientSocket));
                clientsThreadPool.submit(new ClientHandler(onlinePlayers.getLast()));
                playerCounter++;

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPlayerIPMessage () {

        return ServerMessage.CONNECTION_WITH + playerSockets[playerCounter].getInetAddress() + ".\n";
    }

    public Player[] getFancyPlayers() {
        return this.fancyPlayers;
    }

    public LinkedList<Player> getOnlinePlayers () {
        return this.onlinePlayers;
    }

    public int getPlayerCounter() {
        return playerCounter;
    }
}
