package com.example.collegemate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class ResourceSharingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource_sharing);

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
}