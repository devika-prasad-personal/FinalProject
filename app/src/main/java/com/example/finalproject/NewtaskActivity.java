package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class NewtaskActivity extends AppCompatActivity {

    float stressNum;
    float weightNum;
    /**
     * Task Name, [Days Left Till Due, Stress Level, Assignment Size]
     */
    HashMap<String, Task> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newtask);
        Button create = findViewById(R.id.create);

        Intent intent = getIntent();
        int year = intent.getIntExtra("year", 0);
        int month = intent.getIntExtra("month", 0);
        int day = intent.getIntExtra("day", 0);
        String dateSelected = month + "/" + day + "/" + year;
        String dateNow = intent.getStringExtra("currentDate");
        tasks = ((HashMap<String, Task>) intent.getSerializableExtra("tasks"));


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
        final Task t = new Task(dateNow, dateSelected, stressNum, weightNum);
        //String daysBetweenAsString = "" + daysBetween;
        //displayDays.setText(daysBetweenAsString);

        EditText name = findViewById(R.id.projectName);
        final String taskName = name.getText().toString();


        create.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //this should run algorithm for adding tasks
                tasks.put(taskName, t);
                Intent intent = new Intent(NewtaskActivity.this, MainActivity.class);
                intent.putExtra("tasks", tasks);
                startActivity(intent);
            }
        });
    }

}
