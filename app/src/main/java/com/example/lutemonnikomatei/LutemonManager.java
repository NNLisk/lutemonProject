package com.example.lutemonnikomatei;


import com.example.lutemonnikomatei.GUI.HomePage;
import com.example.lutemonnikomatei.LutemonClasses.Janne;
import com.example.lutemonnikomatei.LutemonClasses.Lutemon;
import com.example.lutemonnikomatei.enums.LUTEMONTYPES;

import java.util.ArrayList;
public class LutemonManager {

    public static LutemonManager lutemonManager = null;
    ArrayList<Lutemon> listOfLutemons;

    Lutemon player1Lutemon;
    Lutemon player2Lutemon;
    private LutemonManager() {
        this.listOfLutemons = new ArrayList<Lutemon>();
    }
    public static LutemonManager getInstance() {
        if (lutemonManager == null) {
            lutemonManager = new LutemonManager();
        }
        return lutemonManager;
    }

    public static void createLutemon(String name) {
        Lutemon lutemon = new Janne(name); //test purposes, later to be changed to switch case to account for types
        HomePage.lutemonManager.listOfLutemons.add(lutemon);
    }

    public Lutemon getPlayer1() {
        return this.player1Lutemon;
    }

    public Lutemon getPlayer2() {
        return this.player2Lutemon;
    }

    public void setPlayer1(Lutemon player) {
        this.player1Lutemon = player;
    }

    public void setPlayer2(Lutemon player) {
        this.player2Lutemon = player;
    }

    public static void updateLutemonWins(Lutemon lutemon) {
        lutemon.addWin();
    }

    public static void updateLutemonLosses(Lutemon lutemon) {
        lutemon.addLoss();
    }
    public static void lutemonLevelUpHandler(Lutemon lutemon) {
        lutemon.increaseLevel();
    }
}
