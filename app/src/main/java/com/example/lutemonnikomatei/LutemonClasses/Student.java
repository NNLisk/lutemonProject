package com.example.lutemonnikomatei.LutemonClasses;

import com.example.lutemonnikomatei.enums.ATTACKTYPES;
import com.example.lutemonnikomatei.enums.DEBUFFTYPES;
import com.example.lutemonnikomatei.enums.BUFFTYPES;
import com.example.lutemonnikomatei.enums.LUTEMONTYPES;

import java.util.ArrayList;

public class Student extends Lutemon{
    public ArrayList<ATTACKTYPES> attacks;
    public ArrayList<DEBUFFTYPES> debuffs;
    public ArrayList<BUFFTYPES> buffs;
    public ArrayList<ATTACKTYPES> weaknesses;

    public Student(String name) {
        super(name, 1.2, 0.9, 1.5,1);
        this.type = LUTEMONTYPES.STUDENT;

        this.attacks = new ArrayList<ATTACKTYPES>();
        this.buffs = new ArrayList<BUFFTYPES>();
        this.debuffs = new ArrayList<DEBUFFTYPES>();

        attacks.add(ATTACKTYPES.PUNCH);
        attacks.add(ATTACKTYPES.PROJECTILE);
        buffs.add(BUFFTYPES.BATTERY);
        buffs.add(BUFFTYPES.HEAL);


    }

    
    public ArrayList<ATTACKTYPES> getAttacks() {
        return this.attacks;
    }
    public ArrayList<DEBUFFTYPES> getDebuffs() {
        return this.debuffs;
    }
    public ArrayList<BUFFTYPES> getBuffs() {
        return this.buffs;
    }
}
