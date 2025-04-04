package com.example.lutemonnikomatei.LutemonClasses;

import com.example.lutemonnikomatei.enums.ATTACKTYPES;
import com.example.lutemonnikomatei.enums.DEBUFFTYPES;
import com.example.lutemonnikomatei.enums.BUFFTYPES;
import com.example.lutemonnikomatei.enums.LUTEMONTYPES;

import java.util.ArrayList;

public class Janne extends Lutemon {
    public ArrayList<ATTACKTYPES> attacks;
    public ArrayList<DEBUFFTYPES> debuffs;
    public ArrayList<BUFFTYPES> buffs;
    public Janne(String name) {
        super(name, 3, 3, 3);
        this.type = LUTEMONTYPES.Janne;

        this.attacks = new ArrayList<ATTACKTYPES>();
        this.buffs = new ArrayList<BUFFTYPES>();
        this.debuffs = new ArrayList<DEBUFFTYPES>();

        attacks.add(ATTACKTYPES.SLAM);
        attacks.add(ATTACKTYPES.PROJECTILE);
        buffs.add(BUFFTYPES.HEAL);
        debuffs.add(DEBUFFTYPES.CONFUSION);
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
