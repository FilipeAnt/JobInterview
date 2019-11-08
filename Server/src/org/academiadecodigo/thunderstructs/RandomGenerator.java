package org.academiadecodigo.thunderstructs;

public class RandomGenerator {

    public static int generateRandomNum (int max, int min) {

        return (int) (Math.random() * (max - min)) + min;

    }
}
