package com.example.lutemonnikomatei.enums;

public enum HEALTYPES {
    BATTERY(0),
    HEAL(3),
    PASSIVEHEAL(4),

    MYSTERYBUFF(4);

    private final int cost;
    HEALTYPES(int value) {
        this.cost = value;
    }

    public int getCost() {
        return cost;
    }

}
