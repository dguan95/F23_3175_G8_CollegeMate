package com.example.collegemate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MatchActivity extends AppCompatActivity {
    List<GalleryImageMatchActivity> ImageList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        AddData();

        RecyclerView recyclerViewImages = findViewById(R.id.RecyclerViewMatchActivity);

        RecyclerViewMatchAdapter myAdapter = new RecyclerViewMatchAdapter(ImageList);
        //GridLayoutManager gm= new GridLayoutManager(this,3);
        recyclerViewImages.setAdapter(myAdapter);
        recyclerViewImages.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }
    private void AddData(){
        ImageList.add(new GalleryImageMatchActivity(101,"Eagle",R.drawable.user1));
        ImageList.add(new GalleryImageMatchActivity(102,"Elephant",R.drawable.user7));
        ImageList.add(new GalleryImageMatchActivity(103,"Gorila",R.drawable.user3));
        ImageList.add(new GalleryImageMatchActivity(104,"Panda",R.drawable.user4));
        ImageList.add(new GalleryImageMatchActivity(105,"Panther",R.drawable.user6));
        ImageList.add(new GalleryImageMatchActivity(106,"Polar Bear",R.drawable.user2));
    }
}