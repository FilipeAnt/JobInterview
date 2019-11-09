package org.academiadecodigo.thunderstructs;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private final int PORTNUMBER = 6565;
    private String adress;
    private BufferedReader reader;
    private ClientQuiz quiz;
    private Prompt prompt = new Prompt(System.in,System.out);


    public Client() {
        quiz = new ClientQuiz();
    }

    public void start() {
        welcomeMessage();
        connect();
        initialMenu();
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
            System.out.println(input);
            launchQuiz();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void launchQuiz() {
        quiz.start(socket);
    }


    public void initialMenu() {
        String[] options = {"Launch as guest","Login","Register","Exit"};
        MenuInputScanner initialMenu = new MenuInputScanner(options);
        initialMenu.setMessage("Choose:");
        int answer = prompt.getUserInput(initialMenu);
        try {
            PrintWriter option = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
            option.println(answer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
