package com.destar.platform.destar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class deSewaActivity extends AppCompatActivity {
    int[] sampleImages = {R.drawable.image_promo1, R.drawable.image_promo2, R.drawable.image_promo3, R.drawable.image_promo4};
    CarouselView carouselView;
    private LinearLayout kendaraan, alatpertanian, alatmedis, lainnya;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.desewa_activity);
        carouselView = (CarouselView) findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);

        carouselView.setImageListener(imageListener);
        kendaraan = (LinearLayout) findViewById(R.id.btn_kendaraan);
        kendaraan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(deSewaActivity.this, Kendaraan.class);
                startActivity(intent);
            }
        });
        alatmedis = (LinearLayout) findViewById(R.id.btn_alatMedis);
        alatmedis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(deSewaActivity.this, AlatMedis.class);
                startActivity(intent);
            }
        });
        alatpertanian = (LinearLayout) findViewById(R.id.btn_alatPertanian);
        alatpertanian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(deSewaActivity.this, AlatPertanian.class);
                startActivity(intent);
            }
        });
        lainnya = (LinearLayout) findViewById(R.id.btn_lainnya);
        lainnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(deSewaActivity.this, deSewaLainnya.class);
                startActivity(intent);
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
