package net.proys.proysrail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class L4_sektor extends AppCompatActivity {
    ListView list;
    Get_Set veri;
    TextView name;
    SQLiteHelper database;
    LinearLayout tick_linear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l4_sektor);
        init();
        setListview();
        setOnclickListeners();
    }
    protected void init(){
        list = findViewById(R.id.listview_l4_verimsizlik);
        name = findViewById(R.id.textView);
        tick_linear = findViewById(R.id.tick_linear);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);//to prevent display of keyboard on launch
    }
    protected void setListview() {
        veri = new Get_Set();

        database = new SQLiteHelper(L4_sektor.this);
        String imalatid = database.ReadImalatwisim(veri.getImalat())[0];
        List<String> sektorler = new ArrayList<String>();
        for (int i = 1; i <= 10; i++) {
            if (database.ReadSektor(100 + i)[6].contains(imalatid)) {
                if (!sektorler.contains(database.ReadSektor(100 + i)[0])) {
                    sektorler.add(database.ReadSektor(100 + i)[0]);
                }
            }
        }
        final String[] sektorler_array = sektorler.toArray(new String[sektorler.size()]);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sektorler_array);
        list.setAdapter(arrayAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(L4_sektor.this, L3_imalat.class);
                veri.setSektör(sektorler_array[position]);
                L1_main l1_main = new L1_main();
                SQLiteHelper database = new SQLiteHelper(L4_sektor.this);
                String tarih = l1_main.getMaintitle()[veri.getPosition()].subSequence(l1_main.getMaintitle()[veri.getPosition()].length() - 11, l1_main.getMaintitle()[veri.getPosition()].length() - 1).toString();
                //String bildiri = database.ReadBildirilerwIsim(l1_main.getMaintitle()[veri.getPosition()].split(" ")[0] + " " + l1_main.getMaintitle()[veri.getPosition()].split(" ")[1])[0];
                List[] diziler = database.ReadTaslakfList(String.valueOf(veri.getKod()));
                Integer[] kopya_nolar = Arrays.copyOf(diziler[5].toArray(new Integer[diziler[5].size()]), diziler[5].toArray(new Integer[diziler[5].size()]).length, Integer[].class);
                database.UpdateTaslak(String.valueOf(veri.getKod()), tarih, veri.getImalat(), kopya_nolar[veri.getPositionL2()], "SEKTOR", veri.getSektör());
                startActivity(intent);
            }
        });



    }
    protected void setOnclickListeners(){
        tick_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L4_sektor.this,L3_imalat.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,L3_imalat.class);
        startActivity(intent);
    }
}
