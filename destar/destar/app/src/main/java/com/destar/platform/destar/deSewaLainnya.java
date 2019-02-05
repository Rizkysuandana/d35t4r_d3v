package com.destar.platform.destar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class deSewaLainnya extends AppCompatActivity {
    private LinearLayout bor, buku, carrier, kamera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_de_sewa_lainnya);
        bor = (LinearLayout)findViewById(R.id.bor);
        buku = (LinearLayout)findViewById(R.id.buku);
        carrier = (LinearLayout)findViewById(R.id.carrier);
        kamera = (LinearLayout)findViewById(R.id.kamera);

        bor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(deSewaLainnya.this, LanjutanLainnya.class);
                startActivity(intent);
            }
        });
        buku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(deSewaLainnya.this, LanjutanLainnya.class);
                startActivity(intent);
            }
        });
        carrier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(deSewaLainnya.this, LanjutanLainnya.class);
                startActivity(intent);
            }
        });
        kamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(deSewaLainnya.this, LanjutanLainnya.class);
                startActivity(intent);
            }
        });
    }
}
