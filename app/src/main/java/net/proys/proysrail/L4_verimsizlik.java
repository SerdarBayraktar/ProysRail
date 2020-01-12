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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class L4_verimsizlik extends AppCompatActivity {
    ListView list;
    Get_Set veri;
    TextView name;
    SQLiteHelper database;
    LinearLayout tick_linear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l4_verimsizlik);
        init();
        setListview();
        setOnclickListeners();
    }
    protected void init(){
        TextView imalattxt = findViewById(R.id.imalattxt);
        imalattxt.setText(String.valueOf(veri.getKod()).substring(13,15)+"."+String.valueOf(veri.getKod()).substring(11,13)+"."+String.valueOf(veri.getKod()).substring(7,11));
        list = findViewById(R.id.listview_l4_verimsizlik);
        name = findViewById(R.id.textView);
        tick_linear = findViewById(R.id.tick_linear);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);//to prevent display of keyboard on launch
    }
    protected void setListview() {
        Intent intent = getIntent();
        veri = new Get_Set();
        final SQLiteHelper database = new SQLiteHelper(L4_verimsizlik.this);
        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, database.ReadEtkenListesi());
        L4_verimsizlik_adapter arrayAdapter = new L4_verimsizlik_adapter(L4_verimsizlik.this, database.ReadEtkenListesi()[0], database.ReadVerimsizlik(String.valueOf(veri.getKod()), database.ReadGet_Set("ImalatId"), database.ReadEtkenListesi()[0]));
        list.setAdapter(arrayAdapter);
        /*list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    String etken_id = database.ReadEtkenListesiforid(database.ReadEtkenListesi()[0][position]);

                    if (not checked){
                        database.WriteTaslakVerimsizlik(String.valueOf(veri.getKod()),"tarih",database.ReadGet_Set("ImalatId"),etken_id,database.ReadEtkenListesifordeger(etken_id));
                    }else if (checked){
                        database.DeleteTaslakVerimsizlik(String.valueOf(veri.getKod()),database.ReadGet_Set("ImalatId"),etken_id);
                    }

                    Intent intent = new Intent(L4_verimsizlik.this, L3_verimsizlik.class);
                    startActivity(intent);



            }
        });*/

    }
    protected void setOnclickListeners(){
        tick_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L4_verimsizlik.this,L3_verimsizlik.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,L3_verimsizlik.class);
        startActivity(intent);
    }
}
