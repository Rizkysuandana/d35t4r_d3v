package com.destar.platform.destar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class LanjutanKendaraan extends AppCompatActivity{
    int[] image = {R.drawable.gerobak_motor, R.drawable.pickup, R.drawable.pickup_box, R.drawable.engkel_bak};
    private ImageView kendaraan;
    private TextView beratKendaraan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lanjutan_kendaraan);
        beratKendaraan = (TextView)findViewById(R.id.beratKendaraan);
    }
}
