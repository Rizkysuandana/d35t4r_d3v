package com.destar.platform.destar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class TampilanAwalActivity extends AppCompatActivity {
    private Button btn_loginAwal, btn_registerAwal,google_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampilan_awal);
        btn_loginAwal = (Button)findViewById(R.id.login);
        btn_registerAwal = (Button)findViewById(R.id.register);
        google_btn = (Button)findViewById(R.id.google_btn);
        btn_loginAwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TampilanAwalActivity.this, LoginActivity.class);
                startActivity(intent);
                TampilanAwalActivity.this.finish();
            }
        });
        btn_registerAwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TampilanAwalActivity.this, RegisterActivity.class);
                startActivity(intent);
                TampilanAwalActivity.this.finish();
            }
        });
        google_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TampilanAwalActivity.this, Dashboard.class);
                startActivity(intent);
                TampilanAwalActivity.this.finish();
            }
        });
    }
}
