package com.example.collegemate;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.Period;

public class ProfilePage extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    TextView txtViewFName;
    TextView TxtViewLName;
    TextView TxtViewMajor;
    TextView TxtViewDOB;
    ImageView imageView;
    Button profileSettings;
    DBHelper dbHelper;
    TextView TxtViewDescription;
    String fNameInstance;
    String lNameInstance;
    String majorInstance;

    int birthYearInstance;
    int birthMonthInstance;
    int birthDateInstance;
    Uri URI;


    //shared preferences save state across activities
    private SharedPreferences sharedPreferences;


    BottomNavigationView bottomNavigationView;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        txtViewFName = findViewById(R.id.txtViewFName);
        TxtViewLName = findViewById(R.id.txtViewLName);
        TxtViewDOB = findViewById(R.id.txtViewBirth);
        TxtViewMajor = findViewById(R.id.txtViewMajor);
        imageView = findViewById(R.id.imgViewProfilIcon2);
        TxtViewDescription=findViewById(R.id.txtViewDescription);

        dbHelper = new DBHelper(this);



        bottomNavigationView=findViewById(R.id.BottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.profile_page);

        //initialize the state
        sharedPreferences = getPreferences(Context.MODE_PRIVATE);

        Intent intent1 = getIntent();
        if (intent1 != null && intent1.hasExtra("userId")) {
            long userId = intent1.getLongExtra("userId", -1);
            Log.d("ProfilePageActivity", "Retrieved userId: " + userId);

        }



        Intent i = getIntent();
        Bundle bundle = i.getExtras();
            Log.d("TEST", "HERE 1");



            if (bundle != null && bundle.getString("FNAME") != null) {
                String fName = bundle.getString("FNAME");
                String lName = bundle.getString("LNAME");
                String major = bundle.getString("MAJOR");
                String day = bundle.getString("DAY");
                String month = bundle.getString("MONTH");
                String year = bundle.getString("YEAR");
                String userDesc = bundle.getString("DESCRIPTION");
                String imagePath = bundle.getString("IMAGE");
                if(imagePath!=null)
                {
                    URI = Uri.parse(imagePath);
                    imageView.setImageURI(URI);

                    Glide.with(this)
                            .load(URI)
                            .transform(new CircleCrop())
                            .into(imageView);
                }


                int dayInt = Integer.parseInt(day);
                int monthInt = Integer.parseInt(month);
                int yearInt = Integer.parseInt(year);


                LocalDate birthdate = LocalDate.of(yearInt, monthInt, dayInt);
                LocalDate currentDate = LocalDate.now();
                Period age = Period.between(birthdate, currentDate);
                int ageYears = age.getYears();
                String ageStr= Integer.toString(ageYears);
                //Log.d("AGE", ageStr);

                txtViewFName.setText(fName);
                TxtViewLName.setText(lName);
                TxtViewMajor.setText(major);
                TxtViewDOB.setText(ageStr);
                //TxtViewDOB.setText(day + "-" + month + "-" + year);
                TxtViewDescription.setText(userDesc);


            } else {
                Log.d("TEST 1", "HERE");
                if (getIntent() != null) {
                    Log.d("TEST 2", getIntent().getSerializableExtra("user") + "");
                    Log.d("GETTING USER", "asfsafsa");
                    User user = (User) getIntent().getSerializableExtra("user");

                    if (user != null) {
                        Log.d("BIBAS", user.getEmail() + " " + user.getPassword());
                        Toast.makeText(this, "Success " + user.getEmail() + " " + user.getFirstName(), Toast.LENGTH_SHORT).show();

                        txtViewFName.setText(user.getFirstName());
                        TxtViewLName.setText(user.getLastName());
                        TxtViewMajor.setText(user.getMajor());
                        TxtViewDescription.setText(user.getDesciption());

                        if(user.getImagePath()!=null)
                        {
                            URI = Uri.parse(user.getImagePath());


                            Glide.with(this)
                                    .load(Uri.parse(user.getImagePath()))
                                    .transform(new CircleCrop())
                                    .into(imageView);
                        }


                        int dayInt = user.getBirthDate();
                        int monthInt = user.getBirthMonth();
                        int yearInt = user.getBirthYear();

                        LocalDate birthdate = LocalDate.of(yearInt, monthInt, dayInt);
                        LocalDate currentDate = LocalDate.now();
                        Period age = Period.between(birthdate, currentDate);
                        int ageYears = age.getYears();
                        String ageStr= Integer.toString(ageYears);
                        //Log.d("AGE", ageStr);

                        TxtViewDOB.setText(ageStr);

                       // TxtViewDOB.setText(user.getBirthDate() + "-" + user.getBirthMonth() + "-" + user.getBirthYear());
                    } else {
                        if (sharedPreferences.contains("FNAME") && sharedPreferences.contains("LNAME")
                                && sharedPreferences.contains("MAJOR") && sharedPreferences.contains("DOB")) {
                            Log.d("LOADING STATE", "FNAME: " + sharedPreferences.getString("FNAME", "none"));
                            loadState();
                        } else {
                            txtViewFName.setText("name");
                            TxtViewLName.setText("last name");
                            TxtViewMajor.setText("major");
                            TxtViewDOB.setText("DOB");
                            TxtViewDescription.setText("Description");
                        }
                    }
                } else {
                    Log.d("TEST 3", "HERE");
                    if (sharedPreferences.contains("FNAME") && sharedPreferences.contains("LNAME")
                            && sharedPreferences.contains("MAJOR") && sharedPreferences.contains("DOB")) {
                        Log.d("LOADING STATE", "FNAME: " + sharedPreferences.getString("FNAME", "none"));
                        loadState();
                    } else {
                        txtViewFName.setText("name");
                        TxtViewLName.setText("last name");
                        TxtViewMajor.setText("major");
                        TxtViewDOB.setText("DOB");
                        TxtViewDescription.setText("Description");
                    }
                }
            }



        }
