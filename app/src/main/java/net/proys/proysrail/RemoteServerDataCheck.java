package net.proys.proysrail;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import net.proys.proysrail.Entities.BildiriTipListeEntity;
import net.proys.proysrail.Entities.BildirilerEntity;
import net.proys.proysrail.Entities.CalisanListeEntity;
import net.proys.proysrail.Entities.EtkenListeEntity;
import net.proys.proysrail.Entities.ImalatListeEntity;
import net.proys.proysrail.Entities.ImalatMakineEslesmeEntity;
import net.proys.proysrail.Entities.ImalatSektorEslesmeEntity;
import net.proys.proysrail.Entities.KullaniciBildiriEslesmeEntity;
import net.proys.proysrail.Entities.KullaniciImalatEslesmeEntity;
import net.proys.proysrail.Entities.KullanicilarEntity;
import net.proys.proysrail.Entities.MakineKategoriEntity;
import net.proys.proysrail.Entities.MakineListeEntity;
import net.proys.proysrail.Entities.MaliyetDagiticiEntity;
import net.proys.proysrail.Entities.SektorListeEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;

public class RemoteServerDataCheck {

    Context mContext;
    RoomDatabase database;

    public RemoteServerDataCheck(Context mContext) {
        this.mContext = mContext;
         database = RoomDatabase.getDatabase(mContext);
    }

    protected void entegrationDataManagement() {
        all4Kullanicilar();
        all4KullaniciBildiriEslesme();
        all4BildiriTipListe();
        /*try {
            all4MakineListe();
        } catch (Exception e) {
            e.printStackTrace();
        }
        all4MakineKategori();
        all4ImalatMakineEslesme();
        all4ImalatListe();
        all4ImalatSektorEslesme();
        all4MaliyetDagitici();
        all4SektorListe();
        all4EtkenListe();
        all4Bildiriler();
        all4CalisanListe();*/


    }
    // flags for understand if its update or create of an row

    private void all4CalisanListe(){
        if (database.calisanListeDao().readAll().size()>1){
            getCalisanListe(0);//update
        }else{
            getCalisanListe(1);    //sıfırdan create
        }
    }
    private List<JSONObject> getCalisanListe(final int flag) {
        RequestQueue mRequestQueue;
        final List<JSONObject> list = new ArrayList<>();
        StringRequest mStringRequest;
        String url = "http://31.210.91.198/beta/panel/rest/get/calisanliste";
        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(mContext);
        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String[] array = response.substring(1,response.length()-1).split("\\}"+"\\,");
                try {
                   for (int i = 0; i<array.length;i++){
                        String object = array[i]+"\\}";
                        object = object.substring(0,object.length()-2)+object.substring(object.length()-1);
                        JSONObject jsonObject = new JSONObject(object);
                        list.add(jsonObject);
                   }
                    Toast.makeText(mContext, response, Toast.LENGTH_SHORT).show();
                }catch (JSONException err){
                    Log.d("Error", err.toString());
                }
                setCalisanListe(list,flag);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mContext, error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        mRequestQueue.add(mStringRequest);

        return list;
    }
    private void setCalisanListe(List<JSONObject> jsonList,int flag){
        for (int i = 0; i < jsonList.size() ; i++ ){
            CalisanListeEntity entity = new CalisanListeEntity();
            try{

                entity.setCalisan_id(jsonList.get(i).getInt("calisan_id"));
                entity.setIsim(jsonList.get(i).getString("isim"));
                entity.setSoyisim(jsonList.get(i).getString("soyisim"));
                entity.setIsim_tam(jsonList.get(i).getString("isim_tam"));
                entity.setMaviyaka(jsonList.get(i).getBoolean("maviyaka"));
                entity.setDirekt(jsonList.get(i).getBoolean("direkt"));
                entity.setPozisyon(jsonList.get(i).getString("pozisyon"));
                entity.setVt_puantaj(jsonList.get(i).getDouble("vt_puantaj"));
                entity.setUnvan(String.valueOf(jsonList.get(i).getInt("unvan")));
                entity.setDepartman(String.valueOf(jsonList.get(i).getInt("departman")));
                entity.setTaseron(jsonList.get(i).getString("calisan_id"));
                entity.setSorumlu(jsonList.get(i).getString("taseron"));
                entity.setV_imalat(jsonList.get(i).getString("v_imalat"));

                if (flag==0){
                    database.calisanListeDao().update(entity);
                }else if (flag==1){
                    database.calisanListeDao().ekle(entity);
                }
            }catch (JSONException err){
                Toast.makeText(mContext, err.toString()+"yükleyemedim calisan liste", Toast.LENGTH_SHORT).show();

            }

        }
        List<CalisanListeEntity> list = database.calisanListeDao().readAll();
        list.get(0);
    }

