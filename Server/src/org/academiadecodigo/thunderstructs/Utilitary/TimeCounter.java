package org.academiadecodigo.thunderstructs.Utilitary;

import org.academiadecodigo.thunderstructs.Game.Quiz;

public class TimeCounter implements Runnable {

    private Quiz quiz;

    public TimeCounter(Quiz quiz) {
        this.quiz = quiz;
    }

    @Override
    public void run() {

        for (int i = 45; i > 0; i--) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        quiz.setIsTimeout();
    }
}
