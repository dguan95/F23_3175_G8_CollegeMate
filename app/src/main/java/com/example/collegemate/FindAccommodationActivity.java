package com.example.collegemate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class FindAccommodationActivity extends AppCompatActivity implements FloorPlansRecyclerViewAdapter.OnItemClickListener {

    List<FloorPlans> FloorPlanList = new ArrayList<>();
    Button btnBack;
    ImageView imgViewFloorPlan;
    int SelIndex;
    TextView txtViewSelectionDetail;
    Button btnReserveRoom;
    TextView txtViewSelectedFloorPlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_accommodation);
        AddFloorPlans();

        SharedPreferences pref = this.getPreferences(Context.MODE_PRIVATE);
        SelIndex = pref.getInt("FloorPlanInd", -1);

        RecyclerView recyclerViewFloorPlans = findViewById(R.id.recyclerViewFloorPlans);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setBackgroundColor(Color.BLACK);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        imgViewFloorPlan = findViewById(R.id.imgViewAccommodation);
        txtViewSelectionDetail = findViewById(R.id.txtViewSelectionDetail);

        btnReserveRoom = findViewById(R.id.btnReserveOrBookToView);
        txtViewSelectedFloorPlan = findViewById(R.id.txtViewSelectedFloorPlan);

        if(SelIndex != -1){
            imgViewFloorPlan.setImageResource(FloorPlanList.get(SelIndex).getFloorPlanPic());
            txtViewSelectionDetail.setText(FloorPlanList.get(SelIndex).getFloorPlanDetails());
            //floorPlanPrice = FloorPlanList.get(SelIndex).getFloorPlanPrice();
        } else {
            imgViewFloorPlan.setImageResource(0);
        }

        //FloorPlansRecyclerViewAdapter floorPlanAdapter = new FloorPlansRecyclerViewAdapter(FloorPlanList);
        FloorPlansRecyclerViewAdapter floorPlanAdapter = new FloorPlansRecyclerViewAdapter(FloorPlanList, this);

        GridLayoutManager gridManager = new GridLayoutManager(this,3);

        recyclerViewFloorPlans.setAdapter(floorPlanAdapter);
        recyclerViewFloorPlans.setLayoutManager(gridManager);

        btnReserveRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedIndex = floorPlanAdapter.getSelectedInd();
                int roomPrice = FloorPlanList.get(selectedIndex).getFloorPlanPrice();
                String roomSelected = FloorPlanList.get(selectedIndex).getFloorPlanName();
                DecimalFormat df = new DecimalFormat("$#####.##");
                txtViewSelectedFloorPlan.setText("Room type selected: " + roomSelected + "\nPrice: " + df.format(roomPrice));
            }
        });


    }

    private void AddFloorPlans() {
        FloorPlanList.add(new FloorPlans(1, "Studio", R.drawable.studiofloorplan, "Single Main Room plus Bathroom\nINCLUDED:\nKitchen area\nSmart TV\nFull size fridge\nMicrowave\nStove\nPrice:$1800", 1800));
        FloorPlanList.add(new FloorPlans(2, "Suite 2-2 ", R.drawable.sharedfloorplan, "2 Bedrooms 2 Bathrooms\nINCLUDED:\nCommon area with kitchen\nSmart TV\nFull size fridge\nMicrowave\nStove\nPrice:$1650/person", 1650));
        FloorPlanList.add(new FloorPlans(3, "Suite 3-2", R.drawable.threebedroomplan, "3 Bedrooms 2 Bathrooms\nINCLUDED:\nCommon area with kitchen\nSmart TV\nFull size fridge\nMicrowave\nStove\nPrice:$1590/person", 1590));
    }

    @Override
    public void onItemClick(int i) {
        if(i != -1) {
            imgViewFloorPlan.setImageResource(FloorPlanList.get(i).getFloorPlanPic());
            txtViewSelectionDetail.setText(FloorPlanList.get(i).getFloorPlanDetails());
        }
    }

    @Override
    protected void onPause() {
        SharedPreferences preference = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = preference.edit();
        prefEditor.putInt("FloorPlanInd", SelIndex);
        prefEditor.apply();
        super.onPause();
    }
}