    private void all4MakineListe() throws  Exception{
        if (database.makineListeDao().readAll().size()>1){
            getMakineListe(0);//update
        }else{
            getMakineListe(1);    //sıfırdan create
        }
    }
    private List<JSONObject> getMakineListe(final int flag) throws Exception {
        RequestQueue mRequestQueue;
        final List<JSONObject> list = new ArrayList<>();
        StringRequest mStringRequest;
        //String url = "http://31.210.91.198/beta/panel/rest/get/makineliste";
        String url = "http://31.210.91.198/beta/panel/rest/get/makineliste";

        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(mContext);
        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int x =0;
                try {
                    JSONArray jsonObjectResponse=new JSONArray(response);

                    String s= String.valueOf(jsonObjectResponse.get(0));
                    Toast.makeText(mContext, s, Toast.LENGTH_SHORT).show();
                    Log.d("json",s);
                  for (int i=0;i<jsonObjectResponse.length();i++){

                  }

                } catch (JSONException e) {
                    e.printStackTrace();
                }







                /*String[] array = response.substring(1,response.length()-1).split("\\}"+"\\,");
                try {
                    for (int i = 0; i<array.length;i++){
                        String object = array[i]+"\\}";
                        object = object.substring(0,object.length()-2)+object.substring(object.length()-1);
                        JSONObject jsonObject = new JSONObject(object);
                        list.add(jsonObject);
                    }
                    Toast.makeText(mContext, response, Toast.LENGTH_SHORT).show();
                }catch (JSONException err){
                    Log.d("Error", err.toString());
                }*/
                //setMakineListe(list,flag);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mContext, error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        mRequestQueue.add(mStringRequest);

        return list;
    }
    private void setMakineListe(List<JSONObject> jsonList, int flag){
        for (int i = 0; i < jsonList.size() ; i++ ){
            MakineListeEntity entity = new MakineListeEntity();
            try{

                entity.setMakine_id(jsonList.get(i).getInt("makine_id"));
                entity.setMakine_ismi(jsonList.get(i).getString("makine_ismi"));
                entity.setKisa_isim(jsonList.get(i).getString("kisa_isim"));
                entity.setPlaka(jsonList.get(i).getString("plaka"));
                entity.setVt_puantaj(jsonList.get(i).getDouble("vt_puantaj"));
                entity.setTemin(jsonList.get(i).getString("temin"));
                entity.setKategori(String.valueOf(jsonList.get(i).getInt("kategori")));
                entity.setTip(String.valueOf(jsonList.get(i).getInt("tip")));
                entity.setTaseron(String.valueOf(jsonList.get(i).getInt("taseron")));
                entity.setGrup_no(jsonList.get(i).getInt("grup_no"));

                if (flag==0){
                    database.makineListeDao().update(entity);
                }else if (flag==1){
                    database.makineListeDao().ekle(entity);
                }
            }catch (JSONException err){
                Toast.makeText(mContext, err.toString()+"yükleyemedim makine liste", Toast.LENGTH_SHORT).show();

            }

        }
        List<MakineListeEntity> list = database.makineListeDao().readAll();
        list.get(0);
    }

