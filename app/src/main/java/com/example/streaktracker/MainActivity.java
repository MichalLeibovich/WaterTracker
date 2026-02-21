package com.example.streaktracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

//    private boolean dataIsLoaded = false;

    FirebaseFirestore db;

    String username;

    SharedPreferences prefs;
    int glassesNum;
    TextView glassesCount;
    TextView congratsReachedYourGoal;
    ImageView imageTickGoal;

    int goalGlassesNum;
    boolean goalGlassesNumFlag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        SplashScreen.installSplashScreen(this);
//        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
//        splashScreen.setKeepOnScreenCondition(() -> !dataIsLoaded);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        // Simulate loading data
//        loadData();



        initializeDataPicker();


        username = "user";
         db = FirebaseFirestore.getInstance();
//        addUserToDbIfNotExists();


        prefs = getSharedPreferences("GlassPrefs", MODE_PRIVATE);
        int glassesNum = prefs.getInt("glassesNum", 0);

        glassesCount = findViewById(R.id.tv_glassesCount);
        //updateUI();
        glassesCount.setText(String.valueOf(glassesNum));

        congratsReachedYourGoal = findViewById(R.id.tv_congratulations);
        imageTickGoal = findViewById(R.id.img_tickGoal);
        congratsReachedYourGoal.setVisibility(View.INVISIBLE);
        imageTickGoal.setVisibility(View.INVISIBLE);

        goalGlassesNum = 8;
        goalGlassesNumFlag = false;
    }

//    private void loadData() {
//        // Example: pretend to load something for 2 seconds
//        new Handler().postDelayed(() -> {
//            dataIsLoaded = true; // Splash can now disappear
//        }, 2000);
//    }


    public void initializeDataPicker()
    {
        EditText dateEdt = findViewById(R.id.idEdtDate);

        dateEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar today = Calendar.getInstance();

                int year = today.get(Calendar.YEAR);
                int month = today.get(Calendar.MONTH);
                int day = today.get(Calendar.DAY_OF_MONTH);

                long now = today.getTimeInMillis();

                // on below line we are creating a variable for date picker dialog.
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // on below line we are setting date to our edit text.
                                dateEdt.setText("Save on: " + dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        },
                        year, month, day);
                datePickerDialog.getDatePicker().setMaxDate(now);
                // at last we are calling show to
                // display our date picker dialog.
                datePickerDialog.show();
            }
        });
    }

    private void addUserToDbIfNotExists() {
        User user = new User(username, 0, Timestamp.now());

        db.collection("users")
                .document(username)
                .set(user)
                .addOnSuccessListener(aVoid ->
                        Log.d("Firestore", "User saved"))
                .addOnFailureListener(e ->
                        Log.e("Firestore", "Error", e));
    }


    public void addGlass(View view)
    {
        glassesNum++;
        prefs.edit().putInt("glassesNum", glassesNum).apply();
        glassesCount.setText(String.valueOf(glassesNum));

        check_if_reached_goal();
        //updateUI();
    }

    public void resetGlasses(View view)
    {
        glassesNum = 0;
        prefs.edit().putInt("glasses", glassesNum).apply();
        glassesCount.setText(String.valueOf(glassesNum));
        //updateUI();
        reset_congrats_reached_goal();
    }

    public void removeGlass(View view)
    {
        if (glassesNum > 0)
            glassesNum--;

        if (goalGlassesNumFlag && glassesNum < goalGlassesNum)
        {
            goalGlassesNumFlag = false;
            reset_congrats_reached_goal();
        }

        prefs.edit().putInt("glasses", glassesNum).apply();
        glassesCount.setText(String.valueOf(glassesNum));
        //updateUI();
    }

    public void check_if_reached_goal()
    {
        if (glassesNum == goalGlassesNum && !goalGlassesNumFlag)
        {
            goalGlassesNumFlag = true;
            congratsReachedYourGoal.setVisibility(View.VISIBLE);
            imageTickGoal.setVisibility(View.VISIBLE);
        }
    }

    public void reset_congrats_reached_goal()
    {
        congratsReachedYourGoal.setVisibility(View.INVISIBLE);
        imageTickGoal.setVisibility(View.INVISIBLE);
        goalGlassesNumFlag = false;
    }

    public void goToStreaksActivity(View view)
    {
//        Intent intent = new Intent(MainActivity.this, StreaksActivity.class);

        // Put data into the Intent using key-value pairs
        // intent.putExtra("EXTRA_MESSAGE", "Hello from MainActivity!");
        // intent.putExtra("EXTRA_NUMBER", 42);
//        intent.putExtra("username", username);
//
//        // Start the new activity
//        startActivity(intent);
    }

    public void saveCount(View view)
    {

    }
}