package org.academiadecodigo.thunderstructs;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.thunderstructs.Coolness.CoolReader;

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

    public void nextQuestion() {

        try {

            PrintWriter pWriter = new PrintWriter(clientSocket.getOutputStream(), true);
            pWriter.println(Messages.REQUEST_NEXT_QUESTION);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    public void logAnswer() {

        //   log.add(answer)

    }


    public String receiveMessage() {

        String receivedMessage = "";
        try {

            BufferedReader bReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));



            receivedMessage = bReader.readLine();

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
        log.add(new Log(decryptor.getQuestion(), decryptor.correctAnswer(), answer));
    }

    public void results() {
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

    public void showResults(){

        //String winner = receiveMessage();

        System.out.println(Messages.SPACE + Messages.TIMEOUT + "\n\n");
        System.out.println("YOUR RESULTS: " + score + "\n");
        results();
        System.out.println("PLATER RESULTS: \n");
        String test = "";

        test = receiveMessage();
        System.out.println(test);

        if (checkIfWinner(test)) {

            System.out.println(Messages.WINNER);
        }
    }

    public boolean checkIfWinner (String payload) {

        String[] analyzeThis = payload.split("==>");
        int ola = Integer.parseInt(analyzeThis[1]);

        if (ola == score) {

            return true;

        }
        return false;
    }

    public int getScore() {
        return score;
    }

    public void sendScore(){
        try{
            PrintWriter sendScore = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()),true);
            sendScore.println(score);
        } catch (IOException e){
            e.getMessage();
        }
    }
}
