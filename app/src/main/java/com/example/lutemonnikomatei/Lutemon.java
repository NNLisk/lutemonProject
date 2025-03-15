package com.example.lutemonnikomatei;

import com.example.lutemonnikomatei.enums.LutemonTypes;

public class Lutemon {
    String name;
    int speed;
    int maxHp;
    int hp;
    LutemonTypes type;
    int attack;
    int wins;
    int losses;

    public Lutemon(String name, int speed, int maxhp, int attack) {
        this.name = name;
        this.speed = speed;
        this.maxHp = assignMaxHp();
        this.hp = maxhp;
        this.attack = attack;
        this.wins = 0;
        this.losses = 0;
    }

    public void fetchLutemonType() {

    }

    public String getName() {
        return this.name;
    }

    public int getSpeed() {
        return this.speed;
    }

    public int getMaxHp() {
        return this.maxHp;
    }

    public int getHp() {
        return this.hp;
    }

    public LutemonTypes getType() {
        return this.type;
    }

    public int getAttack() {
        return this.attack;
    }

    private int assignMaxHp() {
        int hp = 100;

        int constant = (int) (Math.random() * 40) - 20;

        return hp + constant;
    }
}
