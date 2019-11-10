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
    private ExecutorService clientsThreadPool;
    private LinkedList<Player> onlinePlayers;


    public Server() {

        try {

            clientsThreadPool = Executors.newCachedThreadPool();
            serverSocket = new ServerSocket(ServerConfiguration.PORT_NUMBER);
            this.onlinePlayers = new LinkedList<>();
            clientsThreadPool.submit(new RoomManager(onlinePlayers));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {

        try {

            while (serverSocket.isBound()) {

                System.out.println(ServerMessage.AWAITING_CLIENT_CONNECTION);
                Socket clientSocket = serverSocket.accept();

                String playerName = "Temp" + playerCounter;
                onlinePlayers.add( new Player(playerName, clientSocket));
                clientsThreadPool.submit(new ClientHandler(onlinePlayers.getLast()));

                System.out.println(getPlayerIPMessage());
                playerCounter++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPlayerIPMessage () {

        return ServerMessage.CONNECTION_WITH + onlinePlayers.getFirst().getPlayerSocket().getInetAddress() + ".\n";
    }

    public LinkedList<Player> getOnlinePlayers () {
        return this.onlinePlayers;
    }

    public int getPlayerCounter() {
        return playerCounter;
    }
}