    private void all4MakineKategori(){
        if (database.makineKategoriDao().readAll().size()>1){
            getMakineKategori(0);//update
        }else{
            getMakineKategori(1);    //sıfırdan create
        }
    }
    private List<JSONObject> getMakineKategori(final int flag) {
        RequestQueue mRequestQueue;
        final List<JSONObject> list = new ArrayList<>();
        StringRequest mStringRequest;
        String url = "http://31.210.91.198/beta/panel/rest/get/makinekategori";
        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(mContext);
        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String[] array = response.substring(1,response.length()-1).split("\\}"+"\\,");
                try {
                    for (int i = 0; i<array.length;i++){
                        String object = array[i]+"\\}";
                        object = object.substring(0,object.length()-2)+object.substring(object.length()-1);
                        JSONObject jsonObject = new JSONObject(object);
                        list.add(jsonObject);
                    }
                    Toast.makeText(mContext, response, Toast.LENGTH_SHORT).show();
                }catch (JSONException err){
                    Log.d("Error", err.toString());
                }
                setMakineKategori(list,flag);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mContext, error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        mRequestQueue.add(mStringRequest);

        return list;
    }
    private void setMakineKategori(List<JSONObject> jsonList, int flag){
        for (int i = 0; i < jsonList.size() ; i++ ){
            MakineKategoriEntity entity = new MakineKategoriEntity();
            try{

                entity.setKategori_id(jsonList.get(i).getInt("kategori_id"));
                entity.setKategori_isim(jsonList.get(i).getString("makine_ismi"));
                entity.setRekabet(jsonList.get(i).getBoolean("rekabet"));

                if (flag==0){
                    database.makineKategoriDao().update(entity);
                }else if (flag==1){
                    database.makineKategoriDao().ekle(entity);
                }
            }catch (JSONException err){
                Toast.makeText(mContext, err.toString()+"bilyemedim makine kateori", Toast.LENGTH_SHORT).show();

            }

        }
    }

    private void all4ImalatMakineEslesme(){
        if (database.imalatMakineEslesmeDao().readAll().size()>1){
            getImalatMakineEslesme(0);//update
        }else{
            getImalatMakineEslesme(1);    //sıfırdan create
        }
    }
    private List<JSONObject> getImalatMakineEslesme(final int flag) {
        RequestQueue mRequestQueue;
        final List<JSONObject> list = new ArrayList<>();
        StringRequest mStringRequest;
        String url = "http://31.210.91.198/beta/panel/rest/get/imalatmakineeslesme";
        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(mContext);
        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String[] array = response.substring(1,response.length()-1).split("\\}"+"\\,");
                try {
                    for (int i = 0; i<array.length;i++){
                        String object = array[i]+"\\}";
                        object = object.substring(0,object.length()-2)+object.substring(object.length()-1);
                        JSONObject jsonObject = new JSONObject(object);
                        list.add(jsonObject);
                    }
                    Toast.makeText(mContext, response, Toast.LENGTH_SHORT).show();
                }catch (JSONException err){
                    Log.d("Error", err.toString());
                }
                setImalatMakineEslesme(list,flag);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mContext, error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        mRequestQueue.add(mStringRequest);

        return list;
    }
    private void setImalatMakineEslesme(List<JSONObject> jsonList, int flag){
        for (int i = 0; i < jsonList.size() ; i++ ){
            ImalatMakineEslesmeEntity entity = new ImalatMakineEslesmeEntity();
            try{

                entity.setId(jsonList.get(i).getInt("id"));
                entity.setImalat(jsonList.get(i).getString("imalat"));
                entity.setMakine(jsonList.get(i).getString("makine"));

                if (flag==0){
                    database.imalatMakineEslesmeDao().update(entity);
                }else if (flag==1){
                    database.imalatMakineEslesmeDao().ekle(entity);
                }
            }catch (JSONException err){
                Toast.makeText(mContext, err.toString()+"yükleyemedim imalat makine eslesme", Toast.LENGTH_SHORT).show();

            }

        }
    }

