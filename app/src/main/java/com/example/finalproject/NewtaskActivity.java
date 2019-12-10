package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;

public class NewtaskActivity extends AppCompatActivity {

    float stressNum;
    float weightNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newtask);
        Button create = findViewById(R.id.create);

        Intent date = getIntent();
        int year = date.getIntExtra("year", 0);
        int month = date.getIntExtra("month", 0);
        int day = date.getIntExtra("day", 0);
        String dateSelected = month + "/" + day + "/" + year;
        String dateNow = date.getStringExtra("currentDate");

        create.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //this should run algorithm for adding tasks
                Intent intent = new Intent(NewtaskActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //sets the text of the date the project is due
        TextView dateDue = findViewById(R.id.dateDue);
        dateDue.setText(dateSelected);

        // gets the number from the rating bars and saves in stressNum and weightNum
        // also displays the number in text views
        RatingBar howStressed = findViewById(R.id.stressRating);
        stressNum = howStressed.getRating();
        howStressed.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                stressNum = v;
                TextView numberStress = findViewById(R.id.numberStress);
                String stressNumAsString = "" + stressNum;
                numberStress.setText(stressNumAsString);
            }
        });

        RatingBar weight = findViewById(R.id.weight);
        weightNum = weight.getRating();
        weight.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                weightNum = v;
                TextView numberWeight = findViewById(R.id.numberWeight);
                String weightNumAsString = "" + weightNum;
                numberWeight.setText(weightNumAsString);
            }
        });

        TextView displayDays = findViewById(R.id.textView5);
        Task t = new Task(dateNow, dateSelected, stressNum, weightNum);
        int daysBetweent = t.getDaysBetweent();
        String daysBetweenAsString = "" + daysBetweent;
        displayDays.setText(daysBetweenAsString);

    }

}
