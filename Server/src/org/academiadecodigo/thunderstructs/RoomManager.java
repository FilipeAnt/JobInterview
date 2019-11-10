package org.academiadecodigo.thunderstructs;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RoomManager implements Runnable {

    private LinkedList<Player> onlinePlayers;
    private ExecutorService clientsThreadPool;

    public RoomManager (LinkedList<Player> onlinePlayers) {

        this.onlinePlayers = onlinePlayers;
        this.clientsThreadPool = Executors.newCachedThreadPool();
    }

    @Override
    public void run () {

        Player[] gamePlayers = new Player[2];
        while (true) {

            if (onlinePlayers.size() ==  2) {

                gamePlayers[0] = onlinePlayers.getLast();
                gamePlayers[1] = onlinePlayers.getFirst();

                if (gamePlayers[0].getIsReady() && (gamePlayers[1].getIsReady())) {

                    clientsThreadPool.submit(new PlayerHandler(gamePlayers));
                    gamePlayers[1].setIsReady(false);
                }

            }
            System.out.println(onlinePlayers.size());

           // if (onlinePlayers.getLast().getPlayerName().equals("HOHO")) {


            //}


            // for (Player p : onlinePlayers) {



            //}

        }
    }


            /*        if (fancyPlayers[1] != null) {

                    clientsThreadPool.submit(new PlayerHandler(fancyPlayers));
                }*/
}
