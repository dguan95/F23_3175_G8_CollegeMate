package com.example.collegemate;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.Arrays;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "CollegeMateDatabase";
    private static final int DATABASE_VERSION = 4; //i incremented when make changes


    //create table in DB
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
            "image BLOB," +
            "description TEXT);";

    private static final String TABLE_QUIZ_CREATE = "CREATE TABLE quiz_answers " +
            "(quizid INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "user_id INTEGER, " +
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
           "FOREIGN KEY(user_id) REFERENCES users(_id));";



    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_USERS_CREATE);
        db.execSQL(TABLE_QUIZ_CREATE);


    }



    // drop the table when version is changed
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS quiz_answers");
        onCreate(db);

    }


    //add user to the DB
    public long addUser(User user) {
        //used to write to DB
        SQLiteDatabase db = this.getWritableDatabase();
        //class to include values in insert statement easier
        ContentValues values = new ContentValues();
        values.put("email", user.getEmail());
        values.put("password", user.getPassword());

        //simplifies SQL insert statement
        long userId = db.insert("users", null, values);
        db.close();
        return userId;
    }

    public long saveQuizAnswers(long userId, int[] answers) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("user_id", userId);
        for (int i = 0; i < answers.length; i++) {
            values.put("answer" + (i + 1), answers[i]);
        }
        long quizId = db.insert("quiz_answers", null, values);
        db.close();
        return quizId;
    }
    public int[] getQuizAnswersForUser(long userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        int[] answers = new int[10];

        Cursor cursor = db.query("quiz_answers", null, "user_id = ?", new String[]{String.valueOf(userId)},
                null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            for (int i = 0; i < 10; i++) {
                answers[i] = cursor.getInt(i + 2);
                          }
            cursor.close();
        } else {

            Arrays.fill(answers, -1);
        }

        return answers;
    }


    //finds and return user using email and password
    public User getUser(String email, String password) {
        //used to read from DB
        SQLiteDatabase db = this.getReadableDatabase();
        User user = null;


        //cursor goes through records inside DB and finds the appropriate one
        // simplifies SQL select statement(SELECT FROM users where email and password = passed values)
        Cursor cursor = db.query("users", null, "email = ? AND password = ?",
                new String[]{email, password}, null, null, null);

        //goes to the first instance found
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
            int descIndex = cursor.getColumnIndex("description");

//            int imageIndex = cursor.getColumnIndex("image");

            //-1 = not found
            //if records in DB dound -> collect all info into 1 user object
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
                user.setDesciption(cursor.getString(descIndex));
            }
        }

        cursor.close();
        db.close();

        return user;
    }

    //same method as previous, but uses email only
    public User getUser(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        User user = null;

        Cursor cursor = db.query("users", null, "email = ?",
                new String[]{email}, null, null, null);

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
            int descIndex = cursor.getColumnIndex("description");

//            int imageIndex = cursor.getColumnIndex("image");

            if (idIndex != -1 && emailIndex != -1 && passwordIndex != -1) {
                user = new User();
                user.setId(cursor.getLong(idIndex));

                user.setEmail(cursor.getString(emailIndex));
                user.setPassword(cursor.getString(passwordIndex));

                user.setMajor(cursor.getString(majorIndex));
                user.setFirstName(cursor.getString(fnameIndex));
                Log.d("Test 2", "Setting user fname to " + fnameIndex);
                user.setLastName(cursor.getString(lnameIndex));

                user.setBirthYear(cursor.getInt(birthYearIndex));
                user.setBirthMonth(cursor.getInt(birthMonthIndex));
                user.setBirthDate(cursor.getInt(birthDateIndex));
//                user.setImage(cursor.getBlob(imageIndex));
                user.setDesciption(cursor.getString(descIndex));
            }
        }

        cursor.close();
        db.close();

        return user;
    }

    //update user record inside the DB when we fill profile info(FName, LName etc)
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
        values.put("description", user.getDesciption());

        // Updating row
        int rowsAffected = db.update("users", values, "email = ?", new String[]{String.valueOf(user.getEmail())});
        Log.d("TEST", "rows affected: " + rowsAffected);
        db.close();

        return rowsAffected;
    }
    public long getUserIdByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        long userId = -1;

        String[] columns = {"_id"};
        String selection = "email=?";
        String[] selectionArgs = {email};

        Cursor cursor = db.query("users", null, "email = ?",
                new String[]{ email }, null, null, null);
       // Cursor cursor = db.query("users", columns, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex("_id");
            if (idIndex != -1) {
                userId = cursor.getLong(idIndex);
            }
            cursor.close();
        }

        return userId;
    }
    public User getUserById(long userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        User user = null;

        Cursor cursor = db.query(
                "users",  // Table name
                new String[]{"_id", "firstName", "major","image"},
                "_id=?",  // Selection criteria
                new String[]{String.valueOf(userId)},
                null, null, null
        );

        if (cursor != null && cursor.moveToFirst()) {
           user = new User();
            int idIndex = cursor.getColumnIndex("_id");
            int firstNameIndex = cursor.getColumnIndex("firstName");
            int majorIndex = cursor.getColumnIndex("major");
            int imageIndex = cursor.getColumnIndex("image");

            if (idIndex != -1 && firstNameIndex != -1 && majorIndex != -1 && imageIndex != -1) {
                user.setId(cursor.getLong(idIndex));
                user.setFirstName(cursor.getString(firstNameIndex));
                user.setMajor(cursor.getString(majorIndex));
                byte[] imageBytes = cursor.getBlob(imageIndex);
                user.setImage(imageBytes);
            } else {
                Log.e("CursorError", "Column indices not found");
            }
            cursor.close();
        } else {
            Log.e("CursorError", "Cursor is null or empty");
        }

        db.close();
        return user;
    }

}

