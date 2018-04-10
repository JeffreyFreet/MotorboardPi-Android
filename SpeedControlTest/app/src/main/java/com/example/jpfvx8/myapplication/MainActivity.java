package com.example.jpfvx8.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

//API Key is in res/values/strings.xml

public class MainActivity extends AppCompatActivity {

    Integer count = 0;
    TextView counter;
    Button safetyBtn;
    boolean powered = false;

    //All this is checking for correct version of Google Play Services
    private static final String TAG = "MainActivity";
    private static final int ERROR_DIALOUGE_REQUEST = 9001;

    private void init(){
        Button btnMap = findViewById(R.id.mapBtn);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });
    }

    public boolean isServicesOk(){
        Log.d(TAG, "isServicesOk: checking Google Play Services Version");

        int availible = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);

        if(availible == ConnectionResult.SUCCESS){
            //Everything Gucci, map requests can be made
            Log.d(TAG, "isServicesOk: Google Play Services is Working");
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(availible)){
            //not gucchi, but can be resolved
            Log.d(TAG, "isServicesOk: error occured, can be fixed");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, availible, ERROR_DIALOUGE_REQUEST);
            dialog.show();
        }
        else
        {
            Toast.makeText(this, "you can't make map requests", Toast.LENGTH_SHORT).show();
        }

        return false;
    }
    ////////////////////
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(isServicesOk()){
            init();
        }

        counter = findViewById(R.id.counterLbl);
        counter.setText(count.toString());

        safetyBtn = findViewById(R.id.safetyBtn);

        //Detects if the safety button is being held or not
        safetyBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN ) {
                    powered = true;
                }

                if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    powered = false;
                    count = 0;
                    counter.setText(count.toString());
                    //TODO
                        //Bluetooth Signal to Pi
                }
            return true;
            }
        });
    }


    //Key events for volume buttons to increment/decrement speed
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if((keyCode == KeyEvent.KEYCODE_VOLUME_UP) && powered){
            count++;
            counter.setText(count.toString());
            //TODO
                //Bluetooth Signal to Pi
        }

        if((keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) && powered){
            count--;
            counter.setText(count.toString());
            //TODO
                //Bluetooth Signal to Pi
        }
        return true;
    }
}
