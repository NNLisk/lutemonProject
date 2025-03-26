package com.example.lutemonnikomatei;

import com.example.lutemonnikomatei.LutemonClasses.Lutemon;
import com.example.lutemonnikomatei.enums.ATTACKTYPES;
import com.example.lutemonnikomatei.enums.DEBUFFTYPES;
import com.example.lutemonnikomatei.enums.HEALTYPES;
import com.example.lutemonnikomatei.Exceptions.*;

public class BattleManager {

    Lutemon currentPlayer;
    Lutemon receivingPlayer;
    BattleListener listener;
    boolean isGameOver;

    public BattleManager(BattleListener listener) {
        this.listener = listener;
        this.isGameOver = false;
    }
    public void startBattle(Lutemon player1, Lutemon player2) {
        currentPlayer = getStartingPlayer(player1, player2);
        receivingPlayer = getReceivingLutemon(player1, player2);
        listener.onTurnStart(currentPlayer);
    }

    public void onPlayerAttackSelected(ATTACKTYPES attack) throws OutOfStamina {
        if (isGameOver) {
            return;
        }
        while (!handleAttack(currentPlayer, receivingPlayer, attack)) {
            throw new OutOfStamina();
        }

    }
    public void onPlayerBuffSelected(HEALTYPES buff) {
        if (isGameOver) {
            return;
        }

        handleBuffing(currentPlayer, buff);
    }
    public void onPlayerDebuffSelected(DEBUFFTYPES debuff) {
        if (isGameOver) {
            return;
        }

        handleDebuffing(currentPlayer, receivingPlayer, debuff);
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

    private Lutemon getReceivingLutemon(Lutemon player1, Lutemon player2) {
        return (player1.equals(currentPlayer)) ? player2 : player1;
    }

    private void switchPlayers() {
        Lutemon temp = currentPlayer;
        currentPlayer = receivingPlayer;
        receivingPlayer = temp;
    }
}
