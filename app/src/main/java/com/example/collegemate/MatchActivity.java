package com.example.collegemate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MatchActivity extends AppCompatActivity implements RecyclerViewMatchAdapter.OnItemClickListener, BottomNavigationView.OnNavigationItemSelectedListener {
    List<GalleryImageMatchActivity> ImageList = new ArrayList<>();
    TextView TextViewNameMatchActivity;
    TextView TextViewInfoMatchActivity;
    TextView TextViewRoleMatchActivity;
    DBHelper dbHelper;
    String firstName;
    String major;
    long test;

    long userId;

    int SelectedInd;
    Intent intent = getIntent();
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        bottomNavigationView=findViewById(R.id.BottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.chat_page);
        dbHelper = new DBHelper(this);


        AddData();


        RecyclerView recyclerViewImages = findViewById(R.id.RecyclerViewMatchActivity);
        TextViewNameMatchActivity = findViewById(R.id.textViewNameMatchActivity);
        TextViewInfoMatchActivity = findViewById(R.id.textViewInfoMatchActivity);
        TextViewRoleMatchActivity = findViewById(R.id.textViewRoleMatchActivity);
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
        //GridLayoutManager gm= new GridLayoutManager(this,3);
        recyclerViewImages.setAdapter(myAdapter);
        recyclerViewImages.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    private void AddData() {
        ImageList.add(new GalleryImageMatchActivity("Hey there! I'm Juliet, an avid nature lover and wildlife photographer. My lens captures the raw beauty of our planet, aiming to inspire others to cherish and protect our environment. Join me in this visual journey for a greener, more sustainable world!", "Juliet" , R.drawable.user1, "BUSINESS LAW"));
        ImageList.add(new GalleryImageMatchActivity("Namaste! I'm Sophia, a yoga devotee passionate about mindfulness and well-being. Through yoga and meditation, I guide seekers toward inner peace and balance. Join me on the mat for a journey of self-discovery and tranquility", "Sophia", R.drawable.user7, major));
        ImageList.add(new GalleryImageMatchActivity("Hey folks, I'm Noah, a tech enthusiast and coding wizard. With lines of code, I weave solutions that tackle real-world problems. Exploring how technology shapes our lives, I'm on a mission to create apps that bring positive change to our communities", "Noah", R.drawable.user3, major));
        ImageList.add(new GalleryImageMatchActivity("Hey foodies! I'm Jackson, a culinary maestro fascinated by flavors. In my kitchen laboratory, I concoct dishes that merge diverse tastes and cultures.", "Jackson", R.drawable.user4, major));
        ImageList.add(new GalleryImageMatchActivity("Greetings, fellow history buffs! I'm Oliver, a passionate historian and storyteller. Delving into ancient civilizations", "Oliver", R.drawable.user6, major));
        ImageList.add(new GalleryImageMatchActivity("Hola! I'm Amelia, a fashion entrepreneur with a green heart. I'm on a mission to revolutionize fashion by blending style with sustainability.", "Amelia", R.drawable.user2, major));
    }

    @Override
    public void onItemClick(int i) {
        if (i != -1) {
            SelectedInd = i;
            if (i == 0) {
                TextViewNameMatchActivity.setText(ImageList.get(i).getImgName());
                TextViewInfoMatchActivity.setText(ImageList.get(i).getInfo());
                TextViewRoleMatchActivity.setText(ImageList.get(i).getMajor());
            } else if (i==1) {
                userId=18;
                Log.d("MatchActivity", "Retrieved userId: " + userId);
                User user = dbHelper.getUserById(userId);
                firstName = user.getFirstName();
                major = user.getMajor();
                Log.d("MatcheActivity", "Retrieved username: " + user.getFirstName());
                Log.d("MatcheActivity", "Retrieved usermajor: " + user.getMajor());
                TextViewNameMatchActivity.setText(firstName);
                TextViewInfoMatchActivity.setText(ImageList.get(i).getInfo());
                TextViewRoleMatchActivity.setText(major);
            }else if (i==2) {
                userId=19;
                Log.d("MatchActivity", "Retrieved userId: " + userId);
                User user = dbHelper.getUserById(userId);
                firstName = user.getFirstName();
                major = user.getMajor();
                Log.d("MatcheActivity", "Retrieved username: " + user.getFirstName());
                Log.d("MatcheActivity", "Retrieved usermajor: " + user.getMajor());
                TextViewNameMatchActivity.setText(firstName);
                TextViewInfoMatchActivity.setText(ImageList.get(i).getInfo());
                TextViewRoleMatchActivity.setText(major);
            }else if (i==3) {
                userId=21;
                TextViewNameMatchActivity.setText(ImageList.get(i).getImgName());
                TextViewInfoMatchActivity.setText(ImageList.get(i).getInfo());
                TextViewRoleMatchActivity.setText(ImageList.get(i).getMajor());
            }else if (i==4) {
                userId=2;
                TextViewNameMatchActivity.setText(ImageList.get(i).getImgName());
                TextViewInfoMatchActivity.setText(ImageList.get(i).getInfo());
                TextViewRoleMatchActivity.setText(ImageList.get(i).getMajor());
            }else if (i==5) {
                userId=3;
                TextViewNameMatchActivity.setText(ImageList.get(i).getImgName());
                TextViewInfoMatchActivity.setText(ImageList.get(i).getInfo());
                TextViewRoleMatchActivity.setText(ImageList.get(i).getMajor());
            }

        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int menuItemId = item.getItemId();

        if (menuItemId == R.id.home_page){
            Intent quizIntent = new Intent(MatchActivity.this,  MainActivity.class);
            long userId = getIntent().getLongExtra("userId", -1);
            quizIntent.putExtra("userId", userId);
            startActivity(quizIntent);
            return true;
        } else if (menuItemId == R.id.chat_page){

            return true;
        } else if (menuItemId == R.id.search_page){
            startActivity(new Intent(MatchActivity.this, SearchActivity.class));
            return true;
        } else if (menuItemId == R.id.profile_page){
            Intent intent = new Intent(MatchActivity.this, ProfilePage.class);
            long userId = getIntent().getLongExtra("userId", -1);
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
}