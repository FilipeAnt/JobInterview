package org.academiadecodigo.thunderstructs;

public class TimeCounter implements Runnable {

    @Override
    public void run() {

        for (int i = 30; i > 0; i--) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("timeout");
    }
}
