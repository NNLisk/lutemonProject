package com.example.lutemonnikomatei.GUI;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lutemonnikomatei.LutemonClasses.Lutemon;
import com.example.lutemonnikomatei.LutemonManager;
import com.example.lutemonnikomatei.R;

public class Train extends AppCompatActivity {
private Lutemon trainee;
public TextView Status;
public Button trainHpButton;
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
        trainHpButton = findViewById(R.id.HpTrain);
        trainHpButton.setOnClickListener(v -> TrainHp());

    }
    public Lutemon GetTrainee()
    {
        return this.trainee;
    }
public void TrainLutemon()
{

}
public void TrainSpeed()
{

}
public void TrainStamina()
    {

    }
public void TrainHp()
{
int newHp = this.trainee.getMaxHp() +(int)trainee.getHpMultiplier()*10;
this.trainee.setMaxHp(newHp);
}

public boolean CheckEligibility()
{
    return false;
}
public void setTrainee(Lutemon trainee)
{
    this.trainee = trainee;
}
}