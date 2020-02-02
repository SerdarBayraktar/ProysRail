package net.proys.proysrail;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

public class L1_main extends AppCompatActivity {
    ListView list;
    Get_Set veri;
    private static String[] maintitle ;

    public static String[] getMaintitle() {
        return maintitle;
    }

    protected LinearLayout notlar_lin,bek_lin,dosyalar_lin,dahafazla_lin,tamamlanan_lin;

    protected ImageView notlar_icon,t_bild_icon,dosyalar_icon,dahafazla_icon,bek_bild;
    protected TextView bektxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l1_main);
        init();
        setList();
        all4menu();
        setIcons();
        setMaintitle();
    }
    protected void init(){
        bektxt = findViewById(R.id.bektxt);
        notlar_lin = findViewById(R.id.notlar_lin);
        dosyalar_lin = findViewById(R.id.dosyalar_lin);
        dahafazla_lin = findViewById(R.id.dahafazlalin);
        tamamlanan_lin = findViewById(R.id.tamamlanan_lin);
    }
    public void setMaintitle(){
        SQLiteHelper database = new SQLiteHelper(L1_main.this);
        Login_SQLiteHelper login_sqLiteHelper = new Login_SQLiteHelper(L1_main.this);
        int kullanici_id = Integer.valueOf(login_sqLiteHelper.ReadUsername(veri.getKullaniciAdi())[1]);
        List[] rows = database.ReadBildiriListesiforList(kullanici_id,0);
        maintitle = Arrays.copyOf(rows[0].toArray(new String[rows[0].size()]),rows[0].toArray(new String[rows[0].size()]).length,String[].class);


    }
    protected void setList(){
        ListView ls = findViewById(R.id.listview);
        L1_rapor_list adapter;
        SQLiteHelper database = new SQLiteHelper(L1_main.this);
        Login_SQLiteHelper login_sqLiteHelper = new Login_SQLiteHelper(L1_main.this);
        int kullanici_id = Integer.valueOf(login_sqLiteHelper.ReadUsername(veri.getKullaniciAdi())[1]);
        List[] rows = database.ReadBildiriListesiforList(kullanici_id,0);
        final String[] row1 = Arrays.copyOf(rows[0].toArray(new String[rows[0].size()]),rows[0].toArray(new String[rows[0].size()]).length,String[].class);
        final String[] row2 = Arrays.copyOf(rows[1].toArray(new String[rows[1].size()]),rows[1].toArray(new String[rows[1].size()]).length,String[].class);

        adapter = new L1_rapor_list(this, row1, row2);

        list=(ListView)findViewById(R.id.listview);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(L1_main.this,IP1ISCILIKPUANTAJ.class);
                    startActivity(intent);
                } else {

                    Get_Set veri = new Get_Set();
                    veri.setPosition(position);
                    L1_main l1_main = new L1_main();
                    SQLiteHelper database = new SQLiteHelper(L1_main.this);
                    String tarih = l1_main.getMaintitle()[veri.getPosition()].subSequence(l1_main.getMaintitle()[veri.getPosition()].length() - 11, l1_main.getMaintitle()[veri.getPosition()].length() - 1).toString();
                    String bildiri = database.ReadBildirilerwIsim(l1_main.getMaintitle()[veri.getPosition()].split(" ")[0] + " " + l1_main.getMaintitle()[veri.getPosition()].split(" ")[1])[0];
                    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
                    SimpleDateFormat sdf1 = new SimpleDateFormat("dd.MM.yyyy");
                    Long kod = null;
                    try {
                        kod = Long.valueOf(database.ReadGet_Set("KullaniciId") + String.valueOf(bildiri).substring(1) + sdf2.format(sdf1.parse(tarih)));
                        veri.setKod(kod);

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent(L1_main.this, L2_bildiri.class);
                    intent.putExtra("main", row1[position]);
                    intent.putExtra("sub", row2[position]);
                    startActivity(intent);

                }
            }
        });
    }
    protected void all4menu(){
        notlar_icon = findViewById(R.id.imagenotlar);
        t_bild_icon = findViewById(R.id.imagetamamlananbild);
        dosyalar_icon = findViewById(R.id.dosyalar);
        dahafazla_icon = findViewById(R.id.imagedahafazla);
        bek_bild = findViewById(R.id.imagebekbild);
        dahafazla_lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L1_main.this,L1_dahafazla.class);
                startActivity(intent);
            }
        });
        tamamlanan_lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L1_main.this,L1_tamamlanan.class);
                startActivity(intent);
            }
        });

        notlar_lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L1_main.this,L1_Notlar.class);
                startActivity(intent);
            }
        });
        dosyalar_lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L1_main.this,L1_Dosyalar.class);
                startActivity(intent);
            }
        });
    }
    protected void setIcons(){
        bek_bild.setImageResource(R.drawable.bekleyen_bildiriler_o);
        bektxt.setTextColor(getResources().getColor(R.color.text_color_yellow));
    }







    protected void listadapter(){




        /*
    ArrayList<String> arrayList = new ArrayList<String>();
    for(int i = 0; i<6;i++){
    arrayList.add(i+".imalat");
    }
    ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);
    ls.setAdapter(arrayAdapter);

    Imalat_list_adapter adapter = new Imalat_list_adapter(this, maintitle, subtitle);
    list.setAdapter(adapter);


    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


    }
    });*/
    }

    @Override
    public void onBackPressed() {
    }
}
