package com.example.lutemonnikomatei.enums;

public enum HealTypes{
    BATTERY(0),
    HEAL(3),
    PASSIVEHEAL(4);

    private final int cost;
    HealTypes (int value) {
        this.cost = value;
    }

    public int getCost() {
        return cost;
    }

}
