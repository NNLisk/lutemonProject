package com.example.lutemonnikomatei.LutemonClasses;

import com.example.lutemonnikomatei.enums.ATTACKTYPES;
import com.example.lutemonnikomatei.enums.DEBUFFTYPES;
import com.example.lutemonnikomatei.enums.HEALTYPES;
import com.example.lutemonnikomatei.enums.LUTEMONTYPES;

import java.util.ArrayList;

public class Janne extends Lutemon {
    public ArrayList<ATTACKTYPES> attacks;
    public ArrayList<DEBUFFTYPES> debuffs;
    public ArrayList<HEALTYPES> heals;
    public Janne(String name) {
        super(name, LUTEMONTYPES.Janne);
        this.hpMultiplier = 3;
        this.staminaMultiplier = 3;
        this.speedMultiplier = 3;

        this.attacks = new ArrayList<ATTACKTYPES>();
        this.heals = new ArrayList<HEALTYPES>();
        this.debuffs = new ArrayList<DEBUFFTYPES>();

        attacks.add(ATTACKTYPES.SLAM);
        attacks.add(ATTACKTYPES.PROJECTILE);
        heals.add(HEALTYPES.HEAL);
        debuffs.add(DEBUFFTYPES.CONFUSION);
    }

    public ArrayList<ATTACKTYPES> getAttacks() {
        return this.attacks;
    }
    public ArrayList<DEBUFFTYPES> getDebuffs() {
        return this.debuffs;
    }
    public ArrayList<HEALTYPES> getBuffs() {
        return this.getBuffs;
    }
}
