package com.destar.platform.destar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class deSewaLainnya extends AppCompatActivity {
    private LinearLayout bor, buku, carrier, kamera;
    private TextView alatlain, merkalat, alamatalat;
    private String alat, merk, alamat;
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
                alatlain = (TextView)findViewById(R.id.alat1);
                merkalat = (TextView)findViewById(R.id.merk1);
                alamatalat = (TextView)findViewById(R.id.alamat1);
                alat = alatlain.getText().toString();
                merk = merkalat.getText().toString();
                alamat = alamatalat.getText().toString();
                Intent intent = new Intent(deSewaLainnya.this, NextDesewa.class);
                Bundle a = new Bundle();
                a.putString("parse_pesanan",alat);
                a.putString("parse_merkpesanan", merk);
                a.putString("parse_kapasitaspesanan", alamat);
                intent.putExtras(a);
                startActivity(intent);
            }
        });
        buku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alatlain = (TextView)findViewById(R.id.alat2);
                merkalat = (TextView)findViewById(R.id.merk2);
                alamatalat = (TextView)findViewById(R.id.alamat2);
                alat = alatlain.getText().toString();
                merk = merkalat.getText().toString();
                alamat = alamatalat.getText().toString();
                Intent intent = new Intent(deSewaLainnya.this, NextDesewa.class);
                Bundle a = new Bundle();
                a.putString("parse_pesanan",alat);
                a.putString("parse_merkpesanan", merk);
                a.putString("parse_kapasitaspesanan", alamat);
                intent.putExtras(a);
                startActivity(intent);
            }
        });
        carrier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alatlain = (TextView)findViewById(R.id.alat3);
                merkalat = (TextView)findViewById(R.id.merk3);
                alamatalat = (TextView)findViewById(R.id.alamat3);
                alat = alatlain.getText().toString();
                merk = merkalat.getText().toString();
                alamat = alamatalat.getText().toString();
                Intent intent = new Intent(deSewaLainnya.this, NextDesewa.class);
                Bundle a = new Bundle();
                a.putString("parse_pesanan",alat);
                a.putString("parse_merkpesanan", merk);
                a.putString("parse_kapasitaspesanan", alamat);
                intent.putExtras(a);
                startActivity(intent);
            }
        });
        kamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alatlain = (TextView)findViewById(R.id.alat4);
                merkalat = (TextView)findViewById(R.id.merk4);
                alamatalat = (TextView)findViewById(R.id.alamat4);
                alat = alatlain.getText().toString();
                merk = merkalat.getText().toString();
                alamat = alamatalat.getText().toString();
                Intent intent = new Intent(deSewaLainnya.this, NextDesewa.class);
                Bundle a = new Bundle();
                a.putString("parse_pesanan",alat);
                a.putString("parse_merkpesanan", merk);
                a.putString("parse_kapasitaspesanan", alamat);
                intent.putExtras(a);
                startActivity(intent);
            }
        });
    }
}
