package net.proys.proysrail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class L4_makine_detay extends AppCompatActivity {
    TextView isci_txt,imalat_txt,puantaj_txt,adet_txt;
    ImageView onay;
    SQLiteHelper database;
    Get_Set veri;
    ListView listView;
    String kisi_ismi;
    String verim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l4_makine_detay);
        init();
        setOnclickEvents();
        setListView();
    }

    protected void init(){
        database = new SQLiteHelper(L4_makine_detay.this);
        veri = new Get_Set();
        adet_txt = findViewById(R.id.adet);
        isci_txt = findViewById(R.id.isciadi);
        imalat_txt = findViewById(R.id.imalat);
        imalat_txt.setText(veri.getImalatIsgucu());
        puantaj_txt = findViewById(R.id.puantaj);
        onay = findViewById(R.id.onay);
        listView = findViewById(R.id.listview);
        Intent intent = getIntent();
        kisi_ismi = intent.getStringExtra("kisi");
        verim = intent.getStringExtra("verim");
        isci_txt.setText(kisi_ismi);
        puantaj_txt.setText(database.ReadTaslakResourceforMakinedetayPuantaj(String.valueOf(veri.getKod()),database.ReadPersonelwisim(kisi_ismi),verim));
        adet_txt.setText(String.valueOf(database.ReadTaslakResourceforMakinedetaySayi(String.valueOf(veri.getKod()),database.ReadPersonelwisim(kisi_ismi),verim)));

    }
    protected void setOnclickEvents(){
        onay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L4_makine_detay.this,L3_makine.class);
                startActivity(intent);
            }
        });
    }


    public void setListView() {
        List<String>[] lists = database.ReadTaslakResourceforLsMakinedetay(String.valueOf(veri.getKod()),database.ReadPersonelwisim(kisi_ismi));
        L4_isci_detay_adapter adapter = new L4_isci_detay_adapter(L4_makine_detay.this,lists[0],lists[1]);
        listView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,L3_makine.class);
        startActivity(intent);
    }
}
