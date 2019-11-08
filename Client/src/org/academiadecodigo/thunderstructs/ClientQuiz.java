package org.academiadecodigo.thunderstructs;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class ClientQuiz {
    private Prompt prompt;
    private MenuInputScanner menu;
    private BufferedReader reader;
    private PrintWriter requestNextAnswer;
    private Decryptor decryptor;
    private int score = 0;
    private List<Log> log;


    public ClientQuiz() {
        prompt = new Prompt(System.in, System.out);
        decryptor = new Decryptor();
        log = new LinkedList<>();
    }

    public void start(Socket socket) {
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            requestNextAnswer = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("startClientQuiz");
        question(reader, requestNextAnswer);
    }


    public void question(BufferedReader reader, PrintWriter requestNextAnswer) {

        String input = "";
        while (!input.equals("end")) {
            input = receiveMessage(reader);
            decryptor.split(input);
            menu = new MenuInputScanner(decryptor.getOptions());
            menu.setMessage(decryptor.getMessage());
            int answerNo = prompt.getUserInput(menu);
            requestNextAnswer.println(answerNo);

            String answer = getAnswer(answerNo);
            requestNextAnswer.println(answer);
            checkAnswer(answer);

            addToLog(answer);
        }
        presentResults();
    }

    public String receiveMessage(BufferedReader reader) {
        String input = "";
        try {
            input = reader.readLine();
            System.out.println("receivedMessage");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
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
            System.out.println(logs.toString());
        }
    }

}
