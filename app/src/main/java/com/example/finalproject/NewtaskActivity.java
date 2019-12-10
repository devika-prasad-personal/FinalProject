package com.example.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.ArrayList;

public class NewtaskActivity extends AppCompatActivity {

    String name;
    float stressNum;
    float weightNum;
    String dateSelected;
    String dateNow;
    ArrayList<Task> tasks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newtask);
        Button create = findViewById(R.id.create);

        Intent date = getIntent();
        int year = date.getIntExtra("year", 0);
        int month = date.getIntExtra("month", 0);
        int day = date.getIntExtra("day", 0);
        dateSelected = month + "/" + day + "/" + year;
        dateNow = date.getStringExtra("currentDate");

        //gets the project name
        EditText projectName = findViewById(R.id.projectName);
        name = projectName.getText().toString();
        projectName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                name = charSequence.toString();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //sets the on click listener for the button when a new task is created
        create.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //this should run algorithm for adding tasks
                Intent intent = new Intent(NewtaskActivity.this, MainActivity.class);
                Task task = new Task(name, dateNow, dateSelected, stressNum, weightNum);
                intent.putExtra("task", task);
                setResult(Activity.RESULT_OK, intent);
                finish();
                //startActivity(intent);
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

        Task t = new Task(name, dateNow, dateSelected, stressNum, weightNum);
        int daysBetweent = t.getDaysBetweent();
        String daysBetweenAsString = "" + daysBetweent;

    }

}
