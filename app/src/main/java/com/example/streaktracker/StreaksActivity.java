package com.example.streaktracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StreaksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_streaks);
    }

    public void goToMainActivity(View view)
    {
        Intent intent = new Intent(StreaksActivity.this, MainActivity.class);
        startActivity(intent);
    }
}