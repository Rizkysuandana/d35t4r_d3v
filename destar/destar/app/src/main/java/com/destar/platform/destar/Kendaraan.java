package com.destar.platform.destar;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Kendaraan extends AppCompatActivity {
    private LinearLayout germor, pickupbox, dumptruck, pickupstandar;
    private TextView kapasitasKendaraan, merkKendaraan,namakendaraan;
    private Context context;
    private ImageView gambarKendaraan;
    private String namakend, merkKend, kapasitasKend;
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
                namakendaraan = (TextView)findViewById(R.id.namakendaraan);
                merkKendaraan = (TextView)findViewById(R.id.merkKendaraan);
                kapasitasKendaraan = (TextView)findViewById(R.id.kapasitasKendaraan);
                namakend = namakendaraan.getText().toString();
                merkKend = merkKendaraan.getText().toString();
                kapasitasKend = kapasitasKendaraan.getText().toString();
                Intent intent = new Intent(Kendaraan.this, NextDesewa.class);
                Bundle a = new Bundle();
                a.putString("parse_pesanan",namakend);
                a.putString("parse_merkpesanan", merkKend);
                a.putString("parse_kapasitaspesanan", kapasitasKend);
                intent.putExtras(a);
                startActivity(intent);
            }
        });
        pickupstandar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                namakendaraan = (TextView)findViewById(R.id.namakendaraan2);
                merkKendaraan = (TextView)findViewById(R.id.merkKendaraan2);
                kapasitasKendaraan = (TextView)findViewById(R.id.kapasitasKendaraan2);
                namakend = namakendaraan.getText().toString();
                merkKend = merkKendaraan.getText().toString();
                kapasitasKend = kapasitasKendaraan.getText().toString();
                Intent intent = new Intent(Kendaraan.this, NextDesewa.class);
                Bundle a = new Bundle();
                a.putString("parse_pesanan",namakend);
                a.putString("parse_merkpesanan", merkKend);
                a.putString("parse_kapasitaspesanan", kapasitasKend);
                intent.putExtras(a);
                startActivity(intent);
            }
        });
        pickupbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                namakendaraan = (TextView)findViewById(R.id.namakendaraan4);
                merkKendaraan = (TextView)findViewById(R.id.merkKendaraan4);
                kapasitasKendaraan = (TextView)findViewById(R.id.kapasitasKendaraan4);
                namakend = namakendaraan.getText().toString();
                merkKend = merkKendaraan.getText().toString();
                kapasitasKend = kapasitasKendaraan.getText().toString();
                Intent intent = new Intent(Kendaraan.this, NextDesewa.class);
                Bundle a = new Bundle();
                a.putString("parse_pesanan",namakend);
                a.putString("parse_merkpesanan", merkKend);
                a.putString("parse_kapasitaspesanan", kapasitasKend);
                intent.putExtras(a);
                startActivity(intent);
            }
        });
        dumptruck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                namakendaraan = (TextView)findViewById(R.id.namakendaraan3);
                merkKendaraan = (TextView)findViewById(R.id.merkKendaraan3);
                kapasitasKendaraan = (TextView)findViewById(R.id.kapasitasKendaraan3);
                namakend = namakendaraan.getText().toString();
                merkKend = merkKendaraan.getText().toString();
                kapasitasKend = kapasitasKendaraan.getText().toString();
                Intent intent = new Intent(Kendaraan.this, NextDesewa.class);
                Bundle a = new Bundle();
                a.putString("parse_pesanan",namakend);
                a.putString("parse_merkpesanan", merkKend);
                a.putString("parse_kapasitaspesanan", kapasitasKend);
                intent.putExtras(a);
                startActivity(intent);
            }
        });
    }
}
