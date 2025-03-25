package com.example.lutemonnikomatei.LutemonClasses;

import com.example.lutemonnikomatei.enums.LUTEMONTYPES;
import java.util.Random;

public class Lutemon {
    String name;
    int speed;
    int maxHp;
    int damageTaken;
    int damageDealt;
    int hp;
    int stamina;
    LUTEMONTYPES type;
    int attack;
    int wins;
    int experience;
    int losses;
    double hpMultiplier;
    double speedMultiplier;
    double attackMultiplier;

    public Lutemon(String name) {
        this.name = name;
        this.speed = assignSpeed();
        this.maxHp = assignMaxHp();
        this.attack = assignAttackPower();
        this.stamina = assignStamina();
        this.wins = 0;
        this.losses = 0;
        this.experience = 0;
        this.damageDealt = 0;
        this.damageTaken = 0;
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

    public LUTEMONTYPES getType() {
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
        this.hp = baseHp;
        return baseHp;
    }

    private int assignSpeed() {
        return 0;
    }

    private int assignAttackPower() {
        return 0;
    }

    private int assignStamina() {
        return 0;
    }
}
