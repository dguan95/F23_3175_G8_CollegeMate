package com.example.collegemate;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "CollegeMateDatabase";
    private static final int DATABASE_VERSION = 4; //i incremented when make changes

    private static final String TABLE_USERS_CREATE = "CREATE TABLE users " +
            "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "email TEXT UNIQUE, " +
            "password TEXT, " +
            "birthYear INTEGER, " +
            "birthMonth INTEGER, " +
            "birthDate INTEGER, " +
            "firstName TEXT, " +
            "lastName TEXT, " +
            "major TEXT, " +
            "image BLOB);";



    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_USERS_CREATE);
        String CREATE_QUIZ_TABLE = "CREATE TABLE Quiz (" +
                "idquiz INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "userid INTEGER," +
                "answer1 INTEGER, " +
                "answer2 INTEGER, " +
                "answer3 INTEGER, " +
                "answer4 INTEGER, " +
                "answer5 INTEGER, " +
                "answer6 INTEGER, " +
                "answer7 INTEGER, " +
                "answer8 INTEGER, " +
                "answer9 INTEGER, " +
                "answer10 INTEGER, " +
                "totalScore INTEGER, " +
                "FOREIGN KEY(userid) REFERENCES users(_id)" +
                ");";

        db.execSQL(CREATE_QUIZ_TABLE);

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

    public long addQuizData(long userId, int[] answers) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // Add user ID
        values.put("userid", userId);

        // Add individual answers (answer1 to answer10)
        for (int i = 0; i < answers.length; i++) {
            values.put("answer" + (i + 1), answers[i]);
        }

        // Calculate total score
        int totalScore = 0;
        for (int answer : answers) {
            totalScore += answer;
        }
        values.put("totalScore", totalScore);

        // Insert data into the Quiz table
        long quizId = db.insert("Quiz", null, values);
        db.close();
        return quizId;
    }

    public Cursor getQuizDataForUser(long userId) {
        SQLiteDatabase db = this.getReadableDatabase();

        // Columns to retrieve from the Quiz table
        String[] quizColumns = {
                "idquiz",
                "userid",
                "answer1", "answer2", "answer3", "answer4", "answer5",
                "answer6", "answer7", "answer8", "answer9", "answer10",
                "totalScore"
        };

        // Query to get quiz data for a specific user
        return db.query("Quiz", quizColumns, "userid = ?", new String[]{String.valueOf(userId)},
                null, null, null);
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

            int majorIndex = cursor.getColumnIndex("major");
            int birthDateIndex = cursor.getColumnIndex("birthDate");
            int birthYearIndex = cursor.getColumnIndex("birthYear");
            int birthMonthIndex = cursor.getColumnIndex("birthMonth");
            int fnameIndex = cursor.getColumnIndex("firstName");
            int lnameIndex = cursor.getColumnIndex("lastName");

//            int imageIndex = cursor.getColumnIndex("image");

            if (idIndex != -1 && emailIndex != -1 && passwordIndex != -1) {
                user = new User();
                user.setId(cursor.getLong(idIndex));

                user.setEmail(cursor.getString(emailIndex));
                user.setPassword(cursor.getString(passwordIndex));

                user.setMajor(cursor.getString(majorIndex));
                user.setFirstName(cursor.getString(fnameIndex));
                Log.d("Test", "Setting user fname to " + fnameIndex);
                user.setLastName(cursor.getString(lnameIndex));

                user.setBirthYear(cursor.getInt(birthYearIndex));
                user.setBirthMonth(cursor.getInt(birthMonthIndex));
                user.setBirthDate(cursor.getInt(birthDateIndex));
//                user.setImage(cursor.getBlob(imageIndex));
            }
        }

        cursor.close();
        db.close();

        return user;
    }

    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        Log.d("USER UPDATE", "updating user fname to " + user.getFirstName());

        values.put("birthYear", user.getBirthYear());
        values.put("birthMonth", user.getBirthMonth());
        values.put("birthDate", user.getBirthDate());
        values.put("firstName", user.getFirstName());
        values.put("lastName", user.getLastName());
        values.put("major", user.getMajor());
//        values.put("image", user.getImage());

        // Updating row
        int rowsAffected = db.update("users", values, "email = ?", new String[]{String.valueOf(user.getEmail())});
        Log.d("TEST", "rows affected: " + rowsAffected);
        db.close();

        return rowsAffected;
    }
}
