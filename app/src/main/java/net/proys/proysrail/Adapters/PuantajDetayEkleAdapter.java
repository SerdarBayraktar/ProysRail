package net.proys.proysrail.Adapters;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import net.proys.proysrail.Items.PuantajDetayItem;
import net.proys.proysrail.R;

import java.util.ArrayList;

public class PuantajDetayEkleAdapter  extends RecyclerView.Adapter<PuantajDetayEkleAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<PuantajDetayItem> puantajDetayItems;
    private LayoutInflater inflater;

    public PuantajDetayEkleAdapter(Context mContext, ArrayList<PuantajDetayItem> puantajDetayItems) {
        this.mContext = mContext;
        this.puantajDetayItems = puantajDetayItems;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.puantaj_detay_ekle_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        PuantajDetayItem item=puantajDetayItems.get(position);
        holder.setData(item);

        final boolean isToogledRadio=false;


     holder.detay_radio.setOnClickListener(new View.OnClickListener() {
    @Override
      public void onClick(View view) {

         if(!puantajDetayItems.get(position).getChecked()==false)
         {
             holder.detay_radio.setChecked(false);
             puantajDetayItems.get(position).setChecked(false);
         }
         else
         {
             puantajDetayItems.get(position).setChecked(true);
         }







    }
});


    }

    @Override
    public int getItemCount() {
        return  puantajDetayItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView detay_name;
        RadioButton detay_radio;



        public ViewHolder(View view) {
            super(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(!puantajDetayItems.get(getAdapterPosition()).getChecked()==false)
                    {
                        detay_radio.setChecked(false);
                        puantajDetayItems.get(getAdapterPosition()).setChecked(false);
                    }
                    else
                    {
                        detay_radio.setChecked(true);
                        puantajDetayItems.get(getAdapterPosition()).setChecked(true);
                    }


                }
            });
            detay_name=view.findViewById(R.id.detay_name);
            detay_radio=view.findViewById(R.id.detay_radio);
        }

        public void setData(PuantajDetayItem item) {
            this.detay_name.setText(item.getDetay_name());
        }

    }
}
