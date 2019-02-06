package com.destar.platform.destar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Kendaraan extends AppCompatActivity {
    private LinearLayout germor, pickupbox, dumptruck, pickupstandar;
    private TextView berat_kendaraan, jeniskendaraan;
    private ImageView gambarKendaraan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kendaraan);
        germor=(LinearLayout)findViewById(R.id.Germor);
        pickupbox = (LinearLayout)findViewById(R.id.pickupbox);
        pickupstandar = (LinearLayout)findViewById(R.id.pickupStandar);
        dumptruck = (LinearLayout) findViewById(R.id.DumpTruck);
        germor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Kendaraan.this, NextDesewa.class);
                startActivity(intent);
            }
        });
        pickupstandar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Kendaraan.this, NextDesewa.class);
                startActivity(intent);
            }
        });
        pickupbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Kendaraan.this, NextDesewa.class);
                startActivity(intent);
            }
        });
        dumptruck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Kendaraan.this, NextDesewa.class);
                startActivity(intent);
            }
        });
    }
}
