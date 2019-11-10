package org.academiadecodigo.thunderstructs;

import org.academiadecodigo.thunderstructs.Utility.Decryptor;
import org.academiadecodigo.thunderstructs.Utility.Log;
import org.academiadecodigo.thunderstructs.Utility.Messages;
import org.academiadecodigo.thunderstructs.Menus.ClientQuizMenu;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class ClientQuiz {

    private Decryptor decryptor;
    private int score = 0;
    private List<Log> log;
    private ClientQuizMenu clientQuizMenu;
    private Socket clientSocket;


    public ClientQuiz() {
        decryptor = new Decryptor();
        log = new LinkedList<>();
        clientQuizMenu = new ClientQuizMenu();
    }


    public void start(Socket socket) {
        this.clientSocket = socket;
        question();
    }


    private void question() {

        String serverMessage;

        countdown();

        while (!(serverMessage = receiveMessage()).equals("end")) {

            decryptor.update(serverMessage);

            String question = decryptor.getQuestion();
            String[] options = decryptor.getOptions();

            System.out.println(Messages.SPACE);

            int answerNum = clientQuizMenu.promptQuizAnswer(question, options);

            String answer = getAnswer(answerNum);

            checkAnswer(answer);

            addToLog(answer);

            System.out.println(Messages.SPACE + Messages.NEXT_QUESTION);

            nextQuestion();
        }

        sendScore();

        showResults();
    }


    private void nextQuestion() {

        try {
            PrintWriter pWriter = new PrintWriter(clientSocket.getOutputStream(), true);
            pWriter.println(Messages.REQUEST_NEXT_QUESTION);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    private String receiveMessage() {

        String receivedMessage = "";

        try {
            BufferedReader bReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            receivedMessage = bReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return receivedMessage;
    }


    private String getAnswer(int answerNumber) {
        return decryptor.getOptions()[answerNumber - 1];
    }


    private void checkAnswer(String answer) {
        if (answer.equals(decryptor.correctAnswer())) {
            score++;
        }
    }


    private void addToLog(String answer) {
        log.add(new Log(decryptor.getQuestion(), decryptor.correctAnswer(), answer));
    }


    private void results() {
        for (Log logs : log) {
            System.out.println(logs.toString());
        }
    }


    private void countdown() {
        try {
            System.out.println(Messages.SPACE + Messages.THREE);
            Thread.sleep(1000);

            System.out.println(Messages.TWO);
            Thread.sleep(1000);

            System.out.println(Messages.ONE);
            Thread.sleep(1000);
            System.out.println(Messages.SPACE);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private void showResults() {

        System.out.println(Messages.SPACE + Messages.TIMEOUT + "\n\n");
        System.out.println("YOUR RESULTS: " + score + "\n");

        results();
    }


    private void sendScore() {
        try {
            PrintWriter sendScore = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);
            sendScore.println(score);
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