    private void all4ImalatSektorEslesme(){
        if (database.imalatMakineEslesmeDao().readAll().size()>1){
            getImalatSektorEslesme(0);//update
        }else{
            getImalatSektorEslesme(1);    //sıfırdan create
        }
    }
    private List<JSONObject> getImalatSektorEslesme(final int flag) {
        RequestQueue mRequestQueue;
        final List<JSONObject> list = new ArrayList<>();
        StringRequest mStringRequest;
        String url = "http://31.210.91.198/beta/panel/rest/get/imalatsektoreslesme";
        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(mContext);
        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String[] array = response.substring(1,response.length()-1).split("\\}"+"\\,");
                try {
                    for (int i = 0; i<array.length;i++){
                        String object = array[i]+"\\}";
                        object = object.substring(0,object.length()-2)+object.substring(object.length()-1);
                        JSONObject jsonObject = new JSONObject(object);
                        list.add(jsonObject);
                    }
                    Toast.makeText(mContext, response, Toast.LENGTH_SHORT).show();
                }catch (JSONException err){
                    Log.d("Error", err.toString());
                }
                setImalatSektorEslesme(list,flag);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mContext, error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        mRequestQueue.add(mStringRequest);

        return list;
    }
    private void setImalatSektorEslesme(List<JSONObject> jsonList, int flag){
        for (int i = 0; i < jsonList.size() ; i++ ){
            ImalatSektorEslesmeEntity entity = new ImalatSektorEslesmeEntity();
            try{

                entity.setId(jsonList.get(i).getInt("id"));
                entity.setTahmin(jsonList.get(i).getBoolean("tahmin"));
                entity.setImalat(jsonList.get(i).getInt("imalat"));
                entity.setSektor(jsonList.get(i).getInt("sektor"));


                if (flag==0){
                    database.imalatSektorEslesmeDao().update(entity);
                }else if (flag==1){
                    database.imalatSektorEslesmeDao().ekle(entity);
                }
            }catch (JSONException err){
                Toast.makeText(mContext, err.toString()+"yükleyemedim imalat sektor eslesme", Toast.LENGTH_SHORT).show();

            }

        }
    }

    private void all4MaliyetDagitici(){
        if (database.maliyetDagiticiDao().readAll().size()>1){
            getMaliyetDagitici(0);//update
        }else{
            getMaliyetDagitici(1);    //sıfırdan create
        }
    }
    private List<JSONObject> getMaliyetDagitici(final int flag) {
        RequestQueue mRequestQueue;
        final List<JSONObject> list = new ArrayList<>();
        StringRequest mStringRequest;
        String url = "http://31.210.91.198/beta/panel/rest/get/maliyetdagitici";
        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(mContext);
        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String[] array = response.substring(1,response.length()-1).split("\\}"+"\\,");
                try {
                    for (int i = 0; i<array.length;i++){
                        String object = array[i]+"\\}";
                        object = object.substring(0,object.length()-2)+object.substring(object.length()-1);
                        JSONObject jsonObject = new JSONObject(object);
                        list.add(jsonObject);
                    }
                    Toast.makeText(mContext, response, Toast.LENGTH_SHORT).show();
                }catch (JSONException err){
                    Log.d("Error", err.toString());
                }
                setMaliyetDagitici(list,flag);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mContext, error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        mRequestQueue.add(mStringRequest);

        return list;
    }
    private void setMaliyetDagitici(List<JSONObject> jsonList, int flag){
        for (int i = 0; i < jsonList.size() ; i++ ){
            MaliyetDagiticiEntity entity = new MaliyetDagiticiEntity();
            try{

                entity.setId(jsonList.get(i).getInt("id"));
                entity.setDagilan(jsonList.get(i).getInt("dagilan"));
                entity.setOran(jsonList.get(i).getDouble("oran"));
                entity.setToplayan(jsonList.get(i).getInt("toplayan"));


                if (flag==0){
                    database.maliyetDagiticiDao().update(entity);
                }else if (flag==1){
                    database.maliyetDagiticiDao().ekle(entity);
                }
            }catch (JSONException err){
                Toast.makeText(mContext, err.toString()+"yükleyemedim maliyet dagitici", Toast.LENGTH_SHORT).show();

            }

        }
    }

