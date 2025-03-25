package com.example.lutemonnikomatei.LutemonClasses;

import com.example.lutemonnikomatei.enums.ATTACKTYPES;
import com.example.lutemonnikomatei.enums.DEBUFFTYPES;
import com.example.lutemonnikomatei.enums.HEALTYPES;

import java.util.ArrayList;

public class Student extends Lutemon{
    public ArrayList<ATTACKTYPES> attacks;
    public ArrayList<DEBUFFTYPES> debuffs;
    public ArrayList<HEALTYPES> heals;
    public ArrayList<ATTACKTYPES> weaknesses;

    public Student(String name) {
        super(name);
        this.hpMultiplier = 1.2;
        this.attackMultiplier = 0.9;
        this.speedMultiplier = 1.5;

        this.attacks = new ArrayList<ATTACKTYPES>();
        this.heals = new ArrayList<HEALTYPES>();
        this.debuffs = new ArrayList<DEBUFFTYPES>();

        attacks.add(ATTACKTYPES.PUNCH);
        heals.add(HEALTYPES.BATTERY);
        heals.add(HEALTYPES.HEAL);
        debuffs.add(DEBUFFTYPES.CONFUSION);

    }
}
