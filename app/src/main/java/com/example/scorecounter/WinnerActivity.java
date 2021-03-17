package com.example.scorecounter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class WinnerActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_display;
    private static final String TAG = " WinnerActivity";
    private static final int REQUEST_PHONE_CALL = 2;
   private static final int REQUEST_IMAGE_PIC = 1;

   private  EditText textLocation, textPlainMessage;
   private Button messageBtn, locationBtn, dialBtn , btn_picture;

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
        btn_picture = findViewById(R.id.button_pic);

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
                break;
            case R.id.button_call:
                Log.d(TAG,"call button clicked");
                DialNumber();
                break;
            case R.id.button_pic:
                Log.d(TAG, "camera btn clicked");
                TakePicture();
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

    private void  TakePicture(){

        Log.d(TAG, "inside of TakePicture method");

        Intent intentPic = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intentPic.resolveActivity(getPackageManager()) != null){

            startActivity(intentPic);
           // startActivityForResult(intentPic, REQUEST_IMAGE_PIC);

            //need to override the onstartactivityForResult to handle the activity

        }

        Log.d(TAG, "end of TakePicture method");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == REQUEST_IMAGE_PIC){
            Log.d(TAG, "inside of onActivityResult method");

            Intent intenShowImage = new Intent(this, showImageActivity.class);
            //create explicite intent to trigger the third activity (showImageActivity)
            intenShowImage.putExtras(data); //pass data received(image) to 3rd activity
            startActivity(intenShowImage);

            Log.d(TAG, "end of onActivityResult method");
        }

    }
}