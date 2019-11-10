package org.academiadecodigo.thunderstructs;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.thunderstructs.Utility.Messages;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

@SuppressWarnings("Duplicates")
public class Client {
    private Socket socket;
    private final int PORTNUMBER = 6565;
    private String adress;
    private BufferedReader reader;
    private ClientQuiz quiz;
    private Prompt prompt = new Prompt(System.in, System.out);


    public static void main(String[] args) {
        Client client = new Client();
        client.start();
    }


    public Client() {
        quiz = new ClientQuiz();
    }


    public void start() {
        welcomeMessage();
        connect();
        initialMenu();
        chooseRoom();
        listen();
    }


    private void welcomeMessage() {
        System.out.println(Messages.WELCOME);

        Scanner scanner = new Scanner(System.in);
        adress = scanner.nextLine();
    }


    private void connect() {
        try {
            socket = new Socket(adress, PORTNUMBER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void listen() {
        String input;

        waitingMessage();

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


    private void launchQuiz() {
        quiz.start(socket);
    }


    private void initialMenu() {
        String[] options = {"Launch as guest", "Exit"};
        MenuInputScanner initialMenu = new MenuInputScanner(options);
        initialMenu.setMessage("Choose:");
        int userAnswer = prompt.getUserInput(initialMenu);

        try {
            PrintWriter choice = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            choice.println(userAnswer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (userAnswer == 1) {
            nickname();
        }

        if (userAnswer == 4) {
            System.exit(0);
        }
    }


    private void chooseRoom() {

        String[] options = {"Single Player / Training Mode", "Two Players", "Four Players", "Six Players"};
        MenuInputScanner roomMenu = new MenuInputScanner(options);
        roomMenu.setMessage("Choose a room:");
        int answer = prompt.getUserInput(roomMenu);

        try {
            PrintWriter option = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            option.println(answer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void nickname() {

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


    private void waitingMessage() {

        System.out.println(Messages.SPACE + Messages.GREEN + Messages.CONNECTED + Messages.ANSI_RESET);
        System.out.println(Messages.ANSI_YELLOW + Messages.WAITING + Messages.ANSI_RESET);
    }
}
