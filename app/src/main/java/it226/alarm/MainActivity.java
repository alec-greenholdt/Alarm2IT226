package com.example.alarmbuttonstest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b1=(Button)findViewById(R.id.AlarmButton);
        Button b2=(Button)findViewById(R.id.timerButton);
        Button b3= (Button) findViewById(R.id.locationButton);
    }

    public void onClick(View v){
        Intent alarm = new Intent(MainActivity.this, alarmPage.class);
        startActivity(alarm);

    }

    public void timeClick(View v){
        Intent timer = new Intent(MainActivity.this, timer_page.class);
        startActivity(timer);
    }

    public void locationClick (View v){
        Intent location = new Intent(MainActivity.this, location_page.class);
        startActivity(location);
    }


}
