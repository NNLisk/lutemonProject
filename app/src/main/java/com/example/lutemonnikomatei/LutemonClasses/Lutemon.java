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
    int maxStamina;
    int stamina;
    LUTEMONTYPES type;
    int wins;
    int experience;
    int losses;
    double hpMultiplier;
    double speedMultiplier;
    double staminaMultiplier;

    public Lutemon(String name, LUTEMONTYPES type) {
        this.name = name;
        this.speed = assignSpeed();
        this.maxHp = assignMaxHp();
        this.maxStamina = assignMaxStamina();
        this.type = type;
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

    public int getHp() {
        return this.hp;
    }

    public int getStamina() {
        return this.stamina;
    }

    public void addWin() {
        this.wins += 1;
    }

    public void addLoss() {
        this.losses += 1;
    }

    public void increaseLevel() {
        this.experience += 1;
    }

    public void restoreHealth() {
        this.hp = maxHp;
    }
    public void restoreStamina() {this.stamina = maxStamina;}

    public void decreaseHealth(int damage) {
        this.hp = Math.max(0, hp - damage);
    }

    public void heal(int amount) {
        this.hp = Math.min(maxHp, hp + amount);
    }

    public void decreaseStamina(int cost) {
        this.stamina -= cost;
    }


    public LUTEMONTYPES getType() {
        return this.type;
    }

    /*
     method generates maxHp for lutemon
     - Called only by constructor
     - Random and normally distributed
     - limited to 80-120 range without type multiplier
    */
    private int assignMaxHp() {
        int hpMean = 100;
        int hpStandardDeviation = 10;
        int baseHp = -1;
        int hpMultiplied; // this is the hp after multiplier by lutemon type
        Random random = new Random();

        while(baseHp < 80 || baseHp > 120) {
            baseHp = (int) Math.floor(hpMean + hpStandardDeviation * random.nextGaussian());
        }
        hpMultiplied = (int) Math.floor(baseHp * this.hpMultiplier);

        this.hp = hpMultiplied;
        return hpMultiplied;
    }

    private int assignSpeed() {
        int speedMean = 10;
        int speedDeviation = 2;
        int baseSpeed = -1;
        int speedMultiplied;
        Random random = new Random();

        while(baseSpeed < 7 || baseSpeed > 13) {
            baseSpeed = (int) Math.floor(speedMean + speedDeviation*random.nextGaussian());
        }
        speedMultiplied = (int) Math.floor(baseSpeed * this.speedMultiplier);

        this.stamina = speedMultiplied;
        return speedMultiplied;
    }

    private int assignMaxStamina() {
        int staminaMean = 10;
        int staminaDeviation = 2;
        int baseStamina = -1;
        int staminaMultiplied;
        Random random = new Random();

        while(baseStamina < 7 || baseStamina > 13) {
            baseStamina = (int) Math.floor(staminaMean + staminaDeviation*random.nextGaussian());
        }
        staminaMultiplied = (int) Math.floor(baseStamina * this.staminaMultiplier);

        this.stamina = staminaMultiplied;
        return staminaMultiplied;
    }
}
