package net.proys.proysrail;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Iscilik_Puantaj_Imalat extends AppCompatActivity {

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
    Get_Set veri;
    SQLiteHelper database;
    Intent intent;
    Integer[] imgid={
            null,null,
            null,null,
            null,
    };

    String bildiri_id = "109000220200110";
    String tarih = "10.01.2020";
    String formen_tablodan_gelen = "R0004--R0005--R0008";
    protected String[] imalatfavori;
    Intent getIntent;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iscilik__puantaj__imalat);
        getIntent = getIntent();
        veri= new Get_Set();
        //setFavoriArray();
        init();
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
        protected void init(){

                database = new SQLiteHelper(Iscilik_Puantaj_Imalat.this);
                L1_main l1_main = new L1_main();
                //String bildiri = database.ReadBildirilerwIsim(l1_main.getMaintitle()[veri.getPosition()].split(" ")[0]+" "+ l1_main.getMaintitle()[veri.getPosition()].split(" ")[1])[0];
                maintitle = new String[]{"Faz-1 Betonu Dökülmesi","Faz-2 Betonu Dökülmesi (trapez kalıp dahil)"};
                //L4_imalat_adapter adapter = new L4_imalat_adapter(this, maintitle, imgid);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,maintitle);
                list=findViewById(R.id.list_view);
                list.setAdapter(adapter);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Iscilik_Puantaj_Imalat.this);
                        //builder.setTitle("Puantaj");
                        final EditText input = new EditText(Iscilik_Puantaj_Imalat.this);
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.MATCH_PARENT);
                        input.setText("8");
                        builder.setTitle("Puantaj ekle");
                        input.setLayoutParams(lp);
                        builder.setView(input);
                        //builder.setView(R.layout.l3_is_gucu_pop_up);

                        builder.setPositiveButton("tamam", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Get_Set veri = new Get_Set();
                                veri.setPuantajpopup(Integer.valueOf(input.getText().toString()));
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                                LocalDate date = LocalDate.parse(tarih,formatter).minusDays(1);

                                database.WriteTaslakResource(Long.valueOf(bildiri_id),date.format(formatter),database.ReadImalatwidforisim(maintitle[position]),
                                        database.ReadPersonelwisim(veri.getIsci()),"iscilik","iscilik_puantaj",Integer.valueOf(input.getText().toString()),
                                                1,"efor");
                                Intent intent = new Intent(Iscilik_Puantaj_Imalat.this,IP1ISCILIKPUANTAJ.class);
                                startActivity(intent);
                            }
                        });
                        builder.show();

                    }
                });
                /*ListView ls = findViewById(R.id.list_view2);
                ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,imalatfavori);
                ls.setAdapter(arrayAdapter1);
                ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Iscilik_Puantaj_Imalat.this);
                        //builder.setTitle("Puantaj");
                        final EditText input = new EditText(Iscilik_Puantaj_Imalat.this);
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.MATCH_PARENT);
                        input.setText("8");
                        builder.setTitle("Puantaj ekle");
                        input.setLayoutParams(lp);
                        builder.setView(input);
                        //builder.setView(R.layout.l3_is_gucu_pop_up);

                        builder.setPositiveButton("tamam", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Get_Set veri = new Get_Set();
                                veri.setPuantajpopup(Integer.valueOf(input.getText().toString()));



                                Intent intent = new Intent(Iscilik_Puantaj_Imalat.this,IP1ISCILIKPUANTAJ.class);
                                startActivity(intent);
                            }
                        });
                        builder.show();


                    }
                });*/
            }
        /*
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
    }*/



        @Override
        public void onBackPressed() {
            Intent intent = new Intent(Iscilik_Puantaj_Imalat.this,L1_main.class);
            SQLiteHelper database = new SQLiteHelper(Iscilik_Puantaj_Imalat.this);
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








