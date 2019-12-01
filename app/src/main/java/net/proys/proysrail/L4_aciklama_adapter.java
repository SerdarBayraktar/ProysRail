package net.proys.proysrail;

import android.app.Activity;
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


    public L4_aciklama_adapter(Activity context, List<String> aciklamalar) {
        super(context, R.layout.l4_aciklama_row,aciklamalar);
        // TODO Auto-generated constructor stub
        this.context=context;
        this.aciklamalar=aciklamalar;

    }

    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.l4_aciklama_row, null,true);//layout hatalı olabilir
        EditText aciklama_edit = rowView.findViewById(R.id.aciklama_edit);
        aciklama_edit.setText(aciklamalar.get(position));
        return rowView;

    }
}
