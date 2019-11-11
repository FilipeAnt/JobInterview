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
    private Prompt prompt = new Prompt(System.in, System.out);


    public Client() {
        quiz = new ClientQuiz();
    }

    public void start() {
        welcomeMessage();
        connect();
        initialMenu();
        chooseRoom();
        listen();
        start();
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

        waitingMessage();

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
        String[] options = {"Launch as guest", "Login", "Register", "Exit"};
        MenuInputScanner initialMenu = new MenuInputScanner(options);
        initialMenu.setMessage("Choose:");
        int answer = prompt.getUserInput(initialMenu);
        try {
            PrintWriter option = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            option.println(answer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (answer == 1) {
            nickname();
        }

        if (answer == 4) {
            System.exit(0);
        }
    }

    public void chooseRoom () {

        String[] options = {"Single Player / Training Mode", "Two Players", "Four Players", "Six Players"}; //TODO: server messages
        MenuInputScanner roomMenu = new MenuInputScanner(options);
        roomMenu.setMessage("Choose a room:");
        int answer = prompt.getUserInput(roomMenu);

        try {
            PrintWriter option = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            option.println(answer);
        }catch (IOException e)  {
            e.printStackTrace();
        }

    }

    public void nickname() {

        System.out.println(Messages.SPACE);
        Scanner name = new Scanner(System.in);
        System.out.println(Messages.CHOOSE_NAME);
        String nickname = name.nextLine();

        try {
            PrintWriter nameWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            nameWriter.println(nickname);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void waitingMessage() {

        System.out.println(Messages.SPACE + Messages.GREEN + Messages.CONNECTED + Messages.ANSI_RESET);
        System.out.println(Messages.ANSI_YELLOW + Messages.WAITING + Messages.ANSI_RESET);

    }
}
