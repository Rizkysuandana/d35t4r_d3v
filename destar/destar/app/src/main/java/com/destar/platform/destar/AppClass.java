package com.destar.platform.destar;

import android.app.Application;
import android.os.AsyncTask;
import android.text.TextUtils;

import java.util.HashMap;

import com.destar.platform.destar.utils.MasifaController;

public class AppClass extends Application {
    private String pFBase;
    private String pUser,pHakAkses;
    private String pLong,pLat,pNamaUser;
    private String android_id ;String pActif;
    String pServer = "http://server.masifa.xyz/vcb/app/android/InputLokasi";
    String pServerLogout ="http://server.masifa.xyz/vcb/app/android/Logout" ;
    boolean check = true;
    public String getFBase() { return pFBase; }
    MasifaController msfC ;
    public void SetFBase(String pIsi) {
        this.pFBase = pIsi;
    }
    public void SetUser(String pIsi){ this.pUser=pIsi;}
    public String getusern() {
        return pUser;
    }
    public void SetHakAkses(String pIsi){ this.pHakAkses=pIsi;}
    public String getuHakAkses() {
        return pHakAkses;
    }
    public void Setandroidid(String pIsi){ this.android_id=pIsi;}
    public String Getandroidid() {
        return android_id;
    }


    private String xUSERNAME,xCUSTOMERTYPENAME,xCUSTOMERTYPEID;
    public void setUSERNAME(String pIsi){ this.xUSERNAME=pIsi;}
    public void setCUSTOMERTYPENAME(String pIsi){ this.xCUSTOMERTYPENAME=pIsi;}
    public void setCUSTOMERTYPEID(String pIsi){ this.xCUSTOMERTYPEID=pIsi;}
    public String getUSERNAME() {
        return xUSERNAME;
    }
    public String getCUSTOMERTYPENAME() {
        return xCUSTOMERTYPENAME;
    }
    public String getCUSTOMERTYPEID() {
        return xCUSTOMERTYPEID;
    }


    public void DapatLokasi(String xlat, String xlong, String xUser){
        this.pLong=xlong;
        this.pLat=xlat;
        this.pNamaUser=xUser;
        //SimpanLokasi();
    }
    public void SimpanLokasi(){
        msfC = new MasifaController();
        class AsyncTaskUploadClass extends AsyncTask<Void,Void,String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }
            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> HashMapParams = new HashMap<String,String>();
                HashMapParams.put("pLong", pLong);
                HashMapParams.put("pLat", pLat);
                HashMapParams.put("pNamaUser", pNamaUser);
                String FinalData = msfC.HttpRequest(pServer, HashMapParams);
                return FinalData;

            }
            @Override
            protected void onPostExecute(String string1) {
                super.onPostExecute(string1);
               if (TextUtils.isEmpty(string1)) {
                    return;
                }
            }
        }
        AsyncTaskUploadClass AsyncTaskUploadClassOBJ = new AsyncTaskUploadClass();
        AsyncTaskUploadClassOBJ.execute();
    }
    public void LogoutBro( ){
        msfC = new MasifaController();
        class AsyncTaskUploadClass extends AsyncTask<Void,Void,String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }
            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> HashMapParams = new HashMap<String,String>();
                HashMapParams.put("hardwareid", android_id);
                String FinalData = msfC.HttpRequest(pServerLogout, HashMapParams);
                return FinalData;

            }
            @Override
            protected void onPostExecute(String string1) {
                super.onPostExecute(string1);

                if (TextUtils.isEmpty(string1)) {
                    return;
                }
                else {

                }
            }
        }
        AsyncTaskUploadClass AsyncTaskUploadClassOBJ = new AsyncTaskUploadClass();
        AsyncTaskUploadClassOBJ.execute();
    }
}
