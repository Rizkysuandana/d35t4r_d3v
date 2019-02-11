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
                Intent intent = new Intent(AlatMedis.this, NextDesewa.class);
                startActivity(intent);
            }
        });
        tabungoksigen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlatMedis.this, NextDesewa.class);
                startActivity(intent);
            }
        });
        ranjangpasien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlatMedis.this, NextDesewa.class);
                startActivity(intent);
            }
        });
        alatcekguladarah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlatMedis.this, NextDesewa.class);
                startActivity(intent);
            }
        });
    }
}
