package net.proys.proysrail;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import net.proys.proysrail.Adapters.İp1PopupAdapter;
import net.proys.proysrail.Adapters.İscilikPuantajAdapter;
import net.proys.proysrail.Items.İsciPuantajItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class IP1ISCILIKPUANTAJ extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<İsciPuantajItem> array;
    İscilikPuantajAdapter adapter;
    ImageView image;
    AlertDialog dialogCustom;
    AlertDialog.Builder builderSingle,builderCustom;

    String bildiri_id = "109000220200110";
    String tarih = "10.01.2020";
    String formen_tablodan_gelen = "R0004--R0005--R0008";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip1_iscilikpuantaj);
        Get_Set veri = new Get_Set();
        SQLiteHelper database = new SQLiteHelper(IP1ISCILIKPUANTAJ.this);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate date = LocalDate.parse(tarih,formatter).minusDays(1);

        String check = date.format(formatter);
        init();
        //String.valueOf(veri.getKod()).substring(13,15)+"."+String.valueOf(veri.getKod()).substring(11,13)+"."+String.valueOf(veri.getKod()).substring(7,11);
        array = database.ReadİşçilikPuantaj(date.format(formatter),formen_tablodan_gelen);

        adapter=new İscilikPuantajAdapter(array,getApplicationContext(),date.format(formatter),bildiri_id);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));







        //array.add(new İsciPuantajItem("Armağan Civelek", (float) 12.5));
        //array.add(new İsciPuantajItem("Cihan Örenli", (float) 13.5));
        //array.add(new İsciPuantajItem("Kerem  Civelek", (float) 11.5));


           builderSingle= new AlertDialog.Builder(this);
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_expandable_list_item_1);
                arrayAdapter.add("Hardik");
                arrayAdapter.add("Hardik");
                arrayAdapter.add("Archit");
                arrayAdapter.add("Jignesh");


        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String strName = arrayAdapter.getItem(which);
                AlertDialog.Builder builderInner = new AlertDialog.Builder(IP1ISCILIKPUANTAJ.this);
                builderInner.setMessage(strName);
                builderInner.setTitle("Your Selected Item is");
                builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int which) {
                        dialog.dismiss();
                    }
                });
                builderInner.show();
            }
        });
        builderSingle.setView(R.layout.iscilik_puantaj_popup);
        builderSingle.create().getWindow().setGravity(Gravity.BOTTOM);

        ///////////////////////////////////////////////////////////////////////////////////
        builderCustom=new AlertDialog.Builder(this);
        View view= LayoutInflater.from(this).inflate(R.layout.iscilik_puantaj_popup,null);

        Button button = view.findViewById(R.id.imalat_sec);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(IP1ISCILIKPUANTAJ.this, "toast", Toast.LENGTH_SHORT).show();
            }
        });

        RecyclerView recyclerView1=view.findViewById(R.id.recycler);




        ArrayList<İsciPuantajItem> array1 = new ArrayList<>();
           İsciPuantajItem item=new İsciPuantajItem();
           item.setName("Emirhan Civelek");
           İsciPuantajItem item1=new İsciPuantajItem();
           item1.setName("Armağan Civelek");
           İsciPuantajItem item2=new İsciPuantajItem();
           item2.setName("Kerem Ören Bayraklı");
        İsciPuantajItem item3=new İsciPuantajItem();
        item3.setName("Kerem Ören Bayraklı");
        İsciPuantajItem item4=new İsciPuantajItem();
        item4.setName("Kerem Ören Bayraklı");
        İsciPuantajItem item5=new İsciPuantajItem();
        item5.setName("Kerem Ören Bayraklı");
           array1.add(item2);
            array1.add(item);
            array1.add(item1);
        array1.add(item3);
        array1.add(item4);
        array1.add(item5);








        recyclerView1.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView1.setLayoutManager(linearLayoutManager);

              İp1PopupAdapter adapter1=new İp1PopupAdapter(array1,this,tarih,"asd",bildiri_id);

             recyclerView1.setAdapter(adapter1);

              builderCustom.setView(view);
             dialogCustom=builderCustom.create();






      image.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
             // builderSingle.show();

          //dialogCustom.show();
            Intent intent = new Intent(IP1ISCILIKPUANTAJ.this,L1_main.class);
            startActivity(intent);


          }
      });


    }

    private void init() {
        image=findViewById(R.id.sent);
        array=new ArrayList<>();
        /*ImageView sync = findViewById(R.id.sync);
        sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IP1ISCILIKPUANTAJ.this,IP1ISCILIKPUANTAJ.class);
                startActivity(intent);
            }
        });*/
      recyclerView=findViewById(R.id.recycler);
    }
}
