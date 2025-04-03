package com.example.lutemonnikomatei.GUI;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lutemonnikomatei.LutemonClasses.Lutemon;
import com.example.lutemonnikomatei.LutemonClasses.TA;
import com.example.lutemonnikomatei.R;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class viewLutemons extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LutemonAdapter lutemonAdapter;
    private List<Lutemon> lutemonList;

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

        // Create sample Lutemon data
        lutemonList = createSampleLutemons();

        // Initialize adapter with click listener
        lutemonAdapter = new LutemonAdapter(lutemonList, new LutemonAdapter.OnLutemonClickListener() {
            @Override
            public void onLutemonClick(Lutemon lutemon) {
                // Handle click event
                Toast.makeText(viewLutemons.this,
                        "Selected: " + lutemon.getName(),
                        Toast.LENGTH_SHORT).show();

            }
        });

        // Set adapter to RecyclerView
        recyclerView.setAdapter(lutemonAdapter);
    }

    // Method to create sample data
    private List<Lutemon> createSampleLutemons() {
        List<Lutemon> lutemons = new ArrayList<>();

        lutemons.add(new TA("diud"));
        return lutemons;
    }
}
