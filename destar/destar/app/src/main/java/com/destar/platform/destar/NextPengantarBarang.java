package com.destar.platform.destar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.TextView;

import com.destar.platform.destar.R;

public class NextPengantarBarang extends AppCompatActivity {
    private TextView nambar, berbar;
    private String namabarang, beratbarang;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_pengantar_barang);
        nambar = (TextView)findViewById(R.id.nambar);
        berbar = (TextView)findViewById(R.id.berbar);
        Bundle a = getIntent().getExtras();
        namabarang = a.getString("parse_namabarang");
        beratbarang = a.getString("parse_beratbarang");
        nambar.setText(namabarang);
        berbar.setText(beratbarang);

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                Intent intent = new Intent(this, Dashboard.class);
                startActivity(intent);
                this.finish();
            }
            return super.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }
}
