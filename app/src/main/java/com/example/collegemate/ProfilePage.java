package com.example.collegemate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ProfilePage extends AppCompatActivity {
    TextView txtViewFName;
    TextView TxtViewLName;
    TextView TxtViewMajor;
    TextView TxtViewDOB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        txtViewFName = findViewById(R.id.txtViewFName);
        TxtViewLName = findViewById(R.id.txtViewLName);
        TxtViewDOB = findViewById(R.id.txtViewBirth);
        TxtViewMajor = findViewById(R.id.txtViewMajor);

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
}