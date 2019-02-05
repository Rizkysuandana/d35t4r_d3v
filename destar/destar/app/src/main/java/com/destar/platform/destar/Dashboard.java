package com.destar.platform.destar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class Dashboard extends AppCompatActivity {
    private CardView transportasi;
    private CardView deSewa;
    CarouselView carouselView;
    int[] sampleImages = {R.drawable.image_promo1, R.drawable.image_promo2, R.drawable.image_promo3, R.drawable.image_promo4};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        transportasi = (CardView)findViewById(R.id.btn_transportasi);
        transportasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, TransportasiActivity.class);
                startActivity(intent);
            }
        });
        deSewa = (CardView)findViewById(R.id.btn_penyewaan);
        deSewa.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Dashboard.this, deSewaActivity.class);
                startActivity(intent);
            }
        });
        carouselView = (CarouselView) findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);

        carouselView.setImageListener(imageListener);
        ToolBarEvent();
    }
    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };
    private void ToolBarEvent(){
        LinearLayout beranda1 = (LinearLayout) findViewById(R.id.beranda);
        LinearLayout pesanan1 = (LinearLayout)findViewById(R.id.pesanan);
        LinearLayout riwayat1 = (LinearLayout)findViewById(R.id.riwayat);
        LinearLayout akun1 = (LinearLayout)findViewById(R.id.akun);
        beranda1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, Dashboard.class);
                startActivity(intent);
                Dashboard.this.finish();
            }
        });
        pesanan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashboard.this, PesananActivity.class);
                startActivity(i);
                Dashboard.this.finish();
            }
        });
        riwayat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashboard.this, RiwayatActivity.class);
                startActivity(i);
                Dashboard.this.finish();
            }
        });
        akun1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashboard.this, AkunActivity.class);
                startActivity(i);
                Dashboard.this.finish();
            }
        });
    }

}
