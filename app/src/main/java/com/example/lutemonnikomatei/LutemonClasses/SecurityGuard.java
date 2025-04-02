package com.example.lutemonnikomatei.LutemonClasses;

import com.example.lutemonnikomatei.enums.ATTACKTYPES;
import com.example.lutemonnikomatei.enums.BUFFTYPES;
import com.example.lutemonnikomatei.enums.DEBUFFTYPES;
import com.example.lutemonnikomatei.enums.LUTEMONTYPES;

import java.util.ArrayList;

public class SecurityGuard extends Lutemon{

    public ArrayList<ATTACKTYPES> attacks;
    public ArrayList<BUFFTYPES> buffs;
    public ArrayList<ATTACKTYPES> weaknesses;
    public SecurityGuard(String name) {
        super(name, LUTEMONTYPES.SECURITY_GUARD);
        this.hpMultiplier = 0.8;
        this.staminaMultiplier = 1.3;
        this.speedMultiplier = 1.4;

        this.attacks = new ArrayList<ATTACKTYPES>();
        this.buffs = new ArrayList<BUFFTYPES>();

        attacks.add(ATTACKTYPES.PUNCH);
        attacks.add(ATTACKTYPES.PROJECTILE);
        attacks.add(ATTACKTYPES.SLAM);
        buffs.add(BUFFTYPES.HEAL);
    }

    
    public ArrayList<ATTACKTYPES> getAttacks() {
        return this.attacks;
    }

    public ArrayList<DEBUFFTYPES> getDebuffs(){return null;};
    public ArrayList<BUFFTYPES> getBuffs() {
        return this.buffs;
    }
}
