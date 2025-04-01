package com.example.lutemonnikomatei.statuseffects;

import com.example.lutemonnikomatei.LutemonClasses.Lutemon;

public interface StatusEffect {
    void applyEffect(Lutemon lutemon);
    boolean isExpired();
    int getTurnsLeft();
}
