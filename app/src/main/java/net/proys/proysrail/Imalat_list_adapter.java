package net.proys.proysrail;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by AVES on 16.06.2019.
 */

public class Imalat_list_adapter
     extends ArrayAdapter<String>

    {

        private final Activity context;
        private final String[] maintitle;
        private final String[] subtitle;
        private final Integer[] imgid;

    public Imalat_list_adapter(Activity context, String[] maintitle, String[] subtitle, Integer[] imgid) {
            super(context, R.layout.imalat_listview, maintitle);
            // TODO Auto-generated constructor stub

            this.context=context;
            this.maintitle=maintitle;
            this.subtitle=subtitle;
            this.imgid=imgid;

        }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.imalat_listview, null,true);//layout hatalı olabilir

        TextView titleText = (TextView) rowView.findViewById(R.id.title);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView subtitleText = (TextView) rowView.findViewById(R.id.subtitle);

        titleText.setText(maintitle[position]);
        imageView.setImageResource(imgid[position]);
        subtitleText.setText(subtitle[position]);

        return rowView;

    };
}





