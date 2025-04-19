package com.example.lutemonnikomatei.statuseffects;

import com.example.lutemonnikomatei.LutemonClasses.Lutemon;

public class MultiturnHeal implements StatusEffect{

    int remainingTurns;
    String name;

    public MultiturnHeal(int turns) {
        this.remainingTurns = turns;
        this.name = "Passive Heal";
    }

    public String getName() {
        return this.name;
    }
    @Override
    public void applyEffect(Lutemon lutemon) {
        lutemon.heal((int) Math.floor(lutemon.getMaxHp() * 0.10));
        this.remainingTurns -= 1;
    }

    @Override
    public boolean isExpired() {
        return remainingTurns == 0;
    }

    @Override
    public int getTurnsLeft() {
        return remainingTurns;
    }
}
