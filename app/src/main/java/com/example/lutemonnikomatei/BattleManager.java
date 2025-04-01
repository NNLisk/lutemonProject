package com.example.lutemonnikomatei;

import com.example.lutemonnikomatei.LutemonClasses.Lutemon;
import com.example.lutemonnikomatei.enums.ATTACKTYPES;
import com.example.lutemonnikomatei.enums.DEBUFFTYPES;
import com.example.lutemonnikomatei.enums.HEALTYPES;
import com.example.lutemonnikomatei.Exceptions.*;
import com.example.lutemonnikomatei.statuseffects.MultiturnHeal;
import com.example.lutemonnikomatei.statuseffects.StatusEffect;

import java.util.Iterator;

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
        listener.onTurnStart();
    }

    public void onPlayerAttackSelected(ATTACKTYPES attack) throws OutOfStamina {

        if (isGameOver) {
            return;
        }
        if (!handleAttack(currentPlayer, receivingPlayer, attack)) {
            throw new OutOfStamina();
        }

        if (checkIfBattleOver(currentPlayer, receivingPlayer)) {
            isGameOver = true;
            listener.onGameOver();
            return;
        }

        switchPlayers();
        listener.onTurnStart();
    }
    public void onPlayerBuffSelected(HEALTYPES buff) {
        if (isGameOver) {
            return;
        }

        handleBuffing(currentPlayer, buff);

        switchPlayers();
        listener.onTurnStart();
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


    private boolean handleBuffing(Lutemon lutemon, HEALTYPES buff) {
        if (lutemon.getStamina() < buff.getCost()) {
            return false;
        }
        switch(buff){
            case HEAL:
                lutemon.heal((int) Math.floor(lutemon.getMaxHp()*0.3));
                break;

            case BATTERY:
                lutemon.restoreStamina();
                break;

            case PASSIVEHEAL:
                StatusEffect eff = new MultiturnHeal(3);
                lutemon.addStatusEffect(eff);
                eff.applyEffect(currentPlayer);
                break;
        }
        return true;
    }


    private void handleDebuffing(Lutemon attacking, Lutemon receiving, DEBUFFTYPES debuff) {

    }
    public void processStatusEffects(Lutemon lutemon) {
        Iterator<StatusEffect> iterator = lutemon.getStatusEffects().iterator();
        while (iterator.hasNext()) {
            StatusEffect effect = iterator.next();
            effect.applyEffect(lutemon);
            if (effect.isExpired()) {
                iterator.remove();
            }
        }
    }

    private boolean checkIfBattleOver(Lutemon p1, Lutemon p2) {
        return p1.getHp() == 0 || p2.getHp() == 0;
    }

    private void handleBattleEnd(Lutemon winner, Lutemon loser) {
        winner.addWin();
        loser.addLoss();
        winner.restoreHealth();
        loser.restoreHealth();
        winner.restoreStamina();
        loser.restoreStamina();
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


