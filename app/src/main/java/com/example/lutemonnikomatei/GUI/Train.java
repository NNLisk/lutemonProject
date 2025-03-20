package com.example.lutemonnikomatei.GUI;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lutemonnikomatei.Lutemon;
import com.example.lutemonnikomatei.R;

public class Train extends AppCompatActivity {
private Lutemon trainee;
public TextView Status;

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
    }
public void TrainLutemon()
{

}
public Lutemon GetTrainee()
{
    return this.trainee;
}
public boolean CheckEligibility()
{
    return false;
}

}