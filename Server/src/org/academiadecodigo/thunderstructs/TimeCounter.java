package org.academiadecodigo.thunderstructs;

import org.academiadecodigo.thunderstructs.Game.Quiz;

import java.sql.Time;

public class TimeCounter implements Runnable {

    private Quiz quiz;

    public TimeCounter(Quiz quiz) {
        this.quiz = quiz;
    }

    @Override
    public void run() {

        for (int i = 15; i > 0; i--) {
            try {
                Thread.sleep(1000);
                System.out.println(i);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        quiz.setIsTimeout();
    }
}
