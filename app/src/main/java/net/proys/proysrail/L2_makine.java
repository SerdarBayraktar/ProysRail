package net.proys.proysrail;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class L2_makine extends AppCompatActivity {
    ImageView isci_icon,malzeme_icon,imalat_icon,makine_icon,aciklama_icon,medya_icon,sent;
    LinearLayout isci_linear,imalat_linear,makine_linear,malzeme_linear,aciklama_linear,medya_linear;

    TextView makine_txt;
    ExpandableListView listView;
    Get_Set veri;
    SQLiteHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l2_makine);
        init();
        setOnclickEvents();
        allset4icon();
        setListView();
    }
    protected void init(){

        TextView imalattxt = findViewById(R.id.imalattxt);
        imalattxt.setText(String.valueOf(veri.getKod()).substring(13,15)+"."+String.valueOf(veri.getKod()).substring(11,13)+"."+String.valueOf(veri.getKod()).substring(7,11));
        sent = findViewById(R.id.sent);
        isci_icon = findViewById(R.id.imageisci);
        imalat_icon = findViewById(R.id.imageImalat);
        makine_icon = findViewById(R.id.imageMakine);
        malzeme_icon = findViewById(R.id.imageMalzeme);
        aciklama_icon = findViewById(R.id.imageAciklama);
        medya_icon = findViewById(R.id.imageCamera);
        makine_txt = findViewById(R.id.makine_txt);
        listView = findViewById(R.id.listview);
        imalat_linear = findViewById(R.id.imalat_linear);
        isci_linear = findViewById(R.id.isgucu_linear);
        malzeme_linear = findViewById(R.id.malzeme_linear);
        makine_linear = findViewById(R.id.makine_linear);
        aciklama_linear = findViewById(R.id.aciklama_linear);
        medya_linear = findViewById(R.id.medya_linear);
        veri = new Get_Set();
    }
    protected void allset4icon(){
        makine_icon.setImageResource(R.drawable.l2_makine_o);
        makine_txt.setTextColor(getResources().getColor(R.color.text_color_yellow));
        Typeface typeface = getResources().getFont(R.font.opensans_semibold);
        makine_txt.setTypeface(typeface);
        if (!veri.getAciklamalar().equals("")){
            aciklama_icon.setImageResource(R.drawable.l2_aciklama_d);
        }
    }
    protected void setOnclickEvents(){
        malzeme_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(L2_makine.this,"Henüz hazır değil.",Toast.LENGTH_SHORT).show();

            }
        });
        isci_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L2_makine.this,l2_is_gucu.class);
                startActivity(intent);
            }
        });
        imalat_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L2_makine.this,L2_bildiri.class);
                startActivity(intent);
            }
        });
        aciklama_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L2_makine.this,L2_aciklama.class);
                startActivity(intent);
            }
        });
        medya_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L2_makine.this,L2_medya.class);
                startActivity(intent);
            }
        });

        sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*L1_main l1_main = new L1_main();
                veri = new Get_Set();
                SQLiteHelper database = new SQLiteHelper(L2_makine.this);
                String tarih = l1_main.getMaintitle()[veri.getPosition()].subSequence(l1_main.getMaintitle()[veri.getPosition()].length()-11,l1_main.getMaintitle()[veri.getPosition()].length()-1).toString();
                String bildiri = database.ReadBildirilerwIsim(l1_main.getMaintitle()[veri.getPosition()].split(" ")[0]+" "+ l1_main.getMaintitle()[veri.getPosition()].split(" ")[1])[0];
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
                SimpleDateFormat sdf1 = new SimpleDateFormat("dd.MM.yyyy");
                try {

                    Long kod = Long.valueOf(database.ReadGet_Set("KullaniciId")+String.valueOf(bildiri).substring(1)+sdf2.format(sdf1.parse(tarih)));
                    database.UpdateBildiriListesi(kod,"SENT",1);
                    Intent intent = new Intent(L2_makine.this,L1_main.class);
                    startActivity(intent);
                } catch (ParseException e) {
                    e.printStackTrace();
                }*/
                Rest_Gonderme();
            }
        });

    }
    public void setListView() {
        database = new SQLiteHelper(L2_makine.this);

        final List[] lists = database.CreateL2MakineKartPart1(String.valueOf(veri.getKod()));
        HashMap[] hashMaps = database.CreateL2MakineKartPart2(lists[0],lists[1],String.valueOf(veri.getKod()));

        L2_makine_expandable_listview_adapter adapter = new L2_makine_expandable_listview_adapter(L2_makine.this,lists[0],hashMaps[0],hashMaps[1],hashMaps[2]);//to do new adapter
        listView.setAdapter(adapter);

        listView.setAdapter(adapter);
        listView.setDivider(null);
        listView.setDividerHeight(0);
        listView.setGroupIndicator(null);
        listView.setChildIndicator(null);
        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Get_Set veri = new Get_Set();
                veri.setImalatIsgucu(String.valueOf(lists[0].get(groupPosition)));
                veri.setImalatIsgucuid(String.valueOf(lists[1].get(groupPosition)));
                Intent intent = new Intent(L2_makine.this, L3_makine.class);
                startActivity(intent);
                return true;
            }
        });
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Get_Set veri = new Get_Set();
                veri.setImalatIsgucu(String.valueOf(lists[0].get(groupPosition)));
                veri.setImalatIsgucuid(String.valueOf(lists[1].get(groupPosition)));
                Intent intent = new Intent(L2_makine.this, L3_makine.class);
                startActivity(intent);
                return true;
            }
        });
        for(int i = 0; i<lists[0].size();i++) {
            listView.expandGroup(i);
        }/*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(L2_makine.this, L3_isgucu.class);

                intent.putExtra("tip","makine");
                startActivity(intent);
            }
        });*/


    }


    protected void Rest_Gonderme(){
        String url_imalat = "http://31.210.91.198/rest/imalat";

        SQLiteHelper database = new SQLiteHelper(L2_makine.this);
        L1_main l1_main = new L1_main();
        final String tarih = l1_main.getMaintitle()[veri.getPosition()].subSequence(l1_main.getMaintitle()[veri.getPosition()].length()-11,l1_main.getMaintitle()[veri.getPosition()].length()-1).toString();
        final String bildiri = database.ReadBildirilerwIsim(l1_main.getMaintitle()[veri.getPosition()].split(" ")[0]+" "+ l1_main.getMaintitle()[veri.getPosition()].split(" ")[1])[0];
        List[] diziler = database.ReadTaslakfList(String.valueOf(veri.getKod()));
        String[] imalat_array = Arrays.copyOf(diziler[0].toArray(new String[diziler[0].size()]),diziler[0].toArray(new String[diziler[0].size()]).length,String[].class);
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
        Intent intent = new Intent(L2_makine.this,L1_main.class);
        startActivity(intent);


    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,L1_main.class);
        startActivity(intent);
    }
}
