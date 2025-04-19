package com.example.lutemonnikomatei.GUI;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lutemonnikomatei.BattleListener;
import com.example.lutemonnikomatei.BattleManager;
import com.example.lutemonnikomatei.Exceptions.OutOfStamina;
import com.example.lutemonnikomatei.LutemonClasses.Janne;
import com.example.lutemonnikomatei.LutemonClasses.Lutemon;
import com.example.lutemonnikomatei.LutemonClasses.Student;
import com.example.lutemonnikomatei.LutemonManager;
import com.example.lutemonnikomatei.R;
import com.example.lutemonnikomatei.enums.ATTACKTYPES;
import com.example.lutemonnikomatei.enums.BUFFTYPES;
import com.example.lutemonnikomatei.enums.DEBUFFTYPES;
import com.example.lutemonnikomatei.enums.LUTEMONTYPES;

import java.util.ArrayList;

public class Battle extends AppCompatActivity {

    LutemonManager lutemonManager = LutemonManager.getInstance();

    Button p1abilityButton1, p1abilityButton2, p1abilityButton3, p1abilityButton4;
    ArrayList<Button> buttonListPlayer1 = new ArrayList<>();
    Button p2abilityButton1, p2abilityButton2, p2abilityButton3, p2abilityButton4;
    ArrayList<Button> buttonListPlayer2 = new ArrayList<>();

