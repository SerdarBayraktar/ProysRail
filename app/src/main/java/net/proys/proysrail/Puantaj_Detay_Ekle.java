package net.proys.proysrail;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import net.proys.proysrail.Adapters.PuantajDetayEkleAdapter;
import net.proys.proysrail.Fragments.MakinePuantaj;
import net.proys.proysrail.Items.PuantajDetayItem;

import java.util.ArrayList;

public class Puantaj_Detay_Ekle extends AppCompatActivity {
RecyclerView recyclerView;
ArrayList<PuantajDetayItem> list=new ArrayList<>();
ImageView onayla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puantaj__detay__ekle);
        init();
        list.add(new PuantajDetayItem("Fiiil Çalışma"));
        list.add(new PuantajDetayItem("Fiiil Çalışma"));
        list.add(new PuantajDetayItem("Fiiil Çalışma"));
        list.add(new PuantajDetayItem("Fiiil Çalışma"));
        list.add(new PuantajDetayItem("Mesai içerisinde bekleme"));
        list.add(new PuantajDetayItem("Mesai içerisinde bekleme"));




        PuantajDetayEkleAdapter adapter=new PuantajDetayEkleAdapter(this,list);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        eventHandler();


    }

    private void eventHandler() {
        onayla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), Makine_Isletme_MainFrame.class);
                intent.putExtra("Fragment","PUANTAJ");
                startActivity(intent);
            }
        });
    }


    private void init() {
        onayla=findViewById(R.id.onayla);
        recyclerView=findViewById(R.id.listview);
    }
}
