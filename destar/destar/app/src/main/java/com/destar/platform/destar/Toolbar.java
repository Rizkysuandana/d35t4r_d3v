package com.destar.platform.destar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

public class Toolbar extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_toolbar);
       LinearLayout beranda1 = (LinearLayout) findViewById(R.id.beranda);
       LinearLayout pesanan1 = (LinearLayout)findViewById(R.id.pesanan);
       LinearLayout pemantauan1 = (LinearLayout)findViewById(R.id.pemantauan);
       LinearLayout riwayat1 = (LinearLayout)findViewById(R.id.riwayat);
      LinearLayout akun1 = (LinearLayout)findViewById(R.id.akun);

       beranda1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(Toolbar.this, Dashboard.class);
              startActivity(intent);
          }
       });
       pesanan1.setOnClickListener(new View.OnClickListener() {
          @Override
           public void onClick(View v) {
               Intent i = new Intent(Toolbar.this, PesananActivity.class);
               startActivity(i);
           }
       });
        pemantauan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Toolbar.this, PemantauanActivity.class);
               startActivity(i);
            }
        });
        riwayat1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i = new Intent(Toolbar.this, RiwayatActivity.class);
               startActivity(i);
            }
        });
       akun1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Toolbar.this, AkunActivity.class);
                startActivity(i);
            }
       });
   }

}
