package org.academiadecodigo.thunderstructs.Utility;


public class Decryptor {

    private String[] questionData;


    public void update(String serverMessage) {

        this.questionData = serverMessage.split("#");
    }


    public String getQuestion() {
        return questionData[0];
    }


    public String[] getOptions() {
        String[] options = new String[questionData.length - 2];

        int counter = 0;

        for (int i = 1; i <= options.length; i++) {
            options[counter] = questionData[i];
            counter++;
        }
        return options;
    }


    public String correctAnswer() {
        return questionData[questionData.length - 1];
    }
}
