package com.destar.platform.destar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class PengantarBarangActivity extends AppCompatActivity {
    private Button antarBarangNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengantar_barang);

        antarBarangNext = (Button)findViewById(R.id.next_pengantarBarang);
        antarBarangNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PengantarBarangActivity.this, NextPengantarBarang.class);
                startActivity(intent);
            }
        });
        LinearLayout map_display = (LinearLayout)findViewById(R.id.display_map);
        map_display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PengantarBarangActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });
        LinearLayout beranda2 = (LinearLayout) findViewById(R.id.beranda);
        LinearLayout pesanan2 = (LinearLayout)findViewById(R.id.pesanan);
        LinearLayout riwayat2 = (LinearLayout)findViewById(R.id.riwayat);
        LinearLayout akun2 = (LinearLayout)findViewById(R.id.akun);

        beranda2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PengantarBarangActivity.this, Dashboard.class);
                startActivity(intent);
            }
        });
        pesanan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PengantarBarangActivity.this, PesananActivity.class);
                startActivity(i);
            }
        });
        riwayat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PengantarBarangActivity.this, RiwayatActivity.class);
                startActivity(i);
            }
        });
        akun2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PengantarBarangActivity.this, AkunActivity.class);
                startActivity(i);
            }
        });
    }

}
