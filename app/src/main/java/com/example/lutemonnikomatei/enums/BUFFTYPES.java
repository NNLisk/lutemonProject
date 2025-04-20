package com.example.lutemonnikomatei.enums;

import java.io.Serializable;

public enum BUFFTYPES implements Serializable {
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
