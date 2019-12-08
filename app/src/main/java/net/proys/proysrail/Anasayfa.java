package net.proys.proysrail;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.SimpleDateFormat;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anasayfa);

       init();
       animation();
      setOnclickEvents();
       Benihatirla();


        //System.out.println(database.ReadPersonelwImalat_id("T0002")[0]);

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
    protected void urldeneme(){
        String url = "http://176.53.81.147/panel/restdeneme";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
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
                System.out.println("asd"+error);
            }
        }) {
            /*StringRequest isteğinin temel amacı, MySql'e veri eklemektir. Kullanıcının
            arayüze girdiği veriler Map kullanılarak alınır ve istekler beraber veritabanına
            gönderilir. Map içerisinde veriler key-value çiftleri halinde bulunur. HashMap
            te aynı yapıda bir sınıftır. Temel mantıkları anahtar-veri çiftleri halinde verileri
            saklamaktır*/
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> info = new HashMap<String, String>();
                    /*name ve password bilgileri insert_admin_info.php isimli dosyada $_POST
                    içinde bulunan değerlerdir. Bunların aynı olması gerekiyor.*/
                info.put("gelisme","serdar baba yapti" );
                return info;
            }
        };
            /*istek işlenir.*/
        MySingleton.getInstance(getApplicationContext()).addToRequestQue(stringRequest);

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
        SQLiteHelper database = new SQLiteHelper(Anasayfa.this);

            //TODO read username liste sorgu elemans sayısı
            int kullanici_id = Integer.valueOf(login_sqLiteHelper.ReadUsername(username)[1]);
        String[] gerekli_bildiriler = login_sqLiteHelper.ReadwithIdfor1b(kullanici_id);
        //veri.setKullanici_id(kullanici_id);
        database.UpdateGet_Set("KullaniciId",String.valueOf(kullanici_id));

        for (int i = 0 ; i<gerekli_bildiriler.length; i++) {

            if (database.ReadBildiriListesifor1b(kullanici_id,gerekli_bildiriler[i])==0){

                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd");
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
                String date = sdf.format(calendar.getTime());
                String date1 = sdf2.format(calendar.getTime());
                calendar.add(Calendar.DAY_OF_MONTH,1);
                String deadline = sdf.format(calendar.getTime());
                database.WriteBildiriListesi(Long.valueOf(String.valueOf(kullanici_id)+gerekli_bildiriler[i].substring(1)+date1),kullanici_id,gerekli_bildiriler[i],database.ReadBildiriler(gerekli_bildiriler[i])[1],
                        date,deadline,database.ReadBildiriler(gerekli_bildiriler[i])[7],0);

            }
        }


                if (login_sqLiteHelper.ReadwithIdfor1b(kullanici_id).length>0){

                    List<String> tarihler = new ArrayList();
                    for (int i=0;i<gerekli_bildiriler.length;i++){
                        tarihler.add(database.ReadBildiriListesiforDateComparision(kullanici_id,gerekli_bildiriler[i]));
                    }
                    for (int i=0;i<gerekli_bildiriler.length;i++){
                        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
/*
                        Calendar calendar1= Calendar.getInstance();

                        try {
                            calendar1.setTime(sdf.parse("28.08.2019"));
                        } catch (java.text.ParseException e) {
                            e.printStackTrace();
                        }*/
                        Calendar calendar= Calendar.getInstance();

                        try {
                            calendar.setTime(sdf.parse(tarihler.get(i)));
                        } catch (java.text.ParseException e) {
                            e.printStackTrace();
                        }
                        calendar.add(Calendar.DAY_OF_MONTH,Integer.valueOf(database.ReadBildiriler(gerekli_bildiriler[i])[6]));


                        try {
                            for (Date tarih = sdf.parse(tarihler.get(i)); tarih.before(Calendar.getInstance().getTime());tarih = calendarpday(tarih,gerekli_bildiriler[i])){
                                System.out.println("tarihler  "+sdf.format(tarih));
                                System.out.println(sdf.format(Calendar.getInstance().getTime()));
                                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");

                                database.WriteBildiriListesi(Long.valueOf(String.valueOf(kullanici_id)+gerekli_bildiriler[i].substring(1)+sdf2.format(tarih)),
                                        kullanici_id,gerekli_bildiriler[i],database.ReadBildiriler(gerekli_bildiriler[i])[1],sdf.format(tarih),
                                        sdf.format(calendarpday(tarih,gerekli_bildiriler[i])),database.ReadBildiriler(gerekli_bildiriler[i])[7],0);
                            }
                        } catch (java.text.ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }
    }
    private Date calendarpday(Date tarih,String bildiri){
        SQLiteHelper database = new SQLiteHelper(Anasayfa.this);
        Calendar calendar= Calendar.getInstance();
        calendar.setTime(tarih);
        calendar.add(Calendar.DAY_OF_MONTH,Integer.valueOf(database.ReadBildiriler(bildiri)[6]));
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
                if (database.ReadUsername(username).length>4){
                if (database.ReadUsername(username)[4].equals(password)){
                    veri.setKullaniciAdi(username);
                    SharedPreferences sp = getSharedPreferences("Login",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    if (true){//beni hatirla checkboxceheck TODO

                        editor.putBoolean("isON",true);
                        editor.putString("username",username);
                        editor.putString("password",password);
                        editor.commit();
                    }else{
                        editor.putInt("isON",0);
                    }
                    setBildiriListesi_1b(username);
                    Intent intent = new Intent(Anasayfa.this,L1_main.class);
                    startActivity(intent);
                }


            }
            else {
                    Toast.makeText(Anasayfa.this,"Kullanıcı veya şifre yanlış girildi",Toast.LENGTH_SHORT).show();
                }
            }
        });
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

            StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://proys.net/sektorler.php", new Response.Listener<String>() {
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
                    info.put("id",id);
                    info.put("isim",isim);
                    info.put("hat",hat);
                    info.put("hat_no",hat_no);
                    info.put("km_bas",km_bas);
                    info.put("km_bit",km_bit);
                    info.put("aktif",aktif);
                    info.put("imalat",imalat);
                    info.put("bolge",bolge);
                    return info;
                }
            };
            //Volley.newRequestQueue(MainActivity.this).add(stringRequest);
            MySingleton.getInstance(getApplicationContext()).addToRequestQue(stringRequest);
        }

    @Override
    public void onBackPressed() {
    }
}
