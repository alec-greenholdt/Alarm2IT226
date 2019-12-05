package com.example.working;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LocationAlarm extends AppCompatActivity {

    private LocationManager locationManager;
    private LocationListener locationListener;
    private Button timerButton;
    private Button submitButton;
    private EditText locationTime;
    private TextView startingcoords;
    private TextView endingcoords;
    private String tempString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_alarm);

        if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)){

            }else{
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},10);
            }
        }

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {

            private int counter = 0;
            //called whenever location is updated
            @Override
            public void onLocationChanged(Location location) {
                counter=counter+1;
                if(counter == 1) {
                    startingcoords.setText(location.getLatitude() + " " + location.getLongitude());
                }
                if(counter == 2){
                    endingcoords.setText(location.getLatitude() + " " + location.getLongitude());
                    if(startingcoords.getText().equals(endingcoords.getText())){
                        //  AlertDialog
                        openDialog();
                    }
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onProviderDisabled(String provider) {
                Intent turnOnGPS = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(turnOnGPS);
            }
        };

//--------------------------------------------------------------------------------------------------------------
//        final int locTime;
//        locationTime = (EditText) findViewById(R.id.editText2);
//        submitButton = (Button) findViewById(R.id.submitBtn);
//        submitButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//              if(locationTime.getText().equals("")){
//                  locTime = 10000;
//              }
//              else{
//                  tempString = locationTime.getText();
//              }
//            }
//        });

//--------------------------------------------------------------------------------------------------------------
        startingcoords = (TextView) findViewById(R.id.startingcoords);
        endingcoords = (TextView) findViewById(R.id.endingcoords);



        timerButton = (Button) findViewById(R.id.timerBtn2);
        timerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                               //time in milliseconds (1000*seconds)
                locationManager.requestLocationUpdates("gps", 10000, 0, locationListener);
            }
        });

    }
    @Override
    public void onRequestPermissionsResult(int code, String[] permssions, int[] results){
        if(code == 10){
            if(results.length>0 && results[0] == PackageManager.PERMISSION_GRANTED){
                // todo when they accept permission

            }else{
                //todo when they deny permission
            }
        }
    }
    public void openDialog(){
        moveDialog testDialog = new moveDialog();
        testDialog.show(getSupportFragmentManager(), "example dialog");
    }
}
