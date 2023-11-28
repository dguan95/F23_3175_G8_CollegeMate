package com.example.collegemate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

        listViewSearch.setOnItemClickListener((AdapterView<?> adapterView, View view, int i, long l)->{

            if(i == 0){
                startActivity(new Intent(SearchActivity.this, QuizActivity.class));
            } else if(i == 1){
                startActivity(new Intent(SearchActivity.this, HousingTipsActivity.class));
            } else if(i == 2){
                startActivity(new Intent(SearchActivity.this, FindAccommodationActivity.class));
            } else {
                startActivity(new Intent(SearchActivity.this, ResourceSharingActivity.class));
            }

        });

        bottomNavigationView=findViewById(R.id.BottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.search_page);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int menuItemId = item.getItemId();

        if (menuItemId == R.id.home_page){
            startActivity(new Intent(SearchActivity.this, MainActivity.class));
            return true;
        } else if (menuItemId == R.id.chat_page){
            startActivity(new Intent(SearchActivity.this, ChatActivity.class));
            return true;
        } else if (menuItemId == R.id.search_page){

            return true;
        } else if (menuItemId == R.id.profile_page){
            startActivity(new Intent(SearchActivity.this, ProfileActivity.class));
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