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
 * Created by Proys Yazılım on 20.09.2019.
 */

public class L4_isci_detay_adapter extends ArrayAdapter<String> {

    private final Activity context;
    private final List<String> etken;
    private final List<String> puantaj;


    public L4_isci_detay_adapter(Activity context, List<String> etken,List<String> puantaj) {
        super(context, R.layout.l4_isci_detay_row1, etken);
        // TODO Auto-generated constructor stub
        this.context=context;
        this.etken=etken;
        this.puantaj = puantaj;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.l4_isci_detay_row1, null,true);//layout hatalı olabilir
        TextView etken_textview = (TextView) rowView.findViewById(R.id.etken);
        EditText puantaj_textview = (EditText) rowView.findViewById(R.id.puantaj);
        etken_textview.setText(String.valueOf(etken.get(position)));
        puantaj_textview.setText(String.valueOf(puantaj.get(position)));
        return rowView;

        }
}
