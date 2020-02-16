package net.proys.proysrail;

import android.content.Intent;

import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class L4_aciklama extends AppCompatActivity {
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
        tick = findViewById(R.id.tick);
        listView = findViewById(R.id.listview);
        ekle = findViewById(R.id.ekle);
        TextView imalattxt = findViewById(R.id.imalattxt);
        imalattxt.setText(database.ReadImalatwidforid(database.ReadGet_Set("ImalatId")));
    }
    protected void setOnclickevents(){
        tick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.DeleteAciklamaEmpty();
                Intent intent = new Intent(L4_aciklama.this,L2_aciklama.class);
                startActivity(intent);
            }
        });
        ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.WriteTaslakL4(String.valueOf(veri.getKod()),database.ReadGet_Set("ImalatId"),Integer.valueOf(database.ReadGet_Set("KopyaNo")));
                setListView();
            }
        });

    }
    public void setListView() {
        List[] lists = database.ReadAciklamal4(String.valueOf(veri.getKod()),database.ReadGet_Set("ImalatId"));
        final List<String> aciklamalar = lists[0];
        final List<Integer> aciklama_idler = lists[1];
        L4_aciklama_adapter adapter = new L4_aciklama_adapter(L4_aciklama.this,aciklamalar);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(L4_aciklama.this,L4_aciklama_detay.class);
                intent.putExtra("tip","L4");
                intent.putExtra("id",String.valueOf(aciklama_idler.get(position)));
                intent.putExtra("text",aciklamalar.get(position));
                startActivity(intent);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,final int position, long id) {
                Snackbar snackbar = Snackbar.make(view,"Silinsin mi?",Snackbar.LENGTH_LONG).setAction("Evet", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SQLiteHelper database = new SQLiteHelper(L4_aciklama.this);
                        database.DeleteAciklama(String.valueOf(aciklama_idler.get(position)));
                        setListView();
                    }
                });
                snackbar.setActionTextColor(getResources().getColor(R.color.text_color_yellow));
                snackbar.show();
                return false;
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(L4_aciklama.this,L2_aciklama.class);
        startActivity(intent);
    }
}
