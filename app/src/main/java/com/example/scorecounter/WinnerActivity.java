package com.example.scorecounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class WinnerActivity extends AppCompatActivity {

    private TextView tv_display;
    private static final String TAG = " WinnerActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);


        Log.d(TAG, "inside onCreate");

        tv_display = findViewById(R.id.tv_display2);

        //Get intent from he originating activity be sure to use the correct key to get the value
        Intent intent = getIntent();
        String strScoreCount = intent.getExtras().get(MainActivity.END_RESULT).toString();
        String winningTeam = intent.getExtras().get(MainActivity.WINNING_TEAM).toString();
        tv_display.setText(winningTeam);
        tv_display.append(" " + strScoreCount);


        Log.d(TAG, "end of onCreate");
    }
}