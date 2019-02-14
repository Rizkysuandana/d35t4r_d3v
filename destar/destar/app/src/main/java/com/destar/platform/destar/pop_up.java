package com.destar.platform.destar;


import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Locale;


public class pop_up extends AppCompatActivity {
    private static final long COUNTDOWN_IN_MILLIES = 40000;
    private ColorStateList textColorDefaultCd;
    private long timeLeftInMillis;
    private ProgressBar progressBarCircle;
    private TextView textViewTime;
    private CountDownTimer countDownTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.pop_up);

//        DisplayMetrics dm = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(dm);
//
//        int lebar = dm.widthPixels;
//        int tinggi = dm.heightPixels;
//
//        getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);


        textViewTime = findViewById(R.id.textViewTime);
        progressBarCircle = findViewById(R.id.progressBarCircle);
        textColorDefaultCd = textViewTime.getTextColors();

        timeLeftInMillis = COUNTDOWN_IN_MILLIES;
        startCountDownTimer();

    }

    private void startCountDownTimer() {
        timeLeftInMillis = COUNTDOWN_IN_MILLIES;
        CountDownTimer countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                progressBarCircle.setProgress((int) (millisUntilFinished / 1000));
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateCountDownText();
            }

        }.start();
        countDownTimer.start();
    }


    private void updateCountDownText(){
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        textViewTime.setText(timeFormatted);

        if(timeLeftInMillis < 1000){
            textViewTime.setTextColor(Color.RED);
        }else {
            textViewTime.setTextColor(textColorDefaultCd);
        }
    }

    private void setProgressBarValues() {

        progressBarCircle.setMax((int) timeLeftInMillis / 1000);
        progressBarCircle.setProgress((int) timeLeftInMillis / 1000);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(countDownTimer !=null){
            countDownTimer.cancel();
        }
    }
}