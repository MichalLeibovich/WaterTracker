package com.example.streaktracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StreaksActivity extends AppCompatActivity {

    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_streaks);

        Intent intent = getIntent();
        userName = intent.getStringExtra("USER_NAME");
    }

    public void goToMainActivity(View view)
    {
        Intent intent = new Intent(StreaksActivity.this, MainActivity.class);
        startActivity(intent);
    }
}