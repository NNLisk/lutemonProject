package com.example.lutemonnikomatei.LutemonClasses;

import com.example.lutemonnikomatei.enums.AttackTypes;
import com.example.lutemonnikomatei.enums.DebuffTypes;
import com.example.lutemonnikomatei.enums.HealTypes;

import java.util.ArrayList;

public class Student extends Lutemon{
    public ArrayList<AttackTypes> attacks;
    public ArrayList<DebuffTypes> debuffs;
    public ArrayList<HealTypes> heals;
    public ArrayList<AttackTypes> weaknesses;

    public Student(String name) {
        super(name);
        this.hpMultiplier = 1.2;
        this.attackMultiplier = 0.9;
        this.speedMultiplier = 1.5;
    }
}
