package org.academiadecodigo.thunderstructs;

import org.academiadecodigo.thunderstructs.Game.QuizPreparator;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RoomManager implements Runnable {

    private LinkedList<Player> onlinePlayers;
    private ExecutorService clientsThreadPool;
    public static Player[] singleRoom;
    public static Player[] twoPlayersRoom;
    public static Player[] fourPlayersRoom;
    public static Player[] sixPlayersRoom;
    public static Player[][] gameRooms;


    public RoomManager(LinkedList<Player> onlinePlayers) {

        this.onlinePlayers = onlinePlayers;
        this.clientsThreadPool = Executors.newCachedThreadPool();
        this.singleRoom = new Player[1];
        this.twoPlayersRoom = new Player[2];
        this.fourPlayersRoom = new Player[4];
        this.sixPlayersRoom = new Player[6];
        this.gameRooms = new Player[][]{singleRoom, twoPlayersRoom, fourPlayersRoom, sixPlayersRoom };

    }

    @Override
    public void run () {

        while (true) {

            for (int i = 0; i < gameRooms.length; i++) {

                int lastIndex = gameRooms[i].length - 1;
                //checkOfflinePlayers(gameRooms[i]);

                if (gameRooms[i][lastIndex] != null) {

                    new QuizPreparator(gameRooms[i]).run();
                    //clientsThreadPool.submit(new PlayerHandler(gameRooms[i]));
                    resetRoom(i);
                }
            }
        }
    }

    public void checkOfflinePlayers(Player[] room) {

        for (int i = 0; i < room.length; i++) {

            if (room[i] == null) {
                return;
            }

            System.out.println(room[i].isPlayerConnected());

            if (room[i].isPlayerConnected()) {
                System.out.println("Alguém offline");
                removeOfflinePlayer(room, i);
            }
        }
    }

    public void removeOfflinePlayer (Player[] room, int offlinePlayerIndex) {

        for (int i = offlinePlayerIndex ; i < room.length; i++ ) {

            Player nextPlayer = room[i + 1];
            room[i] = nextPlayer;
            room[i + 1] = null;
            return;
        }
        System.out.println("Offline player removed.");
    }

    public void resetRoom (int i) {

        switch (gameRooms[i].length) {

            case 1:
                singleRoom = new Player[1];
                gameRooms[i] = singleRoom;
                break;

            case 2:
                twoPlayersRoom = new Player[2];
                gameRooms[i] = twoPlayersRoom;
                break;

            case 3:
                fourPlayersRoom = new Player[4];
                gameRooms[i] = fourPlayersRoom;
                break;

            case 4:
                sixPlayersRoom = new Player[6];
                gameRooms[i] = sixPlayersRoom;
                break;
        }
    }
}
