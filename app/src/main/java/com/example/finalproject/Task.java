package com.example.finalproject;

import android.util.Log;
import android.widget.TextView;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Task {
    private String date;
    private String currentDate;
    private int daysBetween;
    private float stress;
    private float weight;
    Task(String cDate, String d, float s, float w) {
        currentDate = cDate;
        date = d;
        stress = s;
        weight = w;
        Date current = new Date(currentDate);
        Date dayDue = new Date(date);
        daysBetween = getDaysBetween(current, dayDue);

    }

    public int getDaysBetween(Date current, Date due) {
        // https://stackoverflow.com/questions/42553017/android-calculate-days-between-two-dates

        long diff = due.getTime() - current.getTime();
        Log.i("hi", "" + (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
        return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }


}
