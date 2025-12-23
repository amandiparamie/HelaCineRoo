package com.ousl.eei4369designproject_s22010144;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String databaseName = "SignUp.db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "SignUp.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        // Create a table to store user emails and passwords
        MyDatabase.execSQL("create Table allusers(email TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDatabase, int i, int i1) {
        // Drop the existing table if it exists
        MyDatabase.execSQL("drop Table if exists allusers");
    }

    // Method to insert new user data into the database
    public Boolean insertData(String email, String password) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = MyDatabase.insert("allusers", null, contentValues);

        return result != -1;
    }

    // Method to check if an email already exists in the database
    public Boolean checkEmail(String email) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from allusers where email = ?", new String[]{email});

        return cursor.getCount() > 0;
    }

    // Method to check if the entered email and password match a record in the database
    public Boolean checkEmailPassword(String email, String password) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from allusers where email = ? and password = ?", new String[]{email, password});

        return cursor.getCount() > 0;
    }

    // Method to update user's email in the database
    public boolean updateEmail(String oldEmail, String newEmail) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", newEmail);
        int result = db.update("allusers", contentValues, "email = ?", new String[]{oldEmail});
        return result > 0;
    }

    // Method to delete user data from the database
    public Boolean deleteUser(String email) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        int result = MyDatabase.delete("allusers", "email = ?", new String[]{email});
        return result > 0;
    }
}
