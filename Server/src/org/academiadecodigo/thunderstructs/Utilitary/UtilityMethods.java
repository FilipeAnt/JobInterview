package org.academiadecodigo.thunderstructs.Utilitary;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

public class UtilityMethods {

    public static int generateRandomNum (int max, int min) {

        return (int) (Math.random() * (max - min)) + min;

    }
}
