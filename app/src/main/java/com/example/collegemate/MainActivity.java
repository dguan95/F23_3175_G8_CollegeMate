package com.example.collegemate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, RecyclerMainAdapter.OnItemClickListener {

    //Hello, this is a test - Diana
    //Hello, this is test again, editing on GitHub to see changes in my PC - Diana
    //HI, this is a test -Zoia !
    //Hi, this is a test -Dani

    BottomNavigationView bottomNavigationView;
    TextView TextViewNameUser;
    List<GalleryMainActivity> ImageList = new ArrayList<>();
    ImageView imageViewHousing1;
    ImageView imageViewHousing2;
    ImageView imageViewHousing3;
    ImageView imageViewHousing4;
    String firstName;
    String major;
    int SelectedInd;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AddData();
        bottomNavigationView=findViewById(R.id.BottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home_page);
        TextViewNameUser = findViewById(R.id.textViewNameUser);
        imageViewHousing1 = findViewById(R.id.imageViewHouse1);
        imageViewHousing2 = findViewById(R.id.imageViewHouse2);
        imageViewHousing3 = findViewById(R.id.imageViewHouse3);
        imageViewHousing4 = findViewById(R.id.imageViewHouse4);
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("userId")) {
            long userId = intent.getLongExtra("userId", -1);
            Log.d("MainActivity", "Retrieved userId: " + userId);
            DBHelper dbHelper = new DBHelper(this);
            User user = dbHelper.getUserById(userId);
            firstName = user.getFirstName();
            major = user.getMajor();
            TextViewNameUser.setText(firstName + "\n" + major );
        }
       /* RecyclerView recyclerViewImages = findViewById(R.id.RecyclerViewTextMain);
        RecyclerMainAdapter myAdapter
                = new RecyclerMainAdapter(ImageList, this);
        recyclerViewImages.setAdapter(myAdapter);
        recyclerViewImages.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
*/
        imageViewHousing1.setOnClickListener((View view) ->{
            startActivity(new Intent(MainActivity.this, ResourceSharingActivity.class));
                });
        imageViewHousing2.setOnClickListener((View view) ->{
            startActivity(new Intent(MainActivity.this, FindAccommodationActivity.class));
        });
        imageViewHousing3.setOnClickListener((View view) ->{
            startActivity(new Intent(MainActivity.this, QuizActivity.class));
        });
        imageViewHousing4.setOnClickListener((View view) ->{
            startActivity(new Intent(MainActivity.this, HousingTipsActivity.class));
        });


    }
    private void AddData() {
        ImageList.add(new GalleryMainActivity("Hey there! I'm Juliet, an avid nature lover and wildlife photographer. My lens captures the raw beauty of our planet, aiming to inspire others to cherish and protect our environment. Join me in this visual journey for a greener, more sustainable world!"));
        ImageList.add(new GalleryMainActivity("Namaste! I'm Sophia, a yoga devotee passionate about mindfulness and well-being. Through yoga and meditation, I guide seekers toward inner peace and balance. Join me on the mat for a journey of self-discovery and tranquility"));
        ImageList.add(new GalleryMainActivity("Hey folks, I'm Noah, a tech enthusiast and coding wizard. With lines of code, I weave solutions that tackle real-world problems. Exploring how technology shapes our lives, I'm on a mission to create apps that bring positive change to our communities"));
        ImageList.add(new GalleryMainActivity("Hey foodies! I'm Jackson, a culinary maestro fascinated by flavors. In my kitchen laboratory, I concoct dishes that merge diverse tastes and cultures."));
        ImageList.add(new GalleryMainActivity("Greetings, fellow history buffs! I'm Oliver, a passionate historian and storyteller. Delving into ancient civilizations"));
        ImageList.add(new GalleryMainActivity("Hola! I'm Amelia, a fashion entrepreneur with a green heart. I'm on a mission to revolutionize fashion by blending style with sustainability."));

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int menuItemId = item.getItemId();
        long userId;

        if (menuItemId == R.id.home_page){
            return true;
        } else if (menuItemId == R.id.chat_page){
            DBHelper dbHelper = new DBHelper(MainActivity.this);
            userId = getIntent().getLongExtra("userId", -1);
            int totalScore = dbHelper.getTotalScoreForUser(userId);

            if (totalScore > 8) {
                // Navigate to MatchActivity
                Intent matchIntent = new Intent(MainActivity.this, MatchActivity.class);
                matchIntent.putExtra("userId", userId);
                startActivity(matchIntent);
            } else {
                // Navigate to QuizActivity
                Intent quizIntent = new Intent(MainActivity.this, QuizActivity.class);
                quizIntent.putExtra("userId", userId);
                startActivity(quizIntent);
            }
            return true;
        } else if (menuItemId == R.id.search_page){
            Intent quizIntent = new Intent(MainActivity.this, SearchActivity.class);
            userId = getIntent().getLongExtra("userId", -1);
            quizIntent.putExtra("userId", userId);
            startActivity(quizIntent);
            return true;
        } else if (menuItemId == R.id.profile_page){
            Intent quizIntent = new Intent(MainActivity.this, ProfilePage.class);
            userId = getIntent().getLongExtra("userId", -1);
            quizIntent.putExtra("userId", userId);
            startActivity(quizIntent);
            return true;
        }

        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    @Override
    public void onItemClick(int i) {

    }
}
