package org.academiadecodigo.thunderstructs;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class ClientQuiz {
    private Prompt prompt;
    private MenuInputScanner menu;
    private BufferedReader reader; //delete
    private PrintWriter requestNextAnswer;
    private Decryptor decryptor; //decryptor DELETE
    private int score = 0;
    private List<Log> log;
    private ClientQuizMenu clientQuizMenu;
    private Socket clientSocket;

    public ClientQuiz() {
        prompt = new Prompt(System.in, System.out);
        decryptor = new Decryptor(); //DELETE
        log = new LinkedList<>();
        clientQuizMenu = new ClientQuizMenu();
    }

    public void start(Socket socket) { //init
            this.clientSocket = socket;
            question();
    }


    public void question() {

        String serverMessage = "";

        while (!(serverMessage = receiveMessage()).equals("end")) {

            System.out.println("Teste");

            clientQuizMenu.update(serverMessage);
            int answerNum = clientQuizMenu.promptQuizAnswer();

            String answer = getAnswer(answerNum);

            checkAnswer(answer);
            addToLog(answer);
            nextQuestion();
        }
    }

    public void nextQuestion () {

        try {

            PrintWriter pWriter = new PrintWriter(clientSocket.getOutputStream(), true);
            pWriter.println(Messages.REQUEST_NEXT_QUESTION);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void logAnswer () {

     //   log.add(answer)

    }


    public String receiveMessage() {

        String receivedMessage = "";
        try {

            BufferedReader bReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            System.out.println("Teste#4");
            receivedMessage = bReader.readLine();
            System.out.println("receivedMessage");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return receivedMessage;
    }

    public String getAnswer(int answerNumber) {
        return decryptor.getOptions()[answerNumber - 1];
    }

    public void checkAnswer(String answer) {
        if (answer.equals(decryptor.correctAnswer())) {
            score++;
        }
    }

    public void addToLog(String answer) {
        log.add(new Log(decryptor.getMessage(), decryptor.correctAnswer(), answer));
    }

    public void presentResults() {
        for (Log logs : log) {
            logs.toString();
        }
    }

}
