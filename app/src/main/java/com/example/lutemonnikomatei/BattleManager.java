package com.example.lutemonnikomatei;

import com.example.lutemonnikomatei.LutemonClasses.Lutemon;
import com.example.lutemonnikomatei.enums.ATTACKTYPES;
import com.example.lutemonnikomatei.enums.DEBUFFTYPES;
import com.example.lutemonnikomatei.enums.BUFFTYPES;
import com.example.lutemonnikomatei.Exceptions.*;
import com.example.lutemonnikomatei.statuseffects.MultiturnHeal;
import com.example.lutemonnikomatei.statuseffects.StatusEffect;

import java.util.Iterator;

public class BattleManager {

    Lutemon currentPlayer;
    Lutemon receivingPlayer;

    Lutemon winner = null;
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

    public void setCurrentPlayer(Lutemon player1) {
        this.currentPlayer = player1;
    }

    public void setReceivingPlayer(Lutemon player2) {
        this.receivingPlayer = player2;
    }

    public void onPlayerAttackSelected(ATTACKTYPES attack) throws OutOfStamina {
        if (isGameOver) {
            return;
        }
        processStatusEffects(currentPlayer);
        if (!handleAttack(currentPlayer, receivingPlayer, attack)) {
            throw new OutOfStamina();
        }

        winner = checkIfBattleOver(currentPlayer, receivingPlayer);
        if (winner != null) {
            isGameOver = true;
            handleBattleEnd(winner, (winner == currentPlayer) ? receivingPlayer : currentPlayer);
            listener.onGameOver();
            return;
        }

        switchPlayers();
        listener.onTurnStart(currentPlayer);
    }
    public void onPlayerBuffSelected(BUFFTYPES buff) throws OutOfStamina {

        if (isGameOver) {
            return;
        }
        processStatusEffects(currentPlayer);
        if (!handleBuffing(currentPlayer, buff)) {
            throw new OutOfStamina();
        }

        winner = checkIfBattleOver(currentPlayer, receivingPlayer);
        if (winner != null) {
            isGameOver = true;
            handleBattleEnd(winner, (winner == currentPlayer) ? receivingPlayer : currentPlayer);
            listener.onGameOver();
            return;
        }
        switchPlayers();
        listener.onTurnStart(currentPlayer);
    }
    public void onPlayerDebuffSelected(DEBUFFTYPES debuff) throws OutOfStamina{
        if (isGameOver) {
            return;
        }
        processStatusEffects(currentPlayer);

        if (!handleDebuffing(currentPlayer, receivingPlayer, debuff)) {
            throw new OutOfStamina();
        }

        winner = checkIfBattleOver(currentPlayer, receivingPlayer);
        if (winner != null) {
            isGameOver = true;
            handleBattleEnd(winner, (winner == currentPlayer) ? receivingPlayer : currentPlayer);
            listener.onGameOver();
            return;
        }
        switchPlayers();
        listener.onTurnStart(currentPlayer);
    }

    private boolean handleAttack(Lutemon attacking, Lutemon receiving, ATTACKTYPES attack) {

        if (attacking.getStamina() < attack.getCost()){
            return false;
        }
        receiving.decreaseHealth(attack.getBaseDamage());
        attacking.decreaseStamina(attack.getCost());
        return true;
    }


    private boolean handleBuffing(Lutemon lutemon, BUFFTYPES buff) {
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


    private boolean handleDebuffing(Lutemon attacking, Lutemon receiving, DEBUFFTYPES debuff) {
        if (attacking.getStamina() < debuff.getCost()) {
            return false;
        }
        return true;
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

    private Lutemon checkIfBattleOver(Lutemon p1, Lutemon p2) {
        if (p1.getHp() == 0) {
            return p1;
        }
        if (p2.getHp() == 0) {
            return p2;
        }
        return null;
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


