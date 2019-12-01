package net.proys.proysrail;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Proys Yazılım on 18.10.2019.
 */

public class L4_isgucu_secilen_adapter extends ArrayAdapter<String> {

        private final Activity context;
        private final List<String> isim;

        public L4_isgucu_secilen_adapter(Activity context, List<String> isim) {
            super(context, R.layout.l4_isgucu_secilen_row, isim);
            // TODO Auto-generated constructor stub

            this.context=context;
            this.isim=isim;

        }

        public View getView(final int position, View view, ViewGroup parent) {
            LayoutInflater inflater=context.getLayoutInflater();
            View rowView=inflater.inflate(R.layout.l4_isgucu_secilen_row, null,true);//layout hatalı olabilir
            TextView titleText = (TextView) rowView.findViewById(R.id.title_text);
            titleText.setText(isim.get(position));
            return rowView;

        }
    }