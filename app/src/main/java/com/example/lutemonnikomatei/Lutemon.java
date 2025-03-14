package com.example.lutemonnikomatei;

import androidx.appcompat.widget.AppCompatRadioButton;
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
        this.maxHp = maxhp;
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
}
