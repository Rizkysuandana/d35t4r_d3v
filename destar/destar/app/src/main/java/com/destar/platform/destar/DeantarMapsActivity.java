package com.destar.platform.destar;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.LocationManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.destar.platform.destar.response.Distance;
import com.destar.platform.destar.response.Duration;
import com.destar.platform.destar.response.LegsItem;
import com.destar.platform.destar.response.ResponseRoute;
import com.destar.platform.destar.service.ApiServices;
import com.destar.platform.destar.service.InitLibrary;
import com.destar.platform.destar.service.MyLocationService;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.maps.android.PolyUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeantarMapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private String API_KEY = "AIzaSyCYNh9kZmt8z0E-GSU2f2vHfBWGy0hs9Ps";

    public LatLng pickUpLatLng = null;
    public LatLng locationLatLng = null;
    private TextView tvStartAddress, tvEndAddress;
    private TextView tvPrice, tvDistance;
    private EditText namabarang, beratbarang;
    private String nama, berat;
    private Button btnNext;
    private LinearLayout infoPanel;
    private LinearLayout motor;
    private Boolean mLocationPermissionsGranted = false;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private GoogleApiClient mGoogleApiClient;
    private Marker mMarker;
    // Deklarasi variable
    private AutoCompleteTextView tvPickUpFrom, tvDestLocation;

    public static final int PICK_UP = 0;
    public static final int DEST_LOC = 1;
    private static int REQUEST_CODE = 0;

    private GoogleMap mMap;
    private MapView mapView;
    private int LOCATION_MIN_DISTANCE = 20;
    private int LOCATION_MIN_TIME = 4000;
    private LocationManager locationManager;
    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            drawMarker(location);
            locationManager.removeUpdates(locationListener);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onProviderDisabled(String provider) {
        }
    };

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengantar_barang);
        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mapView_onMapReady(googleMap);
            }
        });
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
      //  getCurrentLocation();
        btnNext = (Button)findViewById(R.id.btnNext);
        namabarang = (EditText)findViewById(R.id.nambar);
        beratbarang = (EditText)findViewById(R.id.berbar);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama = namabarang.getText().toString();
                berat = beratbarang.getText().toString();
                Intent intent = new Intent(DeantarMapsActivity.this, NextPengantarBarang.class);
                Bundle a = new Bundle();
                a.putString("parse_namabarang", nama);
                a.putString("parse_beratbarang", berat+" Kg");
                intent.putExtras(a);
                startActivity(intent);
            }
        });
//
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                Toast.makeText(this, "Membutuhkan Izin Lokasi", Toast.LENGTH_SHORT).show();
            } else {

                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                        1);
            }
        } else {
            // Permission has already been granted
            Toast.makeText(this, "Izin Lokasi diberikan", Toast.LENGTH_SHORT).show();
        }
        // getSupportActionBar().setTitle("Ojek Hampir Online");

