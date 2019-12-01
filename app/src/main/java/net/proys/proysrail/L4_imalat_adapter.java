package net.proys.proysrail;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Proys Yazılım on 28.06.2019.
 */

public class L4_imalat_adapter extends ArrayAdapter<String> {


    private final Activity context;
    private final String[] maintitle;
    private final Integer[] imgid;

    public L4_imalat_adapter(Activity context, String[] maintitle, Integer[] imgid) {
        super(context, R.layout.l4_imalat_row, maintitle);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.maintitle=maintitle;
        this.imgid=imgid;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.l4_imalat_row, null,true);//layout hatalı olabilir

        TextView titleText = (TextView) rowView.findViewById(R.id.textView18);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imageView18);

        titleText.setText(maintitle[position]);
        //imageView.setImageResource(imgid[position]);
        return rowView;

    };
}
