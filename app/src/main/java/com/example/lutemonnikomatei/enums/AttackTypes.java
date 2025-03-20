package com.example.lutemonnikomatei.enums;

public enum AttackTypes {
    SLASH(3),
    PUNCH(2),
    PIERCE(3),
    PROJECTILE(2),
    SLAM(5);
    private final int cost;

    AttackTypes(int cost) {
        this.cost = cost;
    }

    private int getCost() {
        return cost;
    }
}
