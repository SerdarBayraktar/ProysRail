package net.proys.proysrail;

import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class L4_makine extends AppCompatActivity {

    protected TextView makine,makinegrup,imalat;
    Get_Set veri;
    SQLiteHelper database;
    private EditText arama_motoru;

    protected ImageView onay,ekip_icon;
    private ListView depo_list,secilen_list;
    protected List<String> depo,secilen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l4_makine);
        veri = new Get_Set();
        init();
        setLists();
        setListView1();
        setListView2();
        setOnclickEvents();
        setArama_motoru();
    }
    protected void init(){
        arama_motoru = findViewById(R.id.editText);
        ekip_icon = findViewById(R.id.ekip);
        onay = findViewById(R.id.onay);
        database = new SQLiteHelper(this);
        depo_list  = findViewById(R.id.list_view);
        secilen_list = findViewById(R.id.list_view2);
        makine = findViewById(R.id.makine);
        imalat = findViewById(R.id.imalat);
        imalat.setText(veri.getImalatIsgucu());
        secilen = new ArrayList<>();
        depo = new ArrayList<>();
        makinegrup = findViewById(R.id.makinegrup);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);//to prevent display of keyboard on launch

    }
    protected void setOnclickEvents(){
        onay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L4_makine.this,L3_makine.class);
                startActivity(intent);
            }
        });
        ekip_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L4_makine.this,L4_makine_grup.class);
                startActivity(intent);
            }
        });
    }
    protected void setArama_motoru(){
        arama_motoru.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("")) {
                    depo = database.ReadPersonelforL4SepetMakine(String.valueOf(veri.getKod()));
                } else {
                    List<String> depo_searched = new ArrayList<>();
                    for (int i = 0; i < depo.size(); i++) {
                        if (depo.get(i).toLowerCase().contains(s.toString().toLowerCase())) {
                            depo_searched.add(depo.get(i));
                        }
                    }
                    depo = depo_searched;
                }
                setListView1();
            }
        });
    }

    public void setListView1() {

        L4_is_gucu_adapter1 arrayAdapter = new L4_is_gucu_adapter1(L4_makine.this,depo);
        depo_list.setAdapter(arrayAdapter);
        depo_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,final int position, long id) {
                final String[] bilgiler = database.ReadPersonel(database.ReadPersonelwisim(depo.get(position)));
                if (bilgiler.length>7){
                    if (Integer.valueOf(bilgiler[7])>1){
                        AlertDialog.Builder builder = new AlertDialog.Builder(L4_makine.this);
                        builder.setTitle("Adet");
                        final EditText input = new EditText(L4_makine.this);
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.MATCH_PARENT);
                        input.setText(String.valueOf(bilgiler[7]));
                        input.setLayoutParams(lp);
                        builder.setView(input);
                        builder.setPositiveButton("Ekle", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                secilen.add(depo.get(position));
                                if (bilgiler.length>=6){
                                    Get_Set veri = new Get_Set();
                                    database.WriteTaslakResource(veri.getKod(),"tarih",veri.getImalatIsgucuid(),bilgiler[0],"makine","makine",Integer.valueOf(bilgiler[6]),Integer.valueOf(input.getText().toString()),"efor");//TODO CHECK TARIH IMALAT
                                }
                                depo.remove(position);
                                setListView1();
                                setListView2();

                            }
                        });
                        builder.setNegativeButton("İptal Et",null);
                        builder.show();

                    }else {
                        secilen.add(depo.get(position));
                        if (bilgiler.length>=6){
                            Get_Set veri = new Get_Set();
                            database.WriteTaslakResource(veri.getKod(),"tarih",veri.getImalatIsgucuid(),bilgiler[0],"makine","makine",Integer.valueOf(bilgiler[6]),1,"efor");//TODO CHECK TARIH IMALAT
                        }
                        depo.remove(position);
                        setListView1();
                        setListView2();

                    }

                }
            }
        });

    }
    protected void setLists(){
        Get_Set veri = new Get_Set();
        depo = database.ReadPersonelforL4SepetMakine(String.valueOf(veri.getKod()));
        secilen = database.ReadPersonelforL4SepetSecilenMakine(String.valueOf(veri.getKod()),veri.getImalatIsgucuid());
    }
    protected void setListView2(){

        L4_isgucu_secilen_adapter arrayAdapter = new L4_isgucu_secilen_adapter(L4_makine.this,secilen);
        secilen_list.setAdapter(arrayAdapter);
        secilen_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Get_Set veri =new Get_Set();
                int durum = database.ReadTaslakforL4SepetSecilenClickMakine(String.valueOf(veri.getKod()),database.ReadPersonelwisim(secilen.get(position)),veri.getImalatIsgucuid());
                if (durum==1){
                    depo.add(secilen.get(position));
                    secilen.remove(position);
                }else if (durum==2){
                    secilen.remove(position);
                }
                setListView1();
                setListView2();
            }
        });

    }



    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,L3_makine.class);
        startActivity(intent);
    }
}
