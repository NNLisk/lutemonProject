package com.example.lutemonnikomatei.LutemonClasses;

import com.example.lutemonnikomatei.enums.AttackTypes;
import com.example.lutemonnikomatei.enums.DebuffTypes;
import com.example.lutemonnikomatei.enums.HealTypes;

import java.util.ArrayList;

public class Teacher extends Lutemon{

    public ArrayList<AttackTypes> attacks;
    public ArrayList<DebuffTypes> debuffs;
    public ArrayList<HealTypes> heals;
    public ArrayList<AttackTypes> weaknesses;

    public Teacher(String name) {
        super(name);
        this.hpMultiplier = 1;
        this.attackMultiplier = 1.1;
        this.speedMultiplier = 0.8;
    }
}
