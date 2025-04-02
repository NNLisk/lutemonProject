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
import com.example.lutemonnikomatei.LutemonManager;
import com.example.lutemonnikomatei.R;

public class Battle extends AppCompatActivity {

    LutemonManager lutemonManager = LutemonManager.getInstance();

    Lutemon player1 = lutemonManager.getPlayer1();
    Lutemon player2 = lutemonManager.getPlayer2();
    
    Button abilityButton1;
    Button abilityButton2;
    Button abilityButton3;
    Button abilityButton4;

    Button[] buttonlist = {abilityButton1, abilityButton2, abilityButton3, abilityButton4};

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

        BattleManager battleManager = new BattleManager(battleListener);

        battleManager.startBattle(player1, player2);
        

        
    }

    public void buttonAssigner(Lutemon lutemon) {

    }
}