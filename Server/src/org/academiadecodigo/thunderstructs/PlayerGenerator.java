package org.academiadecodigo.thunderstructs;

import org.academiadecodigo.thunderstructs.Utilitary.Coolness.CoolReader;
import org.academiadecodigo.thunderstructs.Utilitary.ServerMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class PlayerGenerator {

    private Socket clientSocket;
    private DataManager dataManager;

    public PlayerGenerator() {

        this.clientSocket = clientSocket;
        this.dataManager = new DataManager();

    }

    public void generatePlayer (int clientOption, Player player) {

        Player newPlayer;

        switch (clientOption) {

            case 1:
                unregisteredPlayer(player);
                break;

            case 2:
                //newPlayer = loginPlayer ();

            case 3:
                //newPlayer = registerPlayer (clientSocket);

            default:
                unregisteredPlayer(player);
                break;

        }
    }

    public void unregisteredPlayer (Player player) {

        String playerName = "";

        //try {

            //BufferedReader bReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            //PrintWriter pWriter = new PrintWriter(clientSocket.getOutputStream());
            player.setPlayerName("HOHO");//bReader.readLine();

            player.setIsReady(true);
            //while (dataManager.checkIfExists(playerName)) {

              //  pWriter.println(ServerMessage.C_NICKNAME_ALREADY_EXSITS);
               // playerName = bReader.readLine();

            //}

        //} catch (IOException e) {
          //  e.getStackTrace();
        //}

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
