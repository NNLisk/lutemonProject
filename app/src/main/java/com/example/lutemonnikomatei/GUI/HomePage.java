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

public class HomePage extends AppCompatActivity {

    public static LutemonManager lutemonManager = LutemonManager.getInstance();

    Button testBattle;

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

        testBattle = findViewById(R.id.testBattle);

        testBattle.setOnClickListener(view -> {
            Intent battleIntent = new Intent(HomePage.this, Battle.class);
            startActivity(battleIntent);
        });
    }
}