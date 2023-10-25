package com.example.collegemate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

        txtViewQuestion.setText("Question 1");
        txtViewQuestionContent.setText("It is Friday night and your friend drops by with your favorite movie. Your roommate has already left to spend the weekend with family. After a few too many drinks, your friend gets a little drunk and is no condition to drive home safely, so you suggest that she/he stay for the night. Where do you let him/her crash?");

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
                        }
        });
    }
}