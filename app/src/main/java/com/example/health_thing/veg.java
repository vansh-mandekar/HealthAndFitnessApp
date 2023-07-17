package com.example.health_thing;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import androidx.appcompat.app.AppCompatActivity;

public class veg extends AppCompatActivity {
    private Button vegButton;
    private Button nonVegButton;
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foodtype);

        vegButton = findViewById(R.id.vegb);
        nonVegButton = findViewById(R.id.nonvegb);
        Intent intent = getIntent();
        category = intent.getStringExtra("category");
        vegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDietPlanActivity("veg");
            }
        });

        nonVegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDietPlanActivity("nonveg");
            }
        });
    }

    private void startDietPlanActivity(String mealtype) {
        Intent intent = new Intent(veg.this, DietPlan.class);
        intent.putExtra("mealtype", mealtype);
        intent.putExtra("category", category);
        startActivity(intent);
    }
}