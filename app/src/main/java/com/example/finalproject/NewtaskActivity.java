package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NewtaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newtask);
        Button create = findViewById(R.id.create);

        Intent date = getIntent();
        int year = date.getIntExtra("year", 0);
        int month = date.getIntExtra("month", 0);
        int day = date.getIntExtra("day", 0);

        create.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //this should run algorithm for adding tasks
                Intent intent = new Intent(NewtaskActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        /**
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
        }
        });
         **/
    }

}
