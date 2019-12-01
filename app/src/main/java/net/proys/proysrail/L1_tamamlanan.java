package net.proys.proysrail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class L1_tamamlanan extends AppCompatActivity {

    protected ImageView notlar_icon,bek_bild,dosyalar_icon,dahafazla_icon,tamamlanan_bildiriler_icon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l1_tamamlanan);
        all4menu();
        setIcons();
    }
    protected void all4menu(){
        notlar_icon = findViewById(R.id.imagenotlar);
        bek_bild = findViewById(R.id.imagebekbild);
        dosyalar_icon = findViewById(R.id.dosyalar);
        dahafazla_icon = findViewById(R.id.imagedahafazla);
        tamamlanan_bildiriler_icon = findViewById(R.id.imagetamamlananbild);
        dahafazla_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L1_tamamlanan.this,L1_dahafazla.class);
                startActivity(intent);
            }
        });
        bek_bild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L1_tamamlanan.this,L1_main.class);
                startActivity(intent);
            }
        });



    }
    protected void setIcons(){
        tamamlanan_bildiriler_icon.setImageResource(R.drawable.tamamlanan_bildiriler_o);

    }

    @Override
    public void onBackPressed() {
    }
}
