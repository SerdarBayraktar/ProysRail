package net.proys.proysrail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class L1_tamamlanan extends AppCompatActivity {

    protected ImageView notlar_icon,bek_bild,dosyalar_icon,dahafazla_icon,tamamlanan_bildiriler_icon;
    protected TextView tamtxt;
    protected LinearLayout notlar_lin,bek_lin,dosyalar_lin,dahafazla_lin,tamamlanan_lin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l1_tamamlanan);
        init();
        all4menu();
        setIcons();
    }
    protected void init(){
        tamtxt = findViewById(R.id.tamtxt);
        notlar_lin = findViewById(R.id.notlar_lin);
        bek_lin = findViewById(R.id.bek_lin);
        dosyalar_lin = findViewById(R.id.dosyalar_lin);
        dahafazla_lin = findViewById(R.id.dahafazlalin);
        tamamlanan_lin = findViewById(R.id.tamamlanan_lin);

    }
    protected void all4menu(){
        notlar_icon = findViewById(R.id.imagenotlar);
        bek_bild = findViewById(R.id.imagebekbild);
        dosyalar_icon = findViewById(R.id.dosyalar);
        dahafazla_icon = findViewById(R.id.imagedahafazla);
        tamamlanan_bildiriler_icon = findViewById(R.id.imagetamamlananbild);
        dahafazla_lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L1_tamamlanan.this,L1_dahafazla.class);
                startActivity(intent);
            }
        });
        bek_lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L1_tamamlanan.this,L1_main.class);
                startActivity(intent);
            }
        });
        notlar_lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L1_tamamlanan.this,L1_Notlar.class);
                startActivity(intent);
            }
        });
        dosyalar_lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L1_tamamlanan.this,L1_Dosyalar.class);
                startActivity(intent);
            }
        });



    }
    protected void setIcons(){
        tamamlanan_bildiriler_icon.setImageResource(R.drawable.tamamlanan_bildiriler_o);
        tamtxt.setTextColor(getResources().getColor(R.color.text_color_yellow));

    }

    @Override
    public void onBackPressed() {
    }
}
