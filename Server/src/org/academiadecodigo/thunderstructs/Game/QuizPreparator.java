package org.academiadecodigo.thunderstructs.Game;

import org.academiadecodigo.thunderstructs.Player;
import org.academiadecodigo.thunderstructs.Utilitary.UtilityMethods;
import org.academiadecodigo.thunderstructs.Utilitary.ServerConfiguration;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class QuizPreparator implements Runnable {

    private Player[] players;
    private String[] roundQuestions;
    private ExecutorService cachedPool;
    private int highest = 0;


    public QuizPreparator(Player[] playerSockets) {

        this.players = playerSockets;
        this.roundQuestions = new String[ServerConfiguration.QUESTIONS_PER_ROUND];
        this.cachedPool = Executors.newCachedThreadPool();
    }

    public String[] generateRoundQuestions() {
        for (int i = 0; i < roundQuestions.length; i++) {
            String question = getRandomQuestion();
            while(checkQuestion(question,roundQuestions,i)){
                question = getRandomQuestion();
            }

            roundQuestions[i] = question;
        }
        return roundQuestions;
    }

    public String getRandomQuestion() {

        int questionIndex = UtilityMethods.generateRandomNum(QuestionDB.NUM_OF_QUESTIONS, 0);
        String question = QuestionDB.QUESTIONS_DATA_BASE[questionIndex];

        return question;
    }

    private boolean checkQuestion(String message, String[] questions, int currentLength){
        boolean hasQuestion = false;
        for(int j = 0; j < currentLength; j++){
            if(questions[j].equals(message)){
             hasQuestion = true;
            }
        }
        return hasQuestion;
    }

    public void startQuizz(Player[] players) {

        int playerSocketIndex = (players.length - 1); //last player               //ServerConfiguration.PLAYERS_PER_GAME;
        System.out.println("inside StartQuizz");
        while (playerSocketIndex >= 0) {

            Socket playerSocket = players[playerSocketIndex].getPlayerSocket();

            System.out.println(playerSocketIndex);
            System.out.println(players[playerSocketIndex].getPlayerName());

            Quiz quiz = new Quiz(playerSocket, roundQuestions, this);
            System.out.println("Quiz created!");
            cachedPool.submit(quiz);

            playerSocketIndex--;
        }
    }

    public void endQuiz() {

        String finalMessage = finalScoresMessage();

        try {

            for (Player p : players) {

                PrintWriter printWriter = new PrintWriter(p.getPlayerSocket().getOutputStream(), true);
                printWriter.println(finalMessage);

            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public String finalScoresMessage() {

        //String message = "First player score was: " + players[0].getRoundPoints() + " and player two score was: " + players[1].getRoundPoints();

        return "hehe";//message;
    }

    public void updatePlayerScore(Socket playerSocket, int playerScore) {

        for (Player s : players) {

            if (s.getPlayerSocket() == playerSocket) {

                s.setRoundPoints(playerScore);

            }
        }
    }

    public int checkWinner () {


        for (Player s : players) {

            if (s.getRoundPoints() > highest) {

                highest = s.getRoundPoints();

                System.out.println(highest);
            }

        }
        return highest;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void resetScores () {

        for (Player p : players) {

            p.setRoundPoints(0);

        }

    }

    @Override
    public void run() {

        String[] roundQuestions = generateRoundQuestions();
        System.out.println("StartQuiz");
        startQuizz(players);
        //endQuiz();
    }
}
