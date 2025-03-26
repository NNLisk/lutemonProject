package com.example.lutemonnikomatei.enums;

public enum ATTACKTYPES {
    SLASH(3, 30),
    PUNCH(2, 20),
    PIERCE(3, 27),
    PROJECTILE(2, 25),
    SLAM(6, 45);
    private final int cost;
    private final int baseDamage;

    ATTACKTYPES(int cost, int baseDamage) {
        this.cost = cost;
        this.baseDamage = baseDamage;
    }

    public int getCost() {
        return cost;
    }

    public int getBaseDamage() {
        return baseDamage;
    }
}


