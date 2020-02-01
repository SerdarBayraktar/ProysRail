package net.proys.proysrail;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class L3_verimsizlik extends AppCompatActivity {
    protected EditText ariza_edit;
    Get_Set veri;
    protected ImageView ekleme_butonu,tick;
    protected ImageView aciklama_icon;
    protected ImageView ilerleme_icon,medya_icon,imalat_icon,verimsizlik_icon;
    protected LinearLayout imalat_linear,verim_linear,aciklama_linear,medya_linear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l3_verimsizlik);
        veri = new Get_Set();
        init();
        setOnclickevents();
        all4setmenu();
        setIcons();
        setListview();
    }
    protected void init(){

        TextView imalattxt = findViewById(R.id.imalattxt);
        imalattxt.setText(String.valueOf(veri.getKod()).substring(13,15)+"."+String.valueOf(veri.getKod()).substring(11,13)+"."+String.valueOf(veri.getKod()).substring(7,11));
        tick = findViewById(R.id.tick);
        //ariza_edit = findViewById(R.id.ariza_edit);
        ekleme_butonu = findViewById(R.id.ariza_ekleme);
        aciklama_icon = findViewById(R.id.imageAciklama);
        ilerleme_icon = findViewById(R.id.ilerleme_icon);
        verimsizlik_icon = findViewById(R.id.imageverim);

        imalat_icon = findViewById(R.id.imageImalat);
        medya_icon = findViewById(R.id.imageCamera);
        imalat_linear = findViewById(R.id.imalat_linear);
        verim_linear = findViewById(R.id.verim_linear);
        aciklama_linear = findViewById(R.id.aciklama_linear);
        medya_linear = findViewById(R.id.medya_linear);
    }
    protected void setListview(){
        ListView listView = findViewById(R.id.listview);
        SQLiteHelper database = new SQLiteHelper(L3_verimsizlik.this);
        List[] verimsizlik_listler = database.ReadEtkenListesiforlist(String.valueOf(veri.getKod()),database.ReadGet_Set("ImalatId"));
        String[] etken = Arrays.copyOf(verimsizlik_listler[0].toArray(new String[verimsizlik_listler[0].size()]),verimsizlik_listler[0].toArray(new String[verimsizlik_listler[0].size()]).length,String[].class);
        String[] deger = Arrays.copyOf(verimsizlik_listler[1].toArray(new String[verimsizlik_listler[0].size()]),verimsizlik_listler[1].toArray(new String[verimsizlik_listler[1].size()]).length,String[].class);
        L3_verimsizlik_adapter l3_verimsizlik_adapter = new L3_verimsizlik_adapter(L3_verimsizlik.this,etken,deger);
        listView.setAdapter(l3_verimsizlik_adapter);
    }
    protected void setOnclickevents(){
        tick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imalat_check();
                Intent intent = new Intent(L3_verimsizlik.this,L2_bildiri.class);
                startActivity(intent);

            }
        });
       /* ariza_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });*/
        ekleme_butonu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(L3_verimsizlik.this,L4_verimsizlik.class);
                startActivity(intent);

            }
        });

        ilerleme_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L3_verimsizlik.this,L3_aciklama.class);
                startActivity(intent);
            }
        });

    }
    protected void all4setmenu(){
            imalat_linear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(L3_verimsizlik.this,L3_imalat.class);
                    startActivity(intent);
                }
            });
            medya_linear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(L3_verimsizlik.this,L3_medya.class);
                    startActivity(intent);
                }
            });
            aciklama_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L3_verimsizlik.this,L3_aciklama.class);
                startActivity(intent);
            }
        });

        }
    protected void setIcons(){
        verimsizlik_icon.setImageResource(R.drawable.verimsizlik_o);
        /*if ((!veri.getImalat().equals(""))&&(!veri.getSektör().equals(""))&&(veri.getHatno()!=-13)&&(veri.getKmson()!=-13)&&(veri.getKmbas()!=-13)&&(veri.getKmson()!=-13)){
            //imalat doludemektir
            imalat_icon.setImageResource(R.drawable.imalat_d);
        }
        if ((!veri.getAciklamalar().equals(""))){
            aciklama_icon.setImageResource(R.drawable.aciklama_d);
        }
        if ((veri.getIsmedyaloaded()!=-13)){
            medya_icon.setImageResource(R.drawable.medya_d);
        }*/

    }
    protected void imalat_check(){
        if (!veri.getSektör().equals("")){
            SQLiteHelper database = new SQLiteHelper(L3_verimsizlik.this);
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
                        Toast.makeText(L3_verimsizlik.this,"Girdiğiniz kilometre bilgisinde hata bulunmaktadır. Lütfen kontrol ediniz",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(L3_verimsizlik.this,"Girdiğiniz bilgilerde hata bulunmaktadır Lütfen kontrol ediniz",Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(L3_verimsizlik.this,"Girdiğiniz Bilgilerde hata bulunmaktadır. Lütfen kontrol ediniz.",Toast.LENGTH_LONG).show();
            }
        }

    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(L3_verimsizlik.this,L2_bildiri.class);
        startActivity(intent);
    }
}