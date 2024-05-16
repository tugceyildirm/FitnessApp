package com.example.calcontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class signUp extends AppCompatActivity {
    TextView logpage;
    EditText personName,pheight,pweight,password,mail;
    Button kayit;
    DatabaseHelper databaseHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        personName =findViewById(R.id.person);
        pheight=findViewById(R.id.height);
        pweight=findViewById(R.id.weight);
        password=findViewById(R.id.key);
        mail=findViewById(R.id.mail);
        kayit=findViewById(R.id.kayit);
        logpage=findViewById(R.id.logpage);

        databaseHelper= new DatabaseHelper(this);


        kayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String person = personName.getText().toString();
                String weight = pweight.getText().toString();
                String height = pheight.getText().toString();
                String email = mail.getText().toString();
                String key = password.getText().toString();

                long result = databaseHelper.addUser( person, weight, height, email, key);

                if (result != -1) {
                    Toast.makeText(signUp.this, "Kullanıcı başarıyla kaydedildi.", Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(signUp.this,login.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(signUp.this, "Kullanıcı kaydedilirken bir hata oluştu.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        logpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(signUp.this, login.class);
                startActivity(intent);
                finish();
            }
        });

    }
}