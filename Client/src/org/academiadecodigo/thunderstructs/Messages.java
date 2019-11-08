package org.academiadecodigo.thunderstructs;

public class Messages {
    public static final String BLACK_BACKGROUND = "\033[40m";  // BLACK
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
    public static final String BAR = "=======================================================================";
    public static final String WELCOME = YELLOW_BOLD + "   __        _        _____       _                    _                 \n" +
            "   \\ \\  ___ | |__     \\_   \\_ __ | |_  ___ _ ____   __(_) _____      __  \n" +
            "    \\ \\/ _ \\| '_ \\     / /\\/ '_ \\| __|/ _ \\ '__\\ \\ / /| |/ _ \\ \\ /\\ / /  \n" +
            " /\\_/ / (_) | |_) | /\\/ /_ | | | | |_|  __/ |   \\ V / | |  __/\\ V  V /   \n" +
            " \\___/ \\___/|_.__/  \\____/ |_| |_|\\__|\\___|_|    \\_/  |_|\\___| \\_/\\_/    \n" +
            "\n" + BAR + "\n" + ANSI_RESET + "\n Enter your host:";
}
