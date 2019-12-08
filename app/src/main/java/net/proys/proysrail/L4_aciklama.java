package net.proys.proysrail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class L4_aciklama extends AppCompatActivity {
    private EditText aciklama;
    private ImageView tick,ekle;
    ListView listView;
    Get_Set veri;
    SQLiteHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l4_aciklama);
        database = new SQLiteHelper(L4_aciklama.this);
        init();
        setListView();
        setOnclickevents();



    }
    protected void init(){
        aciklama = findViewById(R.id.aciklama_edit);
        tick = findViewById(R.id.tick);
        listView = findViewById(R.id.listview);
        ekle = findViewById(R.id.ekle);
    }
    protected void setOnclickevents(){
        tick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L4_aciklama.this,L3_aciklama.class);
                veri.setAciklamalar(aciklama.getText().toString());
                startActivity(intent);
            }
        });
        ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // database.WriteTaslakL4(String.valueOf(veri.getKod()),veri.getImalatIsgucuid(),Integer.valueOf(database.ReadGet_Set("KopyaNo")));
                //setListView();
            }
        });

    }

    public void setListView() {
        List[] lists = database.ReadAciklamal4(String.valueOf(veri.getKod()),veri.getImalatIsgucuid());
        final List<String> aciklamalar = lists[0];
        final List<Integer> aciklama_idler = lists[1];
        L4_aciklama_adapter adapter = new L4_aciklama_adapter(L4_aciklama.this,aciklamalar);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(L4_aciklama.this,L4_aciklama_detay.class);
                intent.putExtra("id",aciklama_idler.get(position));
                intent.putExtra("text",aciklamalar.get(position));
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,L3_aciklama.class);
        startActivity(intent);
    }
}
