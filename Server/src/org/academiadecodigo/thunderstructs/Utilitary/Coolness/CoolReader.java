package org.academiadecodigo.thunderstructs.Utilitary.Coolness;

import java.io.*;
import java.net.Socket;

public class CoolReader {

    private BufferedReader bReader;

    public CoolReader(Socket socket) {

        try {

            this.bReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
    }


    public String readLine () {

        String readLine = "";

        try {

        readLine = bReader.readLine();

        } catch (IOException e) {

            System.out.println(e.getStackTrace());
        }
        return readLine;
    }

}
