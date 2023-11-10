package com.example.collegemate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FindAccommodationActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_accommodation);

        bottomNavigationView = findViewById(R.id.BottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.search_page);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int menuItemId = item.getItemId();

        if (menuItemId == R.id.home_page){
            return true;
        } else if (menuItemId == R.id.chat_page){
            startActivity(new Intent(FindAccommodationActivity.this, QuizActivity.class));
            return true;
        } else if (menuItemId == R.id.search_page){
            startActivity(new Intent(FindAccommodationActivity.this, SearchActivity.class));
            return true;
        } else if (menuItemId == R.id.profile_page){
            startActivity(new Intent(FindAccommodationActivity.this, ProfilePage.class));
            return true;
        }

        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}