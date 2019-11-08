package org.academiadecodigo.thunderstructs.Game;

import org.academiadecodigo.thunderstructs.Operations.Commands;
import org.academiadecodigo.thunderstructs.UtilityMethods;
import org.academiadecodigo.thunderstructs.ServerConfiguration;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class QuizPreparator implements Commands {

    private Socket[] playerSockets;
    private String[] roundQuestions;

    private Map<Socket, Integer> playerScoreMap;
    private ExecutorService cachedPool;

    public QuizPreparator(Socket[] playerSockets) {

        this.playerSockets = playerSockets;
        this.roundQuestions = new String[ServerConfiguration.QUESTIONS_PER_ROUND];
        this.playerScoreMap = new HashMap<>();
        this.playerScoreMap.put(playerSockets[0], ServerConfiguration.DEFAULT_SCORE);
        this.playerScoreMap.put(playerSockets[1], ServerConfiguration.DEFAULT_SCORE);
        this.cachedPool = Executors.newCachedThreadPool();
    }

    public String[] generateRoundQuestions() {

        for (int i = 0; i < roundQuestions.length; i++) {

            roundQuestions[i] = getRandomQuestion();
        }
        return roundQuestions;
    }

    public String getRandomQuestion() {

        int questionIndex = UtilityMethods.generateRandomNum(QuestionDB.NUM_OF_QUESTIONS, 0);
        String question = QuestionDB.QUESTIONS_DATA_BASE[questionIndex];

        return question;
    }

/*    public Socket getPlayerSocket (Socket[] playerSockets) {

         playerScoreMap.put();

    }*/

    public void startQuizz(Socket[] playerSockets) {

        int playerSocketIndex = ServerConfiguration.PLAYERS_PER_GAME;

        while (playerSocketIndex > 0) {

            Socket playerSocket = playerSockets[--playerSocketIndex];

            Quiz quiz = new Quiz(playerSocket, roundQuestions, this);

            cachedPool.submit(quiz);
        }
    }

    public void endQuiz() {

        String finalMessage = finalScoresMessage();

        try {

            for (Socket s : playerSockets) {

                PrintWriter printWriter = new PrintWriter(s.getOutputStream());
                printWriter.println(finalMessage);

            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public String finalScoresMessage() {

        String message = "First player score was: " + playerScoreMap.get(playerSockets[0]) + " and player two score was: " + playerScoreMap.get(playerSockets[1]);

        return message;
    }

    public void updatePlayerScore(Socket playerSocket, int playerScore) {

        for (Socket s : playerScoreMap.keySet()) {

            if (s == playerSocket) {

                playerScoreMap.replace(s, playerScore);

            }
        }
    }


    @Override
    public void execute() {

        String[] roundQuestions = generateRoundQuestions();
        startQuizz(playerSockets);
        endQuiz();
    }

}