    TextView p1NameContainer, p2NameContainer, p1HealthContainer, p2HealthContainer, p1StaminaContainer, p2StaminaContainer, showTurn, battleMessage;
    ImageView p1ImageContainer, p2ImageContainer;
    BattleManager battleManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_battle);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // assigns new battle listener that defines game action operations like
        // ui updaters and game end notifiers

        BattleListener battleListener = new BattleListener() {
            @Override
            public void onTurnStart(Lutemon lutemon) { // on turn change updates ui and updates button visibilities
                //called on when player is selected
                Log.d("ONTURNSTART", "onTurnStart: onTurnStart CALLED FOR " + lutemon.getName());
                battleManager.processStatusEffects(battleManager.getCurrentPlayer());
                updateBattleUIonTurnChange();
                updateButtonVisibilities(battleManager.getCurrentPlayer(), battleManager.getReceivingPlayer());
            }

            @Override
            public void onGameOver() { //on game over disable all ability buttons and display winner
                Log.d("GAME OVER", "onGameOver: CALLED");
                updateBattleUIonTurnChange();
                disableAllButtons(battleManager.getCurrentPlayer(), battleManager.getReceivingPlayer());
            }

            @Override
            public void updateBattleMessage(String message) {
                battleMessage.setText(message);
            }
        };
        battleManager = new BattleManager(battleListener);

        // find and divide 8 buttons into 2 lists
        p1abilityButton1 = findViewById(R.id.player1Ability1);
        p1abilityButton2 = findViewById(R.id.player1Ability2);
        p1abilityButton3 = findViewById(R.id.player1Ability3);
        p1abilityButton4 = findViewById(R.id.player1Ability4);

        buttonListPlayer1.add(p1abilityButton1);
        buttonListPlayer1.add(p1abilityButton2);
        buttonListPlayer1.add(p1abilityButton3);
        buttonListPlayer1.add(p1abilityButton4);

        p2abilityButton1 = findViewById(R.id.player2Ability1);
        p2abilityButton2 = findViewById(R.id.player2Ability2);
        p2abilityButton3 = findViewById(R.id.player2Ability3);
        p2abilityButton4 = findViewById(R.id.player2Ability4);

        buttonListPlayer2.add(p2abilityButton1);
        buttonListPlayer2.add(p2abilityButton2);
        buttonListPlayer2.add(p2abilityButton3);
        buttonListPlayer2.add(p2abilityButton4);

        showTurn = findViewById(R.id.showTurn);
        battleMessage = findViewById(R.id.battleMessage);

        p1NameContainer = findViewById(R.id.showP1name);
        p2NameContainer = findViewById(R.id.showP2name);

        p1HealthContainer = findViewById(R.id.showP1health);
        p2HealthContainer = findViewById(R.id.showP2health);

        p1StaminaContainer = findViewById(R.id.showP1Stamina);
        p2StaminaContainer = findViewById(R.id.showP2Stamina);

        p1ImageContainer = findViewById(R.id.showP1image);
        p2ImageContainer = findViewById(R.id.showP2image);

        // calls battle manager to start the battle
        battleManager.startBattle();
        buttonAssigner(battleManager.getCurrentPlayer(), getButtonListForPlayer(battleManager.getCurrentPlayer()));
        buttonAssigner(battleManager.getReceivingPlayer(), getButtonListForPlayer(battleManager.getReceivingPlayer()));

        // ui value updater
        updateButtonVisibilities(battleManager.getCurrentPlayer(), battleManager.getReceivingPlayer());
    }

    // assigns buttons based on the lutemons abilities, loops through the ability arraylist
    private void buttonAssigner(Lutemon lutemon, ArrayList<Button> buttons) {
        int index = 0;

        for (ATTACKTYPES attack : lutemon.getAttacks()) {
            if (index < buttons.size()) {
                Button btn = buttons.get(index);
                btn.setText(attack.getBaseDamage() + " , " + attack.getCost() + " | " + attack.toString());
                btn.setVisibility(View.VISIBLE);

                final ATTACKTYPES attackCopy = attack;

                btn.setOnClickListener(view -> {
                    Log.d("BUTTON", "buttonAssigner: attack clicked");
                    try {
                        battleManager.onPlayerAttackSelected(attackCopy);
                    } catch (OutOfStamina e) {
                        Toast.makeText(view.getContext(), "Not enough stamina!", Toast.LENGTH_SHORT).show();
                    }
                });
                index++;
                Log.d("BUTTON", "button assigned");
            } else {
                Log.d("INDEXERROR", "index out of bounds: attacks");
            }
        }

        for (DEBUFFTYPES debuff : lutemon.getDebuffs()) {
            if (index < buttons.size()) {
                buttons.get(index).setText(debuff.getCost() + " | " + debuff.toString());
                buttons.get(index).setVisibility(View.VISIBLE);
                buttons.get(index).setOnClickListener(view -> {

                    try {
                        battleManager.onPlayerDebuffSelected(debuff);
                    } catch (OutOfStamina e) {
                        Toast.makeText(view.getContext(), "Not enough stamina!", Toast.LENGTH_SHORT).show();
                    }
                });
                index++;
                Log.d("BUTTON", "button assigned");
            } else {
                Log.d("INDEXERROR", "index out of bounds: debuffs");
            }
        }

        for (BUFFTYPES buff : lutemon.getBuffs()) {

            if (index < buttons.size()) {
                Button btn = buttons.get(index);
                btn.setText( buff.getCost() + " | " + buff.toString());
                btn.setVisibility(View.VISIBLE);

                final BUFFTYPES buffCopy = buff;

                btn.setOnClickListener(view -> {
                    Log.d("BUTTON", "buttonAssigner: Heal clicked");
                    try {
                        battleManager.onPlayerBuffSelected(buffCopy);
                    } catch (OutOfStamina e) {
                        Toast.makeText(view.getContext(), "Not enough stamina!", Toast.LENGTH_SHORT).show();
                    }
                });
                index++;
                Log.d("BUTTON", "button assigned");
            } else {
                Log.d("INDEXERROR", "index out of bounds: buffs");
                break;
            }
        }
    }


    // updates the player name and stats in the battle ui, called after every turn
    // with onTurnStart in the battlelistener
    private void updateBattleUIonTurnChange() {
        p1NameContainer.setText(lutemonManager.getPlayer1().getName());
        p2NameContainer.setText(lutemonManager.getPlayer2().getName());

        p1HealthContainer.setText(lutemonManager.getPlayer1().getHp() + "/" + lutemonManager.getPlayer1().getMaxHp());
        p2HealthContainer.setText(lutemonManager.getPlayer2().getHp() + "/" + lutemonManager.getPlayer2().getMaxHp());

        p1StaminaContainer.setText(lutemonManager.getPlayer1().getStamina() + "/" + lutemonManager.getPlayer1().getMaxStamina());
        p2StaminaContainer.setText(lutemonManager.getPlayer2().getStamina() + "/" + lutemonManager.getPlayer2().getMaxStamina());
        Log.d("UI", "updateBattleUIonTurnChange: UI VALUES UPDATED");
    }


    // ui method for making the player of the new turn buttons visible and the others Gone
    private void updateButtonVisibilities(Lutemon currentPlayer, Lutemon notCurrentPlayer) {
        ArrayList<Button> currentPlayerButtons = getButtonListForPlayer(currentPlayer);
        for (Button btn : currentPlayerButtons) {
            btn.setVisibility(View.VISIBLE);
        }
        ArrayList<Button> notCurrentPlayerButtons = getButtonListForPlayer(notCurrentPlayer);

        for (Button btn : notCurrentPlayerButtons) {
            btn.setVisibility(View.GONE);
        }

        showTurn.setText(battleManager.getCurrentPlayer().getName() + "'s turn");
    }

    // method makes all buttons invisible
    private void disableAllButtons(Lutemon player1, Lutemon player2) {
        for (Button btn : getButtonListForPlayer(player1)) {
            btn.setVisibility(View.GONE);
        }
        for (Button btn : getButtonListForPlayer(player2)) {
            btn.setVisibility(View.GONE);
        }
    }

    // UI method for game over ui updates
    private void UIwhenGameOver() {

    }

    // list handler for getting each lutemons personal list of ability buttons
    private ArrayList<Button> getButtonListForPlayer(Lutemon player) {
        if (player == lutemonManager.getPlayer1()) {
            return buttonListPlayer1;
        } else {
            return buttonListPlayer2;
        }
    }
}