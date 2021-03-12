package com.example.scorecounter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class WinnerActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_display;
    private static final String TAG = " WinnerActivity";
   private  EditText textLocation, textPlainMessage;
   private Button messageBtn, locationBtn, dialBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);

        getReferenceWidgets();
        setOnClickListenerForButtons();


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

    private void getReferenceWidgets(){
        Log.d(TAG ,"Inside of gerReferenceWidgets methd");
        textLocation = findViewById(R.id.text_location);
        textPlainMessage = findViewById(R.id.textPlainMessage);

        messageBtn = findViewById(R.id.button_text_msg);
        locationBtn = findViewById(R.id.button_location);
        dialBtn = findViewById(R.id.button_call);

        Log.d(TAG ,"end of method ");
    }

    private void setOnClickListenerForButtons(){
        Log.d(TAG, "inside setOnClickListenerForBUttons");

        messageBtn.setOnClickListener(this);
        locationBtn.setOnClickListener(this);
        dialBtn.setOnClickListener(this);

        Log.d(TAG, "inside setOnClickListenerForBUttons");
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "inside  onclick method");
        switch (v.getId()){
            case R.id.button_text_msg:
                Log.d(TAG, "text message button clicked");
                SendTextMessage();
                break;
            case R.id.button_location:
                Log.d(TAG,"location button clicked");
                FindLocation();
                break;
            case R.id.button_call:
                Log.d(TAG,"call button clicked");
                DialNumber();
                break;
        }

        Log.d(TAG, "end onclick method");
    }

    private  void SendTextMessage(){
        Log.d(TAG, "inside  SendTextMessage method");

        String textToSend = textPlainMessage.getText().toString();
        String  texttype = "text/plain";
        ShareCompat.IntentBuilder
                   .from(this)
                   .setType(texttype)
                   .setChooserTitle("pick an app")
                   .setText(textToSend)
                   .startChooser();


        Log.d(TAG, "end  SendTextMessage method");

    }

    private void FindLocation(){
        Log.d(TAG, "inside  FindLocation method");

        String location = textLocation.getText().toString();
        Uri geoLoc = Uri.parse("geo:0,0?q=" + location);

        Intent intent = new Intent(Intent.ACTION_VIEW,geoLoc);

        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }else {
            Log.d(TAG,"Location not found"+ geoLoc);
            Toast.makeText(this, "Location not found "+ geoLoc, Toast.LENGTH_LONG).show();
        }

        Log.d(TAG, "end of  FindLocation method");


    }
    private void DialNumber(){
        Log.d(TAG, "inside of DialNumber method");
        Intent intent = new Intent(Intent.ACTION_DIAL);
        startActivity(intent);

        Log.d(TAG, "end of  DialNumber method");

    }
}