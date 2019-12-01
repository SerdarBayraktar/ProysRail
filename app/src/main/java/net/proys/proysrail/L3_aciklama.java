package net.proys.proysrail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class L3_aciklama extends AppCompatActivity {
    protected ImageView ekleme_butonu;
    protected ImageView medya_icon,ilerleme_icon,tick;
    private EditText aciklama;
    protected ImageView imalat_icon,verimsizlik_icon,aciklama_icon;
    protected LinearLayout imalat_linear,verim_linear,aciklama_linear,medya_linear;
    protected LinearLayout linearLayout;
    Get_Set veri;
    protected ListView listView;
    protected SQLiteHelper database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l3_aciklama);
        veri = new Get_Set();
        database = new SQLiteHelper(L3_aciklama.this);
        init();
        setListView();
        setOnclickevents();
        allset4menu();
        setIcons();
    }
    protected void init(){
        linearLayout = findViewById(R.id.text_aciklama);
        ilerleme_icon = findViewById(R.id.ilerleme_icon);
        ekleme_butonu = findViewById(R.id.aciklama_ekleme);
        medya_icon = findViewById(R.id.imageCamera);

        imalat_icon = findViewById(R.id.imageImalat);
        verimsizlik_icon = findViewById(R.id.imageverim);

        imalat_linear = findViewById(R.id.imalat_linear);
        verim_linear = findViewById(R.id.verim_linear);
        aciklama_linear = findViewById(R.id.aciklama_linear);
        medya_linear = findViewById(R.id.medya_linear);
        tick = findViewById(R.id.tick);
        aciklama_icon = findViewById(R.id.imageAciklama);
        listView = findViewById(R.id.listview);
        if (veri.getAciklamalar().equals("")){
            linearLayout.setVisibility(View.INVISIBLE);
        }else{
            aciklama.setText(veri.getAciklamalar());
        }
    }
    protected void setOnclickevents(){
        tick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imalat_check();
                Intent intent = new Intent(L3_aciklama.this,L2_bildiri.class);
                startActivity(intent);

            }
        });

        ekleme_butonu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.WriteTaslakL3(String.valueOf(veri.getKod()),veri.getImalatIsgucuid(),Integer.valueOf(database.ReadGet_Set("KopyaNo")));
                setListView();
            }
        });

        ilerleme_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L3_aciklama.this,L3_medya.class);
                startActivity(intent);
            }
        });

    }
    protected void allset4menu(){
        imalat_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L3_aciklama.this,L3_imalat.class);
                startActivity(intent);
            }
        });
        verim_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L3_aciklama.this,L3_verimsizlik.class);
                startActivity(intent);
            }
        });
        medya_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L3_aciklama.this,L3_medya.class);
                startActivity(intent);
            }
        });

    }
    protected void setIcons(){
        aciklama_icon.setImageResource(R.drawable.aciklama_o);
        /*if ((!veri.getImalat().equals(""))&&(!veri.getSektör().equals(""))&&(veri.getHatno()!=-13)&&(veri.getKmson()!=-13)&&(veri.getKmbas()!=-13)&&(veri.getKmson()!=-13)){
            //imalat doludemektir
            imalat_icon.setImageResource(R.drawable.imalat_d);
        }
        if ((!veri.getAriza().equals(""))){
            verimsizlik_icon.setImageResource(R.drawable.verimsizlik_d);
        }
        if ((veri.getIsmedyaloaded()!=-13)){
            medya_icon.setImageResource(R.drawable.medya_d);
        }*/

    }
    protected void imalat_check(){
        if (!veri.getSektör().equals("")){
            SQLiteHelper database = new SQLiteHelper(L3_aciklama.this);
            String[] databaseverisi = database.ReadSektor(veri.getSektör());
            String[] hatno_dizi = databaseverisi[2].split("--");// sektörler tablosundakş veriler dizilere aktarılıyor ve ayrıştırılıyor
            String[] imalat_dizi = databaseverisi[6].split("--");
            List<String> imalat_list = Arrays.asList(imalat_dizi);
            List<String> hatno_list = Arrays.asList(hatno_dizi);
            String secili_imalat_order =  database.ReadImalatwisim(veri.getImalat())[0];
            System.out.println(secili_imalat_order);// tablodan imlatın string değerine karşılık gelen Txxxx şeklinde olan ifadeyi aldık
            if (imalat_list.contains(secili_imalat_order)){
                //daha önce T li çektiğimiz 2 değer kontrol edildi.
                //imalat ile sektör uyumu kontrol edildi.
                if (hatno_list.contains(String.valueOf(veri.getHatno()))){
                    //hat nonun doğruluğu kontol edildi dizi listeye çevrilip dizinin hatnoyu içerip içermediğine bakıldı
                    if (((Integer.valueOf(databaseverisi[3]))<=(veri.getKmbas()))&&(veri.getKmson()<=(Integer.valueOf(databaseverisi[4])))){
                        //km baslangıç ve bitiş değerleri kntrol edildi taslak verilerine yükleniyor.
                        L1_main l1_main = new L1_main();
                        String tarih = l1_main.getMaintitle()[veri.getPosition()].subSequence(l1_main.getMaintitle()[veri.getPosition()].length()-11,l1_main.getMaintitle()[veri.getPosition()].length()-1).toString();
                        //taslak verilerine veriler server ile güncelleme için gönderildi
                    }else{
                        Toast.makeText(L3_aciklama.this,"Girdiğiniz kilometre bilgisinde hata bulunmaktadır. Lütfen kontrol ediniz",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(L3_aciklama.this,"Girdiğiniz bilgilerde hata bulunmaktadır Lütfen kontrol ediniz",Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(L3_aciklama.this,"Girdiğiniz Bilgilerde hata bulunmaktadır. Lütfen kontrol ediniz.",Toast.LENGTH_LONG).show();
            }
        }

    }

    public void setListView() {
        List<String> aciklamalar = database.ReadAciklamal3(String.valueOf(veri.getKod()),veri.getImalatIsgucuid(),database.ReadGet_Set("KopyaNo"));
        L3_aciklama_adapter adapter = new L3_aciklama_adapter(L3_aciklama.this,aciklamalar);
        listView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,L2_bildiri.class);
        startActivity(intent);
    }
}
