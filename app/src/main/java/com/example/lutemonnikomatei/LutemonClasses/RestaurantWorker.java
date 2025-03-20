package com.example.lutemonnikomatei.LutemonClasses;

import com.example.lutemonnikomatei.enums.AttackTypes;
import com.example.lutemonnikomatei.enums.DebuffTypes;
import com.example.lutemonnikomatei.enums.HealTypes;

import java.util.ArrayList;

public class RestaurantWorker extends Lutemon{

    public ArrayList<AttackTypes> attacks;
    public ArrayList<DebuffTypes> debuffs;
    public ArrayList<HealTypes> heals;
    public ArrayList<AttackTypes> weaknesses;
    public RestaurantWorker(String name) {
        super(name);
        this.hpMultiplier = 1.7;
        this.attackMultiplier = 0.8;
        this.speedMultiplier = 0.6;
    }
}
