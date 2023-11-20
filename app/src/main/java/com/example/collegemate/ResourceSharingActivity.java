package com.example.collegemate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ResourceSharingActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource_sharing);
        bottomNavigationView=findViewById(R.id.BottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home_page);


        TextView tedLink = findViewById(R.id.txtViewTedLink);
        tedLink.setMovementMethod(LinkMovementMethod.getInstance());

        TextView courseEraLink = findViewById(R.id.txtViewCourseEra);
        courseEraLink.setMovementMethod(LinkMovementMethod.getInstance());

        TextView careerLink = findViewById(R.id.txtViewCareer);
        careerLink.setMovementMethod(LinkMovementMethod.getInstance());

        TextView copyLeaksLink = findViewById(R.id.txtViewCopyleaks);
        copyLeaksLink.setMovementMethod(LinkMovementMethod.getInstance());

        TextView lifeHLink = findViewById(R.id.txtViewLifeH);
        lifeHLink.setMovementMethod(LinkMovementMethod.getInstance());

        TextView healthLineLink = findViewById(R.id.txtViewHealthLine);
        healthLineLink.setMovementMethod(LinkMovementMethod.getInstance());

        TextView studyfyLink = findViewById(R.id.txtViewStudyfy);
        studyfyLink.setMovementMethod(LinkMovementMethod.getInstance());


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int menuItemId = item.getItemId();

        if (menuItemId == R.id.home_page){
            return true;
        } else if (menuItemId == R.id.chat_page){
            startActivity(new Intent(ResourceSharingActivity.this, QuizActivity.class));
            return true;
        } else if (menuItemId == R.id.search_page){
            startActivity(new Intent(ResourceSharingActivity.this, SearchActivity.class));
            return true;
        } else if (menuItemId == R.id.profile_page){
            startActivity(new Intent(ResourceSharingActivity.this, ProfilePage.class));
            return true;
        }

        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}