package com.example.lutemonnikomatei;

import com.example.lutemonnikomatei.LutemonClasses.Lutemon;
import com.example.lutemonnikomatei.enums.ATTACKTYPES;
import com.example.lutemonnikomatei.enums.DEBUFFTYPES;
import com.example.lutemonnikomatei.enums.HEALTYPES;

public class BattleManager {

    public static BattleManager battleManagerInstance = null;
    private BattleManager() {

    }

    public static BattleManager getInstance() {
        if (battleManagerInstance == null) {
            battleManagerInstance = new BattleManager();
        }
        return battleManagerInstance;
    }

    public static void handleAttack(Lutemon attacking, Lutemon receiving, ATTACKTYPES attack) {

    }

    public static void handleBuffing(Lutemon lutemon, HEALTYPES buff) {

    }

    public static void handleDebuffing(Lutemon attacking, Lutemon receiving, DEBUFFTYPES debuff) {

    }

    public static void handleBattleEnd(Lutemon winner, Lutemon loser) {
        winner.addWin();
        loser.addLoss();
        winner.
    }
}
