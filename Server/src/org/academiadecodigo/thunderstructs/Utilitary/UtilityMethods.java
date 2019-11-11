package org.academiadecodigo.thunderstructs.Utilitary;


public class UtilityMethods {

    public static int generateRandomNum (int max, int min) {

        return (int) (Math.random() * (max - min)) + min;

    }
}
