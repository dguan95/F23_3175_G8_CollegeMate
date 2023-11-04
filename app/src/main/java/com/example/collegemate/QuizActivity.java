package com.example.collegemate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    int index=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        TextView txtViewQuestion = findViewById(R.id.textViewQuestion);
        TextView txtViewQuestionContent = findViewById(R.id.textViewQuestionContent);
        ImageView imgViewQuestion = findViewById(R.id.imageViewQuestion);
        Button buttonQuestion = findViewById(R.id.buttonQuestion);
        RadioGroup radGroupQuestion= findViewById(R.id.RadioGroupQuestions);
        RadioButton radioButtonA = findViewById(R.id.radioButtonA);
        RadioButton radioButtonB = findViewById(R.id.radioButtonB);
        RadioButton radioButtonC = findViewById(R.id.radioButtonC);

        txtViewQuestion.setText("Question 1");
        txtViewQuestionContent.setText("It is Friday night and your friend drops by with your favorite movie. Your roommate has already left to spend the weekend with family. After a few too many drinks, your friend gets a little drunk and is no condition to drive home safely, so you suggest that she/he stay for the night. Where do you let him/her crash?");
        radioButtonA.setText("In your roommate's bed");
        radioButtonB.setText("In your bed");
        radioButtonC.setText("On the couch");
        buttonQuestion.setOnClickListener((View view) -> {
            int answer1,answer2,answer3,answer4,answer5,answer6,answer7,answer8,answer9,answer10;

            switch (index){
                case 0:
                    if(radGroupQuestion.getCheckedRadioButtonId()==R.id.radioButtonA){
                        answer1 = 1;
                        index=index+1;
                    } else if (radGroupQuestion.getCheckedRadioButtonId()==R.id.radioButtonB){
                        answer1 = 2;
                        index=index+1;
                    } else{
                        answer1 = 3;
                        index=index+1;
                    }
                    case 1:
                        txtViewQuestion.setText("Question 2");
                        txtViewQuestionContent.setText("It is time to pay the phone bill. Your roommate took the initiative last month so now it is your turn. What would be the most likely outcome?");
                        radioButtonA.setText("You'll pay the bill on time.");
                        radioButtonB.setText("You'll give the money to your roommate and let him/her take care of it.");
                        radioButtonC.setText("You'll forget until your roommate reminds you.");
                        imgViewQuestion.setImageResource(R.drawable.q2);
                        if(radGroupQuestion.getCheckedRadioButtonId()==R.id.radioButtonA){
                            answer2 = 1;
                            index=index+1;
                        } else if (radGroupQuestion.getCheckedRadioButtonId()==R.id.radioButtonB){
                            answer2 = 2;
                            index=index+1;
                        } else{
                            answer2 = 3;
                            index=index+1;
                        }
                        radGroupQuestion.clearCheck();
                        break;
                    case 2:
                        txtViewQuestion.setText("Question 3");
                        txtViewQuestionContent.setText("When do you pay bills?");
                        radioButtonA.setText("Pay immediately");
                        radioButtonB.setText("Pay by due date");
                        radioButtonC.setText("Pay when we get around to them");
                        imgViewQuestion.setImageResource(R.drawable.q3);
                            if(radGroupQuestion.getCheckedRadioButtonId()==R.id.radioButtonA){
                                answer3 = 1;
                                index=index+1;
                            } else if (radGroupQuestion.getCheckedRadioButtonId()==R.id.radioButtonB){
                                answer3 = 2;
                                index=index+1;
                            } else{
                                answer3 = 3;
                                index=index+1;
                            }
                        radGroupQuestion.clearCheck();
                        break;
                case 3:
                    txtViewQuestion.setText("Question 4");
                    txtViewQuestionContent.setText("How do you handle dishes?");
                    radioButtonA.setText("Washed/put away daily");
                    radioButtonB.setText("Washed/dry overnight");
                    radioButtonC.setText("Wash only when everything else is dirty");
                    imgViewQuestion.setImageResource(R.drawable.q4);
                    if (radGroupQuestion.getCheckedRadioButtonId()==-1) {
                        Toast.makeText(this,"Please check an answer",Toast.LENGTH_LONG).show();
                    } else {
                        if(radGroupQuestion.getCheckedRadioButtonId()==R.id.radioButtonA){
                            answer4 = 1;
                            index=index+1;
                        } else if (radGroupQuestion.getCheckedRadioButtonId()==R.id.radioButtonB){
                            answer4 = 2;
                            index=index+1;
                        } else{
                            answer4 = 3;
                            index=index+1;
                        }
                    }
                    radGroupQuestion.clearCheck();
                    break;
                case 4:
                    txtViewQuestion.setText("Question 5");
                    txtViewQuestionContent.setText("Do you mind pets?");
                    radioButtonA.setText("Dogs are fine");
                    radioButtonB.setText("I don’t want pets around");
                    radioButtonC.setText("Other furry critters are ok");
                    imgViewQuestion.setImageResource(R.drawable.q5);
                    if (radGroupQuestion.getCheckedRadioButtonId()==-1) {
                        Toast.makeText(this,"Please check an answer",Toast.LENGTH_LONG).show();
                    } else {
                        if(radGroupQuestion.getCheckedRadioButtonId()==R.id.radioButtonA){
                            answer5 = 1;
                            index=index+1;
                        } else if (radGroupQuestion.getCheckedRadioButtonId()==R.id.radioButtonB){
                            answer5 = 2;
                            index=index+1;
                        } else{
                            answer5 = 3;
                            index=index+1;
                        }
                    }
                    radGroupQuestion.clearCheck();
                    break;
                case 5:
                    txtViewQuestion.setText("Question 6");
                    txtViewQuestionContent.setText("When is noise acceptable?");
                    radioButtonA.setText("During the day and evening, but not at night");
                    radioButtonB.setText("During the daytime only please");
                    radioButtonC.setText("I need the silence of a library");
                    imgViewQuestion.setImageResource(R.drawable.q6);
                    if (radGroupQuestion.getCheckedRadioButtonId()==-1) {
                        Toast.makeText(this,"Please check an answer",Toast.LENGTH_LONG).show();
                    } else {
                        if(radGroupQuestion.getCheckedRadioButtonId()==R.id.radioButtonA){
                            answer6 = 1;
                            index=index+1;
                        } else if (radGroupQuestion.getCheckedRadioButtonId()==R.id.radioButtonB){
                            answer6 = 2;
                            index=index+1;
                        } else{
                            answer6 = 3;
                            index=index+1;
                        }
                    }
                    radGroupQuestion.clearCheck();
                    break;
                case 6:
                    txtViewQuestion.setText("Question 7");
                    txtViewQuestionContent.setText("When do you go to bed during the week?");
                    radioButtonA.setText("Early: between 8pm - 11pm");
                    radioButtonB.setText("Moderate: between 11pm - 1am");
                    radioButtonC.setText("Late: between 1am - 4am");
                    imgViewQuestion.setImageResource(R.drawable.q7);
                    if (radGroupQuestion.getCheckedRadioButtonId()==-1) {
                        Toast.makeText(this,"Please check an answer",Toast.LENGTH_LONG).show();
                    } else {
                        if(radGroupQuestion.getCheckedRadioButtonId()==R.id.radioButtonA){
                            answer7 = 1;
                            index=index+1;
                        } else if (radGroupQuestion.getCheckedRadioButtonId()==R.id.radioButtonB){
                            answer7 = 2;
                            index=index+1;
                        } else{
                            answer7 = 3;
                            index=index+1;
                        }
                    }
                    radGroupQuestion.clearCheck();
                    break;
                case 7:
                    txtViewQuestion.setText("Question 8");
                    txtViewQuestionContent.setText("Will anyone else be living with us?");
                    radioButtonA.setText("No");
                    radioButtonB.setText("Yes, my partner");
                    radioButtonC.setText("Yes, my family (w/kids)");
                    imgViewQuestion.setImageResource(R.drawable.q8);
                    if (radGroupQuestion.getCheckedRadioButtonId()==-1) {
                        Toast.makeText(this,"Please check an answer",Toast.LENGTH_LONG).show();
                    } else {
                        if(radGroupQuestion.getCheckedRadioButtonId()==R.id.radioButtonA){
                            answer8 = 1;
                            index=index+1;
                        } else if (radGroupQuestion.getCheckedRadioButtonId()==R.id.radioButtonB){
                            answer8 = 2;
                            index=index+1;
                        } else{
                            answer8 = 3;
                            index=index+1;
                        }
                    }
                    radGroupQuestion.clearCheck();
                    break;
                case 8:
                    txtViewQuestion.setText("Question 9");
                    txtViewQuestionContent.setText("Anything about food I should know?");
                    radioButtonA.setText("I’m vegetarian/vegan and meat can’t be in the house");
                    radioButtonB.setText("I’m Kosher");
                    radioButtonC.setText("Nothing special");
                    imgViewQuestion.setImageResource(R.drawable.q9);
                    if (radGroupQuestion.getCheckedRadioButtonId()==-1) {
                        Toast.makeText(this,"Please check an answer",Toast.LENGTH_LONG).show();
                    } else {
                        if(radGroupQuestion.getCheckedRadioButtonId()==R.id.radioButtonA){
                            answer9 = 1;
                            index=index+1;
                        } else if (radGroupQuestion.getCheckedRadioButtonId()==R.id.radioButtonB){
                            answer9 = 2;
                            index=index+1;
                        } else{
                            answer9 = 3;
                            index=index+1;
                        }
                    }
                    radGroupQuestion.clearCheck();
                    break;
                case 9:
                    txtViewQuestion.setText("Question 10");
                    txtViewQuestionContent.setText(" How should we share common food items?");
                    radioButtonA.setText("Take turns buying");
                    radioButtonB.setText("Split costs evenly");
                    radioButtonC.setText("Buy our own items separately");
                    imgViewQuestion.setImageResource(R.drawable.q10);
                    if (radGroupQuestion.getCheckedRadioButtonId()==-1) {
                        Toast.makeText(this,"Please check an answer",Toast.LENGTH_LONG).show();
                    } else {
                        if(radGroupQuestion.getCheckedRadioButtonId()==R.id.radioButtonA){
                            answer10 = 1;
                            index=index+1;
                        } else if (radGroupQuestion.getCheckedRadioButtonId()==R.id.radioButtonB){
                            answer10 = 2;
                            index=index+1;
                        } else{
                            answer10 = 3;
                            index=index+1;
                        }
                    }
                    radGroupQuestion.clearCheck();
                    break;
                case 10:
                    startActivity(new Intent(QuizActivity.this, MatchActivity.class));
                    break;
                        }
        });
    }
}