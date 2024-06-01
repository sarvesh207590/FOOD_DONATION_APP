package com.example.project;

import static android.icu.text.MessagePattern.ArgType.SELECT;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String databaseName = "SignLog.db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "SignLog.db", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        String query1 = ("create Table users(email TEXT primary key,Username TEXT ,password TEXT)");
        MyDatabase.execSQL(query1);

        String query2 = ("create Table profile(email TEXT primary key,Username TEXT ,password TEXT, address TEXT)");
        MyDatabase.execSQL(query2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i2) {
        MyDB.execSQL("drop Table if exists users");
        MyDB.execSQL("drop Table if exists profile");
        onCreate(MyDB);
    }

    public Boolean insertData(String email, String Username, String password) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("username", Username);
        contentValues.put("password", password);
        long result = MyDatabase.insert("users", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            MyDatabase.close();
            return true;
        }
    }

    public Boolean checkEmail(String email) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from users where email = ?", new String[]{email});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            MyDatabase.close();
            return false;
        }
    }

    public Boolean checkEmailPassword(String email, String password) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from users where email = ? and password = ?", new String[]{email, password});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            MyDatabase.close();

            return false;
        }
    }
//created bu Super SAM
    public boolean deleteRecord(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Check if email and password match
        Cursor cursor = db.rawQuery("SELECT * FROM users" +
                " WHERE email = ? AND " +
                "password = ?", new String[]{email, password});
        if (cursor.getCount() > 0) {
            // Password matched, delete the tuple
            int rowsDeleted = db.delete("users",
                    "email = ? AND password = ?",
                    new String[]{email, password});
            cursor.close();
            return rowsDeleted > 0;
        } else {
            // No matching record found
            cursor.close();
            return false;
        }
    }
    //created bu Super SAM OVER

    public boolean deleteUser(String email) {
        SQLiteDatabase db = getWritableDatabase();
        int deletedRows = db.delete("users", "email = ?", new String[]{email});
        db.close();
        return deletedRows > 0;
    }

    public void updateprofile(String email, String Username, String password, String address) {
        ContentValues cv = new ContentValues();
        cv.put("email", email);
        cv.put("Username", Username); // Changed column name to 'Username'
        cv.put("password", password); // Changed column name to 'password'
        cv.put("address", address);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("profile", null, cv);
        db.close();
    }

//    public void updatedata(String email, String Username, String password, String address) {
//        ContentValues cv = new ContentValues();
//        cv.put("email", email);
//        cv.put("Username", Username); // Changed column name to 'Username'
//        cv.put("password", password); // Changed column name to 'password'
//        cv.put("address", address);
//        SQLiteDatabase db = getWritableDatabase();
//        db.update("profile", cv, "email=?", new String[]{email}); // Updated the condition to match 'email'
//        db.close();
//    }

//    public void deleteProfile(String email) {
//        SQLiteDatabase db = getWritableDatabase();
//        db.delete("profile", "email=?", new String[]{email});
//        db.close();
//    }

    public boolean checkUser(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE email = ? AND password = ?", new String[]{email, password});
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }

    public void updatePassword(String email, String newPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("password", newPassword);
        db.update("users", values, "email = ?", new String[]{email});
    }


}