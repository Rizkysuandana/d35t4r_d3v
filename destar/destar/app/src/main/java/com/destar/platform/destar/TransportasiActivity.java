package com.destar.platform.destar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;

import com.shashank.platform.destar.R;

public class TransportasiActivity extends AppCompatActivity {
    private CardView pengantarBarang;
    private CardView jasaAngkut;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transportasi);
        pengantarBarang = (CardView)findViewById(R.id.btnpengantaran);
        pengantarBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TransportasiActivity.this, PengantarBarangActivity.class );
                startActivity(intent);
            }
        });
        jasaAngkut = (CardView)findViewById(R.id.btnpengangkutan);
        jasaAngkut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TransportasiActivity.this, JasaAngkutActivity.class);
            }
        });
        ToolBarEvent();
    }
    private void ToolBarEvent(){
        LinearLayout beranda1 = (LinearLayout) findViewById(R.id.beranda);
        LinearLayout pesanan1 = (LinearLayout)findViewById(R.id.pesanan);
        LinearLayout riwayat1 = (LinearLayout)findViewById(R.id.riwayat);
        LinearLayout akun1 = (LinearLayout)findViewById(R.id.akun);
        LinearLayout btnmaps= (LinearLayout)findViewById(R.id.btnmaps);
        beranda1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TransportasiActivity.this, Dashboard.class);
                startActivity(intent);TransportasiActivity.this.finish();
            }
        });
        pesanan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TransportasiActivity.this, PesananActivity.class);
                startActivity(i);TransportasiActivity.this.finish();
            }
        });
        riwayat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TransportasiActivity.this, RiwayatActivity.class);
                startActivity(i);TransportasiActivity.this.finish();
            }
        });
        akun1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TransportasiActivity.this, AkunActivity.class);
                startActivity(i);TransportasiActivity.this.finish();
            }
        });
        btnmaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TransportasiActivity.this, MapsActivity.class);
                startActivity(i);
                TransportasiActivity.this.finish();
            }
        });
    }
}
