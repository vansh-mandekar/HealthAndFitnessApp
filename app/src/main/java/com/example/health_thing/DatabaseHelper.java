package com.example.health_thing;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "fitness.db";
    private static final int DATABASE_VERSION = 5;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the tables
        String createTableQuery = "CREATE TABLE IF NOT EXISTS meal_plan (id INTEGER PRIMARY KEY, category TEXT, plan_name TEXT, mealplan TEXT)";
        db.execSQL(createTableQuery);
        String createTableQuery1 = "CREATE TABLE IF NOT EXISTS workoutplans (wid INTEGER PRIMARY KEY, wcategory TEXT, workoutplan TEXT)";
        db.execSQL(createTableQuery1);

        // Insert initial data
        String insertDataQuery = "INSERT INTO meal_plan (id, category, plan_name, mealplan) VALUES " +
                "('1', 'OVERWEIGHT', 'WLDP1','meal-1\n" +
                "breakfast - oatmeal/oats and green tea\n" +
                "lunch - vegetable salad\n" +
                "evening snack - dry fruits or popcorn\n" +
                "dinner - chapati(roti) with vegetables" +
                "                                      '),\n" +

                "('2', 'OVERWEIGHT', 'WLDP2', 'meal - 2\n" +
                "breakfast - bananas and dosaa/idli (reasonable quantity)\n" +
                "lunch - sweet potatoes\n" +
                "evening snack - oats\n" +
                "dinner - veg rice" +
                "                                         '),\n" +

                "('3', 'OVERWEIGHT', 'WLDP3', 'meal-1(non-veg)\n" +
                "breakfast - eggs and bread\n" +
                "lunch - chicken(non fired) with rice\n" +
                "evening snack - dry fruits or popcorn\n" +
                "dinner - chapati(roti) with vegetables(avoid meat before sleep" +
                "                                             '),\n" +

                "('4', 'OVERWEIGHT', 'WLDP4','meal-2(non-veg)\n" +
                "breakfast - egg fried rice/ pancakes \n" +
                "lunch - fish(not fried) with rice \n" +
                "evening snack - oats \n" +
                "dinner - veg rice with veg currey" +
                "                                   '),\n" +

                "('5', 'UNDERWEIGHT', 'WGDP1','meal-1\n" +
                "breakfast - GREEK YOGURT WITH NUTS, AND BERRIES \n" +
                "lunch - cheese pasta\n" +
                "evening snack - chocolate milkshake\n" +
                "dinner - chapati with vegetables and rice with curry" +
                "                                        '),\n"+

                "('6', 'UNDERWEIGHT', 'WGDP2','meal-2\n" +
                "breakfast - oats/cereals with bannanas and dry fruits\n" +
                "lunch - high calorie noodles or pasta\n" +
                "evening snack - flavoured yogurt\n" +
                "dinner - chapati with paneer or soya and rice" +
                "                                            '),\n"+

                "('7',  'UNDERWEIGHT', 'WGDP3','meal-1(non-veg)\n" +
                "breakfast - 2 or more eggs with bread\n" +
                "lunch - high calorie chicken roll(eat enough until full)\n" +
                "evening snack - sandwich/popcorns\n" +
                "dinner - chapati(roti) and rice with chicken or mutton" +
                "                                                '),\n"+

                "('8', 'UNDERWEIGHT', 'WGDP4','meal-2(non-veg)\n" +
                "breakfast - EGGS AND AVOCADO TOAST with chocolate smoothie\n" +
                "lunch - chicken burger\n" +
                "evening snack - milkshake \n" +
                "dinner - chicken roll" +
                "                                   '),\n"+

                "('9', 'NORMAL', 'NDP1','meal-1\n" +
                "breakfast: Overnight oats made with rolled oats\n" +
                "lunch: Chickpea salad with mixed greens, cucumber\n" +
                "snack: Greek yogurt with granola and berries\n" +
                "dinner: Lentil curry with brown rice" +
                "                                         '),\n"+

                "('10', 'NORMAL', 'NDP2','meal-2\n" +
                "breakfast: masala dosa \n" +
                "lunch: Quinoa and black bean burrito bowl \n" +
                "snack: Fresh fruit salad\n" +
                "dinner: tofu, served with brown rice or noodles" +
                "                                          '),\n"+

                "('11', 'NORMAL', 'NDP3','meal-1(non-veg)\n" +
                "breakfast: Oatmeal with mixed berries\n" +
                "lunch: Grilled chicken or fish \n" +
                "snack: Sliced apple with peanut butter\n" +
                "dinner: Baked salmon with roasted sweet potatoes" +
                "                                    '),\n"+

                "('12', 'NORMAL', 'NDP4','meal-2(non-veg)\n" +
                "breakfast: Scrambled eggs \n" +
                "lunch: Turkey or chicken wrap \n" +
                "nnack: Hummus with carrot sticks\n" +
                "dinner: Grilled shrimp with brown rice" +
                "                                           ')\n";

        String insertWorkoutDataQuery = "INSERT INTO workoutplans (wid, wcategory, workoutplan) VALUES " +
                "('1', 'OVERWEIGHT', 'Low intensity Cardiovascular Exercise: walking(30-45 minutes), \n" +
                "high intensity cardio like: brisk walking(30-45 minutes)\n" +
                "upper body strength training like: dumbell bench press, lateral raise, cable tricep extension\n" +
                "lower body strength training like: leg press, deadlift, seated calf raise'), " +

                "('2', 'UNDERWEIGHT', 'Strength Training: squats, deadlifts, benchpress, \n" +
                "shoulder press, bicep curls, tricep dips, pullups\n" +
                "Progressive Overload: Gradually increase the weight\n" +
                "or resistance used for each exercise over time\n" +
                "Rest and recovery: Allow your muscles time to rest and recover between workouts'), " +

                "('3', 'NORMAL', 'low intensity cardio: walking, jogging, cycling\n" +
                "strength training: weight lifting, bicep curls, tricep dips, lateral rises, pushups\n" +
                "core strengthening: plank, crunches, flutterkicks, russian twists\n" +
                "leg exercises: squats, lunges, calf rises')";

        db.execSQL(insertWorkoutDataQuery);
        db.execSQL(insertDataQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @SuppressLint("Range")
    public String getMealPlansByCategory(String category) {
        List<String> mealPlans = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"mealplan"};
        String selection = "category = ?";
        String[] selectionArgs = {category};
        Cursor cursor = db.query("meal_plan", columns, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String mealPlan = cursor.getString(cursor.getColumnIndex("mealplan"));
                mealPlans.add(mealPlan);
            } while (cursor.moveToNext());

            cursor.close();
        }

        // Concatenate the meal plans using newline character '\n'
        StringBuilder mealPlanBuilder = new StringBuilder();
        for (String mealPlan : mealPlans) {
            mealPlanBuilder.append(mealPlan).append("\n");
        }

        return mealPlanBuilder.toString();
    }
    public String getWorkoutPlansByCategory(String wcategory) {
        List<String> workoutPlans = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"workoutplan"};
        String selection = "wcategory = ?";
        String[] selectionArgs = {wcategory};
        Cursor cursor = db.query("workoutplans", columns, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String workoutPlan = cursor.getString(cursor.getColumnIndex("workoutplan"));
                workoutPlans.add(workoutPlan);
            } while (cursor.moveToNext());

            cursor.close();
        }

        // Concatenate the workout plans using newline character '\n'
        StringBuilder workoutPlanBuilder = new StringBuilder();
        for (String workoutPlan : workoutPlans) {
            workoutPlanBuilder.append(workoutPlan).append("\n");
        }

        return workoutPlanBuilder.toString();
    }
}
