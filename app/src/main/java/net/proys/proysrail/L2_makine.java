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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

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
                L1_main l1_main = new L1_main();
                veri = new Get_Set();
                SQLiteHelper database = new SQLiteHelper(L2_makine.this);
                String tarih = l1_main.getMaintitle()[veri.getPosition()].subSequence(l1_main.getMaintitle()[veri.getPosition()].length()-11,l1_main.getMaintitle()[veri.getPosition()].length()-1).toString();
                String bildiri = database.ReadBildirilerwIsim(l1_main.getMaintitle()[veri.getPosition()].split(" ")[0]+" "+ l1_main.getMaintitle()[veri.getPosition()].split(" ")[1])[0];
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
                SimpleDateFormat sdf1 = new SimpleDateFormat("dd.MM.yyyy");
                try {

                    Long kod = Long.valueOf(database.ReadGet_Set("ImalatId")+String.valueOf(bildiri).substring(1)+sdf2.format(sdf1.parse(tarih)));
                    database.UpdateBildiriListesi(kod,"SENT",1);
                    Intent intent = new Intent(L2_makine.this,L1_main.class);
                    startActivity(intent);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,L1_main.class);
        startActivity(intent);
    }
}
