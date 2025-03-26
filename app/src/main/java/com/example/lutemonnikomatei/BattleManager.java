package com.example.lutemonnikomatei;

import com.example.lutemonnikomatei.LutemonClasses.Lutemon;
import com.example.lutemonnikomatei.enums.ATTACKTYPES;
import com.example.lutemonnikomatei.enums.DEBUFFTYPES;
import com.example.lutemonnikomatei.enums.HEALTYPES;

public class BattleManager {

    Lutemon currentPlayer;
    BattleListener listener;
    boolean isGameOver;

    public BattleManager(BattleListener listener) {
        this.listener = listener;
        this.isGameOver = false;
    }
    public Lutemon startBattle(Lutemon player1, Lutemon player2) {

        listener.onTurnStart(currentPlayer);
    }

    private boolean handleAttack(Lutemon attacking, Lutemon receiving, ATTACKTYPES attack) {
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
    }

    public Lutemon getStartingPlayer(Lutemon player1, Lutemon player2) {
        if (player1.getSpeed() < player2.getSpeed()) {
            return player2;
        }
        return player1;
    }
}
