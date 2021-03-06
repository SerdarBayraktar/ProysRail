package net.proys.proysrail;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Proys Yazılım on 1.12.2019.
 */

public class L3_aciklama_adapter extends ArrayAdapter<String> {
    private final Activity context;
    private final List<String> aciklamalar;
    private final List<Integer> aciklama_idler;
    SQLiteHelper database;
    Get_Set veri;
    List[] lists;



    public L3_aciklama_adapter(Activity context, List<String> aciklamalar, List<Integer> aciklama_idler) {
        super(context, R.layout.l4_aciklama_row,aciklamalar);
        // TODO Auto-generated constructor stub
        this.context=context;
        this.aciklamalar=aciklamalar;
        this.aciklama_idler = aciklama_idler;
        database = new SQLiteHelper(context);
        veri = new Get_Set();
        lists  = database.ReadAciklamal4(String.valueOf(veri.getKod()),database.ReadGet_Set("ImalatId"));
        aciklama_idler= lists[1];
    }

    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.l4_aciklama_row, null,true);//layout hatalı olabilir
        TextView aciklama_edit = rowView.findViewById(R.id.aciklama_txt);
        aciklama_edit.setText(aciklamalar.get(position));
        aciklama_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,L4_aciklama_detay.class);
                intent.putExtra("tip","L3");
                intent.putExtra("id",String.valueOf(aciklama_idler.get(position)));
                intent.putExtra("text",aciklamalar.get(position));
                context.startActivity(intent);
            }
        });

        return rowView;

    }
}
