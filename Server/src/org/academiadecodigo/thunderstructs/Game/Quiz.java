package org.academiadecodigo.thunderstructs.Game;

import org.academiadecodigo.thunderstructs.Utilitary.TimeCounter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Quiz implements Runnable {


    private Socket player;
    private String[] questions;
    private QuizPreparator quizzPreparator;
    private Thread thread;
    private boolean isTimeout = false;
    private int score;
    private PrintWriter sendQuestions;
    private BufferedReader receiveAnswers;

    public Quiz(Socket player, String[] questions, QuizPreparator quizzPreparator) {

        this.player = player;
        this.questions = questions;
        this.quizzPreparator = quizzPreparator;
        this.thread = new Thread(new TimeCounter(this));
        try {
            this.sendQuestions = new PrintWriter(player.getOutputStream(), true);
            this.receiveAnswers = new BufferedReader(new InputStreamReader(player.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        int questionIndex = 0;
        sendQuestions.println("start");
        thread.start();

        while (!isTimeout) {
            //send and receive data
            try {
                Thread.sleep(2000);
                sendQuestions.println(questions[questionIndex]);
                String answer = receiveAnswers.readLine();
                System.out.println(answer);

                questionIndex++;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

        //TODO nao detecta quando a thread acaba
        sendQuestions.println("end");
        quizzPreparator.updatePlayerScore(player, score);

    }

    public void setIsTimeout() {
        this.isTimeout = true;
    }

    public void addScore() {
        score++;
    }

    public String questions(int questionNumber) {
        return questions[questionNumber];
    }

}
