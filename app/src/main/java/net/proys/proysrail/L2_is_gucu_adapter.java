package net.proys.proysrail;

import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Proys Yazılım on 27.07.2019.
 */

public class L2_is_gucu_adapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] ekip_ismi;
    private final String[] calisilan_saat;
    private final Integer[] kayip_saat;
    Get_Set veri;
    public L2_is_gucu_adapter(Activity context, String[] ekip_ismi, String[] calisilan_saat,Integer[] kayip_saat) {
        super(context, R.layout.l2_is_gucu_row, ekip_ismi);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.ekip_ismi=ekip_ismi;
        this.calisilan_saat=calisilan_saat;
        this.kayip_saat = kayip_saat;

    }

    public View getView(final int position, final View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.l2_is_gucu_row, null,true);//layout hatalı olabilir
        veri = new Get_Set();
        TextView titleText = (TextView) rowView.findViewById(R.id.isciadi);
        TextView calisilan_saat_txt = (TextView) rowView.findViewById(R.id.calisilan_saat);
        TextView kayip_saat_txt = rowView.findViewById(R.id.kayip_saat);

        titleText.setText(ekip_ismi[position]);
        calisilan_saat_txt.setText(String.valueOf(calisilan_saat[position]));


        kayip_saat_txt.setText(String.valueOf(kayip_saat[position]));

        return rowView;

    };
}
