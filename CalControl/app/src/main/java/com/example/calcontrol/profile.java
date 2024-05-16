package com.example.calcontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class profile extends AppCompatActivity {
    TextView kilo, boy ,userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        kilo=findViewById(R.id.kilo);
        boy= findViewById(R.id.boy);
        userName= findViewById(R.id.userName);
    }
}