package com.example.lutemonnikomatei.GUI;

import android.os.Bundle;
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
    
    Button abilityButton1 = findViewById(R.id.playerAbility1);
    Button abilityButton2 = findViewById(R.id.playerAbility2);
    Button abilityButton3 = findViewById(R.id.playerAbility3);
    Button abilityButton4 = findViewById(R.id.playerAbility4);
    Button[] buttonList = {abilityButton1, abilityButton2, abilityButton3, abilityButton4};

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

        BattleListener battleListener = new BattleListener() {
            @Override
            public void onTurnStart() {
                // UI UPDATER METHOD CALL HERE
            }

            @Override
            public void onGameOver() {
                // UI UPDATER METHOD CALL HERE
            }
        };

        battleManager = new BattleManager(battleListener);

        battleManager.startBattle(lutemonManager.getPlayer1(), lutemonManager.getPlayer2());
    }

    public void buttonAssigner(Lutemon lutemon) {
        int index = 0;

        for (ATTACKTYPES attack : lutemon.getAttacks()) {
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

    private void updateBattleUIonTurnChange() {

    }

    private void UIwhenGameOver() {

    }
}