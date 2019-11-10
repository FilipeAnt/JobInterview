package org.academiadecodigo.thunderstructs;

import org.academiadecodigo.thunderstructs.Utilitary.Coolness.CoolReader;
import org.academiadecodigo.thunderstructs.Utilitary.ServerMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class PlayerConfigurator {

    private Socket clientSocket;
    private DataManager dataManager;

    public PlayerConfigurator(Player player) {

        this.clientSocket = player.getPlayerSocket();
        this.dataManager = new DataManager();
    }

    public void loginOption (int clientOption, Player player) {

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

            CoolReader cReader = new CoolReader(clientSocket);
            player.setPlayerName(cReader.readLine());
            System.out.println(player.getPlayerName());
 /*           while (dataManager.checkIfExists(playerName)) {

               // pWriter.println(ServerMessage.C_NICKNAME_ALREADY_EXSITS);
                playerName = bReader.readLine();

            }*/

            player.setIsReady(true);
            System.out.println(player.getIsReady());
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
