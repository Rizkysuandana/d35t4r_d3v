package com.destar.platform.destar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.destar.platform.destar.response.StartLocation;

public class AlatPertanian extends AppCompatActivity {
    private LinearLayout traktorTangan, Traktor, Gilingpadi, Sekop;
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
                Intent intent = new Intent(AlatPertanian.this, PertanianLanjutan.class);
                startActivity(intent);
            }
        });
        Traktor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlatPertanian.this, PertanianLanjutan.class);
                startActivity(intent);
            }
        });
        Gilingpadi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlatPertanian.this, PertanianLanjutan.class);
                startActivity(intent);
            }
        });
        Sekop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlatPertanian.this, PertanianLanjutan.class);
                startActivity(intent);
            }
        });
    }
}
