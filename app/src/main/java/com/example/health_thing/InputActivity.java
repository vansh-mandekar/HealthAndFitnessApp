package com.example.health_thing;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class InputActivity extends AppCompatActivity {

    private EditText heightEditText;
    private EditText weightEditText;
    private EditText ageEditText;
    private Button calculateButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        heightEditText = findViewById(R.id.heightEditText);
        weightEditText = findViewById(R.id.weightEditText);
        ageEditText = findViewById(R.id.ageEditText);
        calculateButton = findViewById(R.id.calculateButton);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String heightText = heightEditText.getText().toString();
                String weightText = weightEditText.getText().toString();
                String ageText = ageEditText.getText().toString();

                // Convert height and weight strings to numbers
                double height = Double.parseDouble(heightText);
                double weight = Double.parseDouble(weightText);
                int age = Integer.parseInt(ageText);

                // Calculate the BMI
                double bmi = calculateBMI(weight, height);

                // Create a Bundle to pass the BMI value to ResultActivity
                Bundle bundle = new Bundle();
                bundle.putDouble("BMI", bmi);
                bundle.putInt("Age",age);

                // Create an Intent to navigate to the ResultActivity
                Intent intent = new Intent(InputActivity.this, ResultActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    // Method to calculate the BMI
    private double calculateBMI(double weight, double height) {
        // Calculate the BMI using the formula: BMI = weight / (height * height)
        height = height * 0.3048;
        return weight / (height * height);
    }
}