package com.example.collegemate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.ByteArrayOutputStream;

public class ProfilePage extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    TextView txtViewFName;
    TextView TxtViewLName;
    TextView TxtViewMajor;
    TextView TxtViewDOB;
    ImageView imageView;
    Button profileSettings;
    DBHelper dbHelper;
    String fNameInstance;
    String lNameInstance;
    String majorInstance;

    int birthYearInstance;
    int birthMonthInstance;
    int birthDateInstance;

    private SharedPreferences sharedPreferences;


    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        txtViewFName = findViewById(R.id.txtViewFName);
        TxtViewLName = findViewById(R.id.txtViewLName);
        TxtViewDOB = findViewById(R.id.txtViewBirth);
        TxtViewMajor = findViewById(R.id.txtViewMajor);
        imageView = findViewById(R.id.imgViewProfilIcon2);

        dbHelper = new DBHelper(this);



        bottomNavigationView=findViewById(R.id.BottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.profile_page);

        sharedPreferences = getPreferences(Context.MODE_PRIVATE);

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

//                byte[] image = bundle.getByteArray("IMAGE");
//                Bitmap bitmap = BitmapFactory.decodeByteArray(image,  0, image.length);
//
//                imageView.setImageBitmap(bitmap);
                txtViewFName.setText(fName);
                TxtViewLName.setText(lName);
                TxtViewMajor.setText(major);
                TxtViewDOB.setText(day + "-" + month + "-" + year);
            } else {
                Log.d("TEST 1", "HERE");
                if (getIntent() != null) {
                    Log.d("TEST 2", getIntent().getSerializableExtra("user") + "");
//                String email = getIntent().getExtras().getString("userEmail");
//                String password = getIntent().getExtras().getString("userPassword");
//
//                User user = dbHelper.getUser(email, password);
                    Log.d("GETTING USER", "asfsafsa");
                    User user = (User) getIntent().getSerializableExtra("user");

                    if (user != null) {
                        Log.d("BIBAS", user.getEmail() + " " + user.getPassword());
                        Toast.makeText(this, "Success " + user.getEmail() + " " + user.getFirstName(), Toast.LENGTH_SHORT).show();

                        txtViewFName.setText(user.getFirstName());
                        TxtViewLName.setText(user.getLastName());
                        TxtViewMajor.setText(user.getMajor());
                        TxtViewDOB.setText(user.getBirthDate() + "-" + user.getBirthMonth() + "-" + user.getBirthYear());
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

//        String imageString = sharedPreferences.getString("IMAGE", null);
//
//        if (imageString != null) {
//            byte[] imageByteArray = Base64.decode(imageString, Base64.DEFAULT);
//            Bitmap bitmap = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);
//            imageView.setImageBitmap(bitmap);
//        }
    }

    private void saveState() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("FNAME", txtViewFName.getText().toString());
        editor.putString("LNAME", TxtViewLName.getText().toString());
        editor.putString("MAJOR", TxtViewMajor.getText().toString());
        editor.putString("DOB", TxtViewDOB.getText().toString());

//        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
//        Bitmap bitmap = drawable.getBitmap();
//        byte[] imageByteArray = convertBitmapToByteArray(bitmap);
//        String imageString = Base64.encodeToString(imageByteArray, Base64.DEFAULT);
//
//        editor.putString("IMAGE", imageString);
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
            startActivity(new Intent(ProfilePage.this, MainActivity.class));
            return true;
        } else if (menuItemId == R.id.chat_page){
            startActivity(new Intent(ProfilePage.this, MatchActivity.class));
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

    public static byte[] convertBitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("FNAME", txtViewFName.getText().toString());
        savedInstanceState.putString("LNAME", TxtViewLName.getText().toString());
        savedInstanceState.putString("MAJOR", TxtViewMajor.getText().toString());
        savedInstanceState.putString("DOB", TxtViewDOB.getText().toString());

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