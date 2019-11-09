package org.academiadecodigo.thunderstructs;

import org.academiadecodigo.thunderstructs.Utilitary.Coolness.CoolReader;
import org.academiadecodigo.thunderstructs.Utilitary.ServerMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class PlayerGenerator {

    private int clientOption;
    private Socket clientSocket;
    private DataManager dataManager;

    public PlayerGenerator(String clientOption, Socket clientSocket) {

        this.clientOption = Integer.parseInt(clientOption);
        this.clientSocket = clientSocket;
        this.dataManager = new DataManager();

    }

    public Player generatePlayer () {

        Player newPlayer;

        switch (clientOption) {

            case 1:
                newPlayer = unregisteredPlayer();
                break;

            case 2:
                //newPlayer = loginPlayer ();

            case 3:
                //newPlayer = registerPlayer (clientSocket);

            default:
                newPlayer = unregisteredPlayer();
                break;

        }
        return newPlayer;
    }

    public Player unregisteredPlayer () {

        String playerName = "";

        //try {

            //BufferedReader bReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            //PrintWriter pWriter = new PrintWriter(clientSocket.getOutputStream());
            playerName = "HOHO";//bReader.readLine();

            //while (dataManager.checkIfExists(playerName)) {

              //  pWriter.println(ServerMessage.C_NICKNAME_ALREADY_EXSITS);
               // playerName = bReader.readLine();

            //}

        //} catch (IOException e) {
          //  e.getStackTrace();
        //}

        return new Player(playerName, clientSocket);
    }


    public void loginPlayer () {

        String name = "Teste";

        new Player(name/*String username, String password*/, clientSocket);
    }

    public void registerPlayer (Socket clientSocket) {

        try {

            BufferedReader bReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));


        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}
