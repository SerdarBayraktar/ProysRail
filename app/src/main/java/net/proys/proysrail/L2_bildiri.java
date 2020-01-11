package net.proys.proysrail;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class L2_bildiri extends AppCompatActivity {
    ImageView ekle;
    ImageView isci_icon,imalat_icon,makine_icon,malzeme_icon,aciklama_icon,medya_icon,sent;
    LinearLayout isci_linear,imalat_linear,makine_linear,malzeme_linear,aciklama_linear,medya_linear;
    TextView imalat_txt;
    Get_Set veri;
    protected String[] imalat_array;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l2_bildiri);
        init();

        allset4icon();
        setListView();
        setOnclickevents();

    }
    private void init(){

        TextView imalattxt = findViewById(R.id.imalattxt);
        imalattxt.setText(String.valueOf(veri.getKod()).substring(13,15)+"."+String.valueOf(veri.getKod()).substring(11,13)+"."+String.valueOf(veri.getKod()).substring(7,11));
        sent = findViewById(R.id.sent);
        ekle = findViewById(R.id.imageView100);
        isci_icon = findViewById(R.id.imageisci);
        veri = new Get_Set();
        imalat_icon = findViewById(R.id.imageImalat);
        makine_icon = findViewById(R.id.imageMakine);
        malzeme_icon = findViewById(R.id.imageMalzeme);
        aciklama_icon = findViewById(R.id.imageAciklama);
        medya_icon = findViewById(R.id.imageCamera);
        imalat_txt = findViewById(R.id.imalat_txt);
        imalat_linear = findViewById(R.id.imalat_linear);
        isci_linear = findViewById(R.id.isgucu_linear);
        malzeme_linear = findViewById(R.id.malzeme_linear);
        makine_linear = findViewById(R.id.makine_linear);
        aciklama_linear = findViewById(R.id.aciklama_linear);
        medya_linear = findViewById(R.id.medya_linear);

    }
    protected void allset4icon(){
        imalat_icon.setImageResource(R.drawable.l2_ilerleme_o);
        imalat_txt.setTextColor(getResources().getColor(R.color.text_color_yellow));
        Typeface typeface = getResources().getFont(R.font.opensans_semibold);
        imalat_txt.setTypeface(typeface);
        if (!veri.getAciklamalar().equals("")){
            aciklama_icon.setImageResource(R.drawable.l2_aciklama_d);
        }


    }
    protected void setOnclickevents(){
        ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L2_bildiri.this,L4_imalatlar.class);
                veri.setPositionL2(imalat_array.length);
                intent.putExtra("tip","imalat");
                intent.putExtra("version","new");
                //SQLiteHelper database = new SQLiteHelper(L2_bildiri.this);
                //database.WriteTaslak(veri.getKod(),"tarih","",0,"",0,0,0,0,"",0);//todo tarih ayarla
                startActivity(intent);
            }
        });
        isci_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L2_bildiri.this,l2_is_gucu.class);
                startActivity(intent);
            }
        });
        aciklama_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L2_bildiri.this,L2_aciklama.class);
                startActivity(intent);
            }
        });
        malzeme_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(L2_bildiri.this,"Henüz hazır değil.",Toast.LENGTH_SHORT).show();
            }
        });
        medya_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L2_bildiri.this,L2_medya.class);
                startActivity(intent);
            }
        });
        makine_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L2_bildiri.this,L2_makine.class);
                startActivity(intent);
            }
        });
        sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*L1_main l1_main = new L1_main();
                veri = new Get_Set();
                SQLiteHelper database = new SQLiteHelper(L2_bildiri.this);
                String tarih = l1_main.getMaintitle()[veri.getPosition()].subSequence(l1_main.getMaintitle()[veri.getPosition()].length()-11,l1_main.getMaintitle()[veri.getPosition()].length()-1).toString();
                String bildiri = database.ReadBildirilerwIsim(l1_main.getMaintitle()[veri.getPosition()].split(" ")[0]+" "+ l1_main.getMaintitle()[veri.getPosition()].split(" ")[1])[0];
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
                SimpleDateFormat sdf1 = new SimpleDateFormat("dd.MM.yyyy");
                try {

                    Long kod = Long.valueOf(database.ReadGet_Set("KullaniciId")+String.valueOf(bildiri).substring(1)+sdf2.format(sdf1.parse(tarih)));
                    database.UpdateBildiriListesi(kod,"SENT",1);
                    Intent intent = new Intent(L2_bildiri.this,L1_main.class);
                    startActivity(intent);
                } catch (ParseException e) {
                    e.printStackTrace();
                }*/
                Rest_Gonderme();
            }
        });

    }
    public void setListView(){
        L1_main l1_main = new L1_main();
        veri = new Get_Set();
        final SQLiteHelper database = new SQLiteHelper(this);
        final String tarih = l1_main.getMaintitle()[veri.getPosition()].subSequence(l1_main.getMaintitle()[veri.getPosition()].length()-11,l1_main.getMaintitle()[veri.getPosition()].length()-1).toString();
        final String bildiri = database.ReadBildirilerwIsim(l1_main.getMaintitle()[veri.getPosition()].split(" ")[0]+" "+ l1_main.getMaintitle()[veri.getPosition()].split(" ")[1])[0];

 /*       database.WriteTaslak(bildiri,tarih,"İnce Taşlama Yapıması",0,"Tünel-198",2,198500,199000,500,"thm",0);
        database.WriteTaslakResource(veri.getKod(),tarih,"R003","iscilik","İnce Taşlama Yapıması",11,"",1);
        database.WriteTaslakResource(veri.getKod(),tarih,"R004","makine","İnce Taşlama Yapıması",9,"",1);
        database.WriteTaslakAciklamalar(bildiri,tarih,"İnce Taşlama Yapıması","asdasdasd");

        database.WriteTaslak(bildiri,tarih,"Ray Alın Kaynaklarının Yapılması",0,"Tünel-189",1,190500,191000,500,"thm",0);
        database.WriteTaslakResource(veri.getKod(),tarih,"R001","iscilik","Ray Alın Kaynaklarının Yapılması",11,"",1);
        database.WriteTaslakResource(veri.getKod(),tarih,"R002","makine","Ray Alın Kaynaklarının Yapılması",9,"",1);
        database.WriteTaslakAciklamalar(bildiri,tarih,"Ray Alın Kaynaklarının Yapılması","abcabaasc");
        database.WriteTaslakAciklamalar(bildiri,tarih,"Ray Alın Kaynaklarının Yapılması","abcabarexdtcygtubhınjasc");
        database.WriteTaslakResource(veri.getKod(),tarih,"R008","iscilik","Ray Alın Kaynaklarının Yapılması",-1,"verimsizlik",1);
        database.WriteTaslakResource(veri.getKod(),tarih,"R010","iscilik","İnce Taşlama Yapıması",-3,"verimsizlik",1);
        database.WriteTaslakResource(veri.getKod(),tarih,"R009","iscilik","Ray Alın Kaynaklarının Yapılması",-1,"verimsizlik",1);
        database.WriteTaslakResource(veri.getKod(),tarih,"R011","iscilik","İnce Taşlama Yapıması",100,"",1);
        database.WriteTaslakResource(veri.getKod(),tarih,"R012","iscilik","İnce Taşlama Yapıması",10,"",1);
        database.WriteTaslakResource(veri.getKod(),tarih,"R012","iscilik","Ray Alın Kaynaklarının Yapılması",10,"",1);

        database.WriteTaslak(bildiri,tarih,"Panelray Serimi",0,"Tünel-299",2,198500,199000,500,"thm",0);
        database.WriteTaslakResource(veri.getKod(),tarih,"R003","iscilik","Panelray Serimi",19,"",1);
        database.WriteTaslakResource(veri.getKod(),tarih,"R004","makine","Panelray Serimi",90,"",1);
        database.WriteTaslakAciklamalar(bildiri,tarih,"Panelray Serimi","asdasdasd");
        database.WriteTaslak(bildiri,tarih,"İnce Taşlama Yapıması",1,"Tünel-194",1,194500,195000,500,"thm",0);
        database.WriteTaslakResource(veri.getKod(),tarih,"R003","iscilik","İnce Taşlama Yapıması",12,"",1);
        database.WriteTaslakResource(veri.getKod(),tarih,"R004","makine","İnce Taşlama Yapıması",8,"",1);
        database.WriteTaslakAciklamalar(bildiri,tarih,"İnce Taşlama Yapıması","safoehufejqoı");
*/

        List[] diziler = database.ReadTaslakfList(String.valueOf(veri.getKod()));
        imalat_array = Arrays.copyOf(diziler[0].toArray(new String[diziler[0].size()]),diziler[0].toArray(new String[diziler[0].size()]).length,String[].class);
    //    String[] imalat_array_isim = Arrays.copyOf(diziler[6].toArray(new String[diziler[6].size()]),diziler[6].toArray(new String[diziler[6].size()]).length,String[].class);

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
      /*  System.out.println("imala1t "+imalat_array[0]);
        System.out.println("mesafde "+mesafe_array[0]);
        System.out.println("kmbas "+km_bas_array[0]);
        System.out.println("kmson "+km_son_array[0]);
        System.out.println("mesafebirim "+mesafe_birim_array[0]);
        System.out.println("perpuantaj "+personel_puantaj_array[0]);
        System.out.println("per ade1t "+personel_sayisi_array[0]);
        System.out.println("mak puan "+makine_puantaj_array[0]);
        System.out.println("mak sayı"+makine_sayisi_array[0]);
        System.out.println("verim "+verim_array[0]);
        System.out.println("ortver "+ort_verimsiz_sure[0]);
        System.out.println("medya sayı"+medya_array[0]);*/
        ListView listView = findViewById(R.id.listview);
        L2_bildiri_adapter adapter = new L2_bildiri_adapter(L2_bildiri.this,imalat_array,mesafe_array,mesafe_birim_array,km_bas_array,km_son_array,personel_sayisi_array,
                personel_puantaj_array,makine_sayisi_array,makine_puantaj_array,medya_array,aciklama_array,verim_array,ort_verimsiz_sure);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(L2_bildiri.this,L3_imalat.class);
                veri.setImalat(imalat_array[position]);
                //veri.setImalatid(database.ReadImalatwidforisim(imalat_array[position]));
                database.UpdateGet_Set("ImalatId",database.ReadImalatwidforisim(imalat_array[position]));
                veri.setPositionL2(position);
                database.UpdateGet_Set("KopyaNo",String.valueOf(kopya_nolar[position]));
                /*veri.setImalatlar_array(imalat_array);
                veri.setMesafe_array(mesafe_array);
                veri.setKm_bas_array(km_bas_array);
                veri.setKm_son_array(km_son_array);
                veri.setMesafe_birim_array(mesafe_birim_array);
                veri.setKopya_nolar(kopya_nolar);
                veri.setPersonel_puantaj_array(personel_puantaj_array);
                veri.setPersonel_sayisi_array(personel_sayisi_array);
                veri.setMakine_sayisi_array(makine_sayisi_array);
                veri.setVerim_array(verim_array);
                veri.setOrt_verimsiz_sure(ort_verimsiz_sure);
                veri.setMedya_array(medya_array);*/
                startActivity(intent);
            }
        });
        listView.setLongClickable(true);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                Snackbar snackbar = Snackbar.make(view,"Silinsin mi?",Snackbar.LENGTH_LONG).setAction("Evet", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SQLiteHelper database = new SQLiteHelper(L2_bildiri.this);
                        database.DeleteTaslakListItem(String.valueOf(veri.getKod()),tarih,imalat_array[position],String.valueOf(kopya_nolar[position]));
                        setListView();
                    }
                });
                snackbar.setActionTextColor(getResources().getColor(R.color.text_color_yellow));
                snackbar.show();


                return true;
            }
        });


        /*

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(L2_bildiri.this,"did it",Toast.LENGTH_LONG).show();

                return false;
            }
        });*/

        /*TextView imalat_adi = rowView.findViewById(R.id.imalat_adi);
        TextView mesafe = rowView.findViewById(R.id.mesafe);//2 diziden çekecek
        TextView km_bas = rowView.findViewById(R.id.km_bas);
        TextView km_son = rowView.findViewById(R.id.km_son);
        TextView personel_sayisi = rowView.findViewById(R.id.personel_sayisi);
        TextView personel_puantaj = rowView.findViewById(R.id.personel_puantaj);
        TextView makine_sayisi = rowView.findViewById(R.id.makine_sayisi);
        TextView makine_puantaj = rowView.findViewById(R.id.makine_puantaj);
        TextView medya = rowView.findViewById(R.id.medya);
        TextView aciklama = rowView.findViewById(R.id.aciklama);
        TextView verimtxt = rowView.findViewById(R.id.verimtxt);
        TextView verim = rowView.findViewById(R.id.verim);
        TextView ort_verimsiz_sure = rowView.findViewById(R.id.ort_verimsiz_sure);*/
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        setListView();
    }
    @Override
    protected void onResume() {
        super.onResume();
        setListView();
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,L1_main.class);
        startActivity(intent);
    }

    protected void Rest_Gonderme(){
        String url_imalat = "http://31.210.91.198/rest/imalat";

        SQLiteHelper database = new SQLiteHelper(L2_bildiri.this);
        L1_main l1_main = new L1_main();
        final String tarih = l1_main.getMaintitle()[veri.getPosition()].subSequence(l1_main.getMaintitle()[veri.getPosition()].length()-11,l1_main.getMaintitle()[veri.getPosition()].length()-1).toString();
        final String bildiri = database.ReadBildirilerwIsim(l1_main.getMaintitle()[veri.getPosition()].split(" ")[0]+" "+ l1_main.getMaintitle()[veri.getPosition()].split(" ")[1])[0];
        List[] diziler = database.ReadTaslakfList(String.valueOf(veri.getKod()));
        imalat_array = Arrays.copyOf(diziler[0].toArray(new String[diziler[0].size()]),diziler[0].toArray(new String[diziler[0].size()]).length,String[].class);
        //    String[] imalat_array_isim = Arrays.copyOf(diziler[6].toArray(new String[diziler[6].size()]),diziler[6].toArray(new String[diziler[6].size()]).length,String[].class);
        final String[] mesafe_array = Arrays.copyOf(diziler[1].toArray(new String[diziler[1].size()]),diziler[1].toArray(new String[diziler[1].size()]).length,String[].class);
        final String[] km_bas_array = Arrays.copyOf(diziler[2].toArray(new String[diziler[2].size()]),diziler[2].toArray(new String[diziler[2].size()]).length,String[].class);
        final String[] km_son_array = Arrays.copyOf(diziler[3].toArray(new String[diziler[3].size()]),diziler[3].toArray(new String[diziler[3].size()]).length,String[].class);
        final String[] mesafe_birim_array = Arrays.copyOf(diziler[4].toArray(new String[diziler[4].size()]),diziler[4].toArray(new String[diziler[4].size()]).length,String[].class);
        final Integer[] kopya_nolar = Arrays.copyOf(diziler[5].toArray(new Integer[diziler[5].size()]),diziler[5].toArray(new Integer[diziler[5].size()]).length,Integer[].class);
        final Integer[] hat_nolar = Arrays.copyOf(diziler[6].toArray(new Integer[diziler[6].size()]),diziler[6].toArray(new Integer[diziler[6].size()]).length,Integer[].class);
        String[] aciklama_array = database.ReadTaslakAciklamaflist(bildiri,tarih,imalat_array);

        for (int i=0 ; i<imalat_array.length ; i++) {
            final Map<String, String> userParams = new HashMap<String, String>();
            userParams.put("bildiri_id", String.valueOf(veri.getKod()));
            userParams.put("imalat_adi", imalat_array[i]);
            userParams.put("km_bas", km_bas_array[i]);
            userParams.put("km_bit", km_son_array[i]);
            userParams.put("hat_no", String.valueOf(hat_nolar[i]));
            userParams.put("ilerleme", mesafe_array[i]);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url_imalat,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            /*Herhangi bir hata yok ise, kullanıcıya bilgi verilir.*/
                            Toast.makeText(getApplicationContext(), "Sunucuya gönderildi", Toast.LENGTH_SHORT).show();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    /*Hata meydana geldiğinde kullanıcıya bilgi verilir.*/
                    Toast.makeText(getApplicationContext(), "Hata meydana geldi", Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    //Map<String, String> params = new HashMap<String, String>();
                    //JSONObject userJSON = new JSONObject(userParams);
                    return userParams;
                }
            };
            MySingleton.getInstance(getApplicationContext()).addToRequestQue(stringRequest);

        }

        String url_aciklama = "http://31.210.91.198/rest/aciklama";

        for (int i=0 ; i<imalat_array.length ; i++) {
            List<String> aciklamalar = database.ReadAciklamaForRestAPI(String.valueOf(veri.getKod()), database.ReadImalatwidforisim(imalat_array[i]));
            for (int j = 0; j < aciklamalar.size(); j++) {
                final Map<String, String> userParams = new HashMap<String, String>();
                userParams.put("bildiri_id", String.valueOf(veri.getKod()));
                userParams.put("imalat_adi", imalat_array[i]);
                userParams.put("aciklama", aciklamalar.get(j));
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url_aciklama,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                            /*Herhangi bir hata yok ise, kullanıcıya bilgi verilir.*/
                                //Toast.makeText(getApplicationContext(), "Veri kayıt edildi...", Toast.LENGTH_SHORT).show();
                                //Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    /*Hata meydana geldiğinde kullanıcıya bilgi verilir.*/
                        //Toast.makeText(getApplicationContext(), "Hata meydana geldi", Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        //Map<String, String> params = new HashMap<String, String>();
                        //JSONObject userJSON = new JSONObject(userParams);
                        return userParams;
                    }
                };
                MySingleton.getInstance(getApplicationContext()).addToRequestQue(stringRequest);

            }
        }
        Intent intent = new Intent(L2_bildiri.this,L1_main.class);
        startActivity(intent);


    }
}
