package org.academiadecodigo.thunderstructs;

import org.academiadecodigo.thunderstructs.Game.QuizPreparator;

import java.util.LinkedList;

public class RoomManager implements Runnable {

    public static Player[] singleRoom;
    public static Player[] twoPlayersRoom;
    public static Player[] fourPlayersRoom;
    public static Player[] sixPlayersRoom;
    public static Player[][] gameRooms;


    public RoomManager() {

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
                checkOfflinePlayers(gameRooms[i]);

                if (gameRooms[i][lastIndex] != null) {

                    new QuizPreparator(gameRooms[i]).run();
                    resetRoom(i);
                }
            }
        }
    }

    private void checkOfflinePlayers(Player[] room) {

        for (int i = 0; i < room.length; i++) {

            if (room[i] == null) {
                return;
            }


            if (room[i].isPlayerConnected()) {
                removeOfflinePlayer(room, i);
            }
        }
    }

    private void removeOfflinePlayer (Player[] room, int offlinePlayerIndex) {

        for (int i = offlinePlayerIndex ; i < room.length; i++ ) {

            Player nextPlayer = room[i + 1];
            room[i] = nextPlayer;
            room[i + 1] = null;
            return;
        }
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
