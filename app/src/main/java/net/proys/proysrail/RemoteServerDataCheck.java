package net.proys.proysrail;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import net.proys.proysrail.Entities.CalisanListeEntity;
import net.proys.proysrail.Entities.ImalatMakineEslesmeEntity;
import net.proys.proysrail.Entities.ImalatSektorEslesmeEntity;
import net.proys.proysrail.Entities.MakineKategoriEntity;
import net.proys.proysrail.Entities.MakineListeEntity;
import net.proys.proysrail.Entities.MaliyetDagiticiEntity;

import org.json.JSONException;
import org.json.JSONObject;

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
         database = Room.databaseBuilder(mContext,RoomDatabase.class,"ProysDB").allowMainThreadQueries().build();
    }

    protected void entegrationDataManagement(){
        all4CalisanListe();
        all4MakineListe();
        all4MakineKategori();
        all4ImalatMakineEslesme();
        //todo imalatbişeysini atladık
        all4ImalatSektorEslesme();
        all4MaliyetDagitici();

    }

    private void all4CalisanListe(){
        List<CalisanListeEntity> list = database.calisanListeDao().readAll();
        //list.get(0);

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
        String url = "http://www.proys.net/beta/panel/rest/get/calisanliste";
        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(mContext);
        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String[] array = response.substring(1,response.length()-1).split("\\}"+"\\,");
                try {
                   for (int i = 0; i<array.length;i++){
                        String asd = array[i]+"\\}";
                        asd = asd.substring(0,asd.length()-2)+asd.substring(asd.length()-1);
                        JSONObject jsonObject = new JSONObject(asd);
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
                Toast.makeText(mContext, "yükleyemedim", Toast.LENGTH_SHORT).show();

            }

        }
        List<CalisanListeEntity> list = database.calisanListeDao().readAll();
        list.get(0);
    }

    private void all4MakineListe(){
        if (database.makineListeDao().readAll().size()>1){
            getMakineListe(0);//update
        }else{
            getMakineListe(1);    //sıfırdan create
        }
    }
    private List<JSONObject> getMakineListe(final int flag) {
        RequestQueue mRequestQueue;
        final List<JSONObject> list = new ArrayList<>();
        StringRequest mStringRequest;
        String url = "http://www.proys.net/beta/panel/rest/get/makineliste";
        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(mContext);
        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String[] array = response.substring(1,response.length()-1).split("\\}"+"\\,");
                try {
                    for (int i = 0; i<array.length;i++){
                        String asd = array[i]+"\\}";
                        asd = asd.substring(0,asd.length()-2)+asd.substring(asd.length()-1);
                        JSONObject jsonObject = new JSONObject(asd);
                        list.add(jsonObject);
                    }
                    Toast.makeText(mContext, response, Toast.LENGTH_SHORT).show();
                }catch (JSONException err){
                    Log.d("Error", err.toString());
                }
                setMakineListe(list,flag);

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
                Toast.makeText(mContext, "yükleyemedim", Toast.LENGTH_SHORT).show();

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
        String url = "http://www.proys.net/beta/panel/rest/get/MakineKategori";
        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(mContext);
        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String[] array = response.substring(1,response.length()-1).split("\\}"+"\\,");
                try {
                    for (int i = 0; i<array.length;i++){
                        String asd = array[i]+"\\}";
                        asd = asd.substring(0,asd.length()-2)+asd.substring(asd.length()-1);
                        JSONObject jsonObject = new JSONObject(asd);
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
                Toast.makeText(mContext, "yükleyemedim", Toast.LENGTH_SHORT).show();

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
        String url = "http://www.proys.net/beta/panel/rest/get/imalatmakineeslesme";
        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(mContext);
        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String[] array = response.substring(1,response.length()-1).split("\\}"+"\\,");
                try {
                    for (int i = 0; i<array.length;i++){
                        String asd = array[i]+"\\}";
                        asd = asd.substring(0,asd.length()-2)+asd.substring(asd.length()-1);
                        JSONObject jsonObject = new JSONObject(asd);
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
                Toast.makeText(mContext, "yükleyemedim", Toast.LENGTH_SHORT).show();

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
        String url = "http://www.proys.net/beta/panel/rest/get/imalatsektoreslesme";
        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(mContext);
        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String[] array = response.substring(1,response.length()-1).split("\\}"+"\\,");
                try {
                    for (int i = 0; i<array.length;i++){
                        String asd = array[i]+"\\}";
                        asd = asd.substring(0,asd.length()-2)+asd.substring(asd.length()-1);
                        JSONObject jsonObject = new JSONObject(asd);
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
                Toast.makeText(mContext, "yükleyemedim", Toast.LENGTH_SHORT).show();

            }

        }
    }

    private void all4MaliyetDagitici(){
        if (database.imalatMakineEslesmeDao().readAll().size()>1){
            getMaliyetDagitici(0);//update
        }else{
            getMaliyetDagitici(1);    //sıfırdan create
        }
    }
    private List<JSONObject> getMaliyetDagitici(final int flag) {
        RequestQueue mRequestQueue;
        final List<JSONObject> list = new ArrayList<>();
        StringRequest mStringRequest;
        String url = "http://www.proys.net/beta/panel/rest/get/maliyetdagitici";
        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(mContext);
        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String[] array = response.substring(1,response.length()-1).split("\\}"+"\\,");
                try {
                    for (int i = 0; i<array.length;i++){
                        String asd = array[i]+"\\}";
                        asd = asd.substring(0,asd.length()-2)+asd.substring(asd.length()-1);
                        JSONObject jsonObject = new JSONObject(asd);
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
                Toast.makeText(mContext, "yükleyemedim", Toast.LENGTH_SHORT).show();

            }

        }
    }
}
