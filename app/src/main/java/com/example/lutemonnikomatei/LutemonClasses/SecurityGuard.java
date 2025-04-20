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
        super(name, 0.8, 1.4, 1.3,1.8);
        this.type = LUTEMONTYPES.SECURITY_GUARD;

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
