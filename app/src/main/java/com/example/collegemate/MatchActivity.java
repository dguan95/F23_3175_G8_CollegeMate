package com.example.collegemate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MatchActivity extends AppCompatActivity implements RecyclerViewMatchAdapter.OnItemClickListener, BottomNavigationView.OnNavigationItemSelectedListener {
    List<GalleryImageMatchActivity> ImageList = new ArrayList<>();
    TextView TextViewNameMatchActivity;
    TextView TextViewInfoMatchActivity;
    TextView TextViewRoleMatchActivity;
    TextView TextViewYourPoints;
    DBHelper dbHelper;
    String firstName;
    String major;
    String description;
    String email;
    String imagePath;

    long test;

    long userId;

    int SelectedInd;
    Intent intent = getIntent();
    BottomNavigationView bottomNavigationView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        bottomNavigationView=findViewById(R.id.BottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.chat_page);


        long userId = getIntent().getLongExtra("userId", -1);
        dbHelper = new DBHelper(this);
        int totalScore = dbHelper.getTotalScoreForUser(userId);

        AddData();


        RecyclerView recyclerViewImages = findViewById(R.id.RecyclerViewMatchActivity);
        TextViewNameMatchActivity = findViewById(R.id.textViewNameMatchActivity);
        TextViewYourPoints = findViewById(R.id.textViewYourPoints);
        TextViewInfoMatchActivity = findViewById(R.id.textViewInfoMatchActivity);
        TextViewRoleMatchActivity = findViewById(R.id.textViewRoleMatchActivity);
        TextViewYourPoints.setText("Your Points: " + totalScore);


        if (SelectedInd != -1) {
            TextViewNameMatchActivity.setText(ImageList.get(SelectedInd).getImgName());
            TextViewInfoMatchActivity.setText(ImageList.get(SelectedInd).getInfo());
            TextViewRoleMatchActivity.setText(ImageList.get(SelectedInd).getMajor());
        } else {
            TextViewNameMatchActivity.setText(0);
            TextViewInfoMatchActivity.setText(0);
            TextViewRoleMatchActivity.setText(0);
        }

        RecyclerViewMatchAdapter myAdapter
                = new RecyclerViewMatchAdapter(ImageList, this);
        recyclerViewImages.setAdapter(myAdapter);
        recyclerViewImages.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    private void AddData() {

        int userCount = dbHelper.getUserCount();
        for (int userId = 1; userId <= userCount; userId++) {
            User user = dbHelper.getUserById(userId);
            if (user != null) {
                int totalScore = dbHelper.getTotalScoreForUser(userId);
                String firstName = user.getFirstName();
                String major = user.getMajor();
                String description = user.getDesciption();
                String email = user.getEmail();
                String imagePath = user.getImagePath();
                Log.d("MatchActivity", "Retrieved ImagePath: " + imagePath);

                Drawable userImage = loadImageFromPath(imagePath);

                ImageList.add(new GalleryImageMatchActivity(
                        description,
                        firstName,
                        userImage,
                        imagePath,
                        major + " - Points: " + totalScore + "\nHit me up : " + email
                ));
            }
        }
    }

    @Override
    public void onItemClick(int i) {
        if (i != -1) {
            SelectedInd = i;
            if(i==0){
                TextViewNameMatchActivity.setText("Juliana");
                TextViewInfoMatchActivity.setText("Hi Guys I'm Juli, hope all is well, I want to know more people and make a lot of friends");
                TextViewRoleMatchActivity.setText("Mobile Developtment - Points: 15 \nHit me up: juli@douglas.com");
            }
            for(int j=1;j<ImageList.size();j++){
                if(SelectedInd!=0) {
                    userId = SelectedInd;
                    Log.d("MatchActivity", "Retrieved userId: " + userId);
                    User user = dbHelper.getUserById(userId);
                    int totalScore = dbHelper.getTotalScoreForUser(userId);
                    firstName = user.getFirstName();
                    major = user.getMajor();
                    description = user.getDesciption();
                    email = user.getEmail();
                    Log.d("MatcheActivity", "Retrieved username: " + user.getFirstName());
                    Log.d("MatcheActivity", "Retrieved usermajor: " + user.getMajor());
                    Log.d("MatcheActivity", "Retrieved userDescription: " + user.getDesciption());
                    Log.d("MatchActivity", "Retrieved TotalScore: " + totalScore);
                    TextViewNameMatchActivity.setText(firstName);
                    TextViewInfoMatchActivity.setText(description);
                    TextViewRoleMatchActivity.setText(major + " - Points: " + totalScore + "\nHit me up : " + email);
                }
            }

        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int menuItemId = item.getItemId();
        long userId;
        if (menuItemId == R.id.home_page){
            Intent quizIntent = new Intent(MatchActivity.this,  MainActivity.class);
            userId = getIntent().getLongExtra("userId", -1);
            quizIntent.putExtra("userId", userId);
            startActivity(quizIntent);
            return true;
        } else if (menuItemId == R.id.chat_page){

            return true;
        } else if (menuItemId == R.id.search_page){
            Intent intent = new Intent(MatchActivity.this, SearchActivity.class);
            userId = getIntent().getLongExtra("userId", -1);
            intent.putExtra("userId", userId);
            startActivity(intent);
            return true;
        } else if (menuItemId == R.id.profile_page){
            Intent intent = new Intent(MatchActivity.this, ProfilePage.class);
            userId = getIntent().getLongExtra("userId", -1);
            intent.putExtra("userId", userId);
            startActivity(intent);
            return true;
        }
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }


    private Drawable loadImageFromPath(String imagePath) {
        Drawable drawable;
        try {
            ContentResolver resolver = getContentResolver();
            InputStream inputStream = resolver.openInputStream(Uri.parse(imagePath));
            Log.d("MatchActivity", "Retrieved inputstream: " + inputStream);

            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            Log.d("MatchActivity", "Retrieved bitmap: " + bitmap);
            drawable = new BitmapDrawable(getResources(), bitmap);

            if (inputStream != null) {
                inputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            drawable = getResources().getDrawable(R.drawable.user7);
        }
        return drawable;
    }
}