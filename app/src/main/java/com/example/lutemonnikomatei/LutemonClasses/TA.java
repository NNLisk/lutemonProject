package com.example.lutemonnikomatei.LutemonClasses;

import com.example.lutemonnikomatei.enums.AttackTypes;
import com.example.lutemonnikomatei.enums.DebuffTypes;
import com.example.lutemonnikomatei.enums.HealTypes;

import java.util.ArrayList;

public class TA extends Lutemon{
    public ArrayList<AttackTypes> attacks;
    public ArrayList<DebuffTypes> debuffs;
    public ArrayList<HealTypes> heals;
    public ArrayList<AttackTypes> weaknesses;
    public TA(String name) {
        super(name);
        this.hpMultiplier = 1;
        this.attackMultiplier = 1;
        this.speedMultiplier = 1;
    }
}
