package net.proys.proysrail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class L1_Notlar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l1__notlar);

        onBackPressed();
        Toast.makeText(L1_Notlar.this, "Henüz hazır değil.", Toast.LENGTH_SHORT).show();
    }
}
