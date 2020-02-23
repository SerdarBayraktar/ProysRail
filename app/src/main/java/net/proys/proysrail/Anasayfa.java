package net.proys.proysrail;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import net.proys.proysrail.Entities.BildirilerEntity;
import net.proys.proysrail.Entities.KullanicilarEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Anasayfa extends AppCompatActivity {
    private Button login;
    private EditText username_e;
    private EditText password_e;
    private ImageView logo;
    private CheckBox rememberMe;
    Get_Set veri;
    private Animation downtoup,uptodown,uptodown1,uptodown2,visibility;
    private TextView txt_password,txt_mail;
    private LinearLayout linearLayout;
    private RoomHelper dh;
    private RoomDatabase db;
    int kullanici_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anasayfa);
        dh = new RoomHelper(Anasayfa.this);
        db = RoomDatabase.getDatabase(Anasayfa.this);
       init();
       animation();
        //SyncWMySQl();
        //asd();
        //abc();
        setOnclickEvents();
       Benihatirla();
       //urldeneme();
        //urlget();

    }
    private void animation() {
        visibility.setDuration(1500);
        visibility.setStartOffset(250);
        downtoup.setDuration(500);
        uptodown.setDuration(500);
        uptodown2.setDuration(500);
        uptodown1.setDuration(500);
        uptodown.setStartOffset(250);
        uptodown1.setStartOffset(500);
        uptodown2.setStartOffset(750);
        downtoup.setStartOffset(250);

        login.setAnimation(downtoup);
        logo.setAnimation(uptodown);
        username_e.setAnimation(uptodown1);
        password_e.setAnimation(uptodown2);
        txt_mail.setAnimation(visibility);
        txt_password.setAnimation(visibility);
        linearLayout.setAnimation(visibility);


    }
    public void Benihatirla(){
        SharedPreferences sp = getSharedPreferences("Login",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        if (sp.getBoolean("isON",false)) {
            username_e.setText(sp.getString("username", ""));
            password_e.setText(sp.getString("password",""));
        }
    }
    public void init(){
        login = findViewById(R.id.login);
        username_e = findViewById(R.id.email);
        password_e = findViewById(R.id.password);
        linearLayout=findViewById(R.id.rememberMe);
        rememberMe=findViewById(R.id.check_remember);
        veri = new Get_Set();
        txt_password=findViewById(R.id.txt_password);
        txt_mail=findViewById(R.id.txt_eposta);
        logo=findViewById(R.id.imageLogo);
        visibility= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.visibility);
        downtoup= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.downtoup);
        uptodown= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.uptodown);
        uptodown1= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.uptodown);
        uptodown2= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.uptodown);
    }
    protected void setBildiriListesi_1b(String username){
        Login_SQLiteHelper login_sqLiteHelper = new Login_SQLiteHelper(Anasayfa.this);
        //SQLiteHelper database = new SQLiteHelper(Anasayfa.this);
        //int kullanici_id = Integer.valueOf(login_sqLiteHelper.ReadUsername(username)[1]);
        //String[] gerekli_bildiriler = login_sqLiteHelper.ReadwithIdfor1b(kullanici_id);
        //int kullanici_id = db.kullanicilarDao().read(dh.readGetSet("kullaniciId")).get(0).getKullanici_id();
        //List<String> gerekli_bildiriler = dh.ReadwithIdfor1b(kullanici_id);
        List<String> gerekli_bildiriler = new ArrayList<>();

        //veri.setKullanici_id(kullanici_id);sil
        //database.UpdateGet_Set("KullaniciId",String.valueOf(kullanici_id));
        dh.updateGetSet("kullaniciId",String.valueOf(kullanici_id));


            //database.ReadBildiriListesifor1b(kullanici_id,gerekli_bildiriler.get(i))==0
        for (int i = 0 ; i<gerekli_bildiriler.size(); i++) {// eğer bildiriden hiç yoksa o tarihe göre ilk kez oluşturuyor
            if (db.bildirilerDao().ReadBildiriListesifor1b(kullanici_id,gerekli_bildiriler.get(i)).size()==0){
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
                String date = sdf.format(calendar.getTime());
                String date1 = sdf2.format(calendar.getTime());
                calendar.add(Calendar.DAY_OF_MONTH,1);
                String deadline = sdf.format(calendar.getTime());

                BildirilerEntity entity = new BildirilerEntity();
                entity.setBildiri_id(Long.valueOf(String.valueOf(kullanici_id)+gerekli_bildiriler.get(i).substring(1)+date1));
                entity.setKabul_zamani(null);
                entity.setKabul(0);
                entity.setSon_giris(deadline+" 09:00 ");
                entity.setBildiri_tarih(date);
                entity.setKullanici(kullanici_id);
                entity.setBildiri_tipi(Integer.valueOf(gerekli_bildiriler.get(i)));
                db.bildirilerDao().ekle(entity);
                /*database.WriteBildiriListesi(Long.valueOf(String.valueOf(kullanici_id)+gerekli_bildiriler.get(i).substring(1)+date1),kullanici_id,gerekli_bildiriler.get(i),
                        database.ReadBildiriler(gerekli_bildiriler.get(i))[1],
                        date,deadline,database.ReadBildiriler(gerekli_bildiriler.get(i))[7],0);
*/
            }
        }
                if (gerekli_bildiriler.size()>0){
                    List<String> tarihler = new ArrayList();
                    for (int i=0;i<gerekli_bildiriler.size();i++){
                        //tarihler.add(database.ReadBildiriListesiforDateComparision(kullanici_id,gerekli_bildiriler.get(i)));
                        dh.ReadBildiriListesiforDateComparision(kullanici_id,gerekli_bildiriler.get(i));
                    }
                    for (int i=0;i<gerekli_bildiriler.size();i++){
                        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                         try {
                            for (Date tarih = sdf.parse(tarihler.get(i)); tarih.before(Calendar.getInstance().getTime());tarih = calendarpday(tarih,gerekli_bildiriler.get(i))){
                                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
                                /*database.WriteBildiriListesi(Long.valueOf(String.valueOf(kullanici_id)+gerekli_bildiriler.get(i).substring(1)+sdf2.format(tarih)),
                                        kullanici_id,gerekli_bildiriler.get(i),database.ReadBildiriler(gerekli_bildiriler.get(i))[1],sdf.format(tarih),
                                        sdf.format(calendarpday(tarih,gerekli_bildiriler.get(i))),database.ReadBildiriler(gerekli_bildiriler.get(i))[7],0);*/
                                BildirilerEntity entity = new BildirilerEntity();
                                entity.setBildiri_id(Long.valueOf(String.valueOf(kullanici_id)+gerekli_bildiriler.get(i).substring(1)+sdf2.format(tarih)));
                                entity.setKabul_zamani(null);
                                entity.setKabul(0);
                                entity.setSon_giris(sdf.format(calendarpday(tarih,gerekli_bildiriler.get(i))+" 09:00 "));
                                entity.setBildiri_tarih(sdf.format(tarih));
                                entity.setKullanici(kullanici_id);
                                entity.setBildiri_tipi(Integer.valueOf(gerekli_bildiriler.get(i)));
                                db.bildirilerDao().ekle(entity);
                            }
                        } catch (java.text.ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }
    }
    private Date calendarpday(Date tarih,String bildiri){
        //SQLiteHelper database = new SQLiteHelper(Anasayfa.this);
        Calendar calendar= Calendar.getInstance();
        calendar.setTime(tarih);
        //calendar.add(Calendar.DAY_OF_MONTH,Integer.valueOf(database.ReadBildiriler(bildiri)[6]));
        calendar.add(Calendar.DAY_OF_MONTH,db.bildiriTipListeDao().read(Integer.valueOf(bildiri)).get(0).getFrekans());
        Date date = calendar.getTime();
        return date;
    }
    protected void setOnclickEvents(){
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Get_Set veri = new Get_Set();
                String username = username_e.getText().toString();
                String password = password_e.getText().toString();
                Login_SQLiteHelper database = new Login_SQLiteHelper(Anasayfa.this);
                //db.kullanicilarDao().read(username).get(0).getPassword().equals(password)
                //if (database.ReadUsername(username).length>4){
                //                if (database.ReadUsername(username)[4].equals(password)){
                KullanicilarEntity kullanici = db.kullanicilarDao().read(username).get(0);
                if (kullanici.getPassword().equals(password)){
                    kullanici_id = kullanici.getKullanici_id();
                    dh.updateGetSet("kullaniciId",String.valueOf(kullanici.getKullanici_id()));
                    //veri.setKullaniciAdi(username);
                    SharedPreferences sp = getSharedPreferences("Login",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    if (rememberMe.isChecked()){
                        editor.putBoolean("isON",true);
                        editor.putString("username",username);
                        editor.putString("password",password);
                        editor.commit();
                    }else{
                        editor.putInt("isON",0);
                    }
                    setBildiriListesi_1b(username);
                    syncBildiriler();
                    Intent intent = new Intent(Anasayfa.this,L1_main.class);
                    startActivity(intent);
                }
            //}
            else {
                    Toast.makeText(Anasayfa.this,"Kullanıcı veya şifre yanlış girildi",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void syncBildiriler(){
        //check connectivity
        RequestQueue mRequestQueue;
        final List<JSONObject> list = new ArrayList<>();
        StringRequest mStringRequest;
        String url = "http://31.210.91.198/beta/panel/rest/get/bildiriler";
        mRequestQueue = Volley.newRequestQueue(Anasayfa.this);
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        //int kullanici_id = db.kullanicilarDao().read(dh.readGetSet("kullaniciId")).get(0).getKullanici_id();
                        if (object.getInt("kullanici")==kullanici_id) {
                            BildirilerEntity entity = new BildirilerEntity();
                            entity.setBildiri_tipi(object.getInt("bildiri_tipi"));
                            entity.setKullanici(object.getInt("kullanici"));
                            entity.setBildiri_tarih(object.getString("bildiri_tarih"));
                            entity.setSon_giris(object.getString("son_giris"));
                            entity.setKabul(object.getInt("kabul"));
                            entity.setKabul_zamani(object.getString("kabul_zamani"));
                            entity.setBildiri_id(object.getLong("bildiri_id"));
                            if (dh.bildirilerCheckkwithConnection(object.getLong("bildiri_id"))) {
                                //bildiriyi kaydet
                                db.bildirilerDao().ekle(entity);
                            } else {
                                //bildiriyi update et
                                db.bildirilerDao().update(entity);
                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Anasayfa.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        mRequestQueue.add(mStringRequest);
    }



    protected void SyncWMySQl(){
            final String id = "90002";
            final String isim = "Tünel-194";
            final String hat = "anahat";
            final String hat_no = "1";
            final String km_bas = "194175";
            final String km_bit = "195069";
            final String aktif = "1";
            final String imalat = "T0012--T0013--T0014--T0015--T0006--T0007--T0008--T0009";
            final String bolge = "A1";

            StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://31.210.91.198/beta/panel/rest/post/bildiri", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    System.out.println(response);
                    Toast.makeText(getApplicationContext(),"kaydedildi",Toast.LENGTH_LONG).show();
                }
            },new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println(error);
                    Toast.makeText(getApplicationContext(),"hata",Toast.LENGTH_LONG).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> info = new HashMap<String, String>();
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("deneme","proys");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    info.put("deneme",jsonObject.toString());


                    return info;
                }
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Content-Type", "application/json; charset=utf-8");
                    return headers;
                }
            };
            //Volley.newRequestQueue(MainActivity.this).add(stringRequest);
            MySingleton.getInstance(getApplicationContext()).addToRequestQue(stringRequest);
        }
    public void asd(){
            JSONArray array = new JSONArray();
            array.put("serdar1");
            array.put("kerem2");
            array.put("cihan3");
            array.put("proys4");
            JSONObject js = new JSONObject();
            String[] strings = new String[]{"serdar1","kerem2","cihan3","proys4"};
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("a","android");
                jsonObject.put("b","ios");
                js.put("deneme",jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            // Make request for JSONObject
            JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                    Request.Method.POST, "http://31.210.91.198/beta/panel/rest/post/bildiri", js,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(Anasayfa.this, response.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Anasayfa.this, error.toString(), Toast.LENGTH_SHORT).show();

                }
            }) {

                /**
                 * Passing some request headers
                 */
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Content-Type", "application/json; charset=utf-8");
                    return headers;
                }

            };

            // Adding request to request queue
            Volley.newRequestQueue(this).add(jsonObjReq);

        }
    public void abc(){
            JSONObject map = new JSONObject();
            String username = "proys";
            try {
                map.put("deneme", username);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JsonObjectRequest sr = new JsonObjectRequest(Request.Method.POST, "http://31.210.91.198/beta/panel/rest/post/bildiri", map, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject result) {
                    Toast.makeText(Anasayfa.this, result.toString(), Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("ServiceCall", error.toString());
                    Toast.makeText(Anasayfa.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            });
            RequestQueue mRequestQueue = Volley.newRequestQueue(Anasayfa.this);
            mRequestQueue.add(sr);
        }
    protected void urldeneme(){
        String url = "http://31.210.91.198/rest/bildiri_cek";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        /*Herhangi bir hata yok ise, kullanıcıya bilgi verilir.*/
                        Toast.makeText(getApplicationContext(), "Veri kayıt edildi...", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                /*Hata meydana geldiğinde kullanıcıya bilgi verilir.*/
                Toast.makeText(getApplicationContext(), "Hata meydana geldi", Toast.LENGTH_SHORT).show();
                System.out.println("asd"+error.getLocalizedMessage());
                System.out.println("asd"+error.getMessage());
            }
        }) {
            /*StringRequest isteğinin temel amacı, MySql'e veri eklemektir. Kullanıcının
            arayüze girdiği veriler Map kullanılarak alınır ve istekler beraber veritabanına
            gönderilir. Map içerisinde veriler key-value çiftleri halinde bulunur. HashMap
            te aynı yapıda bir sınıftır. Temel mantıkları anahtar-veri çiftleri halinde verileri
            saklamaktır*/
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                /*Map<String, String> info = new HashMap<String, String>();
                 *//*name ve password bilgileri insert_admin_info.php isimli dosyada $_POST
                    içinde bulunan değerlerdir. Bunların aynı olması gerekiyor.*//*
                info.put("gelisme","serdar baba yapti" );
                return info;*/
                Map<String, String> params = new HashMap<String, String>();

                Map<String, String[]> userParams = new HashMap<String, String[]>();
                userParams.put("name",new String[]{"serdar","armagan"});
                userParams.put("ages",new String[]{"16","23"});

                JSONObject userJSON = new JSONObject(userParams);

                params.put("users", userJSON.toString());

                return params;
            }
        };
        /*istek işlenir.*/
        MySingleton.getInstance(getApplicationContext()).addToRequestQue(stringRequest);

    }
    protected void urlget() {
        RequestQueue mRequestQueue;
        StringRequest mStringRequest;
        String url = "http://31.210.91.198/rest/bildiri_cek?format=json";


        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(getApplicationContext(), "Response :" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen
                //System.out.println("deneme1 "+response.split("\\{")[1].split(",")[0].split(":")[0]);
                //System.out.println("deneme2 "+response.split("\\{")[1].split(",")[0].split(":")[1]);
                List<JSONObject> list = new ArrayList<>();
                String[] array = response.substring(1,response.length()-1).split("\\}"+"\\,");
                //System.out.println("deneme0 "+ array[0]);
                try {
                    //JSONObject jsonObject = new JSONObject(response.substring(1,response.length()-1));
                    //System.out.println("deneme3 "+jsonObject.get("bildiri_id"));
                    //System.out.println("deneme3 "+response);

                    for (int i = 0; i<array.length;i++){
                        //System.out.println("deneme0 "+ array[i]);
                        String asd = array[i]+"\\}";
                        asd = asd.substring(0,asd.length()-2)+asd.substring(asd.length()-1);

                        JSONObject jsonObject = new JSONObject(asd);
                        list.add(jsonObject);

                        System.out.println("deneme "+String.valueOf(i)+" " + list.get(i).keys().next());
                    }
                }catch (JSONException err){
                    Log.d("Error", err.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Anasayfa.this, error.toString(), Toast.LENGTH_LONG).show();
                System.out.println("asd" + error.toString());
                Log.i("tag", "Error :" + error.toString());
            }
        });

        mRequestQueue.add(mStringRequest);

    }

    @Override
    public void onBackPressed() {
    }
}
