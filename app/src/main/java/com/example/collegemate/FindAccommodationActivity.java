package com.example.collegemate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class FindAccommodationActivity extends AppCompatActivity implements FloorPlansRecyclerViewAdapter.OnItemClickListener {

    List<FloorPlans> FloorPlanList = new ArrayList<>();
    Button btnBack;
    ImageView imgViewFloorPlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_accommodation);
        AddFloorPlans();

        RecyclerView recyclerViewFloorPlans = findViewById(R.id.recyclerViewFloorPlans);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setBackgroundColor(Color.BLACK);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        //need to set this with onClickListener
        imgViewFloorPlan = findViewById(R.id.imgViewAccommodation);

        //FloorPlansRecyclerViewAdapter floorPlanAdapter = new FloorPlansRecyclerViewAdapter(FloorPlanList);
        FloorPlansRecyclerViewAdapter floorPlanAdapter = new FloorPlansRecyclerViewAdapter(FloorPlanList, this);

        GridLayoutManager gridManager = new GridLayoutManager(this,3);

        recyclerViewFloorPlans.setAdapter(floorPlanAdapter);
        recyclerViewFloorPlans.setLayoutManager(gridManager);


    }

    private void AddFloorPlans() {
        FloorPlanList.add(new FloorPlans(1, "Studio", R.drawable.studiofloorplan));
        FloorPlanList.add(new FloorPlans(2, "Suite 2-2 ", R.drawable.sharedfloorplan));
        FloorPlanList.add(new FloorPlans(3, "Suite 3-2", R.drawable.threebedroomplan));
    }

    @Override
    public void onItemClick(int i) {
        if(i != -1) {
            imgViewFloorPlan.setImageResource(FloorPlanList.get(i).getFloorPlanPic());
        }
    }
}