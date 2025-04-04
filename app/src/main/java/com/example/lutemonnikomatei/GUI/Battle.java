package com.example.lutemonnikomatei.GUI;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lutemonnikomatei.BattleListener;
import com.example.lutemonnikomatei.BattleManager;
import com.example.lutemonnikomatei.Exceptions.OutOfStamina;
import com.example.lutemonnikomatei.LutemonClasses.Lutemon;
import com.example.lutemonnikomatei.LutemonManager;
import com.example.lutemonnikomatei.R;
import com.example.lutemonnikomatei.enums.ATTACKTYPES;
import com.example.lutemonnikomatei.enums.DEBUFFTYPES;
import com.example.lutemonnikomatei.enums.BUFFTYPES;

public class Battle extends AppCompatActivity {

    LutemonManager lutemonManager = LutemonManager.getInstance();
    
    Button abilityButton1;
    Button abilityButton2;
    Button abilityButton3;
    Button abilityButton4;
    Button[] buttonList = {abilityButton1, abilityButton2, abilityButton3, abilityButton4};

    TextView p1NameContainer;
    TextView p2NameContainer;
    TextView p1HealthContainer;
    TextView p2HealthContainer;
    TextView p1StaminaContainer;
    TextView p2StaminaContainer;

    ImageView p1ImageContainer;
    ImageView p2ImageContainer;

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

        abilityButton1 = findViewById(R.id.playerAbility1);
        abilityButton2 = findViewById(R.id.playerAbility2);
        abilityButton3 = findViewById(R.id.playerAbility3);
        abilityButton4 = findViewById(R.id.playerAbility4);

        p1NameContainer = findViewById(R.id.showP1name);
        p2NameContainer = findViewById(R.id.showP2name);

        p1HealthContainer = findViewById(R.id.showP1health);
        p2HealthContainer = findViewById(R.id.showP2health);

        p1StaminaContainer = findViewById(R.id.showP1stamina);
        p2StaminaContainer = findViewById(R.id.showP2stamina);

        p1ImageContainer = findViewById(R.id.showP1image);
        p2ImageContainer = findViewById(R.id.showP2image);

        updateBattleUIonTurnChange(); // sets player name, hp, stamina and image(eventually)
        
        BattleListener battleListener = new BattleListener() {
            @Override
            public void onTurnStart(Lutemon lutemon) {
                buttonAssigner(lutemon);
                updateBattleUIonTurnChange();
            }

            @Override
            public void onGameOver() {
                // UI UPDATER METHOD CALL HERE
            }
        };

        battleManager = new BattleManager(battleListener);

        battleManager.startBattle(lutemonManager.getPlayer1(), lutemonManager.getPlayer2());
    }

    // assigns buttons based on the lutemons abilities, loops through the ability arraylist
    private void buttonAssigner(Lutemon lutemon) {
        int index = 0;
        resetButtons();

        for (ATTACKTYPES attack : lutemon.getAttacks()) {
            buttonList[index].setText(attack.name());
            buttonList[index].setVisibility(View.VISIBLE);
            buttonList[index].setOnClickListener(view -> {
                try {
                    battleManager.onPlayerAttackSelected(attack);
                } catch (OutOfStamina e) {
                    Toast.makeText(view.getContext(), "Not enough stamina!", Toast.LENGTH_SHORT).show();
                }
            });
            index++;
        }

        for (DEBUFFTYPES debuff : lutemon.getDebuffs()) {
            buttonList[index].setText(debuff.name());
            buttonList[index].setVisibility(View.VISIBLE);
            buttonList[index].setOnClickListener(view -> {
                try {
                    battleManager.onPlayerDebuffSelected(debuff);
                } catch (OutOfStamina e) {
                    Toast.makeText(view.getContext(), "Not enough stamina!", Toast.LENGTH_SHORT).show();
                }
            });
            index++;
        }

        for (BUFFTYPES buff : lutemon.getBuffs()) {
            buttonList[index].setText(buff.name());
            buttonList[index].setVisibility(View.VISIBLE);
            buttonList[index].setOnClickListener(view -> {
                try {
                    battleManager.onPlayerBuffSelected(buff);
                } catch (OutOfStamina e) {
                    Toast.makeText(view.getContext(), "Not enough stamina!", Toast.LENGTH_SHORT).show();
                }
            });
            index++;
        } 
    }

    // ability buttons invisible and cleared
    private void resetButtons() {
        for (Button button : buttonList) {
            button.setOnClickListener(null);
            button.setVisibility(View.INVISIBLE);
        }
    }


    // updates the player name and stats
    private void updateBattleUIonTurnChange() {
        p1NameContainer.setText(lutemonManager.getPlayer1().getName());
        p2NameContainer.setText(lutemonManager.getPlayer2().getName());

        p1HealthContainer.setText(String.valueOf(lutemonManager.getPlayer1().getHp()) + "/" + String.valueOf(lutemonManager.getPlayer1().getMaxHp()));
        p2HealthContainer.setText(String.valueOf(lutemonManager.getPlayer2().getHp()) + "/" + String.valueOf(lutemonManager.getPlayer2().getMaxHp()));

        p1StaminaContainer.setText(String.valueOf(lutemonManager.getPlayer1().getStamina()) + "/" + String.valueOf(lutemonManager.getp1().getMaxStamina()));
        p2StaminaContainer.setText(String.valueOf(lutemonManager.getPlayer2().getStamina()) + "/" + String.valueOf(lutemonManager.getp2().getMaxStamina()));
        
    }

    private void UIwhenGameOver() {

    }
}