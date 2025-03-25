package com.example.lutemonnikomatei.LutemonClasses;

import com.example.lutemonnikomatei.enums.ATTACKTYPES;
import com.example.lutemonnikomatei.enums.DEBUFFTYPES;
import com.example.lutemonnikomatei.enums.HEALTYPES;

import java.util.ArrayList;

public class Teacher extends Lutemon{

    public ArrayList<ATTACKTYPES> attacks;
    public ArrayList<DEBUFFTYPES> debuffs;
    public ArrayList<HEALTYPES> heals;
    public ArrayList<ATTACKTYPES> weaknesses;

    public Teacher(String name) {
        super(name);
        this.hpMultiplier = 1;
        this.staminaMultiplier = 1.1;
        this.speedMultiplier = 0.8;

        this.attacks = new ArrayList<ATTACKTYPES>();
        this.heals = new ArrayList<HEALTYPES>();
        this.debuffs = new ArrayList<DEBUFFTYPES>();

        attacks.add(ATTACKTYPES.PUNCH);
        attacks.add(ATTACKTYPES.PROJECTILE);
        debuffs.add(DEBUFFTYPES.CONFUSION);
        heals.add(HEALTYPES.HEAL);

    }
}
