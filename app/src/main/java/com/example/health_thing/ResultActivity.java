package com.example.health_thing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;

public class ResultActivity extends AppCompatActivity {

    private TextView bmiTextView;
    private TextView addit;
    private Button nextButton;
    int agee;
    private DatabaseHelper databaseHelper;
    private TextView mealPlanTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);

        bmiTextView = findViewById(R.id.bmiTextView);
        addit = findViewById(R.id.additionalTextView);
        databaseHelper = new DatabaseHelper(this);

        Bundle bundle = getIntent().getExtras();
        String bmiText;
        String categoryText = null;

        if (bundle != null) {
            double bmi = bundle.getDouble("BMI", 0.0);
            int age = bundle.getInt("Age", 0);
            agee = age;
            bmiText = String.format(Locale.getDefault(), "YOUR BMI: %.2f", bmi);
            bmiTextView.setText(bmiText);

            // Determine the category based on the BMI value
            if (bmi < 16) {
                categoryText = "UNDERWEIGHT";
            } else if (bmi >=16 && bmi < 17) {
                categoryText = "UNDERWEIGHT";
            } else if (bmi>=17 && bmi < 18.5) {
                categoryText = "UNDERWEIGHT";
            } else if (bmi>=18.5 && bmi < 25) {
                categoryText = "NORMAL";
            } else if (bmi>=25 && bmi < 30) {
                categoryText = "OVERWEIGHT";
            } else if (bmi>=30 && bmi < 35) {
                categoryText = "OVERWEIGHT";
            } else if (bmi>=35 && bmi < 40) {
                categoryText = "OVERWEIGHT";
            } else {
                categoryText = "OVERWEIGHT";
            }

            addit.setText(categoryText);
        }
        nextButton = findViewById(R.id.nextButton);
        String finalCategoryText = categoryText;
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = finalCategoryText;
                String mealPlan = databaseHelper.getMealPlansByCategory(category).toString();

                Intent intent = new Intent(ResultActivity.this, DietPlan.class);
                intent.putExtra("category", category);
                intent.putExtra("Age", agee);
                startActivity(intent);
            }
        });
    }
}