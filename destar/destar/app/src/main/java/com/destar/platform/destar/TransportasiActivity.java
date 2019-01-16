package com.destar.platform.destar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.shashank.platform.destar.R;

public class TransportasiActivity extends AppCompatActivity {
    private Button pengantarBarang;
    private Button jasaAngkut;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transportasi);
        pengantarBarang = (Button)findViewById(R.id.btn_pengantarBarang);
        pengantarBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TransportasiActivity.this, PengantarBarangActivity.class );
                startActivity(intent);
            }
        });
        jasaAngkut = (Button)findViewById(R.id.jasaAngkut);
        jasaAngkut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TransportasiActivity.this, JasaAngkutActivity.class);
            }
        });
    }

}
