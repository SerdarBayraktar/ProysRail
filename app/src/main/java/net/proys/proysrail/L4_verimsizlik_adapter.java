package net.proys.proysrail;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Proys Yazılım on 31.08.2019.
 */

public class L4_verimsizlik_adapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] etken;
    private final String[] radio;

    public L4_verimsizlik_adapter(Activity context, String[] etken,String[] radio) {
        super(context, R.layout.l4_verimsizlik_row, etken);
        this.context = context;
        this.etken = etken;
        this.radio = radio;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        final SQLiteHelper database = new SQLiteHelper(context);
        View rowView =inflater.inflate(R.layout.l4_verimsizlik_row, null,true);//layout hatalı olabilir
        TextView etken_isim = rowView.findViewById(R.id.etken);
        etken_isim.setText(String.valueOf(etken[position]));
        final RadioButton radioButton = rowView.findViewById(R.id.radioButton);
        if (radio[position].equals("0")){
            radioButton.setChecked(false);
        }else if(radio[position].equals("1")){
            radioButton.setChecked(true);
        }
        final Get_Set veri = new Get_Set();
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String etken_id = database.ReadEtkenListesiforid(database.ReadEtkenListesi()[0][position]);
                if (radioButton.isChecked()){
                    database.DeleteTaslakVerimsizlik(String.valueOf(veri.getKod()),database.ReadGet_Set("ImalatId"),etken_id);
                    radioButton.setChecked(false);
                }
                else if(!radioButton.isChecked()) {
                    database.WriteTaslakVerimsizlik(String.valueOf(veri.getKod()),"tarih",database.ReadGet_Set("ImalatId"),etken_id,database.ReadEtkenListesifordeger(etken_id));
                    radioButton.setChecked(true);
                }
            }
        });
        radioButton.setClickable(false);
        return rowView;

    }

}
