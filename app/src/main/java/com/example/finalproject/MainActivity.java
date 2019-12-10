package com.example.finalproject;

//import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    CalendarView calendarView;
    TextView theDate;
    int year;
    int month;
    int day;
    String dateNow;
    ArrayList<Task> allTasks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button newTask = findViewById(R.id.newTask);
        calendarView = findViewById(R.id.calendarView);
        //allTasks.add(new Task("homework","12/9/2019", "12/10/2019", (float) 5.5, (float) 5.5));
        //allTasks.add(new Task("math test","12/9/2019", "12/15/2019", (float) 3.5, (float) 2.5));

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

                //startActivity(intent);
                startActivityForResult(intent, 0);
            }
        });
    }

    public void setUpUI() {
        LinearLayout taskLayout = findViewById(R.id.displayTasks);
        //Intent i = getIntent();
        //Task createdTask = (Task) i.getSerializableExtra("task");
        //allTasks.add(createdTask);
        taskLayout.removeAllViews();
        for (int j = 0; j < allTasks.size(); j++) {
            View taskChunk = getLayoutInflater().inflate(R.layout.chunk_task,
                    taskLayout, false);
            TextView taskName = taskChunk.findViewById(R.id.taskName);
            TextView timeToday = taskChunk.findViewById(R.id.timeToday);
            TextView daysLeft = taskChunk.findViewById(R.id.daysLeft);
            taskName.setText(allTasks.get(j).getName());
            timeToday.setText(allTasks.get(j).getTime());
            final Task currentTask = allTasks.get(j);
            Button progress = taskChunk.findViewById(R.id.progress);
            progress.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    currentTask.decreaseStress();
                    if (currentTask.isFinished()) {
                        allTasks.remove(currentTask);
                    }
                    setUpUI();
                }
            });
            String s = "Days Left: " + allTasks.get(j).getDaysBetweent() + "";
            daysLeft.setText(s);
            taskLayout.addView(taskChunk);
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 0) {
            // Make sure the request was successful
            if (resultCode == Activity.RESULT_OK) {
                // The user picked a contact.
                // The Intent's data Uri identifies which contact was selected.
                Task createdTask = (Task) data.getSerializableExtra("task");
                allTasks.add(createdTask);
                setUpUI();
                // Do something with the contact here (bigger example below)
            } else {
                System.out.println("didn't work");
            }
        }
    }
}
