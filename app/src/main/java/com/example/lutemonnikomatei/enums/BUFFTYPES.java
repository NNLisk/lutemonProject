package com.example.lutemonnikomatei.enums;

public enum BUFFTYPES {
    BATTERY(0),
    HEAL(3),
    PASSIVEHEAL(6),

    MYSTERYBUFF(4);

    private final int cost;
    BUFFTYPES(int value) {
        this.cost = value;
    }

    public int getCost() {
        return cost;
    }

}
