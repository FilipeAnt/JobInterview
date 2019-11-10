package org.academiadecodigo.thunderstructs;

import org.academiadecodigo.thunderstructs.Utilitary.Coolness.CoolReader;

import java.net.Socket;

public class PlayerConfigurator {

    private Socket clientSocket;

    public PlayerConfigurator(Player player) {

        this.clientSocket = player.getPlayerSocket();
    }

    public void loginOption (int clientOption, Player player) {

        if(clientOption == 1) {
                unregisteredPlayer(player);
        }
    }

    public void unregisteredPlayer (Player player) {

            CoolReader cReader = new CoolReader(clientSocket);
            player.setPlayerName(cReader.readLine());

            player.setIsReady(true);
    }
}
