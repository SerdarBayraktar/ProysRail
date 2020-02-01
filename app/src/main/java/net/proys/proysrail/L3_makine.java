package net.proys.proysrail;

import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class L3_makine extends AppCompatActivity {


    protected ImageView ekleme,tick, change_image;
    protected ExpandableListView listView;
    SQLiteHelper database;
    List<String> puantajdataheader;
    List<String> sayidataheader;
    Get_Set veri;
    TextView imalat;
    L3_makine_expandableListView_adapter listadapter;
    L3_makine_expandableListView_adapter_kapali listadapter_kapali;
    private List<String> listdataheader;
    private HashMap<String,List<String>> listHash;
    private HashMap<String,List<String>> listHashPuantaj;
    int x = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l3_makine);
        veri = new Get_Set();
        init();
        setOnclickEvents();
        setExpandableListViewOnNormal();
    }

    protected void init(){
        change_image = findViewById(R.id.change);
        ekleme = findViewById(R.id.plus);
        listView = findViewById(R.id.listview);
        tick = findViewById(R.id.tick);
        imalat = findViewById(R.id.imalat);
        imalat.setText(veri.getImalatIsgucu());
    }

    protected void setOnclickEvents(){
        ekleme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L3_makine.this,L4_makine.class);
                startActivity(intent);
            }
        });
        tick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L3_makine.this,L2_makine.class);
                startActivity(intent);

            }
        });
        change_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (x==0){
                    setExpandableListViewOnPuantaj();
                    change_image.setImageResource(R.drawable.tickforpuantaj);
                    x=1;

                }else if (x==1){
                    setExpandableListViewOnNormal();
                    change_image.setImageResource(R.drawable.pencil);
                    x=0;

                }
            }
        });

    }
    protected void setExpandableListViewOnPuantaj(){
        final SQLiteHelper database = new SQLiteHelper(L3_makine.this);
        final Get_Set veri = new Get_Set();
        List<String>[] groups = database.ReadTaslakResourceforExListViewGroupMakine(String.valueOf(veri.getKod()),veri.getImalatIsgucuid());//list[]

        final List<String> listdataheader_id = groups[0];
        listdataheader = groups[1];
        puantajdataheader = groups[2];
        sayidataheader = groups[3];

        HashMap<String,List<String>>[] hashMaps = database.ReadTaslakResourceforExListViewChildMakine(String.valueOf(veri.getKod()),veri.getImalatIsgucuid(),listdataheader_id,listdataheader);
        listHash = hashMaps[0];
        listHashPuantaj = hashMaps[1];
        Log.d("puantaj1","asd");

        listadapter  = new L3_makine_expandableListView_adapter(this,listdataheader,puantajdataheader,listHash,listHashPuantaj, sayidataheader);
        listView.setAdapter(listadapter);
        for(int i = 0; i<listdataheader.size();i++) {
            listView.expandGroup(i);
        }
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, final int groupPosition, final int childPosition, long id) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(L3_makine.this);

                builder.setTitle("Puantaj Değiştir");
                final EditText input = new EditText(L3_makine.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setText(listHashPuantaj.get(listdataheader.get(groupPosition)).get(childPosition));
                input.setLayoutParams(lp);
                builder.setView(input);
                builder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        database.PopUpUpdate(String.valueOf(veri.getKod()),veri.getImalatIsgucuid(),Integer.valueOf(input.getText().toString()),database.ReadPersonelwisim(listHash.get(listdataheader.get(groupPosition)).get(childPosition)));
                        if (!database.ReadPersonel(database.ReadPersonelwisim(listHash.get(listdataheader.get(groupPosition)).get(childPosition)))[7].equals("1")){
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(L3_makine.this);
                            builder1.setTitle("Sayıyı Değiştir");
                            final EditText sayi = new EditText(L3_makine.this);
                            LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.MATCH_PARENT);
                            sayi.setText(listHashPuantaj.get(listdataheader.get(groupPosition)).get(childPosition));
                            sayi.setLayoutParams(lp1);
                            builder1.setView(sayi);
                            builder1.setPositiveButton("Değiştir", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    database.PopUpUpdateWsayi(String.valueOf(veri.getKod()),veri.getImalatIsgucuid(),Integer.valueOf(input.getText().toString()),database.ReadPersonelwisim(listHash.get(listdataheader.get(groupPosition)).get(childPosition)),Integer.valueOf(sayi.getText().toString()));
                                    setExpandableListViewOnPuantaj();

                                }
                            });
                            builder1.setNegativeButton("İptal",null);
                            builder1.show();
                        }
                        setExpandableListViewOnPuantaj();
                    }
                });
                builder.setNegativeButton("iptal",null);
                builder.show();
                /*

                if (!database.ReadPersonel(database.ReadPersonelwisim(listdataheader.get(groupPosition)))[7].equals("1")){
                    final TextView text = new TextView(L3_makine.this);
                    text.setText("Sayı");
                    text.setLayoutParams(lp);
                    builder.setView(text);
                    final EditText sayi = new EditText(L3_makine.this);
                    sayi.setText(sayidataheader.get(groupPosition));
                    sayi.setLayoutParams(lp);
                    builder.setView(sayi);
                    builder.setPositiveButton("tamam", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            database.PopUpUpdateWsayi(String.valueOf(veri.getKod()),veri.getImalatIsgucuid(),Integer.valueOf(input.getText().toString()),database.ReadPersonelwisim(listHash.get(listdataheader.get(groupPosition)).get(childPosition)),Integer.valueOf(sayi.getText().toString()));
                            setExpandableListViewOnPuantaj();
                        }
                    });
                    builder.setNegativeButton("iptal",null);
                    builder.show();
                }else {


                }
*/
                return false;
            }
        });
        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, final int groupPosition, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(L3_makine.this);
                builder.setTitle("Puantaj ve Sayıyı değiştir");
                final EditText input = new EditText(L3_makine.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setText(puantajdataheader.get(groupPosition));
                input.setLayoutParams(lp);
                builder.setView(input);
                    /*final TextView text = new TextView(L3_makine.this);
                    text.setText("Sayjlkjhgı");
                    text.setLayoutParams(lp);
                    builder.setView(text);
                    final EditText sayi = new EditText(L3_makine.this);
                    sayi.setText(sayidataheader.get(groupPosition));
                    sayi.setLayoutParams(lp);
                    builder.setView(sayi);*/
                    builder.setPositiveButton("tamam", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //kayna id için  databaseden isim to id yapılmadı child on clickten farklı olarak çünkü veri çekilen list zaten id tutuyor isim tutmuyor
                            database.PopUpUpdate(String.valueOf(veri.getKod()),veri.getImalatIsgucuid(),Integer.valueOf(input.getText().toString()),listdataheader_id.get(groupPosition));
                            if (!database.ReadPersonel(database.ReadPersonelwisim(listdataheader.get(groupPosition)))[7].equals("1")){
                                AlertDialog.Builder builder1 = new AlertDialog.Builder(L3_makine.this);
                                builder1.setTitle("Sayıyı değiştir");
                                final EditText sayi = new EditText(L3_makine.this);
                                LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(
                                        LinearLayout.LayoutParams.MATCH_PARENT,
                                        LinearLayout.LayoutParams.MATCH_PARENT);
                                sayi.setText(sayidataheader.get(groupPosition));
                                sayi.setLayoutParams(lp1);
                                builder1.setView(sayi);
                                builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        database.PopUpUpdateWsayi(String.valueOf(veri.getKod()),veri.getImalatIsgucuid(),Integer.valueOf(input.getText().toString()),listdataheader_id.get(groupPosition),Integer.valueOf(sayi.getText().toString()));
                                        setExpandableListViewOnPuantaj();

                                    }
                                });
                                builder1.setNegativeButton("no",null);
                                builder1.show();
                            }
                            setExpandableListViewOnPuantaj();
                        }
                    });
                    builder.setNegativeButton("iptal",null);
                    builder.show();



                return true;
            }
        });

    }
    protected void setExpandableListViewOnNormal(){
        final SQLiteHelper database = new SQLiteHelper(L3_makine.this);
        Get_Set veri = new Get_Set();
        final List<String>[] groups = database.ReadTaslakResourceforExListViewGroupMakine(String.valueOf(veri.getKod()),veri.getImalatIsgucuid());//list[]
        final HashMap<String,List<String>>[] hashMaps = database.ReadTaslakResourceforExListViewChildMakine(String.valueOf(veri.getKod()),veri.getImalatIsgucuid(),groups[0],groups[1]);

        listadapter_kapali  = new L3_makine_expandableListView_adapter_kapali(this,groups[1],groups[2],hashMaps[0],hashMaps[1],groups[3]);
        listView.setAdapter(listadapter_kapali);

        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Intent intent = new Intent(L3_makine.this,L4_makine_detay.class);
                intent.putExtra("kisi",hashMaps[0].get(groups[1].get(groupPosition)).get(childPosition));
                intent.putExtra("verim",database.ReadPersonelwisim(groups[1].get(groupPosition)));
                startActivity(intent);
                return false;
            }
        });
        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (listadapter_kapali.getChildrenCount(groupPosition)==0){
                    Intent intent = new Intent(L3_makine.this,L4_makine_detay.class);
                    intent.putExtra("kisi",groups[1].get(groupPosition));
                    intent.putExtra("verim","efor");
                    startActivity(intent);
                }else{
                    //do nothing
                    return false;
                }
                return true;
            }
        });

    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,L2_makine.class);
        startActivity(intent);
    }

}
