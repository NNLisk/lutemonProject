package com.example.lutemonnikomatei;

import com.example.lutemonnikomatei.LutemonClasses.Lutemon;

public interface BattleListener {
    void onTurnStart(Lutemon currentPlayer);
    void onGameOver(Lutemon winner);
}
