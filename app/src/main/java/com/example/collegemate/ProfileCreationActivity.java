package com.example.collegemate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProfileCreationActivity extends AppCompatActivity {

    ImageView imgViewProfile;
    EditText firstName;
    EditText lastName;
    EditText dOB;
    EditText year;
    // new branch
    Spinner month;
    EditText major;
    Button btnCreateProfile;
    int yearInt=0;
    List<String> months = new ArrayList<>(Arrays.asList("Month","January","February","March","April","May","June","July", "August","September", "October", "November","December"));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_creation);
        firstName = findViewById(R.id.editTXtFName);
        lastName = findViewById(R.id.editTXtLastName);
        dOB = findViewById(R.id.editTxtDayB);
        year = findViewById(R.id.editTxtYearB);
        month = findViewById(R.id.spinnerMonth);

        major = findViewById(R.id.editTxtMajor);

        try {
            yearInt = Integer.parseInt(year.getText().toString());
        } catch(Exception e)
        {
            e.printStackTrace();
            Log.d("Parse error",e.getMessage());
        }


        btnCreateProfile = findViewById(R.id.btnCreateProfile);
        btnCreateProfile.setOnClickListener((View v) -> {
                if(firstName.getText().toString().isEmpty())
                {
                    Toast.makeText(this, "Enter Your Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(lastName.getText().toString().isEmpty())
                {
                    Toast.makeText(this, "Enter Your Last Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(dOB.getText().toString().isEmpty())
                {
                    Toast.makeText(this, "Enter Date of Birth", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(year.getText().toString().isEmpty())
                {
                    Toast.makeText(this, "Enter year", Toast.LENGTH_SHORT).show();

                    return;
                }else if (yearInt%1 !=0)
                {
                    Toast.makeText(this, "Year is invalid!", Toast.LENGTH_SHORT).show();

                    return;
                }// else if(yearInt<1920 || yearInt >2023)
                //{
                   // Toast.makeText(this, "Year is invalid", Toast.LENGTH_SHORT).show();

                   // return;
               // }
                if(month.getSelectedItemPosition()==0)
                {
                    Toast.makeText(this, "Choose month", Toast.LENGTH_SHORT).show();

                    return;
                }

                try {

                    String fNameString = firstName.getText().toString();
                    String lNameString = lastName.getText().toString();
                    String dayString = dOB.getText().toString();
                    String majoString = major.getText().toString();
                    String yearString = year.getText().toString();
                    String monthString="";

                    for (int i=0; i<months.size();i++)
                    {
                        if(month.getSelectedItemPosition()==i)
                        {
                            monthString = months.get(i);
                        }
                    }

                    Bundle bundle = new Bundle();
                    bundle.putString("FNAME", fNameString);
                    bundle.putString("LNAME", lNameString);
                    bundle.putString("MAJOR",majoString);
                    bundle.putString("DAY", dayString);
                    bundle.putString("MONTH", monthString);
                    bundle.putString("YEAR",yearString);

                    Intent intent = new Intent(ProfileCreationActivity.this, ProfilePage.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                    Log.d("Profile Creation Error", ex.getMessage());
                }



        });



    }
}