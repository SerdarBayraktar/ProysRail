package net.proys.proysrail;

import android.app.Activity;
import android.content.Context;
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

public class L4_aciklama_adapter extends ArrayAdapter<String> {
    private final Activity context;
    private final List<String> aciklamalar;
    SQLiteHelper database;
    Get_Set veri;


    public L4_aciklama_adapter(Activity context, List<String> aciklamalar) {
        super(context, R.layout.l4_aciklama_row,aciklamalar);
        // TODO Auto-generated constructor stub
        this.context=context;
        this.aciklamalar=aciklamalar;
        database = new SQLiteHelper(context);
        veri = new Get_Set();
    }

    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.l4_aciklama_row, null,true);//layout hatalı olabilir
        final TextView aciklama_txt = rowView.findViewById(R.id.aciklama_txt);
        /*if (aciklamalar.get(position).equals("")){
            aciklama_txt.setText("Lütfen açıklama giriniz.");
        }else {
            aciklama_txt.setText(aciklamalar.get(position));
        }*/
        aciklama_txt.setText(aciklamalar.get(position));
        return rowView;
    }
}
