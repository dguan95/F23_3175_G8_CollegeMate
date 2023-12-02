package com.example.collegemate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    List<String> SearchItems = new ArrayList<>(Arrays.asList("Find Roommates", "Housing and Tips",
            "Find Accommodation", "Resource Sharing"));

    List<Search> SearchList = new ArrayList<>();

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        LoadDataSearchList();

        ListView listViewSearch = findViewById(R.id.listViewSearch);
        SearchAdapter2 searchAdap = new SearchAdapter2(SearchList);
        listViewSearch.setAdapter(searchAdap);


        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("userId")) {
            long userId = intent.getLongExtra("userId", -1);
            Log.d("SearchActivity", "Retrieved userId: " + userId);
        }


        listViewSearch.setOnItemClickListener((AdapterView<?> adapterView, View view, int i, long l)->{

            if(i == 0){
                startActivity(new Intent(SearchActivity.this, QuizActivity.class));
            } else if(i == 1){
                startActivity(new Intent(SearchActivity.this, HousingTipsActivity.class));
            } else if(i == 2){
                Intent findRoomIntent = new Intent(SearchActivity.this, FindAccommodationActivity.class);
                long userId = getIntent().getLongExtra("userId", -1);
                findRoomIntent.putExtra("userId", userId);
                startActivity(findRoomIntent);

                //startActivity(new Intent(SearchActivity.this, FindAccommodationActivity.class));
            } else {
                startActivity(new Intent(SearchActivity.this, ResourceSharingActivity.class));
            }

        });

        bottomNavigationView=findViewById(R.id.BottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.search_page);

        /*Intent intent = getIntent();
        if (intent != null && intent.hasExtra("userId")) {
            long userId = intent.getLongExtra("userId", -1);
            Log.d("SearchActivity", "Retrieved userId: " + userId);
        }*/


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int menuItemId = item.getItemId();
        long userId;
        if (menuItemId == R.id.home_page){
            Intent quizIntent = new Intent(SearchActivity.this, MainActivity.class);
            userId = getIntent().getLongExtra("userId", -1);
            quizIntent.putExtra("userId", userId);
            startActivity(quizIntent);
            return true;
        } else if (menuItemId == R.id.chat_page){
            DBHelper dbHelper = new DBHelper(SearchActivity.this);
            userId = getIntent().getLongExtra("userId", -1);
            int totalScore = dbHelper.getTotalScoreForUser(userId);

            if (totalScore > 8) {
                // Navigate to MatchActivity
                Intent matchIntent = new Intent(SearchActivity.this, MatchActivity.class);
                matchIntent.putExtra("userId", userId);
                startActivity(matchIntent);
            } else {
                // Navigate to QuizActivity
                Intent quizIntent = new Intent(SearchActivity.this, QuizActivity.class);
                quizIntent.putExtra("userId", userId);
                startActivity(quizIntent);
            }
            return true;
        } else if (menuItemId == R.id.search_page){

            return true;
        } else if (menuItemId == R.id.profile_page){
            Intent quizIntent = new Intent(SearchActivity.this, ProfilePage.class);
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

    private void LoadDataSearchList(){
        for(int i = 0; i < SearchItems.size(); i++){
            Search eachItem = new Search(SearchItems.get(i));
            SearchList.add(eachItem);
        }
    }
}