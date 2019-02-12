package com.destar.platform.destar;

import android.app.DatePickerDialog;
import android.content.Context;
import android.media.Image;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class NextDesewa extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private DatePickerDialog datePickerDialogKembali;
    private SimpleDateFormat dateFormat;
    private ImageButton kembali, pesan;
    private Button btn_pesan;
    private TextView tglpesan, tglkembali, namabarang, namaMerk, kapasitas;
    private Context context;
    private RelativeLayout relativeLayout;
    private PopupWindow popupWindow;
    private String nama;
    int quantity=0;
    private String namapesanan, merkpesanan, kapasitaspesanan = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_desewa);
        dateFormat = new SimpleDateFormat("dd-MM-yyy", Locale.US);
        tglkembali = (TextView)findViewById(R.id.tglkembali);
        tglpesan = (TextView)findViewById(R.id.tglpesan);
        pesan = (ImageButton)findViewById(R.id.tanggalpesan);
        kembali = (ImageButton)findViewById(R.id.tanggalkembali);
        btn_pesan = (Button)findViewById(R.id.pesan);
        context = getApplicationContext();
        relativeLayout = (RelativeLayout)findViewById(R.id.next_desewa);
        pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialogKembali();
            }
        });
        final RadioGroup tipepesan = (RadioGroup)findViewById(R.id.tipepengambilan);
        namabarang = (TextView)findViewById(R.id.namapesanan);
        namaMerk = (TextView)findViewById(R.id.merk);
        kapasitas = (TextView)findViewById(R.id.kapasitas);
        Bundle extras = getIntent().getExtras();
        namapesanan = extras.getString("parse_pesanan");
        merkpesanan = extras.getString("parse_merkpesanan");
        kapasitaspesanan = extras.getString("parse_kapasitaspesanan");
        namabarang.setText(namapesanan);
        namaMerk.setText(merkpesanan);
        kapasitas.setText(kapasitaspesanan);
        btn_pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = tipepesan.getCheckedRadioButtonId();
                switch (id){
                    case R.id.diantar :
                        LayoutInflater inflater = (LayoutInflater)context.getSystemService(LAYOUT_INFLATER_SERVICE);
                        View customView = inflater.inflate(R.layout.pop_up_diantar, null);
                        popupWindow = new PopupWindow(customView, RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
                        if (Build.VERSION.SDK_INT>=21){
                            popupWindow.setElevation(5.0f);
                        }
                        Button btn_ok = (Button)customView.findViewById(R.id.btn_benar);
                        Button btn_cek = (Button)customView.findViewById(R.id.btn_cek);
                        btn_ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //diganti untuk memesan
                                popupWindow.dismiss();
                            }
                        });
                        btn_cek.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //cek kembali alamat
                                popupWindow.dismiss();
                            }
                        });
                        popupWindow.showAtLocation(relativeLayout, Gravity.CENTER,0,0);
                        break;
                    case R.id.diambil :
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
                tglpesan.setText("Tanggal pesan : " + dateFormat.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
    private void showDateDialogKembali() {

        Calendar newCalendar = Calendar.getInstance();
        datePickerDialogKembali = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                tglkembali.setText("Tanggal kembali : " + dateFormat.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialogKembali.show();
    }

    public void increment(View view) {
        if(quantity==10){
            Toast.makeText(this,"10",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity+1 ;
        display(quantity);
    }

    public void decrement(View view) {
        if (quantity==1){
            Toast.makeText(this,"pesanan minimal 1",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity -1;
        display(quantity);
    }
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.totalHarga);
        priceTextView.setText(message);
    }
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_textview);
        quantityTextView.setText("" + number);
    }
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.totalHarga);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
    private int calculateprice(){//jumlah pesanan * harga
        int harga=5000;


        return quantity * harga;
    }
}
