package com.destar.platform.destar;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.akexorcist.googledirection.model.Line;
import com.destar.platform.destar.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class NextPengantarBarang extends AppCompatActivity {
    private TextView nambar, berbar, tanggalinput, tvpickup, tvdest;
    private String namabarang, beratbarang, tvpickupfrom, tvdestloc;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormat;
    private Context context;
    private LinearLayout linearLayout;
    private PopupWindow popupWindow;
    private Button pesan;
    private RadioButton sekarang, nanti;
    private static final long COUNTDOWN_IN_MILLIES = 60000;
    private ColorStateList textColorDefaultCd;
    private long timeLeftInMillis;
    private ProgressBar progressBarCircle;
    private TextView textViewTime;
    private CountDownTimer countDownTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_pengantar_barang);
        tvpickup = (TextView)findViewById(R.id.lokasijemput);
        tvdest = (TextView)findViewById(R.id.lokasitujuan);
        nambar = (TextView)findViewById(R.id.nambar);
        berbar = (TextView)findViewById(R.id.berbar);
        Bundle a = getIntent().getExtras();
        linearLayout = (LinearLayout)findViewById(R.id.next_pengantarbarang);
        namabarang = a.getString("parse_namabarang");
        beratbarang = a.getString("parse_beratbarang");
        tvpickupfrom = a.getString("parse_pickupfrom");
        tvdestloc = a.getString("parse_destlok");
        context = getApplicationContext();
        nambar.setText(namabarang);
        berbar.setText(beratbarang);
        tvpickup.setText(tvpickupfrom);
        tvdest.setText(tvdestloc);
        pesan = (Button)findViewById(R.id.btntanggal);

        pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });
        final RadioGroup pesankapan = (RadioGroup)findViewById(R.id.pesankapan);
        tanggalinput = (TextView)findViewById(R.id.tanggalinput);
        Button button = (Button)findViewById(R.id.pesannext);
        final LinearLayout inputtanggal = (LinearLayout)findViewById(R.id.inputtanggal);
        inputtanggal.setVisibility(View.GONE);
        sekarang = (RadioButton)findViewById(R.id.sekarang);
        sekarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputtanggal.setVisibility(View.GONE);
            }
        });
        nanti = (RadioButton)findViewById(R.id.nanti);
        nanti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputtanggal.setVisibility(View.VISIBLE);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = pesankapan.getCheckedRadioButtonId();
                LinearLayout inputtanggal = (LinearLayout)findViewById(R.id.inputtanggal);
                switch (id){
                    case R.id.sekarang :
                        LayoutInflater inflater = (LayoutInflater)context.getSystemService(LAYOUT_INFLATER_SERVICE);
                        View customView = inflater.inflate(R.layout.pop_up, null);
                        progressBarCircle = (ProgressBar)customView.findViewById(R.id.progressBarCircle);
                        textViewTime = (TextView)customView.findViewById(R.id.textViewTime);
                        textColorDefaultCd = textViewTime.getTextColors();
                        timeLeftInMillis = COUNTDOWN_IN_MILLIES;
                        startCountDownTimer();
                        popupWindow = new PopupWindow(customView, RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
                        if (Build.VERSION.SDK_INT>=21){
                            popupWindow.setElevation(5.0f);
                        }
                        popupWindow.showAtLocation(linearLayout, Gravity.CENTER,0,0);
                        break;
                    case R.id.nanti :
                                Intent intent = new Intent(NextPengantarBarang.this, Dashboard.class);
                        break;
                }
            }
        });

    }
    private void showDateDialog() {
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                tanggalinput.setText("Tanggal pesan : " + dateFormat.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
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
                finish();
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
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(countDownTimer !=null){
            countDownTimer.cancel();

        }
    }
}
