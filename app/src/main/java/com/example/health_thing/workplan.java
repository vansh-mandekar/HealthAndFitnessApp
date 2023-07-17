package com.example.health_thing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class workplan extends AppCompatActivity {

    private TextView wplan;
    int agee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout_plan);

        wplan = findViewById(R.id.wplan);

        Intent intent = getIntent();
        String workoutPlan = intent.getStringExtra("workoutplan");
        agee = intent.getIntExtra("Age", 0);
        wplan.setText(workoutPlan);
        agefactor(agee);


    }
    public void agefactor(int age)
    {
        if(age>40)
        {
            Toast.makeText(workplan.this, "Due to your age, you are recommended to reduce your workout sets and reps by a factor of 2 ", Toast.LENGTH_LONG).show();
        }
        if(age<15)
        {
            Toast.makeText(workplan.this, "Due to your age, you are recommended to only follow the cardio workout plans", Toast.LENGTH_LONG).show();
        }
    }
}