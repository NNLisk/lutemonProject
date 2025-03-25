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

    public static boolean handleAttack(Lutemon attacking, Lutemon receiving, ATTACKTYPES attack) {
        // maybe attack messages here
        if (attacking.getStamina() < attack.getCost()){
            return false;
        }
        receiving.decreaseHealth(attack.getBaseDamage());
        attacking.decreaseStamina(attack.getCost());
        return true;
    }

    public static void handleBuffing(Lutemon lutemon, HEALTYPES buff) {

    }

    public static void handleDebuffing(Lutemon attacking, Lutemon receiving, DEBUFFTYPES debuff) {

    }

    public static void handleBattleEnd(Lutemon winner, Lutemon loser) {
        winner.addWin();
        loser.addLoss();
        winner.restoreHealth();
        loser.restoreHealth();
    }
}