//    }

    private void loadState() {
        txtViewFName.setText(sharedPreferences.getString("FNAME", "B"));
        TxtViewLName.setText(sharedPreferences.getString("LNAME", "A"));
        TxtViewMajor.setText(sharedPreferences.getString("MAJOR", "C"));
        TxtViewDOB.setText(sharedPreferences.getString("DOB", "R"));
        TxtViewDescription.setText(sharedPreferences.getString("DESCRIPTION","D"));
        Glide.with(this)
                .load(Uri.parse(sharedPreferences.getString("IMAGE", "I")))
                .transform(new CircleCrop())
                .into(imageView);

    }

    private void saveState() {
        //editor object to save values inside state
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("FNAME", txtViewFName.getText().toString());
        editor.putString("LNAME", TxtViewLName.getText().toString());
        editor.putString("MAJOR", TxtViewMajor.getText().toString());
        editor.putString("DOB", TxtViewDOB.getText().toString());
        editor.putString("DESCRIPTION",TxtViewDescription.getText().toString());
        editor.putString("IMAGE", URI.toString());

        editor.apply();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "Activity Paused", Toast.LENGTH_SHORT).show();
        saveState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int menuItemId = item.getItemId();

        if (menuItemId == R.id.home_page){
            Intent quizIntent = new Intent(ProfilePage.this,  MainActivity.class);
            long userId = getIntent().getLongExtra("userId", -1);
            quizIntent.putExtra("userId", userId);
            startActivity(quizIntent);
            return true;
        } else if (menuItemId == R.id.chat_page){
            DBHelper dbHelper = new DBHelper(ProfilePage.this);
            long userId = getIntent().getLongExtra("userId", -1);
            int totalScore = dbHelper.getTotalScoreForUser(userId);

            if (totalScore > 8) {
                // Navigate to MatchActivity
                Intent matchIntent = new Intent(ProfilePage.this, MatchActivity.class);
                matchIntent.putExtra("userId", userId);
                startActivity(matchIntent);
            } else {
                // Navigate to QuizActivity
                Intent quizIntent = new Intent(ProfilePage.this, QuizActivity.class);
                quizIntent.putExtra("userId", userId);
                startActivity(quizIntent);
            }
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



    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("FNAME", txtViewFName.getText().toString());
        savedInstanceState.putString("LNAME", TxtViewLName.getText().toString());
        savedInstanceState.putString("MAJOR", TxtViewMajor.getText().toString());
        savedInstanceState.putString("DOB", TxtViewDOB.getText().toString());
        savedInstanceState.putString("DESCRIPTION", TxtViewDescription.getText().toString());
        savedInstanceState.putString("IMAGE", URI.toString());

        super.onSaveInstanceState(savedInstanceState);
    }
@Override
protected void onStop() {
    super.onStop();
//    saveState();
    Toast.makeText(this, "Activity Stopped", Toast.LENGTH_SHORT).show();
//    sharedPreferences.edit().clear().apply();
}

@Override
protected void onDestroy() {
    sharedPreferences.edit().clear().apply();
    saveState();
    Toast.makeText(this, "Activity Destroyed", Toast.LENGTH_SHORT).show();
    super.onDestroy();
}
}