package com.destar.platform.destar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.destar.platform.destar.response.StartLocation;

public class AlatPertanian extends AppCompatActivity {
    private LinearLayout traktorTangan, Traktor, Gilingpadi, Sekop;
    private TextView alattani, merkalat, alamatalat;
    private String alat, merk, alamat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alat_pertanian);
        traktorTangan = (LinearLayout)findViewById(R.id.traktortangan);
        Traktor = (LinearLayout)findViewById(R.id.traktor);
        Gilingpadi = (LinearLayout)findViewById(R.id.gilingpadi);
        Sekop = (LinearLayout)findViewById(R.id.sekop);
        traktorTangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alattani = (TextView)findViewById(R.id.alat1);
                merkalat = (TextView)findViewById(R.id.merk1);
                alamatalat = (TextView)findViewById(R.id.alamat1);
                alat = alattani.getText().toString();
                merk = merkalat.getText().toString();
                alamat = alamatalat.getText().toString();
                Intent intent = new Intent(AlatPertanian.this, NextDesewa.class);
                Bundle a = new Bundle();
                a.putString("parse_pesanan",alat);
                a.putString("parse_merkpesanan", merk);
                a.putString("parse_kapasitaspesanan", alamat);
                intent.putExtras(a);
                startActivity(intent);
            }
        });
        Traktor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alattani = (TextView)findViewById(R.id.alat2);
                merkalat = (TextView)findViewById(R.id.merk2);
                alamatalat = (TextView)findViewById(R.id.alamat2);
                alat = alattani.getText().toString();
                merk = merkalat.getText().toString();
                alamat = alamatalat.getText().toString();
                Intent intent = new Intent(AlatPertanian.this, NextDesewa.class);
                Bundle a = new Bundle();
                a.putString("parse_pesanan",alat);
                a.putString("parse_merkpesanan", merk);
                a.putString("parse_kapasitaspesanan", alamat);
                intent.putExtras(a);
                startActivity(intent);
            }
        });
        Gilingpadi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alattani = (TextView)findViewById(R.id.alat3);
                merkalat = (TextView)findViewById(R.id.merk3);
                alamatalat = (TextView)findViewById(R.id.alamat3);
                alat = alattani.getText().toString();
                merk = merkalat.getText().toString();
                alamat = alamatalat.getText().toString();
                Intent intent = new Intent(AlatPertanian.this, NextDesewa.class);
                Bundle a = new Bundle();
                a.putString("parse_pesanan",alat);
                a.putString("parse_merkpesanan", merk);
                a.putString("parse_kapasitaspesanan", alamat);
                intent.putExtras(a);
                startActivity(intent);
            }
        });
        Sekop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alattani = (TextView)findViewById(R.id.alat4);
                merkalat = (TextView)findViewById(R.id.merk4);
                alamatalat = (TextView)findViewById(R.id.alamat4);
                alat = alattani.getText().toString();
                merk = merkalat.getText().toString();
                alamat = alamatalat.getText().toString();
                Intent intent = new Intent(AlatPertanian.this, NextDesewa.class);
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
