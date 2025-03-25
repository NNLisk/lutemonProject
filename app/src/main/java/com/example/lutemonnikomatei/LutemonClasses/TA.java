package com.example.lutemonnikomatei.LutemonClasses;

import com.example.lutemonnikomatei.enums.ATTACKTYPES;
import com.example.lutemonnikomatei.enums.DEBUFFTYPES;
import com.example.lutemonnikomatei.enums.HEALTYPES;

import java.util.ArrayList;

public class TA extends Lutemon{
    public ArrayList<ATTACKTYPES> attacks;
    public ArrayList<DEBUFFTYPES> debuffs;
    public ArrayList<HEALTYPES> heals;
    public ArrayList<ATTACKTYPES> weaknesses;
    public TA(String name) {
        super(name);
        this.hpMultiplier = 1;
        this.attackMultiplier = 1;
        this.speedMultiplier = 1;

        this.attacks = new ArrayList<ATTACKTYPES>();
        this.heals = new ArrayList<HEALTYPES>();
        this.debuffs = new ArrayList<DEBUFFTYPES>();

        attacks.add(ATTACKTYPES.SLASH);
        attacks.add(ATTACKTYPES.PIERCE);
        debuffs.add(DEBUFFTYPES.WEAKEN);
        heals.add(HEALTYPES.BATTERY);
    }
}
