package org.academiadecodigo.thunderstructs;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {

    private Socket clientSocket;
    private BufferedReader bReader;

    public ClientHandler (Socket clientSocket) {

        this.clientSocket = clientSocket;
    }

    public void loginOption () {

        try {

            String clientOption = bReader.readLine();
            PlayerGenerator playerGenerator = new PlayerGenerator(clientOption, clientSocket);
            Player player = playerGenerator.generatePlayer();

        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}
