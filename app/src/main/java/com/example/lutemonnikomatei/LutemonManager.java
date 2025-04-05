package com.example.lutemonnikomatei;


import com.example.lutemonnikomatei.GUI.HomePage;
import com.example.lutemonnikomatei.LutemonClasses.Janne;
import com.example.lutemonnikomatei.LutemonClasses.Lutemon;
import com.example.lutemonnikomatei.LutemonClasses.RestaurantWorker;
import com.example.lutemonnikomatei.LutemonClasses.SecurityGuard;
import com.example.lutemonnikomatei.LutemonClasses.Student;
import com.example.lutemonnikomatei.LutemonClasses.TA;
import com.example.lutemonnikomatei.LutemonClasses.Teacher;
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

    public static void createLutemon(String name, LUTEMONTYPES type) {
        Lutemon lutemon = null; //test purposes, later to be changed to switch case to account for types

        switch (type) {
            case Janne:
                lutemon = new Janne(name);
                break;
            case RESTAURANT_WORKER:
                lutemon = new RestaurantWorker(name);
                break;
            case SECURITY_GUARD:
                lutemon = new SecurityGuard(name);
                break;
            case STUDENT:
                lutemon = new Student(name);
                break;
            case TA:
                lutemon = new TA(name);
                break;
            case TEACHER:
                lutemon = new Teacher(name);
                break;
        }
        if (lutemon != null) {
            HomePage.lutemonManager.listOfLutemons.add(lutemon);
        }
    }

    public Lutemon getPlayer1() {
        return this.player1Lutemon;
    }

    public Lutemon getPlayer2() {
        return this.player2Lutemon;
    }

    public ArrayList<Lutemon> getListOfLutemons() {return this.listOfLutemons;}

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
