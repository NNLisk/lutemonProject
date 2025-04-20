package com.example.lutemonnikomatei.statuseffects;

import com.example.lutemonnikomatei.LutemonClasses.Lutemon;

import java.io.Serializable;

public interface StatusEffect  {
    String name = null;

    public String getName();
    void applyEffect(Lutemon lutemon);
    boolean isExpired();
    int getTurnsLeft();
}
