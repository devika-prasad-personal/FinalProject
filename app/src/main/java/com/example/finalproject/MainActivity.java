package com.example.finalproject;

//import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    CalendarView calendarView;
    TextView theDate;
    int year;
    int month;
    int day;
    String dateNow;
    HashMap<String, Task> tasks;
    boolean tasksCreated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button newTask = findViewById(R.id.newTask);
        calendarView = findViewById(R.id.calendarView);

        Intent intent = getIntent();

        if (tasksCreated) {
            tasks = ((HashMap<String, Task>) intent.getSerializableExtra("tasks"));
            setUpUI();
        }

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
        dateNow = month + "/" + day + "/" + year;


        //gets selected date and adds that information to the intent
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                year = i;
                month = i1 + 1;
                day = i2;
            }
        });

        newTask.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final Intent intent = new Intent(MainActivity.this, NewtaskActivity.class);

                intent.putExtra("year", year);
                intent.putExtra("month", month);
                intent.putExtra("day", day);
                intent.putExtra("currentDate", dateNow);
                intent.putExtra("tasks", tasks);
                tasksCreated = true;
                startActivity(intent);
            }
        });

    }

    private void setUpUI() {
        LinearLayout taskList = findViewById(R.id.tasks);
        taskList.setVisibility(View.VISIBLE);
        taskList.removeAllViews();
        Iterator taskIterator = tasks.entrySet().iterator();
        while (taskIterator.hasNext()) {

            Map.Entry mapElement = (Map.Entry)taskIterator.next();

            View messageChunk = getLayoutInflater().inflate(R.layout.chunk_task, taskList, false);
            //identify boxes
            TextView name = messageChunk.findViewById(R.id.taskName);


            //set text with game information
            name.setText((String) mapElement.getKey());

            taskList.addView(messageChunk);
        }
    }
}
