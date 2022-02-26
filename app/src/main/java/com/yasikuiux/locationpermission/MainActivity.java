package com.yasikuiux.locationpermission;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.yasikuiux.locationpermission.utils.GpsUtils;

public class MainActivity extends AppCompatActivity {

    LocationManager locationManager ;
    boolean GpsStatus ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button alertGPS=(Button)findViewById(R.id.alertGPS);


        ImageView imageView = (ImageView)findViewById(R.id.locationimage);

        CheckGpsStatus() ;



        alertGPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                gpsStatus();


            }
        });


    }




    public void gpsStatus() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean gpsStatus = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!gpsStatus) {
            new GpsUtils(MainActivity.this).turnGPSOn(new GpsUtils.onGpsListener() {
                @Override
                public void gpsStatus(boolean isGPSEnable) {

                }
            });

        } else if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {

        } else {

        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            gpsStatus();
        }


        switch (resultCode) {
            case Activity.RESULT_OK: {

                Intent iin=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(iin);

                break;

            }
            case Activity.RESULT_CANCELED: {

                break;
            }

            default: {
                break;
            }
        }

    }


    public void CheckGpsStatus(){

        locationManager = (LocationManager)getApplicationContext().getSystemService(Context.LOCATION_SERVICE);

        GpsStatus = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    @Override
    public void onBackPressed() { }
}