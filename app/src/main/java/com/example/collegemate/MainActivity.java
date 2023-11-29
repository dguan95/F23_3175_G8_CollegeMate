package com.example.collegemate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    //Hello, this is a test - Diana
    //Hello, this is test again, editing on GitHub to see changes in my PC - Diana
    //HI, this is a test -Zoia !
    //Hi, this is a test -Dani

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView=findViewById(R.id.BottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home_page);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("userId")) {
            long userId = intent.getLongExtra("userId", -1);
            Log.d("MainActivity", "Retrieved userId: " + userId);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int menuItemId = item.getItemId();
        long userId;

        if (menuItemId == R.id.home_page){
            return true;
        } else if (menuItemId == R.id.chat_page){
            DBHelper dbHelper = new DBHelper(MainActivity.this);
            userId = getIntent().getLongExtra("userId", -1);
            int totalScore = dbHelper.getTotalScoreForUser(userId);

            if (totalScore > 8) {
                // Navigate to MatchActivity
                Intent matchIntent = new Intent(MainActivity.this, MatchActivity.class);
                matchIntent.putExtra("userId", userId);
                startActivity(matchIntent);
            } else {
                // Navigate to QuizActivity
                Intent quizIntent = new Intent(MainActivity.this, QuizActivity.class);
                quizIntent.putExtra("userId", userId);
                startActivity(quizIntent);
            }
            return true;
        } else if (menuItemId == R.id.search_page){
            Intent quizIntent = new Intent(MainActivity.this, SearchActivity.class);
            userId = getIntent().getLongExtra("userId", -1);
            quizIntent.putExtra("userId", userId);
            startActivity(quizIntent);
            return true;
        } else if (menuItemId == R.id.profile_page){
            Intent quizIntent = new Intent(MainActivity.this, ProfilePage.class);
            userId = getIntent().getLongExtra("userId", -1);
            quizIntent.putExtra("userId", userId);
            startActivity(quizIntent);
            return true;
        }

        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

}
