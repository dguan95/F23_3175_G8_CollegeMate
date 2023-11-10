package com.example.collegemate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfilePage extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    TextView txtViewFName;
    TextView TxtViewLName;
    TextView TxtViewMajor;
    TextView TxtViewDOB;

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        txtViewFName = findViewById(R.id.txtViewFName);
        TxtViewLName = findViewById(R.id.txtViewLName);
        TxtViewDOB = findViewById(R.id.txtViewBirth);
        TxtViewMajor = findViewById(R.id.txtViewMajor);


        bottomNavigationView=findViewById(R.id.BottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.profile_page);

        Intent i = getIntent();
        Bundle bundle = i.getExtras();

        if (bundle != null) {

            String fName = bundle.getString("FNAME");
            String lName = bundle.getString("LNAME");
            String major = bundle.getString("MAJOR");
            String day = bundle.getString("DAY");
            String month = bundle.getString("MONTH");
            String year = bundle.getString("YEAR");

            txtViewFName.setText(fName);
            TxtViewLName.setText(lName);
            TxtViewMajor.setText(major);
            TxtViewDOB.setText(day + " of " + month + ", " + year);

        } else {
            txtViewFName.setText("First Name");
            TxtViewLName.setText("Last Name");
            TxtViewMajor.setText("Major");
            TxtViewDOB.setText("00-00-0000");
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int menuItemId = item.getItemId();

        if (menuItemId == R.id.home_page){
            startActivity(new Intent(ProfilePage.this, MainActivity.class));
            return true;
        } else if (menuItemId == R.id.chat_page){
            startActivity(new Intent(ProfilePage.this, ChatActivity.class));
            return true;
        } else if (menuItemId == R.id.search_page){
            startActivity(new Intent(ProfilePage.this, SearchActivity.class));
            return true;
        } else if (menuItemId == R.id.profile_page){

            return true;
        }
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

}