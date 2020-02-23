package net.proys.proysrail;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import net.proys.proysrail.Entities.CalisanListeEntity;
import net.proys.proysrail.Entities.CalisanPuantajEntity;
import net.proys.proysrail.Entities.CalisanVerimsizlikEntity;
import net.proys.proysrail.Entities.EtkenListeEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class L4_is_gucu extends AppCompatActivity {
    protected ImageView onay,ekip_icon;
    private ListView depo_list,secilen_list;
    private EditText arama_motoru;
    TextView imalat;
    protected List<String> depo,secilen;
    Get_Set veri;
    SQLiteHelper database;
    RoomHelper dh;
    RoomDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l4_is_gucu);
        veri = new Get_Set();
        dh = new RoomHelper(L4_is_gucu.this);
        db = RoomDatabase.getDatabase(L4_is_gucu.this);
        init();
        setOnclickEvents();
        setLists();
        setListView1();
        setListView2();
        setArama_motoru();
    }
    protected void init(){
        onay = findViewById(R.id.onay);
        depo_list = findViewById(R.id.list_view);
        secilen_list = findViewById(R.id.list_view2);
        arama_motoru = findViewById(R.id.editText);
        imalat = findViewById(R.id.imalat);
        imalat.setText(veri.getImalatIsgucu());
         database = new SQLiteHelper(L4_is_gucu.this);
         secilen = new ArrayList<>();
         depo = new ArrayList<>();
         ekip_icon = findViewById(R.id.ekip);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);//to prevent display of keyboard on launch



    }
    private void setOnclickEvents(){
        onay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L4_is_gucu.this,L3_isgucu.class);
                startActivity(intent);
            }
        });
        ekip_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L4_is_gucu.this,L4_is_gucu_ekip.class);
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
                    //depo = dh.ReadPersonelforL4Sepet(dh.readGetSet("gerceklesmeId"),dh.readGetSet("imalatId"),dh.readGetSet("bildiriId")); todo veritabaniroom
                    depo = database.ReadPersonelforL4Sepet(String.valueOf(veri.getKod()),database.ReadGet_Set("ImalatId"));
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
    protected void setLists(){
        Get_Set veri = new Get_Set();
        //depo = dh.ReadPersonelforL4Sepet(dh.readGetSet("gerceklesmeId"),dh.readGetSet("imalatId"),dh.readGetSet("bildiriId")); todo veritabaniroom
        depo = database.ReadPersonelforL4Sepet(String.valueOf(veri.getKod()),database.ReadGet_Set("ImalatId"));
        //secilen = dh.ReadPersonelforL4SepetSecilen(dh.readGetSet("gerceklesmeId"),dh.readGetSet("imalatId"),dh.readGetSet("bildiriId")); todo veritabaniroom
        secilen = database.ReadPersonelforL4SepetSecilen(String.valueOf(veri.getKod()),veri.getImalatIsgucuid());

    }
    protected void setListView1(){

        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,isciler);
        L4_is_gucu_adapter1 arrayAdapter = new L4_is_gucu_adapter1(L4_is_gucu.this,depo);
        depo_list.setAdapter(arrayAdapter);
        depo_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            secilen.add(depo.get(position));
            String[] bilgiler = database.ReadPersonel(database.ReadPersonelwisim(depo.get(position)));
            if (bilgiler.length>=6){
                String tarih = String.valueOf(veri.getKod()).substring(13,15)+"."+String.valueOf(veri.getKod()).substring(11,13)+"."+String.valueOf(veri.getKod()).substring(7,11);
                Get_Set veri = new Get_Set();
                database.WriteTaslakResource(veri.getKod(),tarih,veri.getImalatIsgucuid(),bilgiler[0],"iscilik","isci",Integer.valueOf(bilgiler[6]),1,"efor");//TODO CHECK TARIH IMALAT
                List[] verimsizlik_listler = database.ReadEtkenListesiforlist(String.valueOf(veri.getKod()),database.ReadGet_Set("ImalatId"));// l3 verimsizlik ile aynı kod bu tüzden etken isim olarak var onu id ye çevirip kullanıcım
                String[] etken = Arrays.copyOf(verimsizlik_listler[0].toArray(new String[verimsizlik_listler[0].size()]),verimsizlik_listler[0].toArray(new String[verimsizlik_listler[0].size()]).length,String[].class);
                String[] deger = Arrays.copyOf(verimsizlik_listler[1].toArray(new String[verimsizlik_listler[0].size()]),verimsizlik_listler[1].toArray(new String[verimsizlik_listler[1].size()]).length,String[].class);
                for (int i =0; i<etken.length; i++){
                    database.WriteTaslakResource(veri.getKod(),tarih,veri.getImalatIsgucuid(),bilgiler[0],"iscilik","verimsiz",Integer.valueOf(deger[i]),1,database.ReadEtkenListesiforid(etken[i]));
                }
            }
                depo.remove(position);
                setListView1();
                setListView2();

            }
        });
