package net.proys.proysrail;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class L3_medya extends AppCompatActivity {
    protected ImageView imalat_icon,verimsizlik_icon,aciklama_icon,medya_icon,ekleme_icon;
    protected LinearLayout imalat_linear,verim_linear,aciklama_linear,medya_linear;

    Get_Set veri;
    protected ImageView tick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l3_medya);
        onBackPressed();
        Toast.makeText(L3_medya.this, "Henüz hazır değil.", Toast.LENGTH_SHORT).show();
/*
        veri = new Get_Set();
        init();
        allset4menu();
        setIcons();
        setOnclickEvents();*/

    }
    protected void init(){
        ekleme_icon = findViewById(R.id.medya_ekleme);
        tick = findViewById(R.id.tick);
        imalat_linear = findViewById(R.id.imalat_linear);
        verim_linear = findViewById(R.id.verim_linear);
        aciklama_linear = findViewById(R.id.aciklama_linear);
        medya_linear = findViewById(R.id.medya_linear);
        imalat_icon = findViewById(R.id.imageImalat);
        verimsizlik_icon = findViewById(R.id.imageverim);
        aciklama_icon = findViewById(R.id.imageAciklama);
        medya_icon = findViewById(R.id.imageCamera);


    }
    protected void setOnclickEvents(){
        ekleme_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L3_medya.this,L4_medya.class);
                startActivity(intent);
            }
        });
        tick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L3_medya.this,L2_bildiri.class);
                startActivity(intent);
            }
        });
    }
    protected void allset4menu(){

        imalat_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L3_medya.this,L3_imalat.class);
                startActivity(intent);
            }
        });
        verim_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L3_medya.this,L3_verimsizlik.class);
                startActivity(intent);
            }
        });
        aciklama_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L3_medya.this,L3_aciklama.class);
                startActivity(intent);
            }
        });

    }
    protected void setIcons(){
        medya_icon.setImageResource(R.drawable.medya_o);
        /*if ((!veri.getImalat().equals(""))&&(!veri.getSektör().equals(""))&&(veri.getHatno()!=-13)&&(veri.getKmson()!=-13)&&(veri.getKmbas()!=-13)&&(veri.getKmson()!=-13)){
            //imalat doludemektir
            imalat_icon.setImageResource(R.drawable.imalat_d);
        }
        if ((!veri.getAriza().equals(""))){
            verimsizlik_icon.setImageResource(R.drawable.verimsizlik_d);
        }
        if ((!veri.getAciklamalar().equals(""))){
            aciklama_icon.setImageResource(R.drawable.aciklama_d);
        }*/

    }
    protected void imalat_check(){
        if (!veri.getSektör().equals("")){
            SQLiteHelper database = new SQLiteHelper(L3_medya.this);
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
                        Toast.makeText(L3_medya.this,"Girdiğiniz kilometre bilgisinde hata bulunmaktadır. Lütfen kontrol ediniz",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(L3_medya.this,"Girdiğiniz bilgilerde hata bulunmaktadır Lütfen kontrol ediniz",Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(L3_medya.this,"Girdiğiniz Bilgilerde hata bulunmaktadır. Lütfen kontrol ediniz.",Toast.LENGTH_LONG).show();
            }
        }

    }
/*
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,L2_bildiri.class);
        startActivity(intent);
    }*/


}
