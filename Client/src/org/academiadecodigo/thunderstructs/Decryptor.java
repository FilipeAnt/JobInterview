package org.academiadecodigo.thunderstructs;

public class Decryptor {
    private String[] input;

    public void split(String string) {
        input = string.split("#");
    }

    public String getMessage() {
        return input[0];
    }

    public String[] getOptions() {
        String[] options = new String[input.length - 2];
        int counter = 0;
        for (int i = 1; i < options.length; i++) {
            options[counter++] = input[i];
        }
        return options;
    }

    public String correctAnswer() {
        return input[input.length - 1];
    }
}
