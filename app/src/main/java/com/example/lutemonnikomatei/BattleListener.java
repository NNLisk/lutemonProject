package com.example.lutemonnikomatei;

import com.example.lutemonnikomatei.LutemonClasses.Lutemon;

public interface BattleListener {
    void onTurnStart(Lutemon lutemon);
    void onGameOver();

    void updateBattleMessage(String message);
}
