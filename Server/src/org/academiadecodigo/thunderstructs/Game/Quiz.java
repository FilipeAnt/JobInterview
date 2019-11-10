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
    private Thread thread;
    private boolean isTimeout = false;


    public Quiz(Socket player, String[] questions) {
        this.player = player;
        this.questions = questions;
        this.thread = new Thread(new TimeCounter(this));
    }


    @Override
    public void run() {
        try {
            PrintWriter sendQuestions = new PrintWriter(player.getOutputStream(), true);
            BufferedReader receiveAnswers = new BufferedReader(new InputStreamReader(player.getInputStream()));

            int questionIndex = 0;
            sendQuestions.println("start");
            thread.start();

            while (!isTimeout) {
                Thread.sleep(1000);

                sendQuestions.println(questions[questionIndex]);
                receiveAnswers.readLine();

                questionIndex++;
            }

            sendQuestions.println("end");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.getMessage();
        }
    }


    public void setIsTimeout() {
        this.isTimeout = true;
    }
}
