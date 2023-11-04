package com.example.collegemate;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "CollegeMateDatabase";
    private static final int DATABASE_VERSION = 1;


    private static final String TABLE_USERS_CREATE = "CREATE TABLE users " +
            "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "email TEXT, " +
            "password TEXT);";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_USERS_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);

    }

    public long addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email", user.getEmail());
        values.put("password", user.getPassword());
        long userId = db.insert("users", null, values);
        db.close();
        return userId;
    }


    public User getUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        User user = null;

        Cursor cursor = db.query("users", null, "email = ? AND password = ?",
                new String[]{email, password}, null, null, null);

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex("_id");
            int emailIndex = cursor.getColumnIndex("email");
            int passwordIndex = cursor.getColumnIndex("password");

            if (idIndex != -1 && emailIndex != -1 && passwordIndex != -1) {
                user = new User();
                user.setId(cursor.getLong(idIndex));
                user.setEmail(cursor.getString(emailIndex));
                user.setPassword(cursor.getString(passwordIndex));
            }
        }

        cursor.close();
        db.close();

        return user;
    }
}
