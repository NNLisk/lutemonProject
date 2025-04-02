package com.example.lutemonnikomatei.LutemonClasses;

import com.example.lutemonnikomatei.enums.ATTACKTYPES;
import com.example.lutemonnikomatei.enums.DEBUFFTYPES;
import com.example.lutemonnikomatei.enums.BUFFTYPES;
import com.example.lutemonnikomatei.enums.LUTEMONTYPES;

import java.util.ArrayList;

public class Teacher extends Lutemon{

    public ArrayList<ATTACKTYPES> attacks;
    public ArrayList<DEBUFFTYPES> debuffs;
    public ArrayList<BUFFTYPES> buffs;
    public ArrayList<ATTACKTYPES> weaknesses;

    public Teacher(String name) {
        super(name, LUTEMONTYPES.TEACHER);
        this.hpMultiplier = 1;
        this.staminaMultiplier = 1.1;
        this.speedMultiplier = 0.8;

        this.attacks = new ArrayList<ATTACKTYPES>();
        this.buffs = new ArrayList<BUFFTYPES>();
        this.debuffs = new ArrayList<DEBUFFTYPES>();

        attacks.add(ATTACKTYPES.PUNCH);
        attacks.add(ATTACKTYPES.PROJECTILE);
        debuffs.add(DEBUFFTYPES.CONFUSION);
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
