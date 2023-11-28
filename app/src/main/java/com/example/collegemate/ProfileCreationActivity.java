package com.example.collegemate;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ProfileCreationActivity extends AppCompatActivity {

    ImageView imgViewProfile;
    DBHelper dbHelper;
    EditText firstName;
    EditText lastName;
    EditText dOB;

    EditText year;
    // new branch
    Spinner month;
    EditText major;
    Button btnCreateProfile;
    long userId;
    int yearInt=0;

    EditText description;

    ActivityResultLauncher<Intent> resultLauncher;

    ImageView imgView;
    FloatingActionButton btnGallery;
    String URI;
    List<String> months = new ArrayList<>(Arrays.asList("Month","January","February","March","April","May","June","July", "August","September", "October", "November","December"));
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_creation);
        firstName = findViewById(R.id.editTXtFName);
        lastName = findViewById(R.id.editTXtLastName);
        dOB = findViewById(R.id.editTxtDayB);
        year = findViewById(R.id.editTxtYearB);
        month = findViewById(R.id.spinnerMonth);
        dbHelper = new DBHelper(this);
        imgView = findViewById(R.id.imgViewProfilIcon);
        btnGallery = findViewById(R.id.btnOpenGallery);

        description=findViewById(R.id.editTxtUserDescription);
        URI = Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.person).toString();


        Log.d("ProfileCreation", "Retrieved userId: " + userId);

        ActivityResultLauncher<PickVisualMediaRequest> pickMedia =
                registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {

                    if (uri != null) {
                        Log.d("PhotoPicker", "Selected URI: " + uri);
                        imgView.setImageURI(uri);
                        //glide with used to upload picture to imgView
                        Glide.with(this)
                                .load(uri)
                                .transform(new CircleCrop())
                                .into(imgView);

                        URI = uri.toString();

                    } else {
                        Log.d("PhotoPicker", "No media selected");
                    }
                });


        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickMedia.launch(new PickVisualMediaRequest.Builder()
                        .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                        .build());
            }
        });



        major = findViewById(R.id.editTxtMajor);




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
                 } else
                 {
                     try
                     {

                         int dayInt=Integer.parseInt(dOB.getText().toString());
                         if (dayInt%1 !=0)
                         {
                             Toast.makeText(this, "Day is invalid!", Toast.LENGTH_SHORT).show();

                             return;
                         } else if(dayInt<1 || dayInt >31)
                         {
                             Toast.makeText(this, "Day is invalid", Toast.LENGTH_SHORT).show();

                             return;
                         }

                     }
                     catch(Exception ex)
                     {
                         ex.printStackTrace();
                     }
                 }



                if(year.getText().toString().isEmpty())
                {
                    Toast.makeText(this, "Enter a year", Toast.LENGTH_SHORT).show();

                    return;
                }else {
                    try {
                        LocalDate currentDate = LocalDate.now();
                        int currYear = currentDate.getYear();
                        yearInt = Integer.parseInt(year.getText().toString());
                        if (yearInt%1 !=0)
                        {
                            Toast.makeText(this, "Year is invalid!", Toast.LENGTH_SHORT).show();

                            return;
                        } else if(yearInt<1920 || yearInt >currYear)
                        {
                            Toast.makeText(this, "Year is invalid", Toast.LENGTH_SHORT).show();

                            return;
                        }
                    } catch(Exception e)
                    {
                        e.printStackTrace();
                        Log.d("Parse error",e.getMessage());
                    }



                }



                if(month.getSelectedItemPosition()==0)
                {
                    Toast.makeText(this, "Choose month", Toast.LENGTH_SHORT).show();

                    return;
                }
                if(description.getText().toString().isEmpty())
                {
                    Toast.makeText(this, "Put some description", Toast.LENGTH_SHORT).show();
                    return;
                } else if (description.getText().toString().length()>200)
                {
                    Toast.makeText(this, "Description must contain up to 200 characters", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {

                    String fNameString = firstName.getText().toString();
                    String lNameString = lastName.getText().toString();
                    String dayString = dOB.getText().toString();
                    String majorString = major.getText().toString();
                    String yearString = year.getText().toString();
                    String monthString = "";
                    String descriptionStr = description.getText().toString();
                    String imagePath = URI;


                    for (int i=0; i<months.size();i++)
                    {
                        if(month.getSelectedItemPosition()==i)
                        {
                            monthString = month.getSelectedItemPosition() + "";
                        }
                    }

                    User user = (User) getIntent().getSerializableExtra("user");

                    user.setFirstName(fNameString);
                    user.setLastName(lNameString);
                    user.setBirthDate(Integer.parseInt(dayString));
                    user.setBirthMonth(Integer.parseInt(monthString));
                    user.setBirthYear(Integer.parseInt(yearString));
                    user.setMajor(majorString);
                    user.setDesciption(descriptionStr);
                    user.setImagePath(imagePath);

                    try {
                        dbHelper.updateUser(user);
                        Toast.makeText(this, "User Information Added", Toast.LENGTH_SHORT).show();
                    } catch (SQLiteException e) {
                        e.printStackTrace();
                    }

                    Bundle bundle = new Bundle();
                    bundle.putString("FNAME", fNameString);
                    bundle.putString("LNAME", lNameString);
                    bundle.putString("MAJOR",majorString);
                    bundle.putString("DAY", dayString);
                    bundle.putString("MONTH", monthString);
                    bundle.putString("YEAR",yearString);
                    bundle.putString("DESCRIPTION", descriptionStr);
                    bundle.putString("IMAGE", imagePath);

                    Intent intent = new Intent(ProfileCreationActivity.this, ProfilePage.class);
                    intent.putExtras(bundle);
                    intent.putExtra("userId", userId);
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