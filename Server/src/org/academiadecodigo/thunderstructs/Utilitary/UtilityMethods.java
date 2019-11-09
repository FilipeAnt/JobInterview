package org.academiadecodigo.thunderstructs.Utilitary;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

public class UtilityMethods {

    public static int generateRandomNum (int max, int min) {

        return (int) (Math.random() * (max - min)) + min;

    }

    public static BufferedReader coolSocketReader (Socket clientSocket) {

        try {

            BufferedReader bReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }

        return null;
    }

    public static BufferedReader coolFileReader (String filePath) {

        try {

            BufferedReader bReader = new BufferedReader(new FileReader(filePath));

        } catch (FileNotFoundException e) {
            System.out.println(e.getStackTrace());
        }

        return null;
    }

}
