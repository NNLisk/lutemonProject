package com.example.lutemonnikomatei;

import com.example.lutemonnikomatei.LutemonClasses.Lutemon;
import com.example.lutemonnikomatei.enums.ATTACKTYPES;
import com.example.lutemonnikomatei.enums.DEBUFFTYPES;
import com.example.lutemonnikomatei.enums.HEALTYPES;

public class BattleManager {

    Lutemon startingLutemon = null;
    Lutemon nonStartingLutemon = null;
    public Lutemon startBattle(Lutemon player1, Lutemon player2) {

        getPlayerOrder(player1, player2);

        Lutemon winner = null;

        while(player1.getHp() > 0 && player2.getHp() > 0) {
            //battle
        }

        return winner;
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


    private void handleBuffing(Lutemon lutemon, HEALTYPES buff) {

    }

    private void handleDebuffing(Lutemon attacking, Lutemon receiving, DEBUFFTYPES debuff) {

    }

    private void handleBattleEnd(Lutemon winner, Lutemon loser) {
        winner.addWin();
        loser.addLoss();
        winner.restoreHealth();
        loser.restoreHealth();
        startingLutemon = nonStartingLutemon = null; // clears the lutemons from the battle
    }

    private void getPlayerOrder(Lutemon player1, Lutemon player2) {
        if (player1.getSpeed() < player2.getSpeed()) {
            startingLutemon = player2;
            nonStartingLutemon = player1;
        } else {
            startingLutemon = player1;
            nonStartingLutemon = player2;
        }
    }
}
