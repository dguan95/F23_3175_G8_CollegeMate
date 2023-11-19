package com.example.collegemate;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
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
    int yearInt=0;

    ActivityResultLauncher<Intent> resultLauncher;

    ImageView imgView;
    FloatingActionButton btnGallery;
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
        dbHelper = new DBHelper(this);
        imgView = findViewById(R.id.imgViewProfilIcon);
        btnGallery = findViewById(R.id.btnOpenGallery);

        ActivityResultLauncher<PickVisualMediaRequest> pickMedia =
                registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
                    // Callback is invoked after the user selects a media item or closes the
                    // photo picker.
                    if (uri != null) {
                        Log.d("PhotoPicker", "Selected URI: " + uri);
                        imgView.setImageURI(uri);
                        Glide.with(this)
                                .load(uri)
                                .transform(new CircleCrop())
                                .into(imgView);
//                        Glide.with(this)
//                                .load(uri)
//                                .transform(new RoundedCorners(imgView.getWidth() / 2))
//                                .into(imgView);
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
                    String majorString = major.getText().toString();
                    String yearString = year.getText().toString();
                    String monthString = "";

//                    BitmapDrawable drawable = (BitmapDrawable) imgView.getDrawable();
//                    Bitmap bitmap = drawable.getBitmap();

//                    byte[] imageByteArray = convertBitmapToByteArray(bitmap);

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



    public static byte[] convertBitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }


}