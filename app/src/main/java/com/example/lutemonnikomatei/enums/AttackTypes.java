package com.example.lutemonnikomatei.enums;

public enum AttackTypes {
    SLASH(3, 30),
    PUNCH(2, 20),
    PIERCE(3, 27),
    PROJECTILE(2, 25),
    SLAM(6, 45);
    private final int cost;
    private final int baseDamage;

    AttackTypes(int cost, int baseDamage) {
        this.cost = cost;
        this.baseDamage = baseDamage;
    }

    private int getCost() {
        return cost;
    }

    private int getBaseDamage() {
        return baseDamage;
    }
}
