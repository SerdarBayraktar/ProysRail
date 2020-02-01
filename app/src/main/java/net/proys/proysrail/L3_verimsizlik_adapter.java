package net.proys.proysrail;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Proys Yazılım on 31.08.2019.
 */

public class L3_verimsizlik_adapter extends ArrayAdapter<String> {
    private final Activity context;
    private List list = new ArrayList();
    private final String[] etken;
    private final String[] deger;

    public L3_verimsizlik_adapter(Activity context, String[] etken, String[] deger) {
        super(context, R.layout.l3_verimsizlik_row, etken);
        this.context = context;
        this.etken = etken;
        this.deger = deger;
    }

    @Override
    public View getView(final int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView =inflater.inflate(R.layout.l3_verimsizlik_row, null,true);//layout hatalı olabilir
        TextView etken_isim = rowView.findViewById(R.id.etken);
        EditText deger_edit = rowView.findViewById(R.id.deger);
        etken_isim.setText(String.valueOf(etken[position]));
        deger_edit.setText(String.valueOf(deger[position]));
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.add("asd");
            }
        });

        return rowView;

    }
}