/*  todo veritabaniroom
        depo_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                secilen.add(depo.get(position));
                List<CalisanListeEntity> bilgiler = db.calisanListeDao().readCalisan(dh.ReadPersonelwIsim(depo.get(position)));
                if (bilgiler.size()>=6){
                    String tarih = String.valueOf(veri.getKod()).substring(13,15)+"."+String.valueOf(veri.getKod()).substring(11,13)+"."+String.valueOf(veri.getKod()).substring(7,11);
                    CalisanPuantajEntity entity = new CalisanPuantajEntity();
                    entity.setCalisan(String.valueOf(bilgiler.get(0).getCalisan_id()));
                    entity.setPuantaj(bilgiler.get(0).getVt_puantaj());
                    entity.setPuantaj_tipi("calisti");
                    entity.setFazla_mesai(0.0f);
                    entity.setTarih(tarih);
                    entity.setBildiri(dh.readGetSet("bildiriId"));
                    entity.setImalat(dh.readGetSet("imalatId"));
                    entity.setGerceklesme(dh.readGetSet("gerceklesmeId"));
                    db.calisanPuantajDao().ekle(entity);

                    List<EtkenListeEntity> etkenListeEntities = db.etkenListeDao().readAll();
                     for (int i =0; i<etkenListeEntities.size(); i++){
                        CalisanVerimsizlikEntity calisanVerimsizlikEntity= new CalisanVerimsizlikEntity();
                        calisanVerimsizlikEntity.setCalisan(String.valueOf(bilgiler.get(0).getCalisan_id()));
                        calisanVerimsizlikEntity.setEtken(etkenListeEntities.get(i).getIsim());
                        calisanVerimsizlikEntity.setDeger(etkenListeEntities.get(i).getVt_deger());
                        calisanVerimsizlikEntity.setTarih(tarih);
                        calisanVerimsizlikEntity.setBildiri(dh.readGetSet("bildiriId"));
                        calisanVerimsizlikEntity.setImalat(dh.readGetSet("imalatId"));
                        db.calisanVerimsizlikDao().ekle(calisanVerimsizlikEntity);
                    }
                }
                depo.remove(position);
                setListView1();
                setListView2();
            }
        });*/
    }

    protected void setListView2(){

        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,isciler);
        L4_isgucu_secilen_adapter arrayAdapter = new L4_isgucu_secilen_adapter(L4_is_gucu.this,secilen);
        secilen_list.setAdapter(arrayAdapter);
        secilen_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Get_Set veri =new Get_Set();
                int durum = database.ReadTaslakforL4SepetSecilenClick(String.valueOf(veri.getKod()),database.ReadPersonelwisim(secilen.get(position)),veri.getImalatIsgucuid());
                if (durum==1){
                    database.DeleteTaslakResourceVerimsizlik(String.valueOf(veri.getKod()),veri.getImalatIsgucuid(),database.ReadPersonelwisim(secilen.get(position)));
                    depo.add(secilen.get(position));
                    secilen.remove(position);
                }else if (durum==2){//gurp kısmı devre dışı todo for beta 1 sonrası ekip
                    database.DeleteTaslakResourceVerimsizlikforGrup(String.valueOf(veri.getKod()),veri.getImalatIsgucuid(),database.ReadPersonelwisim(secilen.get(position)));
                    secilen.remove(position);

                }
                setListView1();
                setListView2();
            }
        });/*todo veritabaniroom
        secilen_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Get_Set veri =new Get_Set();
                int durum = database.ReadTaslakforL4SepetSecilenClick(String.valueOf(veri.getKod()),database.ReadPersonelwisim(secilen.get(position)),veri.getImalatIsgucuid());
                durum =1; //devre dışı şuan ekio o yüzden bunu yaptım
                if (durum==1){
                    db.calisanVerimsizlikDao().deleteVerimsizlik(dh.readGetSet("bildiriId"),dh.ReadPersonelwIsim(secilen.get(position)),dh.readGetSet("imalatId"));
                    depo.add(secilen.get(position));
                    secilen.remove(position);
                }else if (durum==2){//gurp kısmı devre dışı todo for beta 1 sonrası ekip
                    database.DeleteTaslakResourceVerimsizlikforGrup(String.valueOf(veri.getKod()),veri.getImalatIsgucuid(),database.ReadPersonelwisim(secilen.get(position)));
                    secilen.remove(position);

                }
                setListView1();
                setListView2();
            }
        });*/
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,L3_isgucu.class);
        startActivity(intent);
    }
}
