package com.example.calcontrol;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.text.BreakIterator;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {
    ImageView logout,exc,kcal,person;
    Button addButton;
    EditText calories,foodName;
    DatabaseHelper databaseHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calories=findViewById(R.id.calorie);
        foodName=findViewById(R.id.isim);
        databaseHelper = new DatabaseHelper(this);
        DatePicker datePicker = findViewById(R.id.datePicker);
        addButton = findViewById(R.id.addButton);


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String foodNameValue = foodName.getText().toString();
                int caloriesValue = Integer.parseInt(calories.getText().toString());
                String dateStr = datePicker.getDayOfMonth()+ "/"+ (datePicker.getMonth() + 1) + "/" + datePicker.getYear();

                long result=databaseHelper.addFood(foodNameValue,caloriesValue,dateStr);
                if (result != -1) {
                    Toast.makeText(MainActivity.this, "Eklendi", Toast.LENGTH_SHORT).show();
                  
                    foodName.setText("");
                    calories.setText("");
                    datePicker.updateDate(Calendar.getInstance().get(Calendar.YEAR),   Calendar.getInstance().get(Calendar.MONTH),
                            Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

                } else {
                    Toast.makeText(MainActivity.this, "Eklendi ", Toast.LENGTH_SHORT).show();
                }


            }
        });

        logout= findViewById(R.id.logoutimg);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogoutDialog();

                    }
                });
        exc=findViewById(R.id.exc);
        exc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,exercise.class);
                startActivity(intent);

            }
        });
        person=findViewById(R.id.person);
        person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,profile.class);
                startActivity(intent);

            }
        });
        kcal=findViewById(R.id.kcal);
        kcal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });


    }




    private void showLogoutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Oturumu kapat")
                .setMessage("Oturumu kapatmak istediğinize emin misiniz?")
                .setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        signOutAndRedirectToLogin();
                    }
                })
                .setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void signOutAndRedirectToLogin() {
        Intent intent = new Intent(MainActivity.this, login.class);
        startActivity(intent);
        finish();
    }
}
