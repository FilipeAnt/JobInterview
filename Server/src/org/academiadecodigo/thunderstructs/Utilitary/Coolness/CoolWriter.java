package org.academiadecodigo.thunderstructs.Utilitary.Coolness;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class CoolWriter {

    PrintWriter pWriter;

    public CoolWriter (String filePath) {

        try {

            this.pWriter = new PrintWriter(new FileWriter(filePath), true);

        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
    }


    public CoolWriter (Socket socket) {

        try {

            this.pWriter = new PrintWriter(socket.getOutputStream(), true);

        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
    }

    public void addNewLine () {
    }

    public void println (String message) {
        pWriter.println(message);
    }

    public void close () {
        pWriter.close();
    }
}
