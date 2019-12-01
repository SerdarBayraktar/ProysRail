package net.proys.proysrail;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Proys Yazılım on 15.08.2019.
 */

public class L2_bildiri_adapter extends ArrayAdapter<String> {
        private final Activity context;
        private final String[] imalat_array;
        private final String[] mesafe_array;
        private final String[] mesafe_birim_array;
        private final String[] km_bas_array;
        private final String[] km_son_array;
        private final String[] personel_sayisi_array;
        private final String[] personel_puantaj_array;
        private final String[] makine_sayisi_array;
        private final String[] makine_puantaj_array;
        private final String[] medya_array;
        private final String[] aciklama_array;
        private final String[] verim_array;
        private final String[] ort_verimsiz_sure_array;
        public L2_bildiri_adapter(Activity context, String[] imalat_array, String[] mesafe_array,String[] mesafe_birim_array,
                                  String[] km_bas_array, String[] km_son_array, String[] personel_sayisi_array, String[] personel_puantaj_array,
                                  String[] makine_sayisi_array, String[] makine_puantaj_array, String[] medya_array, String[] aciklama_array,
                                  String[] verim_array, String[] ort_verimsiz_sure_array) {
            super(context,R.layout.l2_bildiri_row,imalat_array);
            // TODO Auto-generated constructor stub

            this.context = context;
            this.imalat_array = imalat_array;
            this.mesafe_array=  mesafe_array;
            this.mesafe_birim_array = mesafe_birim_array;
            this.km_bas_array = km_bas_array;
            this.km_son_array = km_son_array;
            this.personel_sayisi_array = personel_sayisi_array;
            this.personel_puantaj_array = personel_puantaj_array;
            this.makine_sayisi_array = makine_sayisi_array;
            this.makine_puantaj_array = makine_puantaj_array;
            this.medya_array = medya_array;
            this.aciklama_array = aciklama_array;
            this.verim_array = verim_array;
            this.ort_verimsiz_sure_array = ort_verimsiz_sure_array;
        }
        public View getView(final int position, final View view, ViewGroup parent) {
            LayoutInflater inflater=context.getLayoutInflater();
            View rowView=inflater.inflate(R.layout.l2_bildiri_row, null,true);//layout hatalı olabilir

            TextView imalat_adi = rowView.findViewById(R.id.imalat_adi);
            TextView mesafe = rowView.findViewById(R.id.mesafe);//2 diziden çekecek
            TextView km_bas = rowView.findViewById(R.id.km_bas);
            TextView km_son = rowView.findViewById(R.id.km_son);
            TextView personel_sayisi = rowView.findViewById(R.id.personel_sayisi);
            TextView personel_puantaj = rowView.findViewById(R.id.personel_puantaj);
            TextView makine_sayisi = rowView.findViewById(R.id.makine_sayisi);
            TextView makine_puantaj = rowView.findViewById(R.id.makine_puantaj);
            TextView medya = rowView.findViewById(R.id.medya);
            TextView aciklama = rowView.findViewById(R.id.aciklama);
            TextView verimtxt = rowView.findViewById(R.id.verimtxt);
            TextView verim = rowView.findViewById(R.id.verim);
            TextView ort_verimsiz_sure = rowView.findViewById(R.id.ort_verimsiz_sure);

            imalat_adi.setText(String.valueOf(imalat_array[position]));
            mesafe.setText(String.valueOf(mesafe_array[position]+" "+ mesafe_birim_array[position]));
            System.out.println(km_bas_array[position]);
            km_bas.setText(String.valueOf(km_bas_array[position].substring(0,3)+"+"+km_bas_array[position].substring(3,6)));
            km_son.setText(String.valueOf(km_son_array[position].substring(0,3)+"+"+km_son_array[position].substring(3,6)));
            personel_sayisi.setText(String.valueOf(personel_sayisi_array[position]+" kişi"));
            personel_puantaj.setText(String.valueOf(personel_puantaj_array[position]+" saat"));
            makine_sayisi.setText(String.valueOf(makine_sayisi_array[position]+ " adet"));
            makine_puantaj.setText(String.valueOf(makine_puantaj_array[position]+" saat"));
            medya.setText(String.valueOf(medya_array[position]));
            aciklama.setText(String.valueOf(aciklama_array[position]));
            verim.setText(String.valueOf("%"+verim_array[position]));//verimtxt colorchange
            ort_verimsiz_sure.setText(String.valueOf("Kaynak başına ortalama\n" +
                    "verimsiz süre: "+ort_verimsiz_sure_array[position]+ " Saat"));

            return rowView;

        };
}
