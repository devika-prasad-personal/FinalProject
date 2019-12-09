package com.example.finalproject;

//import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    CalendarView calendarView;
    TextView theDate;
    int year;
    int month;
    int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button newTask = findViewById(R.id.newTask);
        calendarView = findViewById(R.id.calendarView);

        /**
         * gets the current date in the simple date format and sets the year, month, and day
         */
        // https://stackoverflow.com/questions/22461258/how-to-get-date-from-calendarview-oncreate-with-a-specific-format-e-g-dd-mm
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = sdf.format(new Date(calendarView.getDate()));
        String yearAsString = currentDate.substring(6);
        String monthAsString = currentDate.substring(3,5);
        String dayAsString = currentDate.substring(0,2);
        year = Integer.parseInt(yearAsString);
        month = Integer.parseInt(monthAsString);
        day = Integer.parseInt(dayAsString);


        //gets selected date and adds that information to the intent
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                year = i;
                month = i1;
                day = i2;
            }
        });

        newTask.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final Intent intent = new Intent(MainActivity.this, NewtaskActivity.class);

                intent.putExtra("year", year);
                intent.putExtra("month", month);
                intent.putExtra("day", day);

                startActivity(intent);
            }
        });
    }
}
