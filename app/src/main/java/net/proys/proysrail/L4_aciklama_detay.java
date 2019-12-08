package net.proys.proysrail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class L4_aciklama_detay extends AppCompatActivity {
    protected SQLiteHelper database;
    protected Get_Set veri;
    protected TextView imalat_txt;
    protected EditText aciklama_edit;
    protected ImageView tick;
    protected Intent getintent;
    protected String aciklama_str;
    protected String aciklama_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l4_aciklama_detay);
        veri = new Get_Set();
        database = new SQLiteHelper(L4_aciklama_detay.this);
        getdatawintent();
        init();
        setOnclickEvents();

    }
    protected void init(){
        imalat_txt = findViewById(R.id.textView2);
        aciklama_edit =findViewById(R.id.aciklama_edit);
        tick = findViewById(R.id.tick);

        aciklama_edit.setText(aciklama_str);


    }
    protected void getdatawintent(){
        getintent = getIntent();
        aciklama_str = getintent.getStringExtra("text");
        aciklama_id = getintent.getStringExtra("id");
        System.out.println("asd");
    }
    protected void setOnclickEvents(){
        tick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L4_aciklama_detay.this,L3_aciklama.class);
                database.UpdateAciklamal4(aciklama_id,aciklama_edit.getText().toString());
                startActivity(intent);
            }
        });

    }
}
