package net.proys.proysrail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class L1_dahafazla extends AppCompatActivity {
    protected TextView username;
    Get_Set veri;
    protected ImageView notlar_icon,t_bild_icon,dosyalar_icon,bek_bild_icon,dahafazla_icon;
    protected TextView dahatxt;
    protected LinearLayout notlar_lin,bek_lin,dosyalar_lin,dahafazla_lin,tamamlanan_lin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l1_dahafazla);
        init();
        init_inheritance();
        all4menu();
        setIcons();
    }
    protected void init(){
        dahatxt = findViewById(R.id.dahatxt);

        notlar_lin = findViewById(R.id.notlar_lin);
        bek_lin = findViewById(R.id.bek_lin);
        dosyalar_lin = findViewById(R.id.dosyalar_lin);
        tamamlanan_lin = findViewById(R.id.tamamlanan_lin);
    }
    protected void init_inheritance(){
        username = findViewById(R.id.user);
        username.setText(veri.getKullaniciAdi());
    }
    protected void all4menu(){
        notlar_icon = findViewById(R.id.imagenotlar);
        t_bild_icon = findViewById(R.id.imagetamamlananbild);
        dosyalar_icon = findViewById(R.id.dosyalar);
        bek_bild_icon = findViewById(R.id.imagebekbild);
        dahafazla_icon = findViewById(R.id.imagedahafazla);
        bek_lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L1_dahafazla.this,L1_main.class);
                startActivity(intent);
            }
        });
        tamamlanan_lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L1_dahafazla.this,L1_tamamlanan.class);
                startActivity(intent);
            }
        });
        notlar_lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L1_dahafazla.this,L1_Notlar.class);
                startActivity(intent);
            }
        });
        dosyalar_lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L1_dahafazla.this,L1_Dosyalar.class);
                startActivity(intent);
            }
        });



    }
    protected void setIcons(){
        dahafazla_icon.setImageResource(R.drawable.daha_fazla_o);
        dahatxt.setTextColor(getResources().getColor(R.color.text_color_yellow));

    }

    @Override
    public void onBackPressed() {
    }
}
