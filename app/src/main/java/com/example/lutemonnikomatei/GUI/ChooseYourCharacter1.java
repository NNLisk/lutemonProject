package com.example.lutemonnikomatei.GUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lutemonnikomatei.LutemonClasses.Lutemon;
import com.example.lutemonnikomatei.LutemonManager;
import com.example.lutemonnikomatei.R;

import java.util.List;

public class ChooseYourCharacter1 extends AppCompatActivity {

    private RecyclerView recyclerView;
    Train train = new Train();
    private LutemonAdapter lutemonAdapter;
    private List<Lutemon> lutemonList;
    LutemonManager lutemonManager = LutemonManager.getInstance();
    int currentPlayer;
    TextView playerChoosingText;

    Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        currentPlayer = 1;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_your_character1);

        // Find textView and set the text
        playerChoosingText = findViewById(R.id.playerChoosingTextView);
        setPlayerChoosingText();

        // Find continueButton
        continueButton = findViewById(R.id.chooseCharacterContinueButton);

        // Find RecyclerView
        recyclerView = findViewById(R.id.recyclerView);

        // Set layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Add dividers between items (optional)
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));


        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // If player is missing a lutemon, do not continue
                if (!lutemonManager.lutemonsSet()){
                    Toast.makeText(ChooseYourCharacter1.this,
                            "Missing selection: ",
                            Toast.LENGTH_SHORT).show();
                }
                // If first player chooses, let the next one choose
                if(currentPlayer==1){
                    currentPlayer++;
                    setPlayerChoosingText();
                }
                // If all players have selected their Lutemon, begin the battle
                else {
                    Intent battleIntent = new Intent(ChooseYourCharacter1.this, Battle.class);
                    startActivity(battleIntent);
                }
            }
        });



        // Initialize adapter with click listener
        lutemonAdapter = new LutemonAdapter(LutemonManager.getInstance().getListOfLutemons(), new LutemonAdapter.OnLutemonClickListener() {
            @Override
            public void onLutemonClick(Lutemon lutemon) {
                // Handle click event
                Toast.makeText(ChooseYourCharacter1.this,
                        "Selected: " + lutemon.getName(),
                        Toast.LENGTH_SHORT).show();
                lutemonManager.setPlayer(lutemon, currentPlayer);
            }
        });

        // Set adapter to RecyclerView
        recyclerView.setAdapter(lutemonAdapter);
    }
    // Set the text for the TextView
    private void setPlayerChoosingText() {
        playerChoosingText.setText("Player " + String.valueOf(currentPlayer) + " chooses");
    }

    @Override
    protected void onResume() {
        super.onResume();
        lutemonAdapter.updateData(LutemonManager.getInstance().getListOfLutemons());
        lutemonAdapter.notifyDataSetChanged();
    }

    private List<Lutemon> createSampleLutemons() {
        List<Lutemon> lutemons = lutemonManager.getListOfLutemons();


        return lutemons;
    }
}