package com.example.health_thing;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DietPlan extends AppCompatActivity {

    private Button button;
    private TextView plan;
    private DatabaseHelper databaseHelper;
    private String category;
    private String workoutplan;
    int agee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diet_plan);
        button = findViewById(R.id.randbutton);
        plan = findViewById(R.id.planspace);
        databaseHelper = new DatabaseHelper(this);

        Intent intent = getIntent();
        category = intent.getStringExtra("category");
        agee = intent.getIntExtra("Age", 0);
        if (category.equals("OVERWEIGHT") || category.equals("UNDERWEIGHT") || category.equals("NORMAL")) {
            String mealPlan = databaseHelper.getMealPlansByCategory(category);
            plan.setText(mealPlan);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String workoutplan = ""; // Declare and initialize the variable here
                if (category.equals("OVERWEIGHT") || category.equals("UNDERWEIGHT") || category.equals("NORMAL")) {
                    workoutplan = databaseHelper.getWorkoutPlansByCategory(category);
                    Log.d("DietPlan", "Workout Plan: " + workoutplan);
                }
                Intent intent1 = new Intent(DietPlan.this, workplan.class);
                intent1.putExtra("workoutplan", workoutplan);
                intent1.putExtra("Age", agee);
                startActivity(intent1);
            }
        });
    }
}