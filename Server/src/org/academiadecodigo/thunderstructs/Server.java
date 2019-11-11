package org.academiadecodigo.thunderstructs;

import org.academiadecodigo.thunderstructs.Utilitary.ServerConfiguration;
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


    public static void main(String[] args) {

        Server gameServer = new Server();
        gameServer.start();
    }


    public Server() {

        try {

            clientsThreadPool = Executors.newCachedThreadPool();
            serverSocket = new ServerSocket(ServerConfiguration.PORT_NUMBER);
            this.onlinePlayers = new LinkedList<>();
            clientsThreadPool.submit(new RoomManager());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void start() {

        try {
            while (serverSocket.isBound()) {

                Socket clientSocket = serverSocket.accept();

                String playerName = "Temp" + playerCounter;
                onlinePlayers.add( new Player(playerName, clientSocket));
                clientsThreadPool.submit(new ClientHandler(onlinePlayers.getLast()));

                playerCounter++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
