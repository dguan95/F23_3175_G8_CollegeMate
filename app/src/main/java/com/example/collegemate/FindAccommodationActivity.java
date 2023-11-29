package com.example.collegemate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class FindAccommodationActivity extends AppCompatActivity {

    List<FloorPlans> FloorPlanList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_accommodation);
        AddFloorPlans();

        RecyclerView recyclerViewFloorPlans = findViewById(R.id.recyclerViewFloorPlans);

        //need to set this with onClickListener
        ImageView imgViewFloorPlans = findViewById(R.id.imgViewAccommodation);

        FloorPlansRecyclerViewAdapter floorPlanAdapter = new FloorPlansRecyclerViewAdapter(FloorPlanList);
        GridLayoutManager gridManager = new GridLayoutManager(this,3);
        recyclerViewFloorPlans.setAdapter(floorPlanAdapter);
        recyclerViewFloorPlans.setLayoutManager(gridManager);


    }

    private void AddFloorPlans() {
        FloorPlanList.add(new FloorPlans(1, "Studio", R.drawable.studiofloorplan));
        FloorPlanList.add(new FloorPlans(2, "Suite 2-2 ", R.drawable.sharedfloorplan));
        FloorPlanList.add(new FloorPlans(3, "Suite 3-2", R.drawable.threebedroomplan));
    }

}