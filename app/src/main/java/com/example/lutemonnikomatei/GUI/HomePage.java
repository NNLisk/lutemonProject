package com.example.lutemonnikomatei.GUI;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lutemonnikomatei.LutemonManager;
import com.example.lutemonnikomatei.R;
import com.example.lutemonnikomatei.enums.LUTEMONTYPES;

public class HomePage extends AppCompatActivity {

    public static LutemonManager lutemonManager = LutemonManager.getInstance();

    Button testBattle;
    Button viewLute;
    Button trainLute;

    Button testLutemonCreation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lutemonManager.createLutemon("Niko", LUTEMONTYPES.STUDENT);
        lutemonManager.createLutemon("Matei", LUTEMONTYPES.STUDENT);
        lutemonManager.createLutemon("Cristian", LUTEMONTYPES.STUDENT);

        lutemonManager.createLutemon("JANNE", LUTEMONTYPES.Janne);

        lutemonManager.setPlayer1(lutemonManager.getListOfLutemons().get(3));
        lutemonManager.setPlayer2(lutemonManager.getListOfLutemons().get(0));

        testBattle = findViewById(R.id.testBattle);
        viewLute = findViewById(R.id.viewLutemon);

        trainLute = findViewById(R.id.trainLutemon);

        testLutemonCreation = findViewById(R.id.testCreateLutemon);


        testBattle.setOnClickListener(view -> {
            Intent battleIntent = new Intent(HomePage.this, Battle.class);
            startActivity(battleIntent);
        });
        viewLute.setOnClickListener(view-> {Intent viewIntent = new Intent(HomePage.this, viewLutemons.class);
        startActivity(viewIntent);
        });

        trainLute.setOnClickListener(view-> {Intent viewIntent = new Intent(HomePage.this, Train.class);
            startActivity(viewIntent);
        });

        testLutemonCreation.setOnClickListener(view -> {
            Intent createLutemonIntent = new  Intent(HomePage.this, CreateLutemon.class);
            startActivity(createLutemonIntent);
        });

    }
}