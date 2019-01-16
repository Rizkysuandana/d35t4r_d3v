package com.destar.platform.destar;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import com.destar.platform.destar.app.Config;
import com.google.firebase.messaging.FirebaseMessaging;
import com.shashank.platform.destar.R;

public class SplashActivity extends AppCompatActivity {
    String android;
    String pFkey =null;
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 1;
    private static final long MINIMUM_TIME_BETWEEN_UPDATES = 1000;
    protected LocationManager locationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash);

        android = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        ((AppClass) SplashActivity.this.getApplication()).Setandroidid(android);
        Firebase();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                MINIMUM_TIME_BETWEEN_UPDATES,
                MINIMUM_DISTANCE_CHANGE_FOR_UPDATES,
                new MyLocationListener()
        );
    }
    private class MyLocationListener implements LocationListener {
        public void onLocationChanged(Location location) {
            String Long =String.valueOf(location.getLongitude());
            String Lat =String.valueOf(location.getLatitude());
            String NamaUSer = ((AppClass)getApplication()).getusern();
            ((AppClass) SplashActivity.this.getApplication()).DapatLokasi(Lat,Long,NamaUSer);
        }
        public void onStatusChanged(String s, int i, Bundle b) {

        }
        public void onProviderDisabled(String s) {
            Toast.makeText(SplashActivity.this,
                    "Provider disabled by the user. GPS turned off",
                    Toast.LENGTH_LONG).show();
        }
        public void onProviderEnabled(String s) {
            Toast.makeText(SplashActivity.this,
                    "Provider enabled by the user. GPS turned on",
                    Toast.LENGTH_LONG).show();
        }
    }
    private void Firebase(){
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals( Config.REGISTRATION_COMPLETE)) {
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);
                    displayFirebaseRegId();
                }
                else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    String message = intent.getStringExtra("message");
                }
            }
        };

        displayFirebaseRegId();
    }
    private void displayFirebaseRegId() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        String regId = pref.getString("regId", null);
        ((AppClass) this.getApplication()).SetFBase(regId);
        startActivity(new Intent(getApplicationContext(), TampilanAwalActivity.class));
        finish();
    }
}