    private void all4SektorListe(){
        if (database.sektorListeDao().readAll().size()>1){
            getSektorListe(0);//update
        }else{
            getSektorListe(1);    //sıfırdan create
        }
    }
    private List<JSONObject> getSektorListe(final int flag) {
        RequestQueue mRequestQueue;
        final List<JSONObject> list = new ArrayList<>();
        StringRequest mStringRequest;
        String url = "http://31.210.91.198/beta/panel/rest/get/sektorliste";
        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(mContext);
        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String[] array = response.substring(1,response.length()-1).split("\\}"+"\\,");
                try {
                    for (int i = 0; i<array.length;i++){
                        String object = array[i]+"\\}";
                        object = object.substring(0,object.length()-2)+object.substring(object.length()-1);
                        JSONObject jsonObject = new JSONObject(object);
                        list.add(jsonObject);
                    }
                    Toast.makeText(mContext, response, Toast.LENGTH_SHORT).show();
                }catch (JSONException err){
                    Log.d("Error", err.toString());
                }
                setSektorListe(list,flag);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mContext, error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        mRequestQueue.add(mStringRequest);
        return list;
    }
    private void setSektorListe(List<JSONObject> jsonList, int flag){
        for (int i = 0; i < jsonList.size() ; i++ ){
            SektorListeEntity entity = new SektorListeEntity();
            try{
                entity.setSektor_id(jsonList.get(i).getInt("sektor_id"));
                entity.setIsim(jsonList.get(i).getString("isim"));
                entity.setHat_no(jsonList.get(i).getInt("hat_no"));
                entity.setKm_bas(jsonList.get(i).getInt("km_bas"));
                entity.setKm_bit(jsonList.get(i).getInt("km_bit"));

                if (flag==0){
                    database.sektorListeDao().update(entity);
                }else if (flag==1){
                    database.sektorListeDao().ekle(entity);
                }
            }catch (JSONException err){
                Toast.makeText(mContext, err.toString()+"yükleyemedim sektorliste", Toast.LENGTH_SHORT).show();

            }
        }
    }

    private void all4EtkenListe(){
        if (database.etkenListeDao().readAll().size()>1){
            getEtkenListe(0);//update
        }else{
            getEtkenListe(1);    //sıfırdan create
        }
    }
    private List<JSONObject> getEtkenListe(final int flag) {
        RequestQueue mRequestQueue;
        final List<JSONObject> list = new ArrayList<>();
        StringRequest mStringRequest;
        String url = "http://31.210.91.198/beta/panel/rest/get/etkenliste";
        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(mContext);
        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String[] array = response.substring(1,response.length()-1).split("\\}"+"\\,");
                try {
                    for (int i = 0; i<array.length;i++){
                        String object = array[i]+"\\}";
                        object = object.substring(0,object.length()-2)+object.substring(object.length()-1);
                        JSONObject jsonObject = new JSONObject(object);
                        list.add(jsonObject);
                    }
                    Toast.makeText(mContext, response, Toast.LENGTH_SHORT).show();
                }catch (JSONException err){
                    Log.d("Error", err.toString());
                }
                setEtkenListe(list,flag);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mContext, error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        mRequestQueue.add(mStringRequest);

        return list;
    }
    private void setEtkenListe(List<JSONObject> jsonList, int flag){
        for (int i = 0; i < jsonList.size() ; i++ ){
            EtkenListeEntity entity = new EtkenListeEntity();
            try{
                entity.setEtken_id(jsonList.get(i).getInt("etken_id"));
                entity.setIsim(jsonList.get(i).getString("isim"));
                entity.setVt_deger(jsonList.get(i).getDouble("vt_deger"));
                if (flag==0){
                    database.etkenListeDao().update(entity);
                }else if (flag==1){
                    database.etkenListeDao().ekle(entity);
                }
            }catch (JSONException err){
                Toast.makeText(mContext, err.toString()+"yükleyemedim etkenliste", Toast.LENGTH_SHORT).show();
            }

        }
    }

