package org.academiadecodigo.thunderstructs.Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Quiz {


    private Socket player;
    private String[] questions;
    private QuizzPreparator quizzPreparator;
    private Thread thread;
    private int score;
    private PrintWriter sendQuestions;
    private BufferedReader receiveAnswers;

    public Quizz(Socket player, String[] questions, QuizzPreparator quizzPreparator) {

        this.player = player;
        this.questions = questions;
        this.quizzPreparator = quizzPreparator;
        this.thread = new Thread(new TimeCounter());
        try {
            this.sendQuestions = new PrintWriter(player.getOutputStream(),true);
            this.receiveAnswers = new BufferedReader(new InputStreamReader(player.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {

        int questionNumber = 0;
        sendQuestions.println("start");
        thread.start();

        System.out.println(thread.isAlive());
        while (checkTime()) {
            //send and receive data
            try {

                sendQuestions.println(questions[questionNumber]);
                String answer = receiveAnswers.readLine();
                System.out.println(answer);
                if (answer.equals(questions[questionNumber].split("#")[questions.length - 1])) {
                    addScore();
                }
                questionNumber++;
                System.out.println(checkTime());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("acabou");
        sendQuestions.println("end");
        quizzPreparator.updatePlayerScore(player, score);
        //Need to close this thread


    }

    public boolean checkTime() {
        return thread.isAlive();
    }

    public void addScore() {
        score++;
    }

    public String questions(int questionNumber) {
        return questions[questionNumber];
    }

}
