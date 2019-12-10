package com.example.finalproject;

import android.util.Log;
import android.widget.TextView;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Task implements Serializable {
    private String name;
    private String date;
    private String currentDate;
    private float stress;
    private float weight;
    Task(String n, String cDate, String d, float s, float w) {
        name = n;
        currentDate = cDate;
        date = d;
        stress = s;
        weight = w;
    }

    public int getDaysBetweent() {
        // https://stackoverflow.com/questions/42553017/android-calculate-days-between-two-dates
        Date current = new Date(currentDate);
        Date dayDue = new Date(date);
        long diff = dayDue.getTime() - current.getTime();
        return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        int time = (int) (stress * weight) * 2 / getDaysBetweent();
        return time + " minutes";
    }


}
