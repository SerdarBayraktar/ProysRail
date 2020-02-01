package net.proys.proysrail.Adapters;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.proys.proysrail.Items.PuantajMainItem;
import net.proys.proysrail.R;

import java.util.ArrayList;

public class PuantajMainAdapter  extends RecyclerView.Adapter<PuantajMainAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<PuantajMainItem> puantajMainItems;
     private  LayoutInflater inflater;


    public PuantajMainAdapter(Context mContext,ArrayList<PuantajMainItem> puantajMainItems) {

        inflater = LayoutInflater.from(mContext);
        this.puantajMainItems = puantajMainItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.puantaj_main_row,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
         PuantajMainItem puantajMainItem = puantajMainItems.get(position);
         holder.setData(puantajMainItem);



    }

    @Override
    public int getItemCount() {


        return puantajMainItems.size();
    }


    public class  ViewHolder  extends  RecyclerView.ViewHolder{

        public TextView detay_name,detay_rakam;


        public ViewHolder(View view) {
            super(view);

            detay_name= (TextView) view.findViewById(R.id.detay_name);
            detay_rakam=(TextView)view.findViewById(R.id.detay_rakam);

        }
        public void setData(PuantajMainItem puantajMainItem) {

            this.detay_name.setText(puantajMainItem.getDetay_name());
            this.detay_rakam.setText(puantajMainItem.getDatay_rakam());



        }
    }

}
