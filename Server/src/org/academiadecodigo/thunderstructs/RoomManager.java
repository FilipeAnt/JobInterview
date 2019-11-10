package org.academiadecodigo.thunderstructs;

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

                if (gameRooms[i][lastIndex] != null) {
                    System.out.println("Olá");
                    clientsThreadPool.submit(new PlayerHandler(gameRooms[i]));
                    resetRoom(i);
                }
            }
        }
    }

    public void resetRoom (int i) { //TODO: method for all which requires method to initialize nested arrays without names;

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

/*    @Override
    public void run() {

        Player[] gamePlayers = new Player[2];
        while (true) {

            //System.out.println(onlinePlayers.size());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (onlinePlayers.size() == 2) {

                int size = onlinePlayers.size();
                gamePlayers[0] = onlinePlayers.getLast();
                gamePlayers[1] = onlinePlayers.getFirst();

                if (gamePlayers[0].getIsReady() && gamePlayers[1].getIsReady()) {

                    clientsThreadPool.submit(new PlayerHandler(gamePlayers));
                    gamePlayers[1].setIsReady(false);
                }
            }
        }
    }*/
}
