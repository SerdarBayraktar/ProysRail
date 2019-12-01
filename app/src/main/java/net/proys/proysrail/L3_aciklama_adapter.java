package net.proys.proysrail;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import java.util.List;

/**
 * Created by Proys Yazılım on 1.12.2019.
 */

public class L3_aciklama_adapter extends ArrayAdapter<String> {
    private final Activity context;
    private final List<String> aciklamalar;
    SQLiteHelper database;
    Get_Set veri;


    public L3_aciklama_adapter(Activity context, List<String> aciklamalar) {
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
        EditText aciklama_edit = rowView.findViewById(R.id.aciklama_edit);
        aciklama_edit.setText(aciklamalar.get(position));
        aciklama_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                database.UpdateAciklamal3(String.valueOf(veri.getKod()),database.ReadGet_Set("ImalatId"),s.toString(),database.ReadGet_Set("KopyaNo"),aciklamalar.get(position));
                aciklamalar.set(position,s.toString());
            }
        });
        return rowView;

    }
}
