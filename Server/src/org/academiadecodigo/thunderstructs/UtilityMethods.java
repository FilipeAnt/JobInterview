package org.academiadecodigo.thunderstructs;

import java.util.concurrent.ExecutorService;

public class UtilityMethods {

    public static int generateRandomNum (int max, int min) {

        return (int) (Math.random() * (max - min)) + min;

    }
}