    private void all4BildiriTipListe(){
        if (database.bildiriTipListeDao().readAll().size()>0){
            getBildiriTipListe(0);//update
        }else{
            getBildiriTipListe(1);    //sıfırdan create
        }
    }
    private List<JSONObject> getBildiriTipListe(final int flag) {
        RequestQueue mRequestQueue;
        final List<JSONObject> list = new ArrayList<>();
        StringRequest mStringRequest;
        String url = "http://31.210.91.198/beta/panel/rest/get/bildiritipliste";
        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(mContext);
        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    response = new String(response.getBytes("ISO-8859-1"), "UTF-8");
                    JSONArray jsonObjectResponse=new JSONArray(response);
                    for (int i = 0; i<jsonObjectResponse.length();i++){
                        list.add(jsonObjectResponse.getJSONObject(i));
                    }
                    Toast.makeText(mContext, response, Toast.LENGTH_SHORT).show();
                }catch (JSONException err){
                    Log.d("Error", err.toString());
                }catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                setBildiriTipListe(list,flag);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mContext, error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        mRequestQueue.add(mStringRequest);
        return list;
    }
    private void setBildiriTipListe(List<JSONObject> jsonList, int flag){
        for (int i = 0; i < jsonList.size() ; i++ ){
            BildiriTipListeEntity entity = new BildiriTipListeEntity();
            try{
                entity.setBildiritip_id(jsonList.get(i).getInt("bildiritip_id"));
                entity.setIsim(jsonList.get(i).getString("isim"));
                entity.setSistem(jsonList.get(i).getBoolean("sistem"));
                entity.setMobil_sistem(jsonList.get(i).getBoolean("mobil_sistem"));
                entity.setFrekans(jsonList.get(i).getInt("frekans"));
                entity.setGunluk_rapor(jsonList.get(i).getBoolean("gunluk_rapor"));
                entity.setYayin_saat(jsonList.get(i).getString("yayin_saat"));
                entity.setGiris_sure(jsonList.get(i).getInt("giris_sure"));
                //entity.setBagimli(jsonList.get(i).getBoolean("bagimli"));

                if (flag==0){
                    database.bildiriTipListeDao().update(entity);
                }else if (flag==1){
                    database.bildiriTipListeDao().ekle(entity);
                }
            }catch (JSONException err){
                Toast.makeText(mContext, err.toString()+" bildiritip", Toast.LENGTH_SHORT).show();

            }

        }
    }

    private void all4Bildiriler(){
        if (database.bildirilerDao().readAll().size()>1){
            getBildiriler(0);//update
        }else{
            getBildiriler(1);//sıfırdan create
        }
    }
    private List<JSONObject> getBildiriler(final int flag) {
        RequestQueue mRequestQueue;
        final List<JSONObject> list = new ArrayList<>();
        StringRequest mStringRequest;
        String url = "http://31.210.91.198/beta/panel/rest/get/bildiriler";
        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(mContext);
        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String[] array = response.substring(1,response.length()-1).split("\\}"+"\\,");
                try {
                    for (int i = 0; i<array.length;i++){
                        String object = array[i]+"\\}";
                        object = object.substring(0,object.length()-2)+object.substring(object.length()-1);
                        JSONObject jsonObject = new JSONObject(object);
                        list.add(jsonObject);
                    }
                    Toast.makeText(mContext, response, Toast.LENGTH_SHORT).show();
                }catch (JSONException err){
                    Log.d("Error", err.toString());
                }
                setBildiriler(list,flag);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mContext, error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        mRequestQueue.add(mStringRequest);
        return list;
    }
    private void setBildiriler(List<JSONObject> jsonList, int flag){
        for (int i = 0; i < jsonList.size() ; i++ ){
            BildirilerEntity entity = new BildirilerEntity();
            try{
                entity.setBildiri_id(jsonList.get(i).getLong("bildiri_id"));
                entity.setBildiri_tipi(jsonList.get(i).getInt("bildiri_tipi"));
                entity.setBildiri_tarih(jsonList.get(i).getString("bildiri_tarih"));
                entity.setKullanici(jsonList.get(i).getInt("kullanici"));
                entity.setKabul(jsonList.get(i).getInt("kabul"));
                entity.setSon_giris(jsonList.get(i).getString("son_giris"));
                entity.setKabul_zamani(jsonList.get(i).getString("kabul_zamani"));

                if (flag==0){
                    database.bildirilerDao().update(entity);
                }else if (flag==1){
                    database.bildirilerDao().ekle(entity);
                }
            }catch (JSONException err){
                Toast.makeText(mContext, err.toString()+"yükleyemedim bildiriler", Toast.LENGTH_SHORT).show();

            }

        }
    }

    private void all4Kullanicilar(){
        if (database.kullanicilarDao().readAll().size()>1){
            getKullanicilar(0);//update
        }else{
            getKullanicilar(1);//sıfırdan create
        }
    }
    private List<JSONObject> getKullanicilar(final int flag) {
        RequestQueue mRequestQueue;
        final List<JSONObject> list = new ArrayList<>();
        StringRequest mStringRequest;
        String url = "http://31.210.91.198/beta/panel/rest/get/kullanicilar";
        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(mContext);
        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String[] array = response.substring(1,response.length()-1).split("\\}"+"\\,");
                try {
                    for (int i = 0; i<array.length;i++){
                        String object = array[i]+"\\}";
                        object = object.substring(0,object.length()-2)+object.substring(object.length()-1);
                        JSONObject jsonObject = new JSONObject(object);
                        list.add(jsonObject);
                    }
                }catch (JSONException err){
                    Log.d("Error", err.toString());
                }
                setKullanicilar(list,flag);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mContext, error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        mRequestQueue.add(mStringRequest);
        return list;
    }
    private void setKullanicilar(List<JSONObject> jsonList, int flag){
        for (int i = 0; i < jsonList.size() ; i++ ){
            KullanicilarEntity entity = new KullanicilarEntity();
            try{
                entity.setKullanici_id(jsonList.get(i).getInt("kullanici_id"));
                entity.setIsim(jsonList.get(i).getString("isim"));
                entity.setSoyisim(jsonList.get(i).getString("soyisim"));
                entity.setIsim_tam(jsonList.get(i).getString("isim_tam"));
                entity.setKullanici_adi(jsonList.get(i).getString("kullanici_adi"));
                entity.setEmail(jsonList.get(i).getString("email"));
                entity.setPassword(jsonList.get(i).getString("password"));
                entity.setAktif(jsonList.get(i).getBoolean("aktif"));

                if (flag==0){
                    database.kullanicilarDao().update(entity);
                }else if (flag==1){
                    database.kullanicilarDao().ekle(entity);
                }
            }catch (JSONException err){
                Toast.makeText(mContext, err.toString(), Toast.LENGTH_LONG).show();
                Toast.makeText(mContext, jsonList.get(i).toString()+"  kullanıcılar", Toast.LENGTH_LONG).show();

            }


        }
    }

    private void all4KullaniciBildiriEslesme(){
        if (database.kullaniciBildiriEslesmeDao().readAll().size()>1){
            getKullaniciBildiriEslesme(0);//update
        }else{
            getKullaniciBildiriEslesme(1);//sıfırdan create
        }
    }
    private List<JSONObject> getKullaniciBildiriEslesme(final int flag) {
        RequestQueue mRequestQueue;
        final List<JSONObject> list = new ArrayList<>();
        StringRequest mStringRequest;
        String url = "http://31.210.91.198/beta/panel/rest/get/kullanicibildirieslesme";
        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(mContext);
        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String[] array = response.substring(1,response.length()-1).split("\\}"+"\\,");
                try {
                    for (int i = 0; i<array.length;i++){
                        String object = array[i]+"\\}";
                        object = object.substring(0,object.length()-2)+object.substring(object.length()-1);
                        JSONObject jsonObject = new JSONObject(object);
                        list.add(jsonObject);
                    }
                    Toast.makeText(mContext, response, Toast.LENGTH_SHORT).show();
                }catch (JSONException err){
                    Log.d("Error", err.toString());
                }
                setKullaniciBildiriEslesme(list,flag);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mContext, error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        mRequestQueue.add(mStringRequest);
        return list;
    }
    private void setKullaniciBildiriEslesme(List<JSONObject> jsonList, int flag){
        for (int i = 0; i < jsonList.size() ; i++ ){
            KullaniciBildiriEslesmeEntity entity = new KullaniciBildiriEslesmeEntity();
            try{
                entity.setBildiri_tipi(jsonList.get(i).getInt("bildiri_tipi"));
                entity.setKullanici(jsonList.get(i).getInt("kullanici"));

                if (flag==0){
                    database.kullaniciBildiriEslesmeDao().update(entity);
                }else if (flag==1){
                    database.kullaniciBildiriEslesmeDao().ekle(entity);
                }
            }catch (JSONException err){
                Toast.makeText(mContext, err.toString()+"yükleyemedim kullanıcıbildiri", Toast.LENGTH_SHORT).show();

            }

        }
    }

    private void all4ImalatListe(){
        if (database.imalatListeDao().readAll().size()>1){
            getImalatListe(0);//update
        }else{
            getImalatListe(1);//sıfırdan create
        }
    }
    private List<JSONObject> getImalatListe(final int flag) {
        RequestQueue mRequestQueue;
        final List<JSONObject> list = new ArrayList<>();
        StringRequest mStringRequest;
        String url = "http://31.210.91.198/beta/panel/rest/get/imalatliste";
        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(mContext);
        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String[] array = response.substring(1,response.length()-1).split("\\}"+"\\,");
                try {
                    for (int i = 0; i<array.length;i++){
                        String object = array[i]+"\\}";
                        object = object.substring(0,object.length()-2)+object.substring(object.length()-1);
                        JSONObject jsonObject = new JSONObject(object);
                        list.add(jsonObject);
                    }
                    Toast.makeText(mContext, response, Toast.LENGTH_SHORT).show();
                }catch (JSONException err){
                    Log.d("Error", err.toString());
                }
                setImalatListe(list,flag);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mContext, error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        mRequestQueue.add(mStringRequest);
        return list;
    }
    private void setImalatListe(List<JSONObject> jsonList, int flag){
        for (int i = 0; i < jsonList.size() ; i++ ){
            ImalatListeEntity entity = new ImalatListeEntity();
            try{
                entity.setImalat_id(jsonList.get(i).getInt("imalat_id"));
                entity.setIsim(jsonList.get(i).getString("isim"));
                entity.setUzun_isim(jsonList.get(i).getString("uzun_isim"));
                entity.setBpl_adsa(jsonList.get(i).getDouble("bpl_adsa"));
                entity.setBpl_adsa_net(jsonList.get(i).getDouble("bpl_adsa_net"));
                entity.setBpl_adsa_butce(jsonList.get(i).getDouble("bpl_adsa_butce"));
                entity.setBirim(jsonList.get(i).getString("birim"));
                entity.setTakip_tipi(jsonList.get(i).getString("takip_tipi"));
                entity.setTakip(jsonList.get(i).getBoolean("takip"));
                entity.setKontrol(jsonList.get(i).getBoolean("kontrol"));
                entity.setPuantaj(jsonList.get(i).getBoolean("puantaj"));
                entity.setMaliyet(jsonList.get(i).getBoolean("maliyet"));
                entity.setMinpi_deger(jsonList.get(i).getInt("minpi_deger"));

                if (flag==0){
                    database.imalatListeDao().update(entity);
                }else if (flag==1){
                    database.imalatListeDao().ekle(entity);
                }
            }catch (JSONException err){
                Toast.makeText(mContext, err.toString()+"yükleyemedim imalat liste", Toast.LENGTH_SHORT).show();

            }

        }
    }

}
