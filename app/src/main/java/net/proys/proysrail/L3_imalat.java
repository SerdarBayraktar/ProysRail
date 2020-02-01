package net.proys.proysrail;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.CountDownTimer;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class L3_imalat extends AppCompatActivity {
    protected String imalat;
    protected EditText baskm;
    protected TextView birimtxt;
    protected EditText bitiskm,farktxt;
    protected int bas,son,kmfark;
    protected String[] arraybas;
    protected String[] arrayson;
    protected ImageView hat1;
    protected ImageView hat2;
    protected ImageView hat3;
    protected ImageView hatn;
    protected ImageView imalat_icon,verimsizlik_icon,aciklama_icon,medya_icon;
    protected LinearLayout imalat_linear,verim_linear,aciklama_linear,medya_linear;
    protected TextView sektörtxt,imalatadi,imalattxt;
    protected ImageView tick;
    Get_Set veri;
    Integer[] kopya_nolar;
    String[] imalat_array;
    String[] mesafe_array;
    String[] km_bas_array;
    String[] km_son_array;
    String[] mesafe_birim_array;
    String[] personel_sayisi_array;
    String[] personel_puantaj_array;
    String[] makine_sayisi_array;
    String[] makine_puantaj_array;
    String[] verim_array;
    String[] ort_verimsiz_sure;
    String[] aciklama_array;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l3_imalat);
        veri = new Get_Set();

        init();
        setWatchers();
        setOnclicklisteners();
        allset4menu();
        setIcons();
        imalat_hatnosenc();
        inheritance();
    }
    public void arraylar(){
        SQLiteHelper database = new SQLiteHelper(this);
        L1_main l1_main = new L1_main();
        String tarih = l1_main.getMaintitle()[veri.getPosition()].subSequence(l1_main.getMaintitle()[veri.getPosition()].length()-11,l1_main.getMaintitle()[veri.getPosition()].length()-1).toString();
        String bildiri = database.ReadBildirilerwIsim(l1_main.getMaintitle()[veri.getPosition()].split(" ")[0]+" "+ l1_main.getMaintitle()[veri.getPosition()].split(" ")[1])[0];
        List[] diziler = database.ReadTaslakfList(String.valueOf(veri.getKod()));
        final String[] imalat_array = Arrays.copyOf(diziler[0].toArray(new String[diziler[0].size()]),diziler[0].toArray(new String[diziler[0].size()]).length,String[].class);
        final String[] mesafe_array = Arrays.copyOf(diziler[1].toArray(new String[diziler[1].size()]),diziler[1].toArray(new String[diziler[1].size()]).length,String[].class);
        final String[] km_bas_array = Arrays.copyOf(diziler[2].toArray(new String[diziler[2].size()]),diziler[2].toArray(new String[diziler[2].size()]).length,String[].class);
        final String[] km_son_array = Arrays.copyOf(diziler[3].toArray(new String[diziler[3].size()]),diziler[3].toArray(new String[diziler[3].size()]).length,String[].class);
        final String[] mesafe_birim_array = Arrays.copyOf(diziler[4].toArray(new String[diziler[4].size()]),diziler[4].toArray(new String[diziler[4].size()]).length,String[].class);
        final Integer[] kopya_nolar = Arrays.copyOf(diziler[5].toArray(new Integer[diziler[5].size()]),diziler[5].toArray(new Integer[diziler[5].size()]).length,Integer[].class);


        List[] puantajdiziler = database.ReadTaslakResourceflist(String.valueOf(veri.getKod()),imalat_array);
        final String[] personel_sayisi_array = Arrays.copyOf(puantajdiziler[0].toArray(new String[puantajdiziler[0].size()]),puantajdiziler[0].toArray(new String[puantajdiziler[0].size()]).length,String[].class);
        final String[] personel_puantaj_array = Arrays.copyOf(puantajdiziler[1].toArray(new String[puantajdiziler[1].size()]),puantajdiziler[1].toArray(new String[puantajdiziler[1].size()]).length,String[].class);
        final String[] makine_sayisi_array = Arrays.copyOf(puantajdiziler[2].toArray(new String[puantajdiziler[2].size()]),puantajdiziler[2].toArray(new String[puantajdiziler[2].size()]).length,String[].class);
        String[] makine_puantaj_array = Arrays.copyOf(puantajdiziler[3].toArray(new String[puantajdiziler[3].size()]),puantajdiziler[3].toArray(new String[puantajdiziler[3].size()]).length,String[].class);
        final String[] verim_array = Arrays.copyOf(puantajdiziler[4].toArray(new String[puantajdiziler[4].size()]),puantajdiziler[4].toArray(new String[puantajdiziler[4].size()]).length,String[].class);
        final String[] ort_verimsiz_sure = Arrays.copyOf(puantajdiziler[5].toArray(new String[puantajdiziler[5].size()]),puantajdiziler[5].toArray(new String[puantajdiziler[5].size()]).length,String[].class);
        String[] aciklama_array = database.ReadTaslakAciklamaflist(bildiri,tarih,imalat_array);
        List<String> medya_list = new ArrayList();
        for (int i = 0 ; i<imalat_array.length;i++){
            medya_list.add("0");
        }
        final String[] medya_array = medya_list.toArray(new String[medya_list.size()]);
    }
    public void init(){

        TextView imalat_txt = findViewById(R.id.imalattxt);
        imalat_txt.setText(String.valueOf(veri.getKod()).substring(13,15)+"."+String.valueOf(veri.getKod()).substring(11,13)+"."+String.valueOf(veri.getKod()).substring(7,11));
        imalatadi = findViewById(R.id.textView7);
        sektörtxt = findViewById(R.id.sektor);
        imalattxt = findViewById(R.id.textimalat);
        birimtxt = findViewById(R.id.birimtxt);
        imalat_icon = findViewById(R.id.imageImalat);
        verimsizlik_icon = findViewById(R.id.imageverim);
        aciklama_icon = findViewById(R.id.imageAciklama);
        medya_icon = findViewById(R.id.imageCamera);
        imalat_linear = findViewById(R.id.imalat_linear);
        verim_linear = findViewById(R.id.verim_linear);
        aciklama_linear = findViewById(R.id.aciklama_linear);
        medya_linear = findViewById(R.id.medya_linear);


        tick = findViewById(R.id.tick);
        hat1 = findViewById(R.id.imageView201);
        hat2 = findViewById(R.id.imageView202);
        hat3 = findViewById(R.id.imageView203);
        hatn = findViewById(R.id.imageView204);
        baskm = findViewById(R.id.editText2);
        bitiskm = findViewById(R.id.editText3);
        farktxt = findViewById(R.id.editText4);
        verimsizlik_icon = findViewById(R.id.imageverim);
        aciklama_icon = findViewById(R.id.imageAciklama);
        medya_icon = findViewById(R.id.imageCamera);
    }
    protected void inheritance(){
        SQLiteHelper database = new SQLiteHelper(this);
        L1_main l1_main = new L1_main();
        // TO-DO
        String tarih = l1_main.getMaintitle()[veri.getPosition()].subSequence(l1_main.getMaintitle()[veri.getPosition()].length()-11,l1_main.getMaintitle()[veri.getPosition()].length()-1).toString();
        String bildiri = database.ReadBildirilerwIsim(l1_main.getMaintitle()[veri.getPosition()].split(" ")[0]+" "+ l1_main.getMaintitle()[veri.getPosition()].split(" ")[1])[0];//sadece 2 kelime olan bildirilere adapte
        List[] diziler = database.ReadTaslakfList(String.valueOf(veri.getKod()));
        imalat_array = Arrays.copyOf(diziler[0].toArray(new String[diziler[0].size()]),diziler[0].toArray(new String[diziler[0].size()]).length,String[].class);
        mesafe_array = Arrays.copyOf(diziler[1].toArray(new String[diziler[1].size()]),diziler[1].toArray(new String[diziler[1].size()]).length,String[].class);
        km_bas_array = Arrays.copyOf(diziler[2].toArray(new String[diziler[2].size()]),diziler[2].toArray(new String[diziler[2].size()]).length,String[].class);
        km_son_array = Arrays.copyOf(diziler[3].toArray(new String[diziler[3].size()]),diziler[3].toArray(new String[diziler[3].size()]).length,String[].class);
        mesafe_birim_array = Arrays.copyOf(diziler[4].toArray(new String[diziler[4].size()]),diziler[4].toArray(new String[diziler[4].size()]).length,String[].class);
        kopya_nolar = Arrays.copyOf(diziler[5].toArray(new Integer[diziler[5].size()]),diziler[5].toArray(new Integer[diziler[5].size()]).length,Integer[].class);
        List[] puantajdiziler = database.ReadTaslakResourceflist(String.valueOf(veri.getKod()),imalat_array);
        personel_sayisi_array = Arrays.copyOf(puantajdiziler[0].toArray(new String[puantajdiziler[0].size()]),puantajdiziler[0].toArray(new String[puantajdiziler[0].size()]).length,String[].class);
        personel_puantaj_array = Arrays.copyOf(puantajdiziler[1].toArray(new String[puantajdiziler[1].size()]),puantajdiziler[1].toArray(new String[puantajdiziler[1].size()]).length,String[].class);
        makine_sayisi_array = Arrays.copyOf(puantajdiziler[2].toArray(new String[puantajdiziler[2].size()]),puantajdiziler[2].toArray(new String[puantajdiziler[2].size()]).length,String[].class);
        makine_puantaj_array = Arrays.copyOf(puantajdiziler[3].toArray(new String[puantajdiziler[3].size()]),puantajdiziler[3].toArray(new String[puantajdiziler[3].size()]).length,String[].class);
        verim_array = Arrays.copyOf(puantajdiziler[4].toArray(new String[puantajdiziler[4].size()]),puantajdiziler[4].toArray(new String[puantajdiziler[4].size()]).length,String[].class);
        ort_verimsiz_sure = Arrays.copyOf(puantajdiziler[5].toArray(new String[puantajdiziler[5].size()]),puantajdiziler[5].toArray(new String[puantajdiziler[5].size()]).length,String[].class);
        aciklama_array = database.ReadTaslakAciklamaflist(bildiri,tarih,imalat_array);
        List<String> medya_list = new ArrayList();
        for (int i = 0 ; i<imalat_array.length;i++){
            medya_list.add("0");
        }
        final String[] medya_array = medya_list.toArray(new String[medya_list.size()]);
       // birimtxt.setText(String.valueOf(database.ReadImalatwisim(veri.getImalat())[2]));
     //   String tarih = l1_main.getMaintitle()[veri.getPosition()].subSequence(l1_main.getMaintitle()[veri.getPosition()].length()-11,l1_main.getMaintitle()[veri.getPosition()].length()-1).toString();
       // String bildiri = l1_main.getMaintitle()[veri.getPosition()].split(" ")[0]+" "+ l1_main.getMaintitle()[veri.getPosition()].split(" ")[1];

        imalatadi.setText(veri.getImalat());
        sektörtxt.setText(String.valueOf(database.ReadTaslakfinheritance(String.valueOf(veri.getKod()),
                tarih,
                veri.getImalat(),
                Integer.valueOf(km_bas_array[veri.getPositionL2()]),
                Integer.valueOf(km_son_array[veri.getPositionL2()]))[0]));//TODO position l2 yi + da len of list yap
        //check system with this code System.out.println("  1  "+bildiri+"  1  "+tarih+ "  1  " + veri.getImalat()+"  1  "+km_bas_array[veri.getPositionL2()]+"  1  "+km_son_array[veri.getPositionL2()]);
        int hat_no = Integer.valueOf(database.ReadTaslakfinheritance(String.valueOf(veri.getKod()),tarih,veri.getImalat(),Integer.valueOf(km_bas_array[veri.getPositionL2()]),
                Integer.valueOf(km_son_array[veri.getPositionL2()]))[1]);
        if (hat_no==1){
            hat1.setImageResource(R.drawable.numberonline_1);
        }else if (hat_no==2){
            hat2.setImageResource(R.drawable.numberonline_2);
        }else if (hat_no==3){
            hat3.setImageResource(R.drawable.numberonline_3);
        }

        if (!km_bas_array[veri.getPositionL2()].equals("-1")){
            baskm.setText(String.valueOf(km_bas_array[veri.getPositionL2()]));
        }if (!km_son_array[veri.getPositionL2()].equals("-1")){
            bitiskm.setText(String.valueOf(km_son_array[veri.getPositionL2()]));
        }if (!mesafe_array[veri.getPositionL2()].equals("-1")){
            farktxt.setText(String.valueOf(mesafe_array[veri.getPositionL2()]));
        }
        birimtxt.setText(String.valueOf(mesafe_birim_array[veri.getPositionL2()]));
    }
    protected void imalat_hatnosenc(){
        if ((!veri.getImalat().equals(""))&&(!veri.getSektör().equals(""))){
            SQLiteHelper database = new SQLiteHelper(L3_imalat.this);
            for (int i = 1 ; i<=8;i++){
                if (database.ReadSektor(100+i)[5].equals("1")){
                    if (database.ReadSektor(100+i)[0].equals(veri.getSektör())){
                        String[] hatnolar = database.ReadSektor(100+i)[2].split("--");
                        if (hatnolar.length==1){
                            veri.setHatno(Integer.valueOf(hatnolar[0]));
                            L1_main l1_main = new L1_main();
                            String tarih = l1_main.getMaintitle()[veri.getPosition()].subSequence(l1_main.getMaintitle()[veri.getPosition()].length()-11,l1_main.getMaintitle()[veri.getPosition()].length()-1).toString();
                            String bildiri = l1_main.getMaintitle()[veri.getPosition()].split(" ")[0]+" "+ l1_main.getMaintitle()[veri.getPosition()].split(" ")[1];
                            if (hatnolar[0].equals("1")){
                                veri.setHatno(1);
                                database.UpdateTaslak(String.valueOf(veri.getKod()),tarih,veri.getImalat(),kopya_nolar[veri.getPositionL2()],"HAT_NO",1);
                                hatn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        System.out.println("yapmıyorum");
                                    }
                                });
                                hat1.setImageResource(R.drawable.numberonline_1);
                            }else if (hatnolar[0].equals("2")){
                                veri.setHatno(2);
                                database.UpdateTaslak(String.valueOf(veri.getKod()),tarih,veri.getImalat(),kopya_nolar[veri.getPositionL2()],"HAT_NO",2);
                                hatn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        System.out.println("yapmıyorum");
                                    }
                                });
                                hat2.setImageResource(R.drawable.numberonline_2);
                            }else if (hatnolar[0].equals("3")){
                                veri.setHatno(3);
                                database.UpdateTaslak(String.valueOf(veri.getKod()),tarih,veri.getImalat(),kopya_nolar[veri.getPositionL2()],"HAT_NO",3);
                                hatn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        System.out.println("yapmıyorum");
                                    }
                                });
                                hat3.setImageResource(R.drawable.numberonline_3);
                            }
                        }
                        List list = new ArrayList();
                        Collections.addAll(list,hatnolar);
                        if (!list.contains("1")){
                            hat1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    System.out.println("yapmıorum");
                                }
                            });

                        }
                        if (!list.contains("2")){
                            hat2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    System.out.println("yapmıorum");
                                }
                            });

                        }if (!list.contains("3")){
                            hat3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    System.out.println("yapmıorum");
                                }
                            });

                        }
                    }
                }
            }
        }
    }
    protected void setOnclicklisteners(){
        final SQLiteHelper database = new SQLiteHelper(L3_imalat.this);
        L1_main l1_main = new L1_main();
        final String tarih = l1_main.getMaintitle()[veri.getPosition()].subSequence(l1_main.getMaintitle()[veri.getPosition()].length()-11,l1_main.getMaintitle()[veri.getPosition()].length()-1).toString();
        final String bildiri = database.ReadBildirilerwIsim(l1_main.getMaintitle()[veri.getPosition()].split(" ")[0]+" "+ l1_main.getMaintitle()[veri.getPosition()].split(" ")[1])[0];

        hat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hat_no = Integer.valueOf(database.ReadTaslakfinheritance(String.valueOf(veri.getKod()),tarih,veri.getImalat(),Integer.valueOf(km_bas_array[veri.getPositionL2()]),
                        Integer.valueOf(km_son_array[veri.getPositionL2()]))[1]);
                hat1.setImageResource(R.drawable.number_1);
                hat2.setImageResource(R.drawable.number2);
                hat3.setImageResource(R.drawable.number_2);
                veri.setHatno(1);
                database.UpdateTaslak(String.valueOf(veri.getKod()),tarih,veri.getImalat(),kopya_nolar[veri.getPositionL2()],"HAT_NO",1);
                hat1.setImageResource(R.drawable.numberonline_1);


            }
        });
        hat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hat_no = Integer.valueOf(database.ReadTaslakfinheritance(String.valueOf(veri.getKod()),tarih,veri.getImalat(),Integer.valueOf(km_bas_array[veri.getPositionL2()]),
                        Integer.valueOf(km_son_array[veri.getPositionL2()]))[1]);
                hat1.setImageResource(R.drawable.number_1);
                hat2.setImageResource(R.drawable.number2);
                hat3.setImageResource(R.drawable.number_2);
                database.UpdateTaslak(String.valueOf(veri.getKod()),tarih,veri.getImalat(),kopya_nolar[veri.getPositionL2()],"HAT_NO",2);

                veri.setHatno(2);
                hat2.setImageResource(R.drawable.numberonline_2);
            }
        });
        hat3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hat_no = Integer.valueOf(database.ReadTaslakfinheritance(String.valueOf(veri.getKod()),tarih,veri.getImalat(),Integer.valueOf(km_bas_array[veri.getPositionL2()]),
                        Integer.valueOf(km_son_array[veri.getPositionL2()]))[1]);

                hat1.setImageResource(R.drawable.number_1);
                hat2.setImageResource(R.drawable.number2);
                hat3.setImageResource(R.drawable.number_2);
                veri.setHatno(3);
                database.UpdateTaslak(String.valueOf(veri.getKod()),tarih,veri.getImalat(),kopya_nolar[veri.getPositionL2()],"HAT_NO",3);
                hat3.setImageResource(R.drawable.numberonline_3);

            }
        });
        tick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imalat_check();
                Intent intent = new Intent(L3_imalat.this,L2_bildiri.class);
                startActivity(intent);

            }
        });
        sektörtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L3_imalat.this,L4_sektor.class);
                startActivity(intent);
            }
        });/*
        imalatadi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L3_imalat.this,L4_imalatlar.class);
                intent.putExtra("tip","imalat");
                intent.putExtra("version","old");
                intent.putExtra("kopyano",String.valueOf(kopya_nolar[veri.getPositionL2()]));
                intent.putExtra("imalat",veri.getImalat());
                startActivity(intent);
            }
        });*/

    }
    protected void imalat_check(){
        if (!veri.getSektör().equals("")){
            SQLiteHelper database = new SQLiteHelper(L3_imalat.this);
            String[] databaseverisi = database.ReadSektor(veri.getSektör());
            String[] hatno_dizi = databaseverisi[2].split("--");// sektörler tablosundakş veriler dizilere aktarılıyor ve ayrıştırılıyor
            String[] imalat_dizi = databaseverisi[6].split("--");
            List<String> imalat_list = Arrays.asList(imalat_dizi);
            List<String> hatno_list = Arrays.asList(hatno_dizi);
            String secili_imalat_order =  database.ReadImalatwisim(veri.getImalat())[0];
            System.out.println(secili_imalat_order);// tablodan imlatın string değerine karşılık gelen Txxxx şeklinde olan ifadeyi aldık
            if (veri.getSektör().equals("Balastlı Ana Hat")){
                System.out.println(database.ReadSektorfkmCheck("ana-hat"));

            }else{
            if (imalat_list.contains(secili_imalat_order)){
                //daha önce T li çektiğimiz 2 değer kontrol edildi.
                //imalat ile sektör uyumu kontrol edildi.
                if (hatno_list.contains(String.valueOf(veri.getHatno()))){
                    //hat nonun doğruluğu kontol edildi dizi listeye çevrilip dizinin hatnoyu içerip içermediğine bakıldı
                    if (((Integer.valueOf(databaseverisi[3]))<=(veri.getKmbas()))&&(veri.getKmson()<=(Integer.valueOf(databaseverisi[4])))){
                        //km baslangıç ve bitiş değerleri kntrol edildi taslak verilerine yükleniyor.
                        //sql de kontrol


                        //taslak verilerine veriler server ile güncelleme için gönderildi
                    }else{
                        Toast.makeText(L3_imalat.this,"Girdiğiniz kilometre bilgisinde hata bulunmaktadır. Lütfen kontrol ediniz",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(L3_imalat.this,"Girdiğiniz bilgilerde hata bulunmaktadır Lütfen kontrol ediniz",Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(L3_imalat.this,"Girdiğiniz Bilgilerde hata bulunmaktadır. Lütfen kontrol ediniz.",Toast.LENGTH_LONG).show();
            }
        }
        }

    }
    protected void allset4menu(){

        verim_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L3_imalat.this,L3_verimsizlik.class);
                imalat_check();
                startActivity(intent);

            }
        });
        medya_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L3_imalat.this,L3_medya.class);
                imalat_check();
                startActivity(intent);

            }
        });
        aciklama_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L3_imalat.this,L3_aciklama.class);
                imalat_check();
                startActivity(intent);

            }
        });


        }
    protected void setWatchers(){
        TextWatcher watcher = new TextWatcher() {

            private static final int TOTAL_SYMBOLS = 7; // size of pattern 0000-0000-0000-0000
            private static final int TOTAL_DIGITS = 6; // max numbers of digits in pattern: 0000 x 4
            private static final int DIVIDER_MODULO = 4; // means divider position is every 5th symbol beginning with 1
            private static final int DIVIDER_POSITION = DIVIDER_MODULO - 1; // means divider position is every 4th symbol beginning with 0
            private static final char DIVIDER = '+';

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // noop
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (veri.getKmfarkOnline()!=1) {
                    veri.setKmfarkOnline(0);
                    if (s.length() == 7) {
                        arraybas = baskm.getText().toString().split("\\+");
                        arrayson = bitiskm.getText().toString().split("\\+");
                        if ((arraybas.length == 2) & (arrayson.length == 2)) {
                            bas = (Integer.parseInt(arraybas[0]) * 1000) + (Integer.parseInt(arraybas[1]));
                            son = (Integer.parseInt(arrayson[0]) * 1000) + (Integer.parseInt(arrayson[1]));
                            kmfark = Math.abs(bas - son);
                            if (kmfark > 10000) {
                                Toast.makeText(L3_imalat.this, "Oluşan mesafe değeri çok büyük Lütfen kontrol ediniz.", Toast.LENGTH_LONG).show();

                            } else {
                                veri.setKmbas(bas);
                                veri.setKmson(son);
                                veri.setKmfark(kmfark);
                                SQLiteHelper database = new SQLiteHelper(L3_imalat.this);
                                L1_main l1_main = new L1_main();
                                String tarih = l1_main.getMaintitle()[veri.getPosition()].subSequence(l1_main.getMaintitle()[veri.getPosition()].length()-11,l1_main.getMaintitle()[veri.getPosition()].length()-1).toString();
                                String bildiri = l1_main.getMaintitle()[veri.getPosition()].split(" ")[0]+" "+ l1_main.getMaintitle()[veri.getPosition()].split(" ")[1];
                                System.out.println("bildiri" + bildiri+ "  " + tarih);
                                database.UpdateTaslak(String.valueOf(veri.getKod()),tarih,veri.getImalat(),kopya_nolar[veri.getPositionL2()],"KM_BAS",bas);
                                database.UpdateTaslak(String.valueOf(veri.getKod()),tarih,veri.getImalat(),kopya_nolar[veri.getPositionL2()],"KM_SON",son);
                                database.UpdateTaslak(String.valueOf(veri.getKod()),tarih,veri.getImalat(),kopya_nolar[veri.getPositionL2()],"MESAFE",kmfark);
                                String kmfarktxt = Integer.toString(kmfark);
                                veri.setKmOnline(1);
                                farktxt.setText(kmfarktxt);
                            }
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!isInputCorrect(s, TOTAL_SYMBOLS, DIVIDER_MODULO, DIVIDER)) {
                    s.replace(0, s.length(), buildCorrectString(getDigitArray(s, TOTAL_DIGITS), DIVIDER_POSITION, DIVIDER));
                }
            }
            private boolean isInputCorrect(Editable s, int totalSymbols, int dividerModulo, char divider) {
                boolean isCorrect = s.length() <= totalSymbols; // check size of entered string
                for (int i = 0; i < s.length(); i++) { // check that every element is right
                    if (i > 0 && (i + 1) % dividerModulo == 0) {
                        isCorrect &= divider == s.charAt(i);
                    } else {
                        isCorrect &= Character.isDigit(s.charAt(i));
                    }
                }
                return isCorrect;
            }
            private String buildCorrectString(char[] digits, int dividerPosition, char divider) {
                final StringBuilder formatted = new StringBuilder();

                for (int i = 0; i < digits.length; i++) {
                    if (digits[i] != 0) {
                        formatted.append(digits[i]);
                        if ((i > 0) && (i < (digits.length - 1)) && (((i + 1) % dividerPosition) == 0)) {
                            formatted.append(divider);
                        }
                    }
                }

                return formatted.toString();
            }
            private char[] getDigitArray(final Editable s, final int size) {
                char[] digits = new char[size];
                int index = 0;
                for (int i = 0; i < s.length() && index < size; i++) {
                    char current = s.charAt(i);
                    if (Character.isDigit(current)) {
                        digits[index] = current;
                        index++;
                    }
                }
                return digits;
            }

        };
        TextWatcher watcher1 = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (veri.getKmOnline() == 0) {
                    if (s.length() <= 6) {
                        if (!s.toString().equals("")) {
                            if (Integer.valueOf(s.toString()) < 10000) {
                                if (baskm.getText().toString().length() == 7) {
                                    arraybas = baskm.getText().toString().split("\\+");
                                    if (arraybas.length == 2) {//mesafe girilince sonkmyi hesaplaması için gerekli işlemler
                                        bas = (Integer.parseInt(arraybas[0]) * 1000) + (Integer.parseInt(arraybas[1]));
                                        int son = bas + Integer.valueOf(s.toString());
                                        int sıfır = 6 - (String.valueOf(son).length());
                                        StringBuilder stringBuilder = new StringBuilder();
                                        for (int i = 0; i < sıfır; i++) {//son değişkeni km bitişin sayı değeridir. + nın konumunu doğru tespit edebilmek için
                                            // 6 karaktere tamamlayana kadar başına 0 ekledim ardından sonuna son değişkeni ekledim bu sayede 7 haneli + lı gösterimi elde etttin
                                            stringBuilder.append("0");
                                        }
                                        String sonkm = stringBuilder.toString();
                                        sonkm = sonkm + String.valueOf(son);
                                        //sonkm = sonkm.subSequence(0, 3).toString() + "+" + sonkm.subSequence(3, 6).toString();
                                        veri.setKmfarkOnline(1);
                                        veri.setKmfark(Integer.valueOf(s.toString()));
                                        veri.setKmbas(bas);
                                        SQLiteHelper database = new SQLiteHelper(L3_imalat.this);
                                        L1_main l1_main = new L1_main();
                                        String tarih = l1_main.getMaintitle()[veri.getPosition()].subSequence(l1_main.getMaintitle()[veri.getPosition()].length()-11,l1_main.getMaintitle()[veri.getPosition()].length()-1).toString();
                                        String bildiri = l1_main.getMaintitle()[veri.getPosition()].split(" ")[0]+" "+ l1_main.getMaintitle()[veri.getPosition()].split(" ")[1];
                                        database.UpdateTaslak(String.valueOf(veri.getKod()),tarih,veri.getImalat(),kopya_nolar[veri.getPositionL2()],"KM_SON",son);
                                        database.UpdateTaslak(String.valueOf(veri.getKod()),tarih,veri.getImalat(),kopya_nolar[veri.getPositionL2()],"MESAFE",Integer.valueOf(s.toString()));
                                        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
                                        symbols.setGroupingSeparator('+');
                                        NumberFormat nf = new DecimalFormat("000,000", symbols);
                                        bitiskm.setText(nf.format(Integer.valueOf(sonkm)));

                                        new CountDownTimer(150,500){
                                            @Override
                                            public void onTick(long millisUntilFinished) {

                                            }

                                            @Override
                                            public void onFinish() {
                                                Get_Set data = new Get_Set();
                                                data.setKmfarkOnline(0);

                                            }
                                        }.start();

                                    }
                                }
                            } else {
                                farktxt.setText("9999");
                                Toast.makeText(L3_imalat.this, "Girdiğiniz mesafe değeri çok yüksek lütfen kontrol ediniz.", Toast.LENGTH_LONG).show();
                            }
                        }
                    } else if (s.length() == 100) {
                        int fark = Integer.valueOf(s.toString().subSequence(0, 1).toString() + s.toString().subSequence(2, 5).toString());
                        if (baskm.getText().toString().length() == 7) {
                            arraybas = baskm.getText().toString().split("\\+");
                            if (arraybas.length == 2) {//mesafe girilince sonkmyi hesaplaması için gerekli işlemler
                                bas = (Integer.parseInt(arraybas[0]) * 1000) + (Integer.parseInt(arraybas[1]));
                                int son = bas + Integer.valueOf(fark);
                                int sıfır = 6 - (String.valueOf(son).length());
                                StringBuilder stringBuilder = new StringBuilder();
                                for (int i = 0; i < sıfır; i++) {//son değişkeni km bitişin sayı değeridir. + nın konumunu doğru tespit edebilmek için
                                    // 6 karaktere tamamlayana kadar başına 0 ekledim ardından sonuna son değişkeni ekledim bu sayede 7 haneli + lı gösterimi elde etttin
                                    stringBuilder.append("0");
                                }
                                String sonkm = stringBuilder.toString();
                                sonkm = sonkm + String.valueOf(son);
                                //sonkm = sonkm.subSequence(0, 3).toString() + "+" + sonkm.subSequence(3, 6).toString();
                                veri.setKmfarkOnline(1);
                                DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
                                symbols.setGroupingSeparator('+');
                                NumberFormat nf = new DecimalFormat("000,000", symbols);
                                bitiskm.setText(nf.format(sonkm));

                            }
                        }

                    }
                }else if (veri.getKmOnline()==1){
                    veri.setKmOnline(0);
                    //do nothing çok yakında değişti caten
                }
            }
        };
        TextWatcher watcher2 = new TextWatcher() {

            private static final int TOTAL_SYMBOLS = 7; // size of pattern 0000-0000-0000-0000
            private static final int TOTAL_DIGITS = 6; // max numbers of digits in pattern: 0000 x 4
            private static final int DIVIDER_MODULO = 4; // means divider position is every 5th symbol beginning with 1
            private static final int DIVIDER_POSITION = DIVIDER_MODULO - 1; // means divider position is every 4th symbol beginning with 0
            private static final char DIVIDER = '+';

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // noop
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (veri.getKmfarkOnline()==0) {
                if (s.length() ==7){
                    arraybas = baskm.getText().toString().split("\\+");
                    arrayson = bitiskm.getText().toString().split("\\+");
                    if ((arraybas.length==2)&(arrayson.length==2)) {
                        bas = (Integer.parseInt(arraybas[0]) * 1000) + (Integer.parseInt(arraybas[1]));
                        son = (Integer.parseInt(arrayson[0]) * 1000) + (Integer.parseInt(arrayson[1]));
                        kmfark = Math.abs(bas - son);
                        if (kmfark > 10000) {
                            Toast.makeText(L3_imalat.this, "Oluşan mesafe değeri çok büyük Lütfen kontrol ediniz.", Toast.LENGTH_LONG).show();


                        } else {
                            veri.setKmbas(bas);
                            veri.setKmson(son);
                            veri.setKmfark(kmfark);

                            SQLiteHelper database = new SQLiteHelper(L3_imalat.this);
                            L1_main l1_main = new L1_main();
                            String tarih = l1_main.getMaintitle()[veri.getPosition()].subSequence(l1_main.getMaintitle()[veri.getPosition()].length()-11,l1_main.getMaintitle()[veri.getPosition()].length()-1).toString();
                            String bildiri = l1_main.getMaintitle()[veri.getPosition()].split(" ")[0]+" "+ l1_main.getMaintitle()[veri.getPosition()].split(" ")[1];
                           database.UpdateTaslak(String.valueOf(veri.getKod()),tarih,veri.getImalat(),kopya_nolar[veri.getPositionL2()],"KM_BAS",bas);
                            database.UpdateTaslak(String.valueOf(veri.getKod()),tarih,veri.getImalat(),kopya_nolar[veri.getPositionL2()],"KM_SON",son);
                            database.UpdateTaslak(String.valueOf(veri.getKod()),tarih,veri.getImalat(),kopya_nolar[veri.getPositionL2()],"MESAFE",kmfark);
                            String kmfarktxt = Integer.toString(kmfark);

                            veri.setKmOnline(1);
                            farktxt.setText(kmfarktxt);

                        }
                    }
                }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!isInputCorrect(s, TOTAL_SYMBOLS, DIVIDER_MODULO, DIVIDER)) {
                    s.replace(0, s.length(), buildCorrectString(getDigitArray(s, TOTAL_DIGITS), DIVIDER_POSITION, DIVIDER));
                }
            }

            private boolean isInputCorrect(Editable s, int totalSymbols, int dividerModulo, char divider) {
                boolean isCorrect = s.length() <= totalSymbols; // check size of entered string
                for (int i = 0; i < s.length(); i++) { // check that every element is right
                    if (i > 0 && (i + 1) % dividerModulo == 0) {
                        isCorrect &= divider == s.charAt(i);
                    } else {
                        isCorrect &= Character.isDigit(s.charAt(i));
                    }
                }
                return isCorrect;
            }

            private String buildCorrectString(char[] digits, int dividerPosition, char divider) {
                final StringBuilder formatted = new StringBuilder();

                for (int i = 0; i < digits.length; i++) {
                    if (digits[i] != 0) {
                        formatted.append(digits[i]);
                        if ((i > 0) && (i < (digits.length - 1)) && (((i + 1) % dividerPosition) == 0)) {
                            formatted.append(divider);
                        }
                    }
                }

                return formatted.toString();
            }

            private char[] getDigitArray(final Editable s, final int size) {
                char[] digits = new char[size];
                int index = 0;
                for (int i = 0; i < s.length() && index < size; i++) {
                    char current = s.charAt(i);
                    if (Character.isDigit(current)) {
                        digits[index] = current;
                        index++;
                    }
                }
                return digits;
            }

        };
        baskm.addTextChangedListener(watcher);
    bitiskm.addTextChangedListener(watcher2);
    farktxt.addTextChangedListener(watcher1);
    }
    protected void setIcons(){
        imalat_icon.setImageResource(R.drawable.imalat_o);//set icon color
        Typeface typeface = getResources().getFont(R.font.opensans_semibold);
        imalattxt.setTypeface(typeface);
        imalattxt.setTextColor(getResources().getColor(R.color.text_color_yellow));
        /*if ((!veri.getAriza().equals(""))){
            verimsizlik_icon.setImageResource(R.drawable.verimsizlik_d);
        }
        if ((!veri.getAciklamalar().equals(""))){
            aciklama_icon.setImageResource(R.drawable.aciklama_d);
        }if ((veri.getIsmedyaloaded()!=-13)){
            medya_icon.setImageResource(R.drawable.medya_d);
        }*/


    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,L2_bildiri.class);
        startActivity(intent);
    }
}