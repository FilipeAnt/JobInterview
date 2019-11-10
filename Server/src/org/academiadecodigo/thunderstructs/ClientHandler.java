package org.academiadecodigo.thunderstructs;

import org.academiadecodigo.thunderstructs.Utilitary.Coolness.CoolReader;

import java.net.Socket;

public class ClientHandler implements Runnable {

    private Socket clientSocket;
    private CoolReader cReader;
    private PlayerConfigurator playerConfigurator;
    private Player player;


    public ClientHandler (Player player) {

        this.player = player;
        this.clientSocket = player.getPlayerSocket();
        this.playerConfigurator = new PlayerConfigurator(player);
        this.cReader = new CoolReader(clientSocket);
    }

    public void loginOption () {

            String clientOption = cReader.readLine();
            int loginOption = Integer.parseInt(clientOption);

            playerConfigurator.loginOption(loginOption, player);
            System.out.println(player.getPlayerName());
    }

    public void addToRoom () {

        String clientOption = cReader.readLine();
        int roomOption = Integer.parseInt(clientOption) - 1;

        addToIndex(roomOption);
    }

    public void addToIndex(int roomOption) {

        for (int i = 0; i < RoomManager.gameRooms[roomOption].length; i++) {

            if (RoomManager.gameRooms[roomOption][i] == null) {

                RoomManager.gameRooms[roomOption][i] = player;
                break;
            }
        }
    }


    @Override
    public void run () {

        loginOption();
        addToRoom();
    }
}
