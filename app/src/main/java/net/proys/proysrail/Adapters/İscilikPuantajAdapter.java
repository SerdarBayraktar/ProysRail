package net.proys.proysrail.Adapters;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.proys.proysrail.Items.İsciPuantajItem;
import net.proys.proysrail.R;

import java.util.ArrayList;

public class İscilikPuantajAdapter  extends RecyclerView.Adapter<İscilikPuantajAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<İsciPuantajItem> puantajMainItems;
    private Context mContext;

    public İscilikPuantajAdapter(ArrayList<İsciPuantajItem> puantajMainItems, Context mContext) {
        this.puantajMainItems = puantajMainItems;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.iscilik_puantaj_row,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        İsciPuantajItem  item=puantajMainItems.get(position);
        holder.setData(item);

    }

    @Override
    public int getItemCount() {
        return puantajMainItems.size();
    }

    public class  ViewHolder  extends  RecyclerView.ViewHolder{

        public TextView isci_adi,calisma_saati;


        public ViewHolder(View view) {
            super(view);

            isci_adi= (TextView) view.findViewById(R.id.isci_adi);
            calisma_saati=(TextView)view.findViewById(R.id.calisma_saati);

        }

        public void setData(İsciPuantajItem item) {
            this.isci_adi.setText(item.getName());
            this.calisma_saati.setText(item.getSaat().toString());
        }
    }
}
