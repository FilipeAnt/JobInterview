package org.academiadecodigo.thunderstructs.Game;

import org.academiadecodigo.thunderstructs.Player;
import org.academiadecodigo.thunderstructs.Utilitary.UtilityMethods;
import org.academiadecodigo.thunderstructs.Utilitary.ServerConfiguration;

import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class QuizPreparator implements Runnable {

    private Player[] players;
    private String[] roundQuestions;
    private ExecutorService cachedPool;


    public QuizPreparator(Player[] playerSockets) {

        this.players = playerSockets;
        this.roundQuestions = new String[ServerConfiguration.QUESTIONS_PER_ROUND];
        this.cachedPool = Executors.newCachedThreadPool();
    }


    private String[] generateRoundQuestions() {
        for (int i = 0; i < roundQuestions.length; i++) {
            String question = getRandomQuestion();
            while (checkQuestion(question, roundQuestions, i)) {
                question = getRandomQuestion();
            }

            roundQuestions[i] = question;
        }
        return roundQuestions;
    }


    private String getRandomQuestion() {

        int questionIndex = UtilityMethods.generateRandomNum(QuestionDB.NUM_OF_QUESTIONS, 0);
        return QuestionDB.QUESTIONS_DATA_BASE[questionIndex];
    }


    private boolean checkQuestion(String message, String[] questions, int currentLength) {
        boolean hasQuestion = false;
        for (int j = 0; j < currentLength; j++) {
            if (questions[j].equals(message)) {
                hasQuestion = true;
            }
        }
        return hasQuestion;
    }


    private void startQuiz(Player[] players) {

        int playerSocketIndex = (players.length - 1);
        while (playerSocketIndex >= 0) {

            Socket playerSocket = players[playerSocketIndex].getPlayerSocket();

            Quiz quiz = new Quiz(playerSocket, roundQuestions);
            cachedPool.submit(quiz);

            playerSocketIndex--;
        }
    }

    @Override
    public void run() {
        roundQuestions = generateRoundQuestions();
        startQuiz(players);
    }
}
