package org.academiadecodigo.thunderstructs;

public class Log {
    private String question;

    private String correctAnswer;

    private String userAnswer;

    public Log(String question, String correctAnswer, String userAnswer) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.userAnswer = userAnswer;
    }


    @Override
    public String toString() {
        return "" + question + '\'' +
                " | Correct Answer: " + correctAnswer + '\'' +
                " | User Answer: " + userAnswer + '\'';
    }
}
