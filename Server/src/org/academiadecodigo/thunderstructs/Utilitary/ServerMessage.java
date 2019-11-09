package org.academiadecodigo.thunderstructs.Utilitary;

import org.academiadecodigo.thunderstructs.Utilitary.ServerConfiguration;

public class ServerMessage {

    public static final String AWAITING_CLIENT_CONNECTION = "Waiting for a new PlayerGenerator Connection.";
    public static final String CONNECTION_WITH = "Connection established with the following IP: ";
    public static final String NEW_GAME_STARTED = "A new game was launched between " + ServerConfiguration.PLAYERS_PER_GAME + " players.\n" +
            "==========================================\n";

}
