package com.example.lutemonnikomatei.enums;

public enum HealTypes{
    Battery(0),
    Heal(3),
    PassiveHeal(4);

    private final int cost;
    HealTypes (int value) {
        this.cost = value;
    }

    public int getCost() {
        return cost;
    }

}
