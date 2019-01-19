package com.shashank.platform.destar.service;
/**
 * Created by RIVES on 12/1/2018.
 */

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.shashank.platform.destar.AppClass;


public class MyLocationService extends Service {

    private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 1;
    private static final long MINIMUM_TIME_BETWEEN_UPDATES = 1000;
    protected LocationManager locationManager;
    public static boolean isRunning = false;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        isRunning = true;
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        try {
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    MINIMUM_TIME_BETWEEN_UPDATES,
                    MINIMUM_DISTANCE_CHANGE_FOR_UPDATES,
                    new MyLocationService.MyLocationListener()
            );
        }
        catch (Exception e){}

    }
    private class MyLocationListener implements LocationListener {

        public void onLocationChanged(Location location) {
            String Long = String.valueOf(location.getLongitude());
            String Lat = String.valueOf(location.getLatitude());
            String NamaUSer = ((AppClass)getApplication()).getusern();
            ((AppClass) MyLocationService.this.getApplication()).DapatLokasi(Lat,   Long,NamaUSer);
        }
        public void onStatusChanged(String s, int i, Bundle b) { }
        public void onProviderDisabled(String s) {
            Toast.makeText(MyLocationService.this,
                    "Provider disabled by the user. GPS turned off",
                    Toast.LENGTH_LONG).show();
        }
        public void onProviderEnabled(String s) {
            Toast.makeText(MyLocationService.this,
                    "Provider enabled by the user. GPS turned on",
                    Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onDestroy(){

    }

}