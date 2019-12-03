package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button newTask = findViewById(R.id.newTask);
        final CalendarView calendar = findViewById(R.id.calendarView);

        newTask.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final Intent intent = new Intent(MainActivity.this, NewtaskActivity.class);

                //gets selected date and adds that information to the intent
                calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                        intent.putExtra("year", i);
                        intent.putExtra("month", i1);
                        intent.putExtra("day", i2);
                        Log.e("hi", i1 + " / " + i2 + " / " + i);
                    }
                });

                startActivity(intent);
            }
        });
    }
}
