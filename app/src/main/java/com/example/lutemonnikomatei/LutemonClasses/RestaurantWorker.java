package com.example.lutemonnikomatei.LutemonClasses;

import com.example.lutemonnikomatei.enums.ATTACKTYPES;
import com.example.lutemonnikomatei.enums.DEBUFFTYPES;
import com.example.lutemonnikomatei.enums.BUFFTYPES;
import com.example.lutemonnikomatei.enums.LUTEMONTYPES;

import java.util.ArrayList;

public class RestaurantWorker extends Lutemon{

    public ArrayList<ATTACKTYPES> attacks;
    public ArrayList<DEBUFFTYPES> debuffs;
    public ArrayList<BUFFTYPES> buffs;
    public ArrayList<ATTACKTYPES> weaknesses;
    public RestaurantWorker(String name) {
        super(name, LUTEMONTYPES.RESTAURANT_WORKER);
        this.hpMultiplier = 1.7;
        this.staminaMultiplier = 0.8;
        this.speedMultiplier = 0.6;

        this.attacks = new ArrayList<ATTACKTYPES>();
        this.buffs = new ArrayList<BUFFTYPES>();
        this.debuffs = new ArrayList<DEBUFFTYPES>();

        attacks.add(ATTACKTYPES.PUNCH);
        attacks.add(ATTACKTYPES.SLASH);
        debuffs.add(DEBUFFTYPES.MYSTERYDEBUFF);
        buffs.add(BUFFTYPES.MYSTERYBUFF);
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
