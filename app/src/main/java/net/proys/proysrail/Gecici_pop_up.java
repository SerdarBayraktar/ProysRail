package net.proys.proysrail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Gecici_pop_up extends AppCompatActivity {

    ListView listView;
    TextView isci_txt;
    ImageView tick;
    Button button;
    String bildiri_id;
    String tarih;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gecici_pop_up);
        init();
        setIsci();
        setListView();
        setOnclicks();
    }
    public void init(){
        isci_txt = findViewById(R.id.textView);
        listView = findViewById(R.id.list_view);
        tick = findViewById(R.id.tick);
        button = findViewById(R.id.imalat_sec);

    }
    public void setIsci(){
        Intent getIntent = getIntent();
        isci_txt.setText(getIntent.getStringExtra("isim"));
        bildiri_id = getIntent.getStringExtra("id");
        tarih = getIntent.getStringExtra("tarih");
    }
    public void setListView(){
        String[] values = new String[]{
                "İşe Gelmedi",
                "Yıllık İzin",
                "Raporlu",
                "Mazeret İzni",
                "Gurbetçi İzni"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SQLiteHelper database = new SQLiteHelper(Gecici_pop_up.this);
                Get_Set veri = new Get_Set();
                database.WriteTaslakResource(Long.valueOf(bildiri_id),tarih,"",database.ReadPersonelwisim(veri.getIsci()),"iscilik","verimsiz",999,1,"");
                Intent intent = new Intent(Gecici_pop_up.this,IP1ISCILIKPUANTAJ.class);
                startActivity(intent);

            }
        });
    }
    public void setOnclicks(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Gecici_pop_up.this, Iscilik_Puantaj_Imalat.class);
                startActivity(intent);

            }
        });
    }
}
