package org.academiadecodigo.thunderstructs.Utility;

public class Log {
    private String question;
    private String correctAnswer;
    private String userAnswer;


    public Log(String question, String correctAnswer, String userAnswer) {
        this.question = question;
        this.correctAnswer = correctAnswer;

        if (userAnswer.equals(correctAnswer)) {
            this.userAnswer = Messages.GREEN + userAnswer + Messages.ANSI_RESET + "\n";
        } else {
            this.userAnswer = Messages.RED + userAnswer + Messages.ANSI_RESET + "\n";
        }
    }


    @Override
    public String toString() {
        return "" + question +
                " | Correct Answer: " + correctAnswer +
                " | User Answer: " + userAnswer;
    }
}
