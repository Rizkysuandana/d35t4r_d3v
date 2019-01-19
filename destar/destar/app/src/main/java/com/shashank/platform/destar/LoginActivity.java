package com.shashank.platform.destar;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.shashank.platform.destar.app.AppController;
import com.shashank.platform.destar.utils.MasifaController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import fr.arnaudguyon.xmltojsonlib.XmlToJson;

public class LoginActivity extends AppCompatActivity {

    ProgressDialog pDialog;
    Button btn_login;
    EditText txt_username, txt_password;
    Intent intent;

    int success;
    ConnectivityManager conMgr;
    private String url = ServerActivity.URL + "CustomersLogin";
    private static final String TAG = LoginActivity.class.getSimpleName();
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    public final static String TAG_USERNAME = "username";
    String tag_json_obj = "json_obj_req";
    SharedPreferences sharedpreferences;
    Boolean session = false;
    String id, username;
    public static final String my_shared_preferences = "my_shared_preferences";
    public static final String session_status = "session_status";
    MasifaController msfC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        msfC = new MasifaController();
        btn_login = (Button) findViewById(R.id.btn_login);
        txt_username = (EditText) findViewById(R.id.txt_username);
        txt_password = (EditText) findViewById(R.id.txt_password);
        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        session = sharedpreferences.getBoolean(session_status, false);
        username = sharedpreferences.getString(TAG_USERNAME, null);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {

                xLogin();
           }
        });


    }
    private void checkLogin(final String username, final String password) {
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Logging in ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Login Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Check for error node in json
                    if (success == 1) {
                        String username = jObj.getString(TAG_USERNAME);


                        Log.e("Successfully Login!", jObj.toString());

                        Toast.makeText(getApplicationContext(), jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                        // menyimpan login ke session
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putBoolean(session_status, true);

                        editor.putString(TAG_USERNAME, username);
                        editor.commit();

                        // Memanggil main activity
                        Intent intent = new Intent(LoginActivity.this, Dashboard.class);

                        intent.putExtra(TAG_USERNAME, username);
                        finish();
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();

                hideDialog();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username);
                params.put("password", password);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }
    private void xLogin(){
        class AsyncTaskUploadClass extends AsyncTask<Void,Void,String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                pDialog = ProgressDialog.show(LoginActivity.this,"Login","Please Wait",false,false);
            }
            @Override
            protected String doInBackground(Void... params) {
                String username = txt_username.getText().toString();
                String password = txt_password.getText().toString();
                String pIdHardware = ((AppClass) LoginActivity.this.getApplication()).Getandroidid();
                HashMap<String,String> HashMapParams = new HashMap<String,String>();
                HashMapParams.put("strUserName",username);
                HashMapParams.put("strPwd", password);
                HashMapParams.put("strHardwareID",pIdHardware);

                String FinalData = msfC.HttpPost(url, HashMapParams);
                return FinalData;

            }
            @Override
            protected void onPostExecute(String string1) {
                super.onPostExecute(string1);
                pDialog.dismiss();

                if (TextUtils.isEmpty(string1)) {
                    Toast.makeText(LoginActivity.this,"Koneksi ke Server GAGAL \nHubungi Server Administration",Toast.LENGTH_LONG).show();
                    return; // or break, continue, throw
                }
                else {
                    try {
                        Hasil(string1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e( "Error->", String.valueOf( e ) );
                        Toast.makeText(LoginActivity.this, "User dan Password yang Anda Masukkan Salah", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
        AsyncTaskUploadClass AsyncTaskUploadClassOBJ = new AsyncTaskUploadClass();
        AsyncTaskUploadClassOBJ.execute();
    }
    public void Hasil(String pParam) throws JSONException {
        String xUSERNAME = null;
        Boolean xACTIVE= null;
        Integer xCUSTOMERTYPEID= null;
        String xCUSTOMERTYPENAME= null;
        Log.e("Hasil",pParam);
        String pxJson = pParam.replace( "<?xml version=\"1.0\" encoding=\"utf-8\"?>","" );
        pxJson = pxJson.replace( "<string xmlns=\"http://tempuri.org/\">[","" );
        pxJson = pxJson.replace( "]</string>","" );
        JSONObject jsonObj = new JSONObject(pxJson);
        xUSERNAME= jsonObj.getString("USERNAME");
        xACTIVE= jsonObj.getBoolean("ACTIVE");
        xCUSTOMERTYPEID= jsonObj.getInt("CUSTOMERTYPEID");
        xCUSTOMERTYPENAME= jsonObj.getString("CUSTOMERTYPENAME");
        ((AppClass) LoginActivity.this.getApplication()).setUSERNAME(xUSERNAME);
        ((AppClass) LoginActivity.this.getApplication()).setCUSTOMERTYPEID( String.valueOf( xCUSTOMERTYPEID ) );
        ((AppClass) LoginActivity.this.getApplication()).setCUSTOMERTYPENAME(xCUSTOMERTYPENAME);
        if(xACTIVE){
            Toast.makeText(LoginActivity.this, "Selamat Datang "+xUSERNAME + "\n" + "Type " + xCUSTOMERTYPENAME, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(LoginActivity.this, Dashboard.class);
            startActivity(intent);
            this.finish();
        }
    }
    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }
    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
    private void Login(){
        final String username = txt_username.getText().toString().trim();
        String password = txt_password.getText().toString().trim();
        if (username.equals("a")  && password.equals("a")) {
//                    if (conMgr.getActiveNetworkInfo() != null
//                            && conMgr.getActiveNetworkInfo().isAvailable()
////                            && conMgr.getActiveNetworkInfo().isConnected()) {
//                        checkLogin(username, password);
//                    } else {
            Toast.makeText(LoginActivity.this, "No Internet Connection", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), Dashboard.class);
            startActivity(intent);
//                    }
        } else {
            // Prompt user to enter credentials
            Toast.makeText(getApplicationContext() ,"Kolom tidak boleh kosong", Toast.LENGTH_LONG).show();
        }
    }
}