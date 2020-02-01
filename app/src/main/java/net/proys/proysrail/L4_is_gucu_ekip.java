package net.proys.proysrail;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class L4_is_gucu_ekip extends AppCompatActivity {
    protected Intent getIntent;
    protected ListView listView1;
    protected ListView listView2;
    protected TextView makine,makinegrup;
    Get_Set veri;
    SQLiteHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l4_is_gucu_ekip);
        veri = new Get_Set();
        init();
        setListView();
    }

    protected void init(){

        database = new SQLiteHelper(this);
        getIntent = getIntent();
        listView1  = findViewById(R.id.list_view);
        listView2 = findViewById(R.id.list_view2);
        makine = findViewById(R.id.makine);
        makinegrup = findViewById(R.id.makinegrup);

    }
    protected void setListView(){
        final String[] isciler = database.ReadPersonelforL4(String.valueOf(veri.getKod()),"ekip");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,isciler);
        listView1.setAdapter(arrayAdapter);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                database.WriteTaslakResource(veri.getKod(),"tarih",veri.getImalatIsgucuid(),database.ReadPersonelwisim(isciler[position]),"iscilik","ekip",Integer.valueOf(database.ReadPersonel(database.ReadPersonelwisim(isciler[position]))[6]),Integer.valueOf(database.ReadPersonel(database.ReadPersonelwisim(isciler[position]))[7]),"efor");//TODO CHECK TARIH IMALAT
                String[] personel_id = database.ReadPersonelswEkip_id(database.ReadPersonelwisim(isciler[position]));
                for(int i=0;i<personel_id.length;i++){
                    database.WriteTaslakResource(veri.getKod(),"tarih",veri.getImalatIsgucuid(),personel_id[i],"iscilik","isci",Integer.valueOf(database.ReadPersonel(personel_id[i])[6]),Integer.valueOf(database.ReadPersonel(personel_id[i])[7]),database.ReadPersonelwisim(isciler[position]));

                    List[] listeler = database.ReadTaslakVerimsizlik(String.valueOf(veri.getKod()),veri.getImalatIsgucuid());
                    List<String> etken_idler = listeler[0];
                    List<Integer> degerler = listeler[1];
                    for (int j =0; j<etken_idler.size(); j++){
                        database.WriteTaslakResource(veri.getKod(), "tarih", veri.getImalatIsgucuid(), personel_id[i], "iscilik", "verimsiz", Integer.valueOf(degerler.get(j)), Integer.valueOf(database.ReadPersonel(personel_id[i])[7]), etken_idler.get(j));
                        //ilki puantaj 2. si verimsizlik giriş
                    }
                }
                Intent intent = new Intent(L4_is_gucu_ekip.this,L4_is_gucu.class);
                startActivity(intent);
            }
        });


        Login_SQLiteHelper login_sqLiteHelper = new Login_SQLiteHelper(L4_is_gucu_ekip.this);
        final String[] ekipler_ids = login_sqLiteHelper.ReadLoginforFavEkip(database.ReadGet_Set("ImalatId"));// database.ReadPersonelwekip_adi("iscilik");
        final List<String> ekipler_list = new ArrayList<>();
        /*for (int i = 0; i<ekipler_ids.length;i++){
            ekipler_list.add(database.ReadPersonelwid(ekipler_ids[i]));
        //todo e2 la burada for çöküii gel buna bak muhtemelen i saysıı çok yükseliyor bura favori ekip listviewine veri atıyor
        }
        final String[] ekipler_array = ekipler_list.toArray(new String[ekipler_list.size()]);
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,ekipler_array);
        listView2.setAdapter(arrayAdapter1);
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                database.WriteTaslakResource(veri.getKod(),"tarih",veri.getImalatIsgucuid(),database.ReadPersonelwisim(ekipler_array[position]),"iscilik","ekip",Integer.valueOf(database.ReadPersonel(database.ReadPersonelwisim(ekipler_array[position]))[6]),Integer.valueOf(database.ReadPersonel(database.ReadPersonelwisim(ekipler_array[position]))[7]),"efor");//TODO CHECK TARIH IMALAT
                String[] personel_id = database.ReadPersonelswEkip_id(database.ReadPersonelwisim(ekipler_array[position]));

                for(int i=0;i<personel_id.length;i++){
                    database.WriteTaslakResource(veri.getKod(),"tarih",veri.getImalatIsgucuid(),personel_id[i],"iscilik","isci",Integer.valueOf(database.ReadPersonel(personel_id[i])[6]),Integer.valueOf(database.ReadPersonel(personel_id[i])[7]),database.ReadPersonelwisim(ekipler_array[position]));
                }

                Intent intent = new Intent(L4_is_gucu_ekip.this,L4_is_gucu.class);
                startActivity(intent);
            }
        });*/
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,L3_isgucu.class);
        startActivity(intent);
    }
}
