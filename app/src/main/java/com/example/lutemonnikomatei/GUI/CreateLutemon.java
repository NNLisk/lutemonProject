package com.example.lutemonnikomatei.GUI;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lutemonnikomatei.LutemonManager;
import com.example.lutemonnikomatei.R;

public class CreateLutemon extends AppCompatActivity {
public String chosenType;
public EditText nameText;
public RadioGroup radioGroup;
public RadioButton selectedRadioButton;
public Button createLutemonButton;
public String chosenName;

LutemonManager lutemonManager = LutemonManager.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_lutemon);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nameText = findViewById(R.id.giveNameEditView);
        createLutemonButton = findViewById(R.id.CreateLutemonButton);
        radioGroup = findViewById(R.id.radioGroup);

        createLutemonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateLutemonMethod();
            }
        });

    }

    public void CreateLutemonMethod()
    {
        chosenName = nameText.getText().toString();
        if(chosenName.trim().isEmpty()){
            Toast.makeText(getApplicationContext(), "missing name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (radioGroup.getCheckedRadioButtonId() == -1)
        {
            // none of the radio buttons is checked
            Toast.makeText(getApplicationContext(), "missing type", Toast.LENGTH_SHORT).show();
            return;
        }

        // one of the radio buttons is checked
        // get selected radio button from radioGroup
        int selectedId = radioGroup.getCheckedRadioButtonId();
        // find the radiobutton by returned id
        selectedRadioButton = (RadioButton)findViewById(selectedId);
        chosenType = selectedRadioButton.getText().toString();
        Toast.makeText(getApplicationContext(), "New " + selectedRadioButton.getText().toString()+" Lutemon has been created!", Toast.LENGTH_SHORT).show();

        lutemonManager.createLutemon(chosenName, chosenType);
        lutemonManager.saveLutemons(lutemonManager.getListOfLutemons(),this);
    }

}