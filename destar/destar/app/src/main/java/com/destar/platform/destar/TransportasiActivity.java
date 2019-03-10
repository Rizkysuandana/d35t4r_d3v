package com.destar.platform.destar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.destar.platform.destar.R;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class TransportasiActivity extends AppCompatActivity {
    private LinearLayout pengantarBarang;
    private LinearLayout jasaAngkut;
    int[] sampleImages = {R.drawable.image_promo1, R.drawable.image_promo2, R.drawable.image_promo3, R.drawable.image_promo4};
    CarouselView carouselView1;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transportasi);
        carouselView1 = (CarouselView) findViewById(R.id.carouselView1);
        carouselView1.setPageCount(sampleImages.length);

        carouselView1.setImageListener(imageListener);
        pengantarBarang = (LinearLayout) findViewById(R.id.btnpengantaran);
        pengantarBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TransportasiActivity.this, DeantarMapsActivity.class );
                startActivity(intent);
            }
        });
        jasaAngkut = (LinearLayout) findViewById(R.id.btnpengangkutan);
        jasaAngkut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TransportasiActivity.this, JasaAngkutActivity.class);
            }
        });
        ToolBarEvent();
    }
    private void ToolBarEvent(){
        LinearLayout beranda1 = (LinearLayout) findViewById(R.id.beranda);
        LinearLayout pesanan1 = (LinearLayout)findViewById(R.id.pesanan);
        LinearLayout riwayat1 = (LinearLayout)findViewById(R.id.riwayat);
        LinearLayout akun1 = (LinearLayout)findViewById(R.id.akun);
        beranda1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TransportasiActivity.this, Dashboard.class);
                startActivity(intent);TransportasiActivity.this.finish();
            }
        });
        pesanan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TransportasiActivity.this, PesananActivity.class);
                startActivity(i);TransportasiActivity.this.finish();
            }
        });
        riwayat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TransportasiActivity.this, RiwayatActivity.class);
                startActivity(i);TransportasiActivity.this.finish();
            }
        });
        akun1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TransportasiActivity.this, AkunActivity.class);
                startActivity(i);TransportasiActivity.this.finish();
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
