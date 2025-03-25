package com.example.lutemonnikomatei.LutemonClasses;

import com.example.lutemonnikomatei.enums.ATTACKTYPES;
import com.example.lutemonnikomatei.enums.DEBUFFTYPES;
import com.example.lutemonnikomatei.enums.HEALTYPES;

import java.util.ArrayList;

public class RestaurantWorker extends Lutemon{

    public ArrayList<ATTACKTYPES> attacks;
    public ArrayList<DEBUFFTYPES> debuffs;
    public ArrayList<HEALTYPES> heals;
    public ArrayList<ATTACKTYPES> weaknesses;
    public RestaurantWorker(String name) {
        super(name);
        this.hpMultiplier = 1.7;
        this.staminaMultiplier = 0.8;
        this.speedMultiplier = 0.6;

        this.attacks = new ArrayList<ATTACKTYPES>();
        this.heals = new ArrayList<HEALTYPES>();
        this.debuffs = new ArrayList<DEBUFFTYPES>();

        attacks.add(ATTACKTYPES.PUNCH);
        attacks.add(ATTACKTYPES.SLASH);
        debuffs.add(DEBUFFTYPES.MYSTERYDEBUFF);
        heals.add(HEALTYPES.MYSTERYBUFF);
    }
}
