package com.destar.platform.destar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.destar.platform.destar.R;

public class AkunActivity extends AppCompatActivity {
    LinearLayout blogout;
    String appNamaUSer,appTypeUSer;
    TextView tUser,tType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.akun);
        blogout = (LinearLayout) findViewById(R.id.blogout);
        tUser = (TextView) findViewById(R.id.txtakunnama);
        tType = (TextView) findViewById(R.id.txtakuntype);
        appNamaUSer = ((AppClass) AkunActivity.this.getApplication()).getUSERNAME();
        appTypeUSer = ((AppClass) AkunActivity.this.getApplication()).getCUSTOMERTYPENAME();
        tUser.setText( appNamaUSer );
        tType.setText( appTypeUSer );
        KlikEvent();
        ToolBarEvent();
    }
    private void KlikEvent(){
        blogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertbox = new AlertDialog.Builder(AkunActivity.this);
                alertbox.setTitle("Apakah Anda Ingin Logout ???");
                alertbox.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        ((AppClass) AkunActivity.this.getApplication()).LogoutBro();
                        Intent intent = new Intent(AkunActivity.this, LoginActivity.class);
                        startActivity(intent);
                        AkunActivity.this.finish();
                    }
                });
                alertbox.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });
                alertbox.show();
            }
        });
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
                Intent intent = new Intent(AkunActivity.this, Dashboard.class);
                startActivity(intent);AkunActivity.this.finish();
                AkunActivity.this.finish();
            }
        });
        pesanan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AkunActivity.this, PesananActivity.class);
                startActivity(i);AkunActivity.this.finish();
                AkunActivity.this.finish();
            }
        });
        riwayat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AkunActivity.this, RiwayatActivity.class);
                startActivity(i);AkunActivity.this.finish();
                AkunActivity.this.finish();
            }
        });
        akun1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AkunActivity.this, AkunActivity.class);
                startActivity(i);AkunActivity.this.finish();
                AkunActivity.this.finish();
            }
        });
        btnmaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AkunActivity.this, MapsActivity.class);
                startActivity(i);
                AkunActivity.this.finish();
            }
        });
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
