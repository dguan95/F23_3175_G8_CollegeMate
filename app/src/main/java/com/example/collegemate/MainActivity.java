package com.example.collegemate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
    List<GalleryMainActivity> TextList = new ArrayList<>();
    ImageView imageViewHousing1;
    ImageView imageViewHousing2;
    ImageView imageViewHousing3;
    ImageView imageViewHousing4;
    String firstName;
    String major;
    int SelectedInd2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView=findViewById(R.id.BottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home_page);
        TextViewNameUser = findViewById(R.id.textViewNameUser);
        imageViewHousing1 = findViewById(R.id.imageViewHouse1);
        imageViewHousing2 = findViewById(R.id.imageViewHouse2);
        imageViewHousing3 = findViewById(R.id.imageViewHouse3);
        imageViewHousing4 = findViewById(R.id.imageViewHouse4);
        AddData();
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
        RecyclerView recyclerViewText = findViewById(R.id.RecyclerViewTextMain);



        RecyclerMainAdapter myAdapter2
                = new RecyclerMainAdapter(TextList, this);
        recyclerViewText.setAdapter(myAdapter2);
        final int speedScroll = 1200;
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            int count = 0;
            boolean flag = true;
            @Override
            public void run() {
                if(count < myAdapter2.getItemCount()){
                    if(count==myAdapter2.getItemCount()-1){
                        flag = false;
                    }else if(count == 0){
                        flag = true;
                    }
                    if(flag) count++;
                    else count--;

                    recyclerViewText.smoothScrollToPosition(count);
                    handler.postDelayed(this,speedScroll);
                }
            }
        };
        handler.postDelayed(runnable,speedScroll);
        recyclerViewText.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

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
        TextList.add(new GalleryMainActivity("Roomie Meet & Greet: Connect with potential roommates through speed networking sessions"));
        TextList.add(new GalleryMainActivity("Campus Chill Night: Join us for a laid-back evening of games, snacks, and mingling. (15 words"));
        TextList.add(new GalleryMainActivity("House Hunting Hangout: Explore available housing options with fellow students while enjoying refreshments."));
        TextList.add(new GalleryMainActivity("Hey foodies! I'm Jackson, explore cooking sessions with potential roommates! Share recipes and bond over delicious meals"));
        TextList.add(new GalleryMainActivity("Study Pod Mixer: Find study buddies and create study groups for upcoming exams in a relaxed setting"));
        TextList.add(new GalleryMainActivity("Hola! I'm Amelia, Join our live discussion on finding compatible roommates! Tips, Q&A, and success stories shared."));

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
        SelectedInd2 = i;
    }
}
