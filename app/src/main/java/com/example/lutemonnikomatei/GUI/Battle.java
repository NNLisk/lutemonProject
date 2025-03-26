package com.example.lutemonnikomatei.GUI;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.BatchingListUpdateCallback;

import com.example.lutemonnikomatei.BattleListener;
import com.example.lutemonnikomatei.BattleManager;
import com.example.lutemonnikomatei.LutemonClasses.Lutemon;
import com.example.lutemonnikomatei.R;

public class Battle extends AppCompatActivity {

    // somehow player1 and player2 needs to be set here,
    // maybe from the previous activity?
    Lutemon player1;
    Lutemon player2;

    Button player1Ability1;
    Button player1Ability2;
    Button player1Ability3;
    Button player1Ability4;
    Button player2Ability1;
    Button player2Ability2;
    Button player2Ability3;
    Button player2Ability4;

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
            public void onTurnStart(Lutemon currentPlayer) {

            }

            @Override
            public void onGameOver(Lutemon winner) {

            }
        };

        BattleManager battleManager = new BattleManager(battleListener);

        battleManager.startBattle(player1, player2);
    }
}