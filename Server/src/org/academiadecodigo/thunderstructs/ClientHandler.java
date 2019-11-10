package org.academiadecodigo.thunderstructs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private Socket clientSocket;
    private BufferedReader bReader;
    private Server server;
    private PlayerGenerator playerGenerator;
    private Player player;


    public ClientHandler (Player player, Server server) {

        this.player = player;
        this.clientSocket = player.getPlayerSocket();
        this.playerGenerator = new PlayerGenerator(player);

        try {
            this.bReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }


        this.clientSocket = clientSocket;
        this.server = server;
    }

    public void loginOption () {
        try {

            String clientOption = bReader.readLine();//1
            int clientOption2 = Integer.parseInt(clientOption);
            System.out.println(clientOption);//1

            playerGenerator.generatePlayer(clientOption2, player);
            System.out.println(player.getPlayerName());
            //server.getFancyPlayers()[server.getPlayerCounter() - 1] = player;

        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    @Override
    public void run () {

        loginOption();
    }
}
