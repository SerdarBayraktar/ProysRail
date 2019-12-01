package net.proys.proysrail;

import android.content.Intent;
import android.graphics.Typeface;
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

public class l2_is_gucu extends AppCompatActivity {
    ImageView ImageView, isci_icon, malzeme_icon, imalat_icon, makine_icon, aciklama_icon, medya_icon, plus,sent;
    LinearLayout isci_linear,imalat_linear,makine_linear,malzeme_linear,aciklama_linear,medya_linear;
    TextView isci_txt;
    ExpandableListView listView;
    LinearLayout subbalast;
    Get_Set veri;
    SQLiteHelper database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l2_is_gucu);
        veri = new Get_Set();
        init();
        setListView();
        allset4icon();
        setOnclickEvents();
    }
    private void init() {
        listView = findViewById(R.id.listview);
        isci_txt = findViewById(R.id.isci_txt);
        isci_icon = findViewById(R.id.imageisci);
        imalat_icon = findViewById(R.id.imageImalat);
        makine_icon = findViewById(R.id.imageMakine);
        malzeme_icon = findViewById(R.id.imageMalzeme);
        aciklama_icon = findViewById(R.id.imageAciklama);
        medya_icon = findViewById(R.id.imageCamera);
        subbalast = findViewById(R.id.Subbaslast);
        plus = findViewById(R.id.plus);
        sent = findViewById(R.id.sent);
        imalat_linear = findViewById(R.id.imalat_linear);
        isci_linear = findViewById(R.id.isgucu_linear);
        malzeme_linear = findViewById(R.id.malzeme_linear);
        makine_linear = findViewById(R.id.makine_linear);
        medya_linear = findViewById(R.id.medya_linear);
        aciklama_linear = findViewById(R.id.aciklama_linear);
    }
    protected void allset4icon() {
        isci_icon.setImageResource(R.drawable.l2_isgucu_o);
        isci_txt.setTextColor(getResources().getColor(R.color.text_color_yellow));
        Typeface typeface = getResources().getFont(R.font.opensans_semibold);
        isci_txt.setTypeface(typeface);
        if (!veri.getAciklamalar().equals("")) {
            aciklama_icon.setImageResource(R.drawable.l2_aciklama_d);
        }
    }
    protected void setOnclickEvents() {
        isci_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        malzeme_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(l2_is_gucu.this, "Henüz hazır değil.", Toast.LENGTH_SHORT).show();
            }
        });
        imalat_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(l2_is_gucu.this, L2_bildiri.class);
                startActivity(intent);
            }
        });
        aciklama_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(l2_is_gucu.this, L2_aciklama.class);
                startActivity(intent);
            }
        });
        medya_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(l2_is_gucu.this, L2_medya.class);
                startActivity(intent);
            }
        });
        makine_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(l2_is_gucu.this, L2_makine.class);
                startActivity(intent);
            }
        });
       /* plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(l2_is_gucu.this, "Henüz aktif değil", Toast.LENGTH_SHORT).show();
            }
        });*/
        sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                L1_main l1_main = new L1_main();
                veri = new Get_Set();
                database = new SQLiteHelper(l2_is_gucu.this);
                String tarih = l1_main.getMaintitle()[veri.getPosition()].subSequence(l1_main.getMaintitle()[veri.getPosition()].length()-11,l1_main.getMaintitle()[veri.getPosition()].length()-1).toString();
                String bildiri = database.ReadBildirilerwIsim(l1_main.getMaintitle()[veri.getPosition()].split(" ")[0]+" "+ l1_main.getMaintitle()[veri.getPosition()].split(" ")[1])[0];
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
                SimpleDateFormat sdf1 = new SimpleDateFormat("dd.MM.yyyy");
                try {

                    Long kod = Long.valueOf(database.ReadGet_Set("ImalatId")+String.valueOf(bildiri).substring(1)+sdf2.format(sdf1.parse(tarih)));
                    database.UpdateBildiriListesi(kod,"SENT",1);
                    Intent intent = new Intent(l2_is_gucu.this,L1_main.class);
                    startActivity(intent);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void setListView() {
        database = new SQLiteHelper(l2_is_gucu.this);
        final List[] lists = database.CreateL2IsgucuKartPart1(String.valueOf(veri.getKod()));
        HashMap[] hashMaps = database.CreateL2IsgucuKartPart2(lists[0],lists[1],String.valueOf(veri.getKod()));
        L2_is_gucu_expandable_listview_adapter adapter = new L2_is_gucu_expandable_listview_adapter(l2_is_gucu.this,lists[0],hashMaps[0],hashMaps[1]);



        //L2_is_gucu_adapter adapter = new L2_is_gucu_adapter(this, ekipler, puantaj, kayip_saat);
        listView.setAdapter(adapter);
        listView.setDivider(null);
        listView.setGroupIndicator(null);
        listView.setChildIndicator(null);
        listView.setDividerHeight(0);
        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Get_Set veri = new Get_Set();
                veri.setImalatIsgucu(String.valueOf(lists[0].get(groupPosition)));
                veri.setImalatIsgucuid(String.valueOf(lists[1].get(groupPosition)));
                Intent intent = new Intent(l2_is_gucu.this, L3_isgucu.class);
                startActivity(intent);
                return true;
            }
        });
        for(int i = 0; i<lists[0].size();i++) {
            listView.expandGroup(i);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Get_Set veri = new Get_Set();
                veri.setImalatIsgucu(String.valueOf(lists[0].get(position)));
                veri.setImalatIsgucuid(String.valueOf(lists[1].get(position)));
                Intent intent = new Intent(l2_is_gucu.this, L3_isgucu.class);
                intent.putExtra("tip","isgucu");
                startActivity(intent);
            }
        });


    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,L1_main.class);
        startActivity(intent);
    }
}
