package com.example.lutemonnikomatei.enums;

public enum DEBUFFTYPES {
    POISON(2),
    CONFUSION(4),
    WEAKEN(3),
    MYSTERYDEBUFF(4);

    private final int cost;
    DEBUFFTYPES(int cost) {
        this.cost = cost;
    }

    private int getCost() {
        return cost;
    }
}
