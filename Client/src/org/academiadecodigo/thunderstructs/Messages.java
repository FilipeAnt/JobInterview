package org.academiadecodigo.thunderstructs;

public class Messages {

    public static final String REQUEST_NEXT_QUESTION = "/Next question";
    public static final String RED = "\u001b[31m";
    public static final String BLUE = "\u001B[34m";
    public static final String GREEN = "\u001b[32m";
    public static final String BLACK_BACKGROUND = "\033[40m";  // BLACK
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
    public static final String BAR = "==================================================================================================";

    public static final String SPACE = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";

    public static final String THREE = RED + ".----------------. \n" +
            "| .--------------. |\n" +
            "| |    ______    | |\n" +
            "| |   / ____ `.  | |\n" +
            "| |   `'  __) |  | |\n" +
            "| |   _  |__ '.  | |\n" +
            "| |  | \\____) |  | |\n" +
            "| |   \\______.'  | |\n" +
            "| |              | |\n" +
            "| '--------------' |\n" +
            " '----------------' \n\n\n\n\n" + ANSI_RESET;

    public static final String TWO = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" + ANSI_YELLOW +
            ".----------------. \n" +
            "| .--------------. |\n" +
            "| |    _____     | |\n" +
            "| |   / ___ `.   | |\n" +
            "| |  |_/___) |   | |\n" +
            "| |   .'____.'   | |\n" +
            "| |  / /____     | |\n" +
            "| |  |_______|   | |\n" +
            "| |              | |\n" +
            "| '--------------' |\n" +
            " '----------------' \n\n\n\n\n" + ANSI_RESET;


    public static final String ONE = " \n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" + GREEN +
            ".----------------. \n" +
            "| .--------------. |\n" +
            "| |     __       | |\n" +
            "| |    /  |      | |\n" +
            "| |    `| |      | |\n" +
            "| |     | |      | |\n" +
            "| |    _| |_     | |\n" +
            "| |   |_____|    | |\n" +
            "| |              | |\n" +
            "| '--------------' |\n" +
            " '----------------' \n\n\n\n\n" + ANSI_RESET;

    public static final String WELCOME =
            YELLOW_BOLD +"\n\n\n"+ BAR + "\n" + BAR + BLUE +"\n\n" + "     ██╗ ██████╗ ██████╗     ██╗███╗   ██╗████████╗███████╗██████╗ ██╗   ██╗██╗███████╗██╗    ██╗    \n" +
                    "     ██║██╔═══██╗██╔══██╗    ██║████╗  ██║╚══██╔══╝██╔════╝██╔══██╗██║   ██║██║██╔════╝██║    ██║    \n" +
                    "     ██║██║   ██║██████╔╝    ██║██╔██╗ ██║   ██║   █████╗  ██████╔╝██║   ██║██║█████╗  ██║ █╗ ██║    \n" +
                    "██   ██║██║   ██║██╔══██╗    ██║██║╚██╗██║   ██║   ██╔══╝  ██╔══██╗╚██╗ ██╔╝██║██╔══╝  ██║███╗██║    \n" +
                    "╚█████╔╝╚██████╔╝██████╔╝    ██║██║ ╚████║   ██║   ███████╗██║  ██║ ╚████╔╝ ██║███████╗╚███╔███╔╝    \n" +
                    " ╚════╝  ╚═════╝ ╚═════╝     ╚═╝╚═╝  ╚═══╝   ╚═╝   ╚══════╝╚═╝  ╚═╝  ╚═══╝  ╚═╝╚══════╝ ╚══╝╚══╝     \n" +
                    "\n" + YELLOW_BOLD + BAR + "\n" + BAR + "\n" + ANSI_RESET + "\n Enter your host:";
}
