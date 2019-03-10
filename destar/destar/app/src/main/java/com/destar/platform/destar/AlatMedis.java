package com.destar.platform.destar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AlatMedis extends AppCompatActivity {
    private LinearLayout kursiroda, tabungoksigen, ranjangpasien, alatcekguladarah;
    private TextView alatmedis, merkalat, alamatalat;
    private String alat, merk, alamat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alat_medis);
        kursiroda = (LinearLayout)findViewById(R.id.kursiroda);
        tabungoksigen = (LinearLayout)findViewById(R.id.tabung);
        ranjangpasien = (LinearLayout)findViewById(R.id.ranjangpasien);
        alatcekguladarah = (LinearLayout) findViewById(R.id.alatcekguladarah);
        kursiroda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alatmedis = (TextView)findViewById(R.id.namamedis1);
                merkalat = (TextView)findViewById(R.id.merkmedis1);
                alamatalat = (TextView)findViewById(R.id.alamatmedis1);
                alat = alatmedis.getText().toString();
                merk = merkalat.getText().toString();
                alamat = alamatalat.getText().toString();
                Intent intent = new Intent(AlatMedis.this, NextDesewa.class);
                Bundle a = new Bundle();
                a.putString("parse_pesanan",alat);
                a.putString("parse_merkpesanan", merk);
                a.putString("parse_kapasitaspesanan", alamat);
                intent.putExtras(a);
                startActivity(intent);
            }
        });
        tabungoksigen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alatmedis = (TextView)findViewById(R.id.namamedis2);
                merkalat = (TextView)findViewById(R.id.merkmedis2);
                alamatalat = (TextView)findViewById(R.id.alamatmedis2);
                alat = alatmedis.getText().toString();
                merk = merkalat.getText().toString();
                alamat = alamatalat.getText().toString();
                Intent intent = new Intent(AlatMedis.this, NextDesewa.class);
                Bundle a = new Bundle();
                a.putString("parse_pesanan",alat);
                a.putString("parse_merkpesanan", merk);
                a.putString("parse_kapasitaspesanan", alamat);
                intent.putExtras(a);
                startActivity(intent);
            }
        });
        ranjangpasien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alatmedis = (TextView)findViewById(R.id.namamedis3);
                merkalat = (TextView)findViewById(R.id.merkmedis3);
                alamatalat = (TextView)findViewById(R.id.alamatmedis3);
                alat = alatmedis.getText().toString();
                merk = merkalat.getText().toString();
                alamat = alamatalat.getText().toString();
                Intent intent = new Intent(AlatMedis.this, NextDesewa.class);
                Bundle a = new Bundle();
                a.putString("parse_pesanan",alat);
                a.putString("parse_merkpesanan", merk);
                a.putString("parse_kapasitaspesanan", alamat);
                intent.putExtras(a);
                startActivity(intent);
            }
        });
        alatcekguladarah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alatmedis = (TextView)findViewById(R.id.namamedis4);
                merkalat = (TextView)findViewById(R.id.merkmedis4);
                alamatalat = (TextView)findViewById(R.id.alamatmedis4);
                alat = alatmedis.getText().toString();
                merk = merkalat.getText().toString();
                alamat = alamatalat.getText().toString();
                Intent intent = new Intent(AlatMedis.this, NextDesewa.class);
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
