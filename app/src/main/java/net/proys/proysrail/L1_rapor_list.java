package net.proys.proysrail;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Proys Yazılım on 26.06.2019.
 */

public class L1_rapor_list extends ArrayAdapter<String>
{

    private final Activity context;
    private final String[] maintitle;
    private final String[] subtitle;

    public L1_rapor_list(Activity context, String[] maintitle, String[] subtitle) {
        super(context, R.layout.l1_rapor_row, maintitle);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.maintitle=maintitle;
        this.subtitle=subtitle;

    }
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.l1_rapor_row, null,true);

        TextView titleText = (TextView) rowView.findViewById(R.id.ilerleme);
        TextView subtitleText = (TextView) rowView.findViewById(R.id.tarih);

        titleText.setText(maintitle[position]);
        subtitleText.setText(subtitle[position]);

        return rowView;

    };




}
