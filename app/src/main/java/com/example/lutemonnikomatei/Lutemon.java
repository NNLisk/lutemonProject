package com.example.lutemonnikomatei;

import com.example.lutemonnikomatei.enums.LutemonTypes;
import java.util.Random;

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

    /*
     method generates maxHp for lutemon
     - Called only by constructor
     - Random and normally distributed
     - limited to 80-120 range without type multiplier
    */
    private int assignMaxHp() {
        int hpMean = 100;
        int hpStandardDeviation = 20;
        Random random = new Random();
        int baseHp = -1;

        while(baseHp < 80 || baseHp > 120) {
            baseHp = (int) Math.floor(hpMean + hpStandardDeviation * random.nextGaussian());
        }
        return baseHp;

    }
}
