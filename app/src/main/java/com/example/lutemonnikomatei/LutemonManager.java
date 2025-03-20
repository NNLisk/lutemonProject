package com.example.lutemonnikomatei;


import com.example.lutemonnikomatei.LutemonClasses.Lutemon;

import java.util.ArrayList;
public class LutemonManager {

    public static LutemonManager lutemonManager = null;
    ArrayList<Lutemon> listOfLutemons;

    Lutemon player1Lutemon;
    Lutemon player2Lutemon;
    private LutemonManager() {}
    public static LutemonManager getInstance() {
        if (lutemonManager == null) {
            lutemonManager = new LutemonManager();
        }
        return lutemonManager;
    }

    public static void createLutemon() {

    }

    public static void updateLutemonWins(Lutemon lutemon) {

    }

    public static void updateLutemonLosses(Lutemon lutemon) {

    }
    public static void lutemonLevelUpHandler(Lutemon lutemon)
    {

    }


}
