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
import net.proys.proysrail.Entities.MakineKategoriEntity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.room.Database;
import androidx.room.Room;

public class RemoteServerDataCheck {

    Context mContext;


    public RemoteServerDataCheck(Context mContext) {
        this.mContext = mContext;
    }

    protected void entegrationDataManagement(){
        //List<JSONObject> list = getCalisanListe();
        /*new CountDownTimer(1000, 1000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                String asd;

            }

        }.start();*/
       // setCalisanListe(list);





    }

    private List<JSONObject> getCalisanListe() {
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
                setCalisanListe(list);

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

    private void setCalisanListe(List<JSONObject> jsonList){
        String[] CalisanListeColumns = new String[]{
                "calisan_id",
                "isim",
                "soyisim",
                "isim_tam",
                "maviyaka",
                "direkt",
                "pozisyon",
                "vt_puantaj",
                "unvan",
                "departman",
                "taseron",
                "sorumlu",
                "v_imalat"
        };
        RoomDatabase database = Room.databaseBuilder(mContext,RoomDatabase.class,"ProysDB").allowMainThreadQueries().build();

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


                database.calisanListeDao().ekle(entity);



            }catch (JSONException err){
                Toast.makeText(mContext, "yükleyemedim", Toast.LENGTH_SHORT).show();

            }



        }
        List<CalisanListeEntity> list = database.calisanListeDao().readAll();
        System.out.println("asd");
    }
}
