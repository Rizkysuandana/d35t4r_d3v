package com.destar.platform.destar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class Dashboard extends AppCompatActivity {
    private Button transportasi;
    CarouselView carouselView;
    int[] sampleImages = {R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4, R.drawable.image5};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        transportasi = (Button)findViewById(R.id.btn_transportasi);
        transportasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, TransportasiActivity.class);
                startActivity(intent);
            }
        });
        carouselView = (CarouselView) findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);

        carouselView.setImageListener(imageListener);
        LinearLayout beranda1 = (LinearLayout) findViewById(R.id.beranda);
        LinearLayout pesanan1 = (LinearLayout)findViewById(R.id.pesanan);
        LinearLayout riwayat1 = (LinearLayout)findViewById(R.id.riwayat);
        LinearLayout akun1 = (LinearLayout)findViewById(R.id.akun);
       LinearLayout btnmaps= (LinearLayout)findViewById(R.id.btnmaps);
        beranda1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, Dashboard.class);
                startActivity(intent);
            }
        });
        pesanan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashboard.this, PesananActivity.class);
                startActivity(i);
            }
        });
        riwayat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashboard.this, RiwayatActivity.class);
                startActivity(i);
            }
        });
        akun1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashboard.this, AkunActivity.class);
                startActivity(i);
            }
        });
        btnmaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashboard.this, MapsActivity.class);
                startActivity(i);
                Dashboard.this.finish();
            }
        });
    }
    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };


}
