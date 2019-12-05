package com.example.working;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Button b1;
    private Button b2;
    private Button b3;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         b1=(Button)findViewById(R.id.alarmButton);
         b2=(Button)findViewById(R.id.timerButton);
         b3= (Button) findViewById(R.id.locationButton);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent alarm = new Intent(MainActivity.this, Alarm.class);
                startActivity(alarm);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent timer = new Intent(MainActivity.this, TimerTime.class);
                startActivity(timer);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent locationAlarm = new Intent(MainActivity.this, LocationAlarm.class);
                startActivity(locationAlarm);
            }
        });

    }


    public void onClick(View v){
        Intent alarm = new Intent(MainActivity.this, Alarm.class);
        startActivity(alarm);

    }

    public void timeClick(View v){
        Intent timer = new Intent(MainActivity.this, TimerTime.class);
        startActivity(timer);
    }

    public void locationClick (View v){
        Intent location = new Intent(MainActivity.this, Location.class);
        startActivity(location);
    }


}

