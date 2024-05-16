package com.example.calcontrol;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="UserDB";
    private  static final int DATABASE_VERSION=1;


    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTableQuery = "CREATE TABLE users (id INTEGER PRIMARY KEY AUTOINCREMENT, person TEXT,height TEXT,weight TEXT,email TEXT, password TEXT)";
        db.execSQL(createUserTableQuery);

        String createFoodsTableQuery = "CREATE TABLE foods (id INTEGER PRIMARY KEY AUTOINCREMENT, isim TEXT, calorie INTEGER, datePicker TEXT)";
        db.execSQL(createFoodsTableQuery);
        Log.d("DatabaseHelper","users and foods tables created successfully");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);

    }

    public long addUser(String person, String weight, String height, String email, String key) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("person", person);
        values.put("weight", weight);
        values.put("height", height);
        values.put("email", email);
        values.put("password",key);
        long result = db.insert("users", null, values);
        db.close();
        return result;

    }
    public boolean checkUser(String email,String password ) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"email"};
        String selection = "email=? and password=?";
        String[] selectionArgs = {email, password};
        Cursor cursor = db.query("users", columns, selection, selectionArgs, null, null, null);
        boolean isValid = cursor != null && cursor.getCount() > 0;
        if (cursor != null) {
            ((Cursor) cursor).close();
        }

        db.close();

        return isValid;
    }
    public long addFood(String foodName, int calories, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("food_name", foodName);
        values.put("calories", calories);
        values.put("date", date);
        long result = db.insert("foods", null, values);
        db.close();
        return result;
    }



}
