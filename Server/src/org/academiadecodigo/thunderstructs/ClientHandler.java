package org.academiadecodigo.thunderstructs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private Socket clientSocket;
    private BufferedReader bReader;
    private PlayerGenerator playerGenerator;
    private Player player;


    public ClientHandler (Player player) {

        this.player = player;
        this.clientSocket = player.getPlayerSocket();
        this.playerGenerator = new PlayerGenerator(player);

        try {
            this.bReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
    }

    public void loginOption () {
        try {

            String clientOption = bReader.readLine();//1
            int clientOption2 = Integer.parseInt(clientOption);
            System.out.println(clientOption);//1

            playerGenerator.generatePlayer(clientOption2, player);
            System.out.println(player.getPlayerName());

        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    @Override
    public void run () {

        loginOption();
    }
}
