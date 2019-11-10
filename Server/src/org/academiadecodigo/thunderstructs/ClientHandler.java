package org.academiadecodigo.thunderstructs;

import org.academiadecodigo.thunderstructs.Utilitary.Coolness.CoolReader;

import java.net.Socket;

public class ClientHandler implements Runnable {

    private Socket clientSocket;
    private CoolReader cReader;
    private PlayerConfigurator playerGenerator;
    private Player player;


    public ClientHandler (Player player) {

        this.player = player;
        this.clientSocket = player.getPlayerSocket();
        this.playerGenerator = new PlayerConfigurator(player);
        this.cReader = new CoolReader(clientSocket);
    }

    public void loginOption () {

            String clientOption = cReader.readLine();
            int clientOption2 = Integer.parseInt(clientOption);
            System.out.println(clientOption);

            playerGenerator.loginOption(clientOption2, player);
            System.out.println(player.getPlayerName());
    }

    @Override
    public void run () {

        loginOption();
    }
}
