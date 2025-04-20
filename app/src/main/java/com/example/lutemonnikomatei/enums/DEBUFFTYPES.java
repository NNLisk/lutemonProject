package com.example.lutemonnikomatei.enums;

import java.io.Serializable;

public enum DEBUFFTYPES implements Serializable {
    POISON(2),
    CONFUSION(4),
    WEAKEN(3);

    private final int cost;
    DEBUFFTYPES(int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }
}
