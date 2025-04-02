package com.example.lutemonnikomatei.LutemonClasses;

import com.example.lutemonnikomatei.enums.ATTACKTYPES;
import com.example.lutemonnikomatei.enums.DEBUFFTYPES;
import com.example.lutemonnikomatei.enums.BUFFTYPES;
import com.example.lutemonnikomatei.enums.LUTEMONTYPES;

import java.util.ArrayList;

public class TA extends Lutemon{
    public ArrayList<ATTACKTYPES> attacks;
    public ArrayList<DEBUFFTYPES> debuffs;
    public ArrayList<BUFFTYPES> heals;
    public ArrayList<ATTACKTYPES> weaknesses;
    public TA(String name) {
        super(name, LUTEMONTYPES.TA);
        this.hpMultiplier = 1;
        this.staminaMultiplier = 1;
        this.speedMultiplier = 1;

        this.attacks = new ArrayList<ATTACKTYPES>();
        this.heals = new ArrayList<BUFFTYPES>();
        this.debuffs = new ArrayList<DEBUFFTYPES>();

        attacks.add(ATTACKTYPES.SLASH);
        attacks.add(ATTACKTYPES.PIERCE);
        debuffs.add(DEBUFFTYPES.WEAKEN);
        heals.add(BUFFTYPES.BATTERY);
    }

    
    public ArrayList<ATTACKTYPES> getAttacks() {
        return this.attacks;
    }
    public ArrayList<DEBUFFTYPES> getDebuffs() {
        return this.debuffs;
    }
    public ArrayList<BUFFTYPES> getBuffs() {
        return this.getBuffs;
    }
}
