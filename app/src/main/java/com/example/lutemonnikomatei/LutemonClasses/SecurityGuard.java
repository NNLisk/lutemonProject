package com.example.lutemonnikomatei.LutemonClasses;

import com.example.lutemonnikomatei.enums.AttackTypes;
import com.example.lutemonnikomatei.enums.DebuffTypes;
import com.example.lutemonnikomatei.enums.HealTypes;

import java.util.ArrayList;

public class SecurityGuard extends Lutemon{

    public ArrayList<AttackTypes> attacks;
    public ArrayList<HealTypes> heals;
    public ArrayList<AttackTypes> weaknesses;
    public SecurityGuard(String name) {
        super(name);
        this.hpMultiplier = 0.8;
        this.attackMultiplier = 1.3;
        this.speedMultiplier = 1.4;
    }
}
