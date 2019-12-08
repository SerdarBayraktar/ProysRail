package net.proys.proysrail;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.WorkSource;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class L4_imalatlar extends AppCompatActivity {
    String[] maintitle ={
            "2. Kademe Balast Serimi",
            "Koruyucu Taşlama Yapıması",
            "Panelray Serimi",
            "Balastsız Hat Rayı Montajı",
            "Kendiliğinden Yerleşen Beton Dökülmesi",
            "Travers Dağıtımı",
            "Kot ve Eksen Ayarı",
            "Tij ve Prob Montajı",
            "Panel Serimi",
            "Panel Ön Montajı",
            "Geçici Hat Montajı",
            "Konvansiyonel Hat Montajı",
    };
    String[] sektörmain ={"Yerköy Balastsız İstasyon Hattı","Sivas Konvansiyonel Hat","Tünel-212",
            "Yıldızeli Balastlı İstasyon Hattı"
    };
    String[] verimsizlik = {
            "Makine Arızası",
            "Makine Yakıt İkmali",
            "Hava Koşulları",
            "Sahaya Seyahat Süresi",
            "Tren Trafiği",
            "Malzeme Bekleme"

    };
    Get_Set veri;
    SQLiteHelper database;
    Intent intent;
    Integer[] imgid={
            null,null,
            null,null,
            null,
    };
    protected String[] imalatfavori;
    Intent getIntent;
    ListView list;
    L1_main l1_main;
    String bildiri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l4_imalatlar);
        getIntent = getIntent();
        veri= new Get_Set();
        setFavoriArray();
        setlistviewarray();
        setListview();
    }
    protected void setFavoriArray(){
        Login_SQLiteHelper login_database = new Login_SQLiteHelper(this);
        SQLiteHelper database = new SQLiteHelper(this);
        String[] favoriler = (login_database.ReadUsername(veri.getKullaniciAdi())[5]).split("--");// kullanıcı adi ile favori elemanların listesi alındı. vee diziye kaydoldu
        imalatfavori = new String[favoriler.length];//id den stringe geçiriyorum
        for (int i = 0; i<favoriler.length;i++){
            imalatfavori[i] = database.ReadImalat(favoriler[i])[0];// id olan tüm elemanlar sqlitedan kontrol edilerek str hali bulunup aktarılıyor
        }
    }
    protected void init() {
        database = new SQLiteHelper(L4_imalatlar.this);
        l1_main = new L1_main();
    }
    protected void setlistviewarray() {
        bildiri = database.ReadBildirilerwIsim(l1_main.getMaintitle()[veri.getPosition()].split(" ")[0] + " " + l1_main.getMaintitle()[veri.getPosition()].split(" ")[1])[0];
        maintitle = database.ReadImalatfL4(bildiri);
    }

    protected void setListview(){
        if(getIntent.getStringExtra("tip").equals("imalat")){
            //L4_imalat_adapter adapter = new L4_imalat_adapter(this, maintitle, imgid);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,maintitle);
            list=findViewById(R.id.list_view);
            list.setAdapter(adapter);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(L4_imalatlar.this,L3_imalat.class);
                    veri.setImalat(maintitle[position]);
                    SQLiteHelper database = new SQLiteHelper(L4_imalatlar.this);
                    L1_main l1_main = new L1_main();
                    String tarih = l1_main.getMaintitle()[veri.getPosition()].subSequence(l1_main.getMaintitle()[veri.getPosition()].length()-11,l1_main.getMaintitle()[veri.getPosition()].length()-1).toString();
                    Integer kopya_no = database.ReadTaslakfimalatKopya(String.valueOf(veri.getKod()),tarih,maintitle[position]) + 1;

                    int mesafe = Integer.valueOf(database.ReadImalatwisim(maintitle[position])[7]);
                    int km_bas= Integer.valueOf(database.ReadSektor(Integer.valueOf(database.ReadImalatwisim(maintitle[position])[6]))[3]);
                    if (getIntent.getStringExtra("version").equals("new")) {
                        database.WriteTaslak(veri.getKod(), tarih,maintitle[position],kopya_no,database.ReadSektor(Integer.valueOf(database.ReadImalatwisim(maintitle[position])[6]))[0],
                                1,km_bas,km_bas+mesafe,mesafe,
                                database.ReadImalatwisim(maintitle[position])[2],0);
                        //database.UpdateGet_Set("ImalatId",maintitle[position]);
                        //database.UpdateGet_Set("KopyaNo",String.valueOf(kopya_no));
                    }else if (getIntent.getStringExtra("version").equals("old")){
                        database.UpdateTaslak(veri.getKod(), tarih, maintitle[position], kopya_no, database.ReadSektor(Integer.valueOf(database.ReadImalatwisim(maintitle[position])[6]))[0],
                                1, km_bas, km_bas + mesafe, mesafe,
                                database.ReadImalatwisim(maintitle[position])[2], 0,getIntent.getStringExtra("kopyano"),getIntent.getStringExtra("imalat"));
                        String asd = getIntent.getStringExtra("kopyano");
                        String abc = getIntent.getStringExtra("imalat");
                        Long lll = veri.getKod();
                            String akc = getIntent.getStringExtra("imalat");
                    }
                    startActivity(intent);
                }
            });
            ListView ls = findViewById(R.id.list_view2);
            ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,imalatfavori);
            ls.setAdapter(arrayAdapter1);
            ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(L4_imalatlar.this,L3_imalat.class);
                    SQLiteHelper database = new SQLiteHelper(L4_imalatlar.this);
                    veri.setImalat(imalatfavori[position]);
                    L1_main l1_main = new L1_main();
                    String tarih = l1_main.getMaintitle()[veri.getPosition()].subSequence(l1_main.getMaintitle()[veri.getPosition()].length()-11,l1_main.getMaintitle()[veri.getPosition()].length()-1).toString();
                    int mesafe = Integer.valueOf(database.ReadImalatwisim(maintitle[position])[7]);
                    int km_bas= Integer.valueOf(database.ReadSektor(Integer.valueOf(database.ReadImalatwisim(maintitle[position])[6]))[3]);
                    Integer kopya_no = database.ReadTaslakfimalatKopya(String.valueOf(veri.getKod()),tarih,maintitle[position]) + 1;
                    database.WriteTaslak(veri.getKod(), tarih,imalatfavori[position],kopya_no,database.ReadSektor(Integer.valueOf(database.ReadImalatwisim(imalatfavori[position])[6]))[0],
                            1,km_bas,km_bas+mesafe,mesafe,
                            database.ReadImalatwisim(imalatfavori[position])[2],0);

                    startActivity(intent);
                }
            });
        }
    }
    protected void setSearchEngine(){
        EditText editText= new EditText(this);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("")) {
                    maintitle = database.ReadImalatfL4(bildiri);
                } else {
                    List<String> depo_searched = new ArrayList<>();
                    for (int i = 0; i < maintitle.length; i++) {
                        if (maintitle[i].toLowerCase().contains(s.toString().toLowerCase())) {
                            depo_searched.add(maintitle[i]);
                        }
                    }
                    maintitle = depo_searched.toArray(new String[depo_searched.size()]);
                }
                setListview();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(L4_imalatlar.this,L3_imalat.class);
        SQLiteHelper database = new SQLiteHelper(L4_imalatlar.this);
        database.DeleteTaslak(String.valueOf(veri.getKod()));//id tarih sil
        super.onBackPressed();
    }
}/*else if(intent.getStringExtra("tip").equals("sektör")){
            database = new SQLiteHelper(L4_imalatlar.this);
            String imalatid = database.ReadImalatwisim(veri.getImalat())[0];
            List<String> sektorler = new ArrayList<String>();
            for (int i =1; i<=100;i++){
                if (database.ReadSektor(90000+i)[6].contains(imalatid)){
                    if (!sektorler.contains(database.ReadSektor(90000+i)[0])) {
                        sektorler.add(database.ReadSektor(90000 + i)[0]);
                    }
                }
            }
            final String[] sektorler_array = sektorler.toArray(new String[sektorler.size()]);

            L4_imalat_adapter adapter = new L4_imalat_adapter(this,sektorler_array, imgid);
            list=(ListView)findViewById(R.id.list_view);
            list.setAdapter(adapter);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(L4_imalatlar.this,L3_imalat.class);
                    veri.setSektör(sektorler_array[position]);
                    startActivity(intent);
                }
            });
            ListView ls = findViewById(R.id.list_view2);
            ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,sektörfavori);
            ls.setAdapter(arrayAdapter1);
            ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(L4_imalatlar.this,L3_imalat.class);
                    veri.setSektör(sektörfavori[position]);
                    startActivity(intent);
                }
            });
        }*/