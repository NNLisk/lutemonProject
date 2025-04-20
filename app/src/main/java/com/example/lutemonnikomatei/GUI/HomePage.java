package com.example.lutemonnikomatei.GUI;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lutemonnikomatei.LutemonClasses.Lutemon;
import com.example.lutemonnikomatei.LutemonManager;
import com.example.lutemonnikomatei.R;
import com.example.lutemonnikomatei.enums.LUTEMONTYPES;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity {

    public static LutemonManager lutemonManager;

    Button testBattle;
    Button viewLute;
    Button trainLute;

    Button testLutemonCreation;

    //Button chooseCharacter1;

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

        lutemonManager = LutemonManager.getInstance();
        ArrayList<Lutemon> listLutemons = lutemonManager.loadLutemons(this);
        lutemonManager.setListOfLutemons(listLutemons);

        testBattle = findViewById(R.id.testBattle);
        viewLute = findViewById(R.id.viewLutemon);
        //chooseCharacter1 = findViewById(R.id.chooseLutemon);
        trainLute = findViewById(R.id.trainLutemon);

        testLutemonCreation = findViewById(R.id.testCreateLutemon);


        testBattle.setOnClickListener(view-> {
            if (lutemonManager.getListOfLutemons().size() < 2) {
                Toast.makeText(view.getContext(), "Create the lutemons first!", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent viewIntent = new Intent(HomePage.this, ChooseYourCharacter.class);
            startActivity(viewIntent);
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

        /*chooseCharacter1.setOnClickListener(view-> {Intent viewIntent = new Intent(HomePage.this, ChooseYourCharacter1.class);
            startActivity(viewIntent);
        });*/

    }
}