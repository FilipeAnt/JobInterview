package org.academiadecodigo.thunderstructs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private final int PORTNUMBER = 6565;
    private String adress;
    private BufferedReader reader;
    private ClientQuiz quiz;


    public Client() {
        quiz = new ClientQuiz();
    }

    public void start() {
        welcomeMessage();
        connect();
        listen();


    }

    public void welcomeMessage() {
        System.out.println(Messages.WELCOME);
        Scanner scanner = new Scanner(System.in);
        adress = scanner.nextLine();
    }


    public void connect() {
        try {

            socket = new Socket(adress, PORTNUMBER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen() {
        String input;
        System.out.println("Conected!!!!Waiting for second player...");
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            input = reader.readLine();
            while (!input.equals("start")) {
                input = reader.readLine();
            }

            launchQuiz();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void launchQuiz() {
        quiz.start(socket);
    }


    public void talk() {
    }
}
