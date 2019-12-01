package net.proys.proysrail;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class L2_aciklama extends AppCompatActivity {
    ImageView isci_icon,imalat_icon,makine_icon,sent;
    LinearLayout isci_linear,imalat_linear,makine_linear,malzeme_linear,aciklama_linear,medya_linear;


    protected EditText aciklama1,aciklama2;
    protected ImageView medya_icon,malzeme_icon;
    protected ImageView aciklama_icon;
    protected TextView aciklama_txt;
    Get_Set veri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l2_aciklama);
        veri = new Get_Set();
        init();
        setInheritance();
        setOnclickEvents();
        setWatchers();
        allset4icon();

    }
    protected void init(){
        sent = findViewById(R.id.sent);
        aciklama1 = findViewById(R.id.aciklama_1);
        aciklama2 = findViewById(R.id.aciklama_2);
        medya_icon = findViewById(R.id.imageCamera);
        aciklama2.setVisibility(View.INVISIBLE);
        aciklama_icon = findViewById(R.id.imageAciklama);
        aciklama_txt = findViewById(R.id.aciklama_txt);
        malzeme_icon = findViewById(R.id.imageMalzeme);
        isci_icon = findViewById(R.id.imageisci);
        imalat_icon = findViewById(R.id.imageImalat);
        makine_icon = findViewById(R.id.imageMakine);
        imalat_linear = findViewById(R.id.imalat_linear);
        isci_linear = findViewById(R.id.isgucu_linear);
        malzeme_linear = findViewById(R.id.malzeme_linear);
        makine_linear = findViewById(R.id.makine_linear);
        medya_linear = findViewById(R.id.medya_linear);
        aciklama_linear = findViewById(R.id.aciklama_linear);
    }
    protected void setOnclickEvents(){
        medya_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L2_aciklama.this,L2_medya.class);
                startActivity(intent);
            }
        });
        malzeme_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(L2_aciklama.this,"Henüz hazır değil.",Toast.LENGTH_SHORT).show();
            }
        });
        isci_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L2_aciklama.this,l2_is_gucu.class);
                startActivity(intent);
            }
        });
        imalat_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L2_aciklama.this,L2_bildiri.class);
                startActivity(intent);
            }
        });
        makine_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L2_aciklama.this,L2_makine.class);
                startActivity(intent);
            }
        });
        sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                L1_main l1_main = new L1_main();
                veri = new Get_Set();
                SQLiteHelper database = new SQLiteHelper(L2_aciklama.this);
                String tarih = l1_main.getMaintitle()[veri.getPosition()].subSequence(l1_main.getMaintitle()[veri.getPosition()].length()-11,l1_main.getMaintitle()[veri.getPosition()].length()-1).toString();
                String bildiri = database.ReadBildirilerwIsim(l1_main.getMaintitle()[veri.getPosition()].split(" ")[0]+" "+ l1_main.getMaintitle()[veri.getPosition()].split(" ")[1])[0];
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
                SimpleDateFormat sdf1 = new SimpleDateFormat("dd.MM.yyyy");
                try {

                    Long kod = Long.valueOf(database.ReadGet_Set("ImalatId")+String.valueOf(bildiri).substring(1)+sdf2.format(sdf1.parse(tarih)));
                    database.UpdateBildiriListesi(kod,"SENT",1);
                    Intent intent = new Intent(L2_aciklama.this,L1_main.class);
                    startActivity(intent);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    protected void setInheritance(){
        if (!veri.getAciklamalar().equals("")){
            aciklama1.setText(veri.getAciklamalar());
        }
    }
    protected void allset4icon(){
        aciklama_icon.setImageResource(R.drawable.l2_aciklama_o);
        aciklama_txt.setTextColor(getResources().getColor(R.color.text_color_yellow));
        Typeface typeface = getResources().getFont(R.font.opensans_semibold);
        aciklama_txt.setTypeface(typeface);
    }
    protected void setWatchers(){
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                veri.setAciklamalar(s.toString());
            }
        };
        aciklama1.addTextChangedListener(watcher);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,L1_main.class);
        startActivity(intent);
    }
}
