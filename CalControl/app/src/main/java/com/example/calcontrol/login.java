package com.example.calcontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {
    TextView signup;
    EditText logmail,logpassword;
    Button giris;
    DatabaseHelper databaseHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        giris=findViewById(R.id.giris);
        logmail=findViewById(R.id.logmail);
        logpassword=findViewById(R.id.logpassword);
        signup=findViewById(R.id.signup);

        databaseHelper = new DatabaseHelper(this);


        giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email =logmail.getText().toString();
                String password= logpassword.getText().toString();
                if (databaseHelper.checkUser(email,password)){
                    Intent intent=new Intent(login.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    // Hatalı giriş bilgileri
                    Toast.makeText(login.this, "Hatalı email veya şifre!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(login.this,signUp.class);
                startActivity(intent);
                finish();

            }
        });

    }
}