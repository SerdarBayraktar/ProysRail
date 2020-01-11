package net.proys.proysrail.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import net.proys.proysrail.Get_Set;
import net.proys.proysrail.IP1ISCILIKPUANTAJ;
import net.proys.proysrail.Items.İsciPuantajItem;
import net.proys.proysrail.R;
import net.proys.proysrail.SQLiteHelper;

import java.util.ArrayList;

public class İp1PopupAdapter  extends RecyclerView.Adapter<İp1PopupAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<İsciPuantajItem> puantajMainItems;
    private Context mContext;
    String tarih;
    String isci;
    String bildiri_id;
    public İp1PopupAdapter(ArrayList<İsciPuantajItem> puantajMainItems, Context mContext,String tarih,String isci, String bildiri_id) {
        this.puantajMainItems = puantajMainItems;
        this.mContext = mContext;
        this.tarih = tarih;
        this.isci = isci;
        this.bildiri_id = bildiri_id;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ip1_puantaj_popup_row,parent,false);

        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        İsciPuantajItem  item=puantajMainItems.get(position);
        holder.setData(item);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteHelper database = new SQLiteHelper(mContext);
                Get_Set veri = new Get_Set();
                database.WriteTaslakResource(Long.valueOf(bildiri_id),tarih,"",database.ReadPersonelwisim(veri.getIsci()),"iscilik","verimsiz",999,1,"");
                Intent intent = new Intent(mContext,IP1ISCILIKPUANTAJ.class);
                mContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return  puantajMainItems.size();
    }

    public class  ViewHolder  extends  RecyclerView.ViewHolder{

        public TextView isci_adi;


        public ViewHolder(View view) {
            super(view);
             isci_adi=view.findViewById(R.id.mazaret_name);


        }

        public void setData(İsciPuantajItem item) {
            isci_adi.setText(item.getName());

        }
    }
}
