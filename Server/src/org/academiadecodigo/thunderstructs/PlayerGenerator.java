package org.academiadecodigo.thunderstructs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class PlayerGenerator {

    private int clientOption;
    private Socket clientSocket;

    public PlayerGenerator(String clientOption, Socket clientSocket) {

        this.clientOption = Integer.parseInt(clientOption);
        this.clientSocket = clientSocket;

    }

    public Player generatePlayer () {

        Player newPlayer;

        switch (clientOption) {

            case 1:
                newPlayer = unregisteredPlayer();
                break;

            case 2:
                newPlayer = loginPlayer ();
                break;

            case 3:
                newPlayer = registerPlayer (clientSocket);
                break;

            default:
                newPlayer = unregisteredPlayer();
                break;

        }
        return newPlayer;
    }

    public Player unregisteredPlayer () {

        String playerName = "";

        try {

            BufferedReader bReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            playerName = bReader.readLine();

        } catch (IOException e) {
            e.getStackTrace();
        }

        return new Player(playerName);
    }

    public void loginPlayer () {



        return new Player(String username, String password);
    }

    public void registerPlayer (Socket clientSocket) {

        try {

            BufferedReader bReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));


        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}
