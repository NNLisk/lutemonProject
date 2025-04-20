package com.example.lutemonnikomatei.GUI;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lutemonnikomatei.LutemonClasses.Lutemon;
import com.example.lutemonnikomatei.LutemonManager;
import com.example.lutemonnikomatei.R;
import com.example.lutemonnikomatei.adapter.LutemonTrainAdapter;

public class Train extends AppCompatActivity implements LutemonTrainAdapter.OnLutemonClickListener {
    private Lutemon trainee;
    private TextView textViewSelectedLutemon;
    private TextView textViewLutemonCurrentStats;
    private Button trainHpButton;
    private Button trainStaminaButton;
    private Button trainSpeedButton;
    private Button buttonGoBack;
    private RecyclerView recyclerViewLutemons;
    private LutemonTrainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_train);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize UI components
        textViewSelectedLutemon = findViewById(R.id.textViewSelectedLutemon);
        textViewLutemonCurrentStats = findViewById(R.id.textViewLutemonCurrentStats);
        trainHpButton = findViewById(R.id.HpTrain);
        trainSpeedButton = findViewById(R.id.trainSpeed);
        trainStaminaButton = findViewById(R.id.trainStamina);
        buttonGoBack = findViewById(R.id.buttonGoBack);
        recyclerViewLutemons = findViewById(R.id.recyclerViewLutemons);

        // Set up RecyclerView
        recyclerViewLutemons.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LutemonTrainAdapter(LutemonManager.getInstance().getListOfLutemons(), this);
        recyclerViewLutemons.setAdapter(adapter);

        // Set up button listeners
        trainHpButton.setOnClickListener(v -> TrainHp());
        trainStaminaButton.setOnClickListener(v -> TrainStamina());
        trainSpeedButton.setOnClickListener(v -> TrainSpeed());
        buttonGoBack.setOnClickListener(v -> finish());

        // Initially disable training buttons until a Lutemon is selected
        setTrainingButtonsEnabled(false);
    }

    @Override
    public void onLutemonClick(int position) {
        // Set the selected Lutemon as the trainee
        trainee = LutemonManager.getInstance().getListOfLutemons().get(position);
        updateSelectedLutemonDisplay();
        updateTrainingButtonsState();
    }

    private void updateSelectedLutemonDisplay() {
        if (trainee != null) {
            // Update the selected Lutemon title
            textViewSelectedLutemon.setText("Selected: " + trainee.getName());

            // Update the stats display - now including XP
            String stats = String.format(
                    "Type: %s\nHP: %d/%d\nSpeed: %d\nStamina: %d/%d\nExperience: %d",
                    trainee.getType(),
                    trainee.getHp(),
                    trainee.getMaxHp(),
                    trainee.getSpeed(),
                    trainee.getStamina(),
                    trainee.getMaxStamina(),
                    trainee.getExperience()
            );
            textViewLutemonCurrentStats.setText(stats);
        } else {
            textViewSelectedLutemon.setText("Selected Lutemon:");
            textViewLutemonCurrentStats.setText("Select a Lutemon to view stats");
        }
    }

    private void updateTrainingButtonsState() {
        boolean hasEnoughXp = (trainee != null && trainee.getExperience() > 0);
        setTrainingButtonsEnabled(hasEnoughXp);

        if (trainee != null && !hasEnoughXp) {
            Toast.makeText(this,
                    trainee.getName() + " needs more experience to train!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void setTrainingButtonsEnabled(boolean enabled) {
        trainHpButton.setEnabled(enabled);
        trainStaminaButton.setEnabled(enabled);
        trainSpeedButton.setEnabled(enabled);
    }

    private boolean consumeExperiencePoint() {
        if (trainee != null && trainee.getExperience() > 0) {
            trainee.setExperience(trainee.getExperience() - 1);
            return true;
        }
        return false;
    }

    public void TrainSpeed() {
        if (trainee != null && consumeExperiencePoint()) {
            int newSpeed = trainee.getSpeed() + 1;
            trainee.setSpeed(newSpeed);
            showTrainingSuccess("Speed");
            updateSelectedLutemonDisplay();
            updateTrainingButtonsState();
            adapter.notifyDataSetChanged();
        }
    }

    public void TrainStamina() {
        if (trainee != null && consumeExperiencePoint()) {
            int newStamina = trainee.getMaxStamina() + 1;
            trainee.setMaxStamina(newStamina);
            trainee.restoreStamina(); // Restore stamina after training
            showTrainingSuccess("Stamina");
            updateSelectedLutemonDisplay();
            updateTrainingButtonsState();
            adapter.notifyDataSetChanged();
        }
    }

    public void TrainHp() {
        if (trainee != null && consumeExperiencePoint()) {
            int increment = Math.max(1, (int)(trainee.getHpMultiplier() * 2));
            int newHp = trainee.getMaxHp() + increment;
            trainee.setMaxHp(newHp);
            trainee.restoreHealth(); // Restore health after training
            showTrainingSuccess("HP");
            updateSelectedLutemonDisplay();
            updateTrainingButtonsState();
            adapter.notifyDataSetChanged();
        }
    }

    private void showTrainingSuccess(String attribute) {
        Toast.makeText(this,
                trainee.getName() + "'s " + attribute + " training was successful! (-1 XP)",
                Toast.LENGTH_SHORT).show();
    }
}