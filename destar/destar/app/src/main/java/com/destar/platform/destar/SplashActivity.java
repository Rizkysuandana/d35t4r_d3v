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
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;
import com.destar.platform.destar.R;
import com.destar.platform.destar.app.Config;
import com.destar.platform.destar.utils.MasifaController;

import org.json.JSONException;
import org.json.JSONObject;

public class SplashActivity extends AppCompatActivity {
    String android;
    String pFkey =null;
    private String url = ServerActivity.URL + "CekHardwareIDCustomer";
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 1;
    private static final long MINIMUM_TIME_BETWEEN_UPDATES = 1000;
    protected LocationManager locationManager;
    MasifaController msfC;
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
        msfC = new MasifaController();
        url = url +"?strHardwareID="+android;
        Log.i("Hasil=>",url);
        Cek();
    }
    public void Cek(){
        class AsyncTaskUploadClass extends AsyncTask<Void,Void,String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }
            @Override
            protected String doInBackground(Void... params) {
                String FinalData = msfC.HttpGET(url);
                return FinalData;


            }
            @Override
            protected void onPostExecute(String string1) {
                super.onPostExecute(string1);
                if (TextUtils.isEmpty(string1)) {
                    Intent intent = new Intent(SplashActivity.this, TampilanAwalActivity.class);
                    startActivity(intent);
                    SplashActivity.this.finish();
                    Toast.makeText(SplashActivity.this,"Sesi Anda Telah Habis \nSilahkan Login",Toast.LENGTH_LONG).show();
                }
                else {
                    try {
                        Hasil(string1);
                    } catch (JSONException e) {
                        Log.e( "Error",String.valueOf( e.toString() ) );
                        Intent intent = new Intent(SplashActivity.this, TampilanAwalActivity.class);
                        startActivity(intent);
                        SplashActivity.this.finish();
                        Toast.makeText(SplashActivity.this,"Sesi Anda Telah Habis \nSilahkan Login",Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                }
            }
        }
        AsyncTaskUploadClass AsyncTaskUploadClassOBJ = new AsyncTaskUploadClass();
        AsyncTaskUploadClassOBJ.execute();
    }
    public void Hasil(String pParam) throws JSONException {
        String xUSERNAME = null;
        Boolean xACTIVE= null;
        Integer xCUSTOMERTYPEID= null;
        String xCUSTOMERTYPENAME= null;
        String pxJson = pParam.replace( "<?xml version=\"1.0\" encoding=\"utf-8\"?>","" );
        pxJson = pxJson.replace( "<string xmlns=\"http://tempuri.org/\">[","" );
        pxJson = pxJson.replace( "]</string>","" );
        JSONObject jsonObj = new JSONObject(pxJson);
        xUSERNAME= jsonObj.getString("USERNAME");
        xACTIVE= jsonObj.getBoolean("ACTIVE");
        xCUSTOMERTYPEID= jsonObj.getInt("CUSTOMERTYPEID");
        xCUSTOMERTYPENAME= jsonObj.getString("CUSTOMERTYPENAME");
        ((AppClass) SplashActivity.this.getApplication()).setUSERNAME(xUSERNAME);
        ((AppClass) SplashActivity.this.getApplication()).setCUSTOMERTYPEID( String.valueOf( xCUSTOMERTYPEID ) );
        ((AppClass) SplashActivity.this.getApplication()).setCUSTOMERTYPENAME(xCUSTOMERTYPENAME);
        if(xACTIVE){
            Toast.makeText(SplashActivity.this, "Selamat Datang "+xUSERNAME + "\n" + "Type " + xCUSTOMERTYPENAME, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(SplashActivity.this, Dashboard.class);
            startActivity(intent);
            this.finish();
        }
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

    }
}