//        // Inisialisasi Widget
        initMap();
        infoPanel.setVisibility(View.VISIBLE);
        // Event OnClick

        tvPickUpFrom = (AutoCompleteTextView) findViewById(R.id.tvPickUpFrom);
        wigetInit();
        tvPickUpFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Jalankan Method untuk menampilkan Place Auto Complete
                // Bawa constant PICK_UP
                showPlaceAutoComplete(PICK_UP);
            }
        });
        // Event OnClick
        tvDestLocation = (AutoCompleteTextView) findViewById(R.id.tvDestLocation);
        tvDestLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Jalankan Method untuk menampilkan Place Auto Complete
                // Bawa constant DEST_LOC
                showPlaceAutoComplete(DEST_LOC);
            }
        });

        View bottomSheet = (CoordinatorLayout) findViewById(R.id.sliding);
        BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }

    private void mapView_onMapReady(GoogleMap googleMap) {
        this.mMap = googleMap;
        mapView.getMapAsync(this);
        infoPanel = findViewById(R.id.infoPanel);
        // Widget
        tvPickUpFrom = findViewById(R.id.tvPickUpFrom);
        tvDestLocation = findViewById(R.id.tvDestLocation);

        btnNext = findViewById(R.id.btnNext);
    }

    // Method untuk Inisilisasi Widget agar lebih rapih
    private void wigetInit() {
        mapView.getMapAsync(this);
        // Maps
        mapView.getMapAsync(this);
        infoPanel = findViewById(R.id.infoPanel);
        // Widget
        tvPickUpFrom = findViewById(R.id.tvPickUpFrom);
        tvDestLocation = findViewById(R.id.tvDestLocation);

        btnNext = findViewById(R.id.btnNext);
    }

    // Method menampilkan input Place Auto Complete
    private void showPlaceAutoComplete(int typeLocation) {
        // isi RESUT_CODE tergantung tipe lokasi yg dipilih.
        // titik jmput atau tujuan
        REQUEST_CODE = typeLocation;

        // Filter hanya tmpat yg ada di Indonesia
        AutocompleteFilter typeFilter = new AutocompleteFilter.Builder().setCountry("ID").build();
        try {
            // Intent untuk mengirim Implisit Intent
            Intent mIntent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                    .setFilter(typeFilter).build(this);
            // jalankan intent impilist
            startActivityForResult(mIntent, REQUEST_CODE);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace(); // cetak error
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace(); // cetak error
            // Display Toast
            Toast.makeText(this, "Layanan Play Services Tidak Tersedia", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Toast.makeText(this, "Sini Gaes", Toast.LENGTH_SHORT).show();
        // Pastikan Resultnya OK
        if (resultCode == RESULT_OK) {
            //Toast.makeText(this, "Sini Gaes2", Toast.LENGTH_SHORT).show();
            // Tampung Data tempat ke variable
            Place placeData = PlaceAutocomplete.getPlace(this, data);

            if (placeData.isDataValid()) {
                // Show in Log Cat
                Log.d("autoCompletePlace Data", placeData.toString());

                // Dapatkan Detail
                String placeAddress = placeData.getAddress().toString();
                LatLng placeLatLng = placeData.getLatLng();
                String placeName = placeData.getName().toString();
                // Cek user milih titik jemput atau titik tujuan
                switch (REQUEST_CODE) {
                    case PICK_UP:
                        // Set ke widget lokasi asal
                        tvPickUpFrom.setText(placeAddress);
                        pickUpLatLng = placeData.getLatLng();
                        break;
                    case DEST_LOC:
                        // Set ke widget lokasi tujuan
                        tvDestLocation.setText(placeAddress);
                        locationLatLng = placeData.getLatLng();
                        break;
                }
                // Jalankan Action Route
                if (pickUpLatLng != null && locationLatLng != null) {
                    actionRoute(placeLatLng, REQUEST_CODE);
                }

            } else {
                // Data tempat tidak valid
                Toast.makeText(this, "Invalid Place !", Toast.LENGTH_SHORT).show();
            }
        }
        motor = (LinearLayout) findViewById(R.id.sepeda_motor);
        motor.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Sepeda Motor dipilih", Toast.LENGTH_SHORT).show();
                motor.setBackgroundColor(R.color.colorOrange);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
        getCurrentLocation();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
        locationManager.removeUpdates(locationListener);
    }

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setPadding(10, 180, 10, 10);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        mMap.getUiSettings().setRotateGesturesEnabled(false);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap = googleMap;
        mapView.getMapAsync(this);
        if (mLocationPermissionsGranted) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);

            initMap();
        }
    }

    private void initMap(){
        mapView.getMapAsync(this);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            if (mMap != null){
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
                mMap.getUiSettings().setAllGesturesEnabled(true);
            }
        }else{
                if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},12);
                }
                if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},13);
                }
        }
        infoPanel = findViewById(R.id.infoPanel);
        // Widget
        tvPickUpFrom = findViewById(R.id.tvPickUpFrom);
        tvDestLocation = findViewById(R.id.tvDestLocation);

        btnNext = findViewById(R.id.btnNext);
    }


    private void getCurrentLocation(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED ||
        ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            Location location = null;
            if(!isGPSEnabled && ! isNetworkEnabled){
                Toast.makeText(getApplicationContext(),getText(R.string.network_failed), Toast.LENGTH_LONG).show();
            }else{
                if(isGPSEnabled){
                    locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER,LOCATION_MIN_TIME, LOCATION_MIN_DISTANCE, locationListener);
                    location = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);
                }
                if(isNetworkEnabled){
                    locationManager.requestLocationUpdates(locationManager.NETWORK_PROVIDER,LOCATION_MIN_TIME, LOCATION_MIN_DISTANCE, locationListener);
                    location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
                }
            }
            if (location != null){
                drawMarker(location);
            }
        }
    }
    private void drawMarker (Location location){
        if (mMap!=null){
            mMap.clear();
            LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
            mMap.addMarker(new MarkerOptions().position(latLng).title(getText(R.string.i_here).toString()));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,20));
        }
    }
    private void actionRoute(LatLng placeLatlng, final int requestCode) {
        String lokasiAwal = pickUpLatLng.latitude + "," + pickUpLatLng.longitude;
        String lokasiAkhir = locationLatLng.latitude + "," + locationLatLng.longitude;

        // Clear dulu Map nya
        mMap.clear();
        // Panggil Retrofit
        ApiServices api = InitLibrary.getInstance();
        // Siapkan request
        Call<ResponseRoute> routeRequest = api.request_route(lokasiAwal, lokasiAkhir, API_KEY);
        // kirim request
        routeRequest.enqueue(new Callback<ResponseRoute>() {
            @Override
            public void onResponse(Call<ResponseRoute> call, Response<ResponseRoute> response) {

                if (response.isSuccessful()){
                    // tampung response ke variable
                    ResponseRoute dataDirection = response.body();

                    LegsItem dataLegs = dataDirection.getRoutes().get(0).getLegs().get(0);

                    // Dapatkan garis polyline
                    String polylinePoint = dataDirection.getRoutes().get(0).getOverviewPolyline().getPoints();
                    // Decode
                    List<LatLng> decodePath = PolyUtil.decode(polylinePoint);
                    // Gambar garis ke maps
                    mMap.addPolyline(new PolylineOptions().addAll(decodePath)
                            .width(8f).color(Color.argb(255, 56, 167, 252)))
                            .setGeodesic(true);

                    // Tambah Marker
                    mMap.addMarker(new MarkerOptions().position(pickUpLatLng).title("Lokasi Awal"));
                    mMap.addMarker(new MarkerOptions().position(locationLatLng).title("Lokasi Akhir"));
                    // Dapatkan jarak dan waktu
                    Distance dataDistance = dataLegs.getDistance();
                    Duration dataDuration = dataLegs.getDuration();

                    // Set Nilai Ke Widget
                   double price_per_meter = 250;
                    double priceTotal = dataDistance.getValue() * price_per_meter; // Jarak * harga permeter

                    tvPrice.setText(dataDistance.getText());
                    tvDistance.setText(String.valueOf(priceTotal));
                    /** START
                     * Logic untuk membuat layar berada ditengah2 dua koordinat
                     */

                    LatLngBounds.Builder latLongBuilder = new LatLngBounds.Builder();
                    latLongBuilder.include(pickUpLatLng);
                    latLongBuilder.include(locationLatLng);

                    // Bounds Coordinata
                    LatLngBounds bounds = latLongBuilder.build();

                    int width = getResources().getDisplayMetrics().widthPixels;
                    int height = getResources().getDisplayMetrics().heightPixels;
                    int paddingMap = (int) (width * 0.2); //jarak dari
                    CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, width, height, paddingMap);
                    mMap.animateCamera(cu);

                    /** END
                     * Logic untuk membuat layar berada ditengah2 dua koordinat
                     */
                    // Tampilkan info panel
                    infoPanel.setVisibility(View.VISIBLE);

                    mMap.setPadding(10, 180, 10, 180);

                }
            }

            @Override
            public void onFailure(Call<ResponseRoute> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}