package org.academiadecodigo.thunderstructs;

import org.academiadecodigo.thunderstructs.Utilitary.Coolness.CoolReader;
import org.academiadecodigo.thunderstructs.Utilitary.ServerConfiguration;

public class DataManager {


    public DataManager () {


    }

    public boolean checkIfExists (String nickname) {

        boolean exists = false;
        CoolReader cReader = new CoolReader(ServerConfiguration.PLAYER_RECORD_FILE);
        String nextLine;

        while ((nextLine = cReader.readLine()) != null) {

            String registeredNickname = nextLine.split("#")[0];

            if (registeredNickname.equals(nickname)) {
                exists = true;
            }

        }
        cReader.close();
        return exists;
    }
}
