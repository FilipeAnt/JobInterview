package org.academiadecodigo.thunderstructs.Game;

import org.academiadecodigo.thunderstructs.Player;
import org.academiadecodigo.thunderstructs.Utilitary.Coolness.CoolWriter;
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
        System.out.println("creating");
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

        System.out.println("Created");
    }

    @Override
    public void run() {
        System.out.println("#Quiz Class running");

        int questionIndex = 0;
        sendQuestions.println("start");
        System.out.println("COOOOOL");
        thread.start();

        while (!isTimeout) {
            //send and receive data
            try {
                Thread.sleep(1000);
                sendQuestions.println(questions[questionIndex]);
                System.out.println("QUIZ - LISTENING");
                String answer = receiveAnswers.readLine();
                System.out.println("QUIZ - Readingline");
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

        score = readScore();
        quizzPreparator.updatePlayerScore(player, score);
        boolean stay = true;

        while (stay) {

            quizzPreparator.updatePlayerScore(player, score);

            if (isGameFinished()) {
                System.out.println("player2");
                sendScores();
                stay = false;
                quizzPreparator.resetScores();
            }
        }
    }


    public int readScore(){
        int score = -1;
        try{
            BufferedReader readScore = new BufferedReader(new InputStreamReader(player.getInputStream()));
            String playerScore = readScore.readLine();
            score = Integer.parseInt(playerScore);
        } catch (IOException e){
            e.fillInStackTrace();
        }
        return score;
    }

    public boolean isGameFinished() {

        boolean finished = true;
        Player[] players = quizzPreparator.getPlayers();

        for (Player p : players) {

            System.out.println(p.getRoundPoints());

            if (p.getRoundPoints() == -1) {

                finished = false;

            }

        }
        return finished;
    }

    public synchronized void sendScores () {
        String x ="";

        Player[] players = quizzPreparator.getPlayers();
        for(int i = 0; i < players.length; i++){
            x += players[i].getPlayerName() + ": " + players[i].getRoundPoints() + "||";
        }

        x += "==>" + quizzPreparator.checkWinner();

        CoolWriter cool = new CoolWriter(player);
        cool.println(x);
        cool.println("péu");

    }


    public void setIsTimeout() {
        this.isTimeout = true;
        System.out.println(isTimeout + "Teste mega merda");
    }

    public void addScore() {
        score++;
    }

    public String questions(int questionNumber) {
        return questions[questionNumber];
    }

}
