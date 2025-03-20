package com.example.lutemonnikomatei.LutemonClasses;

import com.example.lutemonnikomatei.enums.AttackTypes;
import com.example.lutemonnikomatei.enums.DebuffTypes;
import com.example.lutemonnikomatei.enums.HealTypes;
import com.example.lutemonnikomatei.enums.LutemonTypes;

import java.util.ArrayList;

public class Janne extends Lutemon {
    public ArrayList<AttackTypes> attacks;
    public ArrayList<DebuffTypes> debuffs;
    public ArrayList<HealTypes> heals;

    public Janne(String name) {
        super(name);
        this.type = LutemonTypes.Janne;
        this.hpMultiplier = 3;
        this.attackMultiplier = 3;
        this.speedMultiplier = 3;
    }
}
