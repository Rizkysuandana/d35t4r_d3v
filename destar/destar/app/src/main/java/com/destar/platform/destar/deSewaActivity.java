package com.destar.platform.destar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class deSewaActivity extends AppCompatActivity {
    int[] sampleImages = {R.drawable.image_promo1, R.drawable.image_promo2, R.drawable.image_promo3, R.drawable.image_promo4};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.desewa_activity);
    }
}
