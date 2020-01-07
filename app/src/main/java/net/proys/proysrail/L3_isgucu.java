package net.proys.proysrail;

import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.Tag;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class L3_isgucu extends AppCompatActivity {
    protected ImageView ekleme,tick;
    private Button btnRemove;
    SQLiteHelper database;
    Get_Set veri;
    ImageView change_image;
    List<String> puantajdataheader;
    TextView imalat;
    private L3_isgucu_ExpandableListView_Adapter listadapter;
    private L3_isgucu_ExpandableListview_adapter_kapali listadapter_kapali;
    private ExpandableListView listView;
    private List<String> listdataheader;
    private HashMap<String,List<String>> listHash;
    private HashMap<String,List<String>> listHashPuantaj;
    int x = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l3_isgucu);
        veri = new Get_Set();
        init();
        //initdata();
        setOnclickEvents();
        setExpandableListViewOnNormal();
    }
    protected void init(){
        ekleme = findViewById(R.id.plus);
        listView = findViewById(R.id.listview);
        tick = findViewById(R.id.tick);
        change_image = findViewById(R.id.change);
        imalat = findViewById(R.id.imalat);
        imalat.setText(veri.getImalatIsgucu());
    }
    private void initdata(){

/*
        listdataheader.add("proys1");
        listdataheader.add("proys2");
        listdataheader.add("proys3");
        List<String> proys1 = new ArrayList<>();
        List<String> proys2 = new ArrayList<>();
        List<String> proys3 = new ArrayList<>();
        proys1.add("proys1");
        proys2.add("bu da proys21");
        proys2.add("bu da proys22");
        listHash.put(listdataheader.get(0),proys1);
        listHash.put(listdataheader.get(1),proys2);
        listHash.put(listdataheader.get(2),proys3);*/
    }
    protected void initdata1(){/*
        database = new SQLiteHelper(L3_isgucu.this);
        database.WriteTaslakResource(101000120190825L,"25.08.2019","T0001","R0002","iscilik","ekip",10,1,"efor");
        database.WriteTaslakResource(101000120190825L,"25.08.2019","T0001","R0001","iscilik","isci",12,1,"R0002");
        database.WriteTaslakResource(101000120190825L,"25.08.2019","T0001","R0003","iscilik","isci",0,1,"R0002");
        database.WriteTaslakResource(101000120190825L,"25.08.2019","T0001","R0004","iscilik","isci",9,1,"R0002");
        database.WriteTaslakResource(101000120190825L,"25.08.2019","T0001","R0005","iscilik","isci",10,1,"R0002");
        database.WriteTaslakResource(101000120190825L,"25.08.2019","T0001","R0006","iscilik","isci",10,1,"R0002");
        database.WriteTaslakResource(101000120190825L,"25.08.2019","T0001","R0007","iscilik","isci",10,1,"R0002");

        database.WriteTaslakResource(101000120190825L,"25.08.2019","T0001","R0013","iscilik","isci",10,1,"R0002");
        database.WriteTaslakResource(101000120190825L,"25.08.2019","T0001","R0014","iscilik","isci",10,1,"R0002");
        database.WriteTaslakResource(101000120190825L,"25.08.2019","T0001","R0015","iscilik","isci",10,1,"R0002");
        database.WriteTaslakResource(101000120190825L,"25.08.2019","T0001","R0016","iscilik","isci",10,1,"R0002");
        database.WriteTaslakResource(101000120190825L,"25.08.2019","T0001","R0017","iscilik","isci",9,1,"efor");*/




        /*
        database.WriteTaslakResource(101000120190825L,"25.08.2019","T0001","R0017","iscilik","verimsiz",-1,1,"V0001");
        database.WriteTaslakResource(101000120190825L,"25.08.2019","T0001","R0001","iscilik","isci",-1,1,"V0001");
        database.WriteTaslakResource(101000120190825L,"25.08.2019","T0001","R0003","iscilik","isci",-1,1,"V0001");
        database.WriteTaslakResource(101000120190825L,"25.08.2019","T0001","R0004","iscilik","isci",-1,1,"V0001");
        database.WriteTaslakResource(101000120190825L,"25.08.2019","T0001","R0005","iscilik","isci",-1,1,"V0001");
        database.WriteTaslakResource(101000120190825L,"25.08.2019","T0001","R0005","iscilik","isci",-2,1,"V0002");
        database.WriteTaslakResource(101000120190825L,"25.08.2019","T0001","R0006","iscilik","isci",-1,1,"V0001");
        database.WriteTaslakResource(101000120190825L,"25.08.2019","T0001","R0007","iscilik","isci",-1,1,"V0001");
        database.WriteTaslakResource(101000120190825L,"25.08.2019","T0001","R0008","iscilik","isci",-1,1,"V0001");
        database.WriteTaslakResource(101000120190825L,"25.08.2019","T0001","R0009","iscilik","isci",-1,1,"V0001");
        database.WriteTaslakResource(101000120190825L,"25.08.2019","T0001","R0010","iscilik","isci",-1,1,"V0001");
        database.WriteTaslakResource(101000120190825L,"25.08.2019","T0001","R0011","iscilik","isci",-1,1,"V0001");
        database.WriteTaslakResource(101000120190825L,"25.08.2019","T0001","R0012","iscilik","isci",-1,1,"V0001");
        database.WriteTaslakResource(101000120190825L,"25.08.2019","T0001","R0013","iscilik","isci",-1,1,"V0001");
        database.WriteTaslakResource(101000120190825L,"25.08.2019","T0001","R0014","iscilik","isci",-1,1,"V0001");
        database.WriteTaslakResource(101000120190825L,"25.08.2019","T0001","R0015","iscilik","isci",-1,1,"V0001");
        database.WriteTaslakResource(101000120190825L,"25.08.2019","T0001","R0016","iscilik","isci",-1,1,"V0001");
        database.WriteTaslakResource(101000120190825L,"25.08.2019","T0001","R0020","makine","makine",10,15,"R0022");
        database.WriteTaslakResource(101000120190825L,"25.08.2019","T0001","R0021","makine","verimsiz",-1,1,"V0001");
        database.WriteTaslakResource(101000120190825L,"25.08.2019","T0001","R0022","makine","grup",10,1,"efor");*/

    }
    protected void setOnclickEvents(){
        ekleme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L3_isgucu.this,L4_is_gucu.class);//TODO ONEMLİ
                intent.putExtra("tip","isgucu");
                startActivity(intent);
            }
        });
        tick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L3_isgucu.this,l2_is_gucu.class);
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
    protected void setExpandableListViewOnNormal(){
        initdata1();
        final SQLiteHelper database = new SQLiteHelper(L3_isgucu.this);
        Get_Set veri = new Get_Set();
        final List<String>[] groups = database.ReadTaslakResourceforExListViewGroup(String.valueOf(veri.getKod()),veri.getImalatIsgucuid());//list[]
        final HashMap<String,List<String>>[] hashMaps = database.ReadTaslakResourceforExListViewChild(String.valueOf(veri.getKod()),veri.getImalatIsgucuid(),groups[0],groups[1]);

        listadapter_kapali  = new L3_isgucu_ExpandableListview_adapter_kapali(this,groups[1],groups[2],hashMaps[0],hashMaps[1]);
        listView.setAdapter(listadapter_kapali);

        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Intent intent = new Intent(L3_isgucu.this,L4_isci_detay.class);
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
                    Intent intent = new Intent(L3_isgucu.this,L4_isci_detay.class);
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
    protected void setExpandableListViewOnPuantaj(){
        initdata1();
        final SQLiteHelper database = new SQLiteHelper(L3_isgucu.this);
        final Get_Set veri = new Get_Set();
        List<String>[] groups = database.ReadTaslakResourceforExListViewGroup(String.valueOf(veri.getKod()),veri.getImalatIsgucuid());//list[]

        final List<String> listdataheader_id = groups[0];
        listdataheader = groups[1];
        puantajdataheader = groups[2];

        HashMap<String,List<String>>[] hashMaps = database.ReadTaslakResourceforExListViewChild(String.valueOf(veri.getKod()),veri.getImalatIsgucuid(),listdataheader_id,listdataheader);
        listHash = hashMaps[0];
        listHashPuantaj = hashMaps[1];
        Log.d("puantaj1","asd");

        listadapter  = new L3_isgucu_ExpandableListView_Adapter(this,listdataheader,puantajdataheader,listHash,listHashPuantaj);
        listView.setAdapter(listadapter);
        for(int i = 0; i<listdataheader.size();i++) {
            listView.expandGroup(i);
        }
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, final int groupPosition, final int childPosition, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(L3_isgucu.this);
                //builder.setTitle("Puantaj");
                final EditText input = new EditText(L3_isgucu.this);
              /*  LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setText(listHashPuantaj.get(listdataheader.get(groupPosition)).get(childPosition));
                input.setLayoutParams(lp);*/
                builder.setView(R.layout.l3_is_gucu_pop_up);

              /*  builder.setPositiveButton("tamam", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        database.PopUpUpdate(String.valueOf(veri.getKod()),veri.getImalatIsgucuid(),Integer.valueOf(input.getText().toString()),database.ReadPersonelwisim(listHash.get(listdataheader.get(groupPosition)).get(childPosition)));
                        setExpandableListViewOnPuantaj();
                    }
                });
                builder.setNegativeButton("iptal",null);*/
                builder.show();
                return false;
            }
        });
        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, final int groupPosition, long id) {
               /*final AlertDialog alertDialog;
               AlertDialog.Builder  dialogBuilder = new AlertDialog.Builder(getApplicationContext());
                View layoutView = getLayoutInflater().inflate(R.layout.l3_is_gucu_pop_up,null);
                Button dialogButton = layoutView.findViewById(R.id.btnadd);
                dialogBuilder.setView(layoutView);
                alertDialog = dialogBuilder.create();
                alertDialog.show();
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                        Toast.makeText(L3_isgucu.this, "dfd        ", Toast.LENGTH_SHORT).show();
                    }
                });*/


               //todo e1 üstüne aramağan abi ile bakcen
                /*builder.setTitle("Puantaj");
                final EditText input = new EditText(L3_isgucu.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setText(puantajdataheader.get(groupPosition));
                input.setLayoutParams(lp);*/
            


               /* builder.setPositiveButton("tamam", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //kayna id için  databaseden isim to id yapılmadı child on clickten farklı olarak çünkü veri çekilen list zaten id tutuyor isim tutmuyor
                        database.PopUpUpdate(String.valueOf(veri.getKod()),veri.getImalatIsgucuid(),Integer.valueOf(input.getText().toString()),listdataheader_id.get(groupPosition));
                        setExpandableListViewOnPuantaj();
                    }
                });
                builder.setNegativeButton("iptal",null);*/
             

                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,l2_is_gucu.class);
        startActivity(intent);
    }

}
