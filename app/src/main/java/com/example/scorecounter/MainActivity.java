 package com.example.scorecounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView gameCount;
    private TextView otherCount;
    private  int T_a_Counter = 0;
    private  int T_b_Counter = 0;
    private static final String TAG = "MainActivity";
    protected static final String END_RESULT = "End Result";
    protected static final String WINNING_TEAM = "Who Won";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "start of onCreate");
        gameCount = findViewById(R.id.show_count);
        otherCount =  findViewById(R.id.show_enemyCount);

        gameCount.setText(""+T_a_Counter);
        otherCount.setText(""+T_b_Counter);
        Log.d(TAG, " my  Counter is"+ gameCount +"other Counter is "+ otherCount);
        Log.d(TAG, "end of onCreate");

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.d(TAG, "inside onSaveInstanceState");
        outState.putInt("team a Counter", T_a_Counter);
        outState.putInt("Tean bCounter", T_b_Counter);
        Log.d(TAG, "end of onSaveInstanceState team a counter ="+ T_a_Counter);
        Log.d(TAG, "end of onSaveInstanceState team b Counter ="+ T_b_Counter);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        T_a_Counter = savedInstanceState.getInt("T_a_Count", T_a_Counter);
        T_b_Counter = savedInstanceState.getInt("T_b_Count", T_b_Counter);
        Log.d(TAG, "T_a_counter is"+ gameCount +"T_b_count is "+ otherCount);
        Log.d(TAG, "end of onRestoreInstanceState");
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG, " onStart");

    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "inside of onResume");
        gameCount.setText(""+T_a_Counter);
        otherCount.setText(""+T_b_Counter);
        Log.d(TAG, "my counter is"+ gameCount +"their count is "+ otherCount);
        Log.d(TAG, "end of onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, " onRestart");

    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d(TAG, " onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, " onStop");

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, " onDestroy");

    }

    public void countUp(View view) {
        T_a_Counter++;
        gameCount.setText(""+ T_a_Counter);
        nextActivity();

    }

    public void eCountUp(View view) {

        T_b_Counter++;
        otherCount.setText(""+ T_b_Counter);
        nextActivity();


    }

    public void nextActivity() {
        Intent intent = new Intent(this, WinnerActivity.class);
        int result;
        if(T_a_Counter == 5){
            result = T_a_Counter - T_b_Counter;
            intent.putExtra(END_RESULT, result);
            intent.putExtra(WINNING_TEAM, " Team A Won By:");
            T_a_Counter =0 ;
            T_b_Counter =0;
            startActivity(intent);



        }
        else if(T_b_Counter == 5) {
             result =  T_b_Counter - T_a_Counter ;
             intent.putExtra(END_RESULT, result);
             intent.putExtra(WINNING_TEAM, "Team B won By:");
             T_b_Counter =0;
            T_a_Counter =0;
            startActivity(intent);

        }

    }

}