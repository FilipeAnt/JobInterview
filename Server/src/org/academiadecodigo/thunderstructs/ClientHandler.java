package org.academiadecodigo.thunderstructs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private Socket clientSocket;
    private BufferedReader bReader;
    private Server server;

    public ClientHandler (Socket clientSocket, Server server) {

        try {
            this.bReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }


        this.clientSocket = clientSocket;
        this.server = server;
    }

    public void loginOption () {
        System.out.println("Teste#1");
        try {

            String clientOption = bReader.readLine();//1
            System.out.println(clientOption);

            PlayerGenerator playerGenerator = new PlayerGenerator(clientOption, clientSocket);
            Player player = playerGenerator.generatePlayer();
            System.out.println(player.getPlayerName());
            server.getFancyPlayers()[Server.playerCounter - 1] = player;
            System.out.println("Player added");

        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    @Override
    public void run () {

        loginOption();
    }
}
