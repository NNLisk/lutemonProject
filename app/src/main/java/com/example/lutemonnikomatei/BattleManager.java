package com.example.lutemonnikomatei;

import android.util.Log;

import com.example.lutemonnikomatei.LutemonClasses.Lutemon;
import com.example.lutemonnikomatei.enums.ATTACKTYPES;
import com.example.lutemonnikomatei.enums.DEBUFFTYPES;
import com.example.lutemonnikomatei.enums.BUFFTYPES;
import com.example.lutemonnikomatei.Exceptions.*;
import com.example.lutemonnikomatei.statuseffects.MultiturnHeal;
import com.example.lutemonnikomatei.statuseffects.StatusEffect;

import java.util.Iterator;

/*
* BATTLE MANAGER CLASS
* --------------------
* HANDLES ALL BATTLE LOGIC THAT IS NOT RELATED TO
* USER INTERFACE CHANGES
*/

public class BattleManager {

    Lutemon currentPlayer;
    Lutemon receivingPlayer;
    Lutemon winner = null;
    BattleListener listener;
    boolean isGameOver;

    // constructor, sets listener, and players
    public BattleManager(BattleListener listener) {
        LutemonManager ltmMngr = LutemonManager.getInstance();
        this.listener = listener;
        this.isGameOver = false;
        currentPlayer = getStartingPlayer(ltmMngr.getPlayer1(), ltmMngr.getPlayer2());
        receivingPlayer = getReceivingLutemon(ltmMngr.getPlayer1(), ltmMngr.getPlayer2());
    }

    // starts battle, displays the right players using listener methods
    public void startBattle() {
        listener.updateBattleMessage("Battle started");
        listener.onTurnStart(currentPlayer);
    }

    // getters
    public Lutemon getCurrentPlayer() {return this.currentPlayer;}
    public Lutemon getReceivingPlayer() {return this.receivingPlayer;}
    public Lutemon getWinner() {return winner;}

    // checks if the battle is over and returns the player with 0 hp left
    private Lutemon checkIfBattleOver(Lutemon p1, Lutemon p2) {
        if (p1.getHp() == 0) {
            return p2;
        }
        if (p2.getHp() == 0) {
            return p1;
        }
        return null;
    }



    // returns the player with higher speed statistic, to determine the starter of battle
    public Lutemon getStartingPlayer(Lutemon player1, Lutemon player2) {
        if (player1.getSpeed() < player2.getSpeed()) {
            return player2;
        }
        return player1;
    }

    // returns the player that is not the starting player
    // i.e. one with lower speed
    private Lutemon getReceivingLutemon(Lutemon player1, Lutemon player2) {
        return (player1.equals(currentPlayer)) ? player2 : player1;
    }

    // switches the current player and receiving player on turn change
    private void switchPlayers() {
        Lutemon temp = currentPlayer;
        currentPlayer = receivingPlayer;
        receivingPlayer = temp;
        Log.d("PLAYER", "switchPlayers: SWITCHED");
    }

    // this function is called when user actually presses an attack button
    // only for attack abilities
    // checks if game over, checks stamina and finally handles turn end
    public void onPlayerAttackSelected(ATTACKTYPES attack) throws OutOfStamina {
        Log.d("ATTACK", "onPlayerAttackSelected: ATTACK SELECTED");
        if (isGameOver) {
            Log.d("GAME OVER", "onPlayerAttackSelected: game over");
            return;
        }

        if (!handleAttack(currentPlayer, receivingPlayer, attack)) {
            Log.d("OUTOFSTAMINA", "onPlayerAttackSelected: " + currentPlayer.getName() + "OUTOFSTAMINA");
            listener.updateBattleMessage(currentPlayer.getName() + " Is out of stamina");
            throw new OutOfStamina();
        } else {
            listener.updateBattleMessage(currentPlayer.getName() + " HIT " + receivingPlayer.getName() + " BY " + attack.getBaseDamage());
        }

        winner = checkIfBattleOver(currentPlayer, receivingPlayer);
        if (winner != null) {
            Log.d("WINNER", "onPlayerAttackSelected: WINNER: " + winner.getName());
            winner.increaseLevel();
            isGameOver = true;
            listener.onGameOver();
            return;
        }

        switchPlayers();
        listener.onTurnStart(currentPlayer);
    }

    // similar to onPlayerAttackSelected above, called when user presses a buff ability
    public void onPlayerBuffSelected(BUFFTYPES buff) throws OutOfStamina {

        if (isGameOver) return;

        if (!handleBuffing(currentPlayer, buff)) {
            listener.updateBattleMessage(currentPlayer.getName() + " Is out of stamina");
            throw new OutOfStamina();
        }

        winner = checkIfBattleOver(currentPlayer, receivingPlayer);
        if (winner != null) {
            isGameOver = true;
            listener.onGameOver();
            return;
        }
        switchPlayers();
        listener.onTurnStart(currentPlayer);
    }

    // similar to onPlayerAttackSelected and onPlayerBuffSelected, called when user presses a
    // debuff ability
    public void onPlayerDebuffSelected(DEBUFFTYPES debuff) throws OutOfStamina{
        if (isGameOver) {
            return;
        }

        if (!handleDebuffing(currentPlayer, receivingPlayer, debuff)) {
            listener.updateBattleMessage(currentPlayer.getName() + " Is out of stamina");
            throw new OutOfStamina();
        }

        winner = checkIfBattleOver(currentPlayer, receivingPlayer);
        if (winner != null) {
            isGameOver = true;
            listener.onGameOver();
            return;
        }
        switchPlayers();
        listener.onTurnStart(currentPlayer);
    }

    // attack logic, called inside onPlayerAttackSelected
    private boolean handleAttack(Lutemon attacking, Lutemon receiving, ATTACKTYPES attack) {

        if (attacking.getStamina() < attack.getCost()){
            return false;
        }
        int damage = (int)Math.round(attack.getBaseDamage()*attacking.getDamageMultiplier());
        receiving.decreaseHealth(damage);
        receiving.addDamageTaken(damage);

        attacking.decreaseStamina(attack.getCost());
        attacking.addDamageDealt(damage);
        return true;
    }

    // buff logic, called inside onPlayerBuffSelected
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
                listener.updateBattleMessage(currentPlayer.getName() + " used passive heal (3 turns)");
                break;
        }
        return true;
    }

    // debuff logic, called inside onPlayerDebuffSelected
    private boolean handleDebuffing(Lutemon attacking, Lutemon receiving, DEBUFFTYPES debuff) {
        if (attacking.getStamina() < debuff.getCost()) {
            return false;
        }
        return true;
    }

    // method to process status effects with an iterator
    public void processStatusEffects(Lutemon lutemon) {
        Iterator<StatusEffect> iterator = lutemon.getStatusEffects().iterator();
        while (iterator.hasNext()) {
            StatusEffect effect = iterator.next();
            listener.updateBattleMessage(effect.getName() + " Was applied");
            effect.applyEffect(lutemon);
            if (effect.isExpired()) {
                iterator.remove();
            }
        }
    }
}


