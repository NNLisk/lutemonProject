package com.example.lutemonnikomatei;


import android.content.Context;

import com.example.lutemonnikomatei.GUI.HomePage;
import com.example.lutemonnikomatei.LutemonClasses.Janne;
import com.example.lutemonnikomatei.LutemonClasses.Lutemon;
import com.example.lutemonnikomatei.LutemonClasses.RestaurantWorker;
import com.example.lutemonnikomatei.LutemonClasses.SecurityGuard;
import com.example.lutemonnikomatei.LutemonClasses.Student;
import com.example.lutemonnikomatei.LutemonClasses.TA;
import com.example.lutemonnikomatei.LutemonClasses.Teacher;
import com.example.lutemonnikomatei.enums.LUTEMONTYPES;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

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

    public void createLutemon(String name, LUTEMONTYPES type) {
        Lutemon lutemon = null;

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
            LutemonManager.getInstance().getListOfLutemons().add(lutemon);
        }
    }

    public void createLutemon(String name, String type) {
        Lutemon lutemon = null; //test purposes, later to be changed to switch case to account for types
        type = type.toLowerCase();
        switch (type) {
            case "janne":
                lutemon = new Janne(name);
                break;
            case "restaurantworker":
                lutemon = new RestaurantWorker(name);
                break;
            case "securityguard":
                lutemon = new SecurityGuard(name);
                break;
            case "student":
                lutemon = new Student(name);
                break;
            case "ta":
                lutemon = new TA(name);
                break;
            case "teacher":
                lutemon = new Teacher(name);
                break;
        }
        if (lutemon != null) {
            LutemonManager.getInstance().getListOfLutemons().add(lutemon);
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

    public void setPlayer(Lutemon player, int playerNr){
        if(playerNr == 1){
            setPlayer1(player);
        }
        else{
            setPlayer2(player);
        }

    }

    public Boolean lutemonsSet(){
        if(player1Lutemon == null || player2Lutemon == null){
            return false;
        }
        return true;
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
    public void saveLutemons(ArrayList<Lutemon> lutemons, Context context) {
        try {
            FileOutputStream fileOut = context.openFileOutput("lutemons.ser", Context.MODE_PRIVATE);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(lutemons);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Loading lutemons
    public ArrayList<Lutemon> loadLutemons(Context context) {
        ArrayList<Lutemon> lutemons = new ArrayList<>();
        try {
            FileInputStream fileIn = context.openFileInput("lutemons.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            lutemons = (ArrayList<Lutemon>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return lutemons;
    }
public void setListOfLutemons(ArrayList listOfLutemons)
{
  this.listOfLutemons = listOfLutemons;
}
}
