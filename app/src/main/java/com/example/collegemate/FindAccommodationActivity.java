package com.example.collegemate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    Button btnProceedToPayment;
    Bundle bundleRoomDetail;
    Intent selectionPrice;
    AlertDialog.Builder alertBuilder;
    AlertDialog.Builder alertBuilder2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_accommodation);
        AddFloorPlans();

        SharedPreferences pref = this.getPreferences(Context.MODE_PRIVATE);
        SelIndex = pref.getInt("FloorPlanInd", -1);

        RecyclerView recyclerViewFloorPlans = findViewById(R.id.recyclerViewFloorPlans);
        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long userid = getIntent().getExtras().getLong("userId", -1);
                Intent backToSearchIntent = new Intent(FindAccommodationActivity.this, SearchActivity.class);
                backToSearchIntent.putExtra("userId", userid);
                startActivity(backToSearchIntent);
                //onBackPressed();
            }
        });

        imgViewFloorPlan = findViewById(R.id.imgViewAccommodation);
        txtViewSelectionDetail = findViewById(R.id.txtViewSelectionDetail);

        btnReserveRoom = findViewById(R.id.btnReserveOrBookToView);
        txtViewSelectedFloorPlan = findViewById(R.id.txtViewSelectedFloorPlan);

        btnProceedToPayment = findViewById(R.id.btnProceedToPayment);


        if(SelIndex != -1){
            imgViewFloorPlan.setImageResource(FloorPlanList.get(SelIndex).getFloorPlanPic());
            txtViewSelectionDetail.setText(FloorPlanList.get(SelIndex).getFloorPlanDetails());
        } else {
            imgViewFloorPlan.setImageResource(0);
        }

        FloorPlansRecyclerViewAdapter floorPlanAdapter = new FloorPlansRecyclerViewAdapter(FloorPlanList, this);

        GridLayoutManager gridManager = new GridLayoutManager(this,3);

        recyclerViewFloorPlans.setAdapter(floorPlanAdapter);
        recyclerViewFloorPlans.setLayoutManager(gridManager);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("userId")) {
            long userId = intent.getLongExtra("userId", -1);
            Log.d("FindAccommodationActivity", "Retrieved userId: " + userId);
        }

        btnReserveRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //for testing purpose
                //long userid = getIntent().getLongExtra("userId", -1);
                int selectedIndex = floorPlanAdapter.getSelectedInd();
                if(selectedIndex == -1){

                    alertBuilder = new AlertDialog.Builder(FindAccommodationActivity.this);
                    alertBuilder.setMessage("Please select room type");
                    alertBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });
                    AlertDialog alertDialog = alertBuilder.create();
                    alertDialog.show();
                    //Toast.makeText(FindAccommodationActivity.this, "Please select room type", Toast.LENGTH_SHORT).show();;
                } else {
                    int roomPrice = FloorPlanList.get(selectedIndex).getFloorPlanPrice();
                    String roomSelected = FloorPlanList.get(selectedIndex).getFloorPlanName();
                    DecimalFormat df = new DecimalFormat("$#####.##");
                    txtViewSelectedFloorPlan.setText("Room type selected: " + roomSelected + "\nPrice: " + df.format(roomPrice));
                }

            }
        });

        Bundle paymentBundle = getIntent().getExtras();
        //long userid = paymentBundle.getLong("userId", -1);
        int amountPaid = paymentBundle.getInt("AmountPaid", 0);
        if(amountPaid != 0){
            txtViewSelectedFloorPlan.setText("Recent amount paid: " + amountPaid);
            btnProceedToPayment.setEnabled(false);
        }

        btnProceedToPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selIndex = floorPlanAdapter.getSelectedInd();
                if(selIndex != -1) {

                    long userId = getIntent().getLongExtra("userId", -1);
                    Log.d("FindAccommodation", "Retrieved : " + userId);
                    bundleRoomDetail = new Bundle();
                    bundleRoomDetail.putInt("ROOMPRICE", FloorPlanList.get(selIndex).getFloorPlanPrice());
                    bundleRoomDetail.putLong("userId", userId);
                    selectionPrice = new Intent(FindAccommodationActivity.this, PaymentDetailsActivity.class);
                    selectionPrice.putExtras(bundleRoomDetail);
                    startActivity(selectionPrice);
                    startActivity(new Intent(FindAccommodationActivity.this, PaymentDetailsActivity.class));
                } else {
                    alertBuilder2 = new AlertDialog.Builder(FindAccommodationActivity.this);
                    alertBuilder2.setMessage("Please select room to reserve");
                    alertBuilder2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    AlertDialog alertDialog = alertBuilder.create();
                    alertDialog.show();
                    //Toast.makeText(FindAccommodationActivity.this, "Select a floor plan and reserve", Toast.LENGTH_SHORT).show();
                }
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