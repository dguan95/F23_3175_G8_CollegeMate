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

        if (menuItemId == R.id.home_page){
            return true;
        } else if (menuItemId == R.id.chat_page){
            Intent quizIntent = new Intent(MainActivity.this, MatchActivity.class);
            long userId = getIntent().getLongExtra("userId", -1); // Get the userId passed from LoginActivity
            quizIntent.putExtra("userId", userId); // Pass the userId to QuizActivity
            startActivity(quizIntent);
            return true;
        } else if (menuItemId == R.id.search_page){
            startActivity(new Intent(MainActivity.this, SearchActivity.class));
            return true;
        } else if (menuItemId == R.id.profile_page){
            Intent quizIntent = new Intent(MainActivity.this, ProfilePage.class);
            long userId = getIntent().getLongExtra("userId", -1); // Get the userId passed from LoginActivity
            quizIntent.putExtra("userId", userId); // Pass the userId to QuizActivity
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
