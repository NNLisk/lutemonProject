package com.example.lutemonnikomatei.GUI;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lutemonnikomatei.LutemonClasses.Lutemon;
import com.example.lutemonnikomatei.LutemonManager;
import com.example.lutemonnikomatei.R;

import java.util.List;

public class viewLutemons extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LutemonAdapter lutemonAdapter;
    private List<Lutemon> lutemonList;
    LutemonManager lutemonManager = LutemonManager.getInstance();

    // Stats view components
    private TextView textViewSelectedLutemon;
    private TextView textViewLutemonDetailsStats;
    private View lutemonDetailsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_lutemons);

        // Find RecyclerView
        recyclerView = findViewById(R.id.recyclerView);

        // Set layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Add dividers between items (optional)
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        // Find stats view components
        textViewSelectedLutemon = findViewById(R.id.textViewSelectedLutemon);
        textViewLutemonDetailsStats = findViewById(R.id.textViewLutemonDetailsStats);
        lutemonDetailsContainer = findViewById(R.id.lutemonDetailsContainer);

        // Hide details container until a Lutemon is selected
        lutemonDetailsContainer.setVisibility(View.GONE);

        // Initialize adapter with click listener
        lutemonAdapter = new LutemonAdapter(LutemonManager.getInstance().getListOfLutemons(), new LutemonAdapter.OnLutemonClickListener() {
            @Override
            public void onLutemonClick(Lutemon lutemon) {
                // Display detailed stats
                displayLutemonDetails(lutemon);
            }
        });

        // Set adapter to RecyclerView
        recyclerView.setAdapter(lutemonAdapter);

        // Set up back button
        Button buttonBack = findViewById(R.id.buttonGoBack);
        if (buttonBack != null) {
            buttonBack.setOnClickListener(v -> finish());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        lutemonAdapter.updateData(LutemonManager.getInstance().getListOfLutemons());
        lutemonAdapter.notifyDataSetChanged();
    }

    /**
     * Displays the stats of the Lutemon
     */
    private void displayLutemonDetails(Lutemon lutemon) {
        // Show the details container
        lutemonDetailsContainer.setVisibility(View.VISIBLE);

        // Update the selected Lutemon title
        textViewSelectedLutemon.setText("Selected: " + lutemon.getName());

        // Format stats
        String stats = String.format(
                "Type: %s\n\n" +
                        "Base Stats:\n" +
                        "HP: %d/%d\n" +
                        "Stamina: %d/%d\n" +
                        "Speed: %d\n" +
                        "Damage Multiplier: %.2f\n" +
                        "Experience: %d\n\n" +
                        "Battle Record:\n" +
                        "Wins: %d\n" +
                        "Losses: %d\n" +
                        "Damage Dealt: %d\n" +
                        "Damage Taken: %d",
                lutemon.getType(),
                lutemon.getHp(), lutemon.getMaxHp(),
                lutemon.getStamina(), lutemon.getMaxStamina(),
                lutemon.getSpeed(),
                lutemon.getDamageMultiplier(),
                lutemon.getExperience(),
                lutemon.getWins(),
                lutemon.getLosses(),
                lutemon.getDamageDealt(),
                lutemon.getDamageTaken()
        );

        textViewLutemonDetailsStats.setText(stats);

        // Show a brief toast to confirm selection
        Toast.makeText(viewLutemons.this,
                "Selected: " + lutemon.getName(),
                Toast.LENGTH_SHORT).show();
    }
}