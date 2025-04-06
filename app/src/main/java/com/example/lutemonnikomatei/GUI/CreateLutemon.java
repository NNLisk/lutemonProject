package com.example.lutemonnikomatei.GUI;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lutemonnikomatei.LutemonManager;
import com.example.lutemonnikomatei.R;
import com.example.lutemonnikomatei.enums.LUTEMONTYPES;

public class CreateLutemon extends AppCompatActivity {
public String chosenType;
public TextView nameText;
public RadioGroup radioGroup;
public RadioButton selectedRadioButton;
public Button createLutemonButton;
public String chosenName;
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

        nameText = findViewById(R.id.giveNameTextView);
        createLutemonButton = findViewById(R.id.CreateLutemonButton);
        radioGroup = findViewById(R.id.radioGroup);
    }
    public void SwitchSprite()
    {

    }
    public void CreateLutemonMethod()
    {
        chosenName = (String) nameText.getText();
        if(chosenName != null && !chosenName.trim().isEmpty()){
            return;
        }
        if (radioGroup.getCheckedRadioButtonId() == -1)
        {
            // none of the radio buttons is checked
            return;
        }
        else
        {
            // one of the radio buttons is checked
            // get selected radio button from radioGroup
            int selectedId = radioGroup.getCheckedRadioButtonId();
            // find the radiobutton by returned id
            selectedRadioButton = (RadioButton)findViewById(selectedId);
            chosenType = selectedRadioButton.getText().toString();
            Toast.makeText(getApplicationContext(), selectedRadioButton.getText().toString()+" is selected", Toast.LENGTH_SHORT).show();
        }
        LutemonManager.createLutemon(chosenName, chosenType);
    }
    public void TransformText()
    {

    }
}