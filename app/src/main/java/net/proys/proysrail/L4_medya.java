package net.proys.proysrail;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class L4_medya extends AppCompatActivity {
    protected ImageView tick;
    Get_Set veri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l4_medya);
        veri = new Get_Set();
        init();
        setOnClickEvent();

    }
    private void init(){
        tick = findViewById(R.id.tick);
    }
    private void setOnClickEvent(){
        tick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L4_medya.this,L3_medya.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,L3_medya.class);
        startActivity(intent);
    }
}
