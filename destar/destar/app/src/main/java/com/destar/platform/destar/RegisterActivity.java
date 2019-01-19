package com.destar.platform.destar;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.destar.platform.destar.utils.MasifaController;
import com.destar.platform.destar.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    private ProgressDialog pDialog;
    MasifaController msfC;
    private String pServer =ServerActivity.URL + "CustomerRegister";
    ImageView imgView;
    Button bSimpan;
    private Bitmap imageBitmap;ImageView ImageViewHolder,imgbutton;
    String  pUsername , pFullName, pEmail,pPwd,pPwd2,pMobileNumber,pCustomerType,pImage,pHrdwareID;
    EditText xUsername,xFullName,xEmail,xPwd,xPwd2,xMobileNumber;
    private int GALLERY = 1, CAMERA = 2;
    private static final String IMAGE_DIRECTORY = "/DeStar";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        msfC = new MasifaController();
        bSimpan = (Button) findViewById(R.id.btn_daftar);
        xUsername =  (EditText) findViewById(R.id.nama_pengguna);
        xFullName =  (EditText) findViewById(R.id.nama_lengkap);
        xEmail =  (EditText) findViewById(R.id.daftar_email);
        xPwd =  (EditText) findViewById(R.id.daftar_sandi);
        xPwd2 =  (EditText) findViewById(R.id.daftar_ulangSandi);
        xMobileNumber =  (EditText) findViewById(R.id.daftar_nomortelp);
        ImageViewHolder = (ImageView) findViewById(R.id.imgfoto);
        imgbutton = (ImageView) findViewById(R.id.unggah);
        imgbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showPictureDialog();
            }
        });
        pHrdwareID =((AppClass) RegisterActivity.this.getApplication()).Getandroidid();
        bSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pUsername = xUsername.getText().toString();
                pFullName = xFullName.getText().toString();
                pEmail = xEmail.getText().toString();
                pPwd = xPwd.getText().toString();
                pPwd2 = xPwd2.getText().toString();
                pImage="-";
                pCustomerType="1";
                pMobileNumber = xMobileNumber.getText().toString();
                if(pPwd.equals( pPwd2 )) {

                    Simpan();
                }
                else{
                    Toast.makeText(RegisterActivity.this,"Password Tidak Sama",Toast.LENGTH_LONG).show();
                    xPwd.setText( "" );
                    xPwd2.setText( "" );
                    xPwd.requestFocus();
                }
            }
        });
    }
    private void showPictureDialog(){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }
    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }
    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    String path = saveImage(bitmap);
                    Toast.makeText(RegisterActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    ImageViewHolder.setImageBitmap(bitmap);
                    imageBitmap = BitmapFactory.decodeFile(path);

                }
                catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(RegisterActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        }
        else if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            imageBitmap = thumbnail;
            ImageViewHolder.setImageBitmap(thumbnail);
            saveImage(thumbnail);
            Toast.makeText(RegisterActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
        }
    }
    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("TAG", "File Saved::---&gt;" + f.getAbsolutePath());

            return f.getAbsolutePath();
        }
        catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }
    public void Simpan(){
        class AsyncTaskUploadClass extends AsyncTask<Void,Void,String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                pDialog = ProgressDialog.show(RegisterActivity.this,"Pendaftaran","Please Wait",false,false);
            }
            @Override
            protected String doInBackground(Void... params) {
                ByteArrayOutputStream byteArrayOutputStreamObject ;
                byteArrayOutputStreamObject = new ByteArrayOutputStream();
                imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStreamObject);
                byte[] byteArrayVar = byteArrayOutputStreamObject.toByteArray();
                final String ConvertImage = Base64.encodeToString(byteArrayVar, Base64.DEFAULT);

                HashMap<String,String> HashMapParams = new HashMap<String,String>();
                HashMapParams.put("strUserName",pUsername);
                HashMapParams.put("strFullName", pFullName);
                HashMapParams.put("strEmail",pEmail);
                HashMapParams.put("strPwd", pPwd);
                HashMapParams.put("strMobileNumber", pMobileNumber);
                HashMapParams.put("strCustomerType", pCustomerType);
                HashMapParams.put("strHrdwareID", "-");
                HashMapParams.put("strImage", ConvertImage);

                String FinalData = msfC.HttpPost(pServer, HashMapParams);
                return FinalData;
            }
            @Override
            protected void onPostExecute(String string1) {
                super.onPostExecute(string1);
                pDialog.dismiss();

                if (TextUtils.isEmpty(string1)) {
                    Toast.makeText(RegisterActivity.this,"Registrasi GAGAL \nSilahkan Cek Kembali Data Anda",Toast.LENGTH_LONG).show();
                    return; // or break, continue, throw
                }
                else {
                    Hasil(string1);
                }
            }
        }
        AsyncTaskUploadClass AsyncTaskUploadClassOBJ = new AsyncTaskUploadClass();
        AsyncTaskUploadClassOBJ.execute();
    }
    public void Hasil(String pParam) {
        String pxJson = pParam.replace( "<?xml version=\"1.0\" encoding=\"utf-8\"?>","" );
        pxJson = pxJson.replace( "<string xmlns=\"http://tempuri.org/\">[","" );
        pxJson = pxJson.replace( "]</string>","" );
        if(pxJson.equals( "-1" )) {
            Toast.makeText(RegisterActivity.this,"Data Gagal Disimpan",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(RegisterActivity.this,"Data Telah Disimpan",Toast.LENGTH_LONG).show();
            xUsername.setText( "" );
            xFullName.setText( "" );
            xEmail.setText( "" );
            xPwd.setText( "" );
            xPwd2.setText( "" );
            xMobileNumber.setText( "" );
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            this.finish();
        }
        //==============================
    }
}
