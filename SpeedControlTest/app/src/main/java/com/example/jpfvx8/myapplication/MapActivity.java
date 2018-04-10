package com.example.jpfvx8.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by jpfvx8 on 3/13/18.
 */

public class MapActivity extends AppCompatActivity {

    private static final String TAG = "MapActivity";
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
    }
}
