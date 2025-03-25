package com.example.lutemonnikomatei.LutemonClasses;

import com.example.lutemonnikomatei.enums.ATTACKTYPES;
import com.example.lutemonnikomatei.enums.HEALTYPES;

import java.util.ArrayList;

public class SecurityGuard extends Lutemon{

    public ArrayList<ATTACKTYPES> attacks;
    public ArrayList<HEALTYPES> heals;
    public ArrayList<ATTACKTYPES> weaknesses;
    public SecurityGuard(String name) {
        super(name);
        this.hpMultiplier = 0.8;
        this.staminaMultiplier = 1.3;
        this.speedMultiplier = 1.4;

        this.attacks = new ArrayList<ATTACKTYPES>();
        this.heals = new ArrayList<HEALTYPES>();

        attacks.add(ATTACKTYPES.PUNCH);
        attacks.add(ATTACKTYPES.PROJECTILE);
        attacks.add(ATTACKTYPES.SLAM);
        heals.add(HEALTYPES.HEAL);
    }
}
