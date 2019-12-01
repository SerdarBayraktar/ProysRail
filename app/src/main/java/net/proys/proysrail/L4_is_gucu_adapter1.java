package net.proys.proysrail;

import android.app.Activity;
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
 * Created by Proys Yazılım on 15.09.2019.
 */

public class L4_is_gucu_adapter1  extends ArrayAdapter<String>{
    private final Activity context;
    private final List<String> isim;

    public L4_is_gucu_adapter1(Activity context, List<String> isim) {
        super(context, R.layout.l4_is_gucu_row1, isim);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.isim=isim;

    }

    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.l4_is_gucu_row1, null,true);//layout hatalı olabilir
        TextView titleText = (TextView) rowView.findViewById(R.id.title_text);
        titleText.setText(isim.get(position));
        return rowView;

    }
}
