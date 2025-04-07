package com.example.lutemonnikomatei.LutemonClasses;

import android.util.Log;

import com.example.lutemonnikomatei.enums.ATTACKTYPES;
import com.example.lutemonnikomatei.enums.BUFFTYPES;
import com.example.lutemonnikomatei.enums.DEBUFFTYPES;
import com.example.lutemonnikomatei.enums.LUTEMONTYPES;
import com.example.lutemonnikomatei.statuseffects.StatusEffect;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public abstract class Lutemon {
    String name;
    int speed;
    int maxHp;
    int damageTaken;
    int damageDealt;
    int hp;
    int maxStamina;
    int stamina;
    ArrayList<StatusEffect> statusEffects;
    LUTEMONTYPES type;
    int wins;
    int experience;
    int losses;
    double hpMultiplier;
    double speedMultiplier;
    double staminaMultiplier;

    public Lutemon(String name, double hpMultiplier, double speedMultiplier, double staminaMultiplier) {
        this.name = name;
        this.hpMultiplier = hpMultiplier;
        this.speedMultiplier = speedMultiplier;
        this.staminaMultiplier = staminaMultiplier;
        this.speed = assignSpeed();
        this.maxHp = assignMaxHp();
        this.maxStamina = assignMaxStamina();
        this.statusEffects = new ArrayList<StatusEffect>();
        this.wins = 0;
        this.losses = 0;
        this.experience = 0;
        this.damageDealt = 0;
        this.damageTaken = 0;
    }

    public abstract ArrayList<ATTACKTYPES> getAttacks();
    public abstract ArrayList<BUFFTYPES> getBuffs();
    public abstract ArrayList<DEBUFFTYPES> getDebuffs();

    public String getName() {
        return this.name;
    }

    public int getSpeed() {
        return this.speed;
    }

    public int getHp() {
        return this.hp;
    }
    public int getMaxHp() {return this.maxHp;}

    public int getStamina() {
        return this.stamina;
    }
    public int getMaxStamina() {return this.maxStamina;}
    public void addStatusEffect(StatusEffect eff) {
        this.statusEffects.add(eff);
    }
    public ArrayList<StatusEffect> getStatusEffects() {
        return this.statusEffects;
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
        int lowerBound = 80;
        int upperBound = 120;
        int maxHp = assignStat(hpMean, hpStandardDeviation, this.hpMultiplier, lowerBound, upperBound);
        this.hp = maxHp;
        return maxHp;
    }

    private int assignSpeed() {
        int speedMean = 10;
        int speedDeviation = 2;
        int lowerBound = 7;
        int upperBound = 13;
        return assignStat(speedMean, speedDeviation, this.speedMultiplier, lowerBound, upperBound);

    }

    private int assignMaxStamina() {
        int staminaMean = 10;
        int staminaDeviation = 2;
        int lowerBound = 7;
        int upperBound = 13;
        int stamina = assignStat(staminaMean, staminaDeviation, this.staminaMultiplier, lowerBound, upperBound);
        this.stamina = stamina;
        return stamina;

    }
    private int assignStat(int mean, int deviation, double multiplier, int lowerBound, int upperBound) {
        Random random = new Random();
        int baseStat;
        baseStat = (int) Math.floor(mean + deviation * random.nextGaussian());
        if (baseStat < lowerBound) {baseStat = lowerBound;}
        if (baseStat > upperBound) {baseStat = upperBound;}

        return (int) Math.floor(baseStat * multiplier);
    }
    public void setMaxHp(int Hp)
    {
        this.maxHp = Hp;
    }
    public void setMaxStamina(int Stamina){this.stamina = Stamina;}
    public void setSpeed(int speed){this.speed = speed;}

    public double getHpMultiplier()
    {
        return this.hpMultiplier;
    }
}
