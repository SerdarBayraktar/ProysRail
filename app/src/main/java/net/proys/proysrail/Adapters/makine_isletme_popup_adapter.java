package net.proys.proysrail.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.proys.proysrail.Fragments.MakinePuantaj;
import net.proys.proysrail.Items.MakinePopupItem;
import net.proys.proysrail.Makine_Isletme_MainFrame;
import net.proys.proysrail.R;

import java.util.ArrayList;

public class makine_isletme_popup_adapter extends RecyclerView.Adapter<makine_isletme_popup_adapter.ViewHolder> {

    private Context mContext;
    private LayoutInflater inflater;
    private ArrayList<MakinePopupItem>  makinePopupItems;

    public makine_isletme_popup_adapter(Context mContext, ArrayList<MakinePopupItem> makinePopupItems) {
        this.mContext = mContext;
        this.makinePopupItems = makinePopupItems;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.popup_makine_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MakinePopupItem puantajMainItem = makinePopupItems.get(position);
        holder.setData(puantajMainItem);

    }

    @Override
    public int getItemCount() {
        return  makinePopupItems.size();
    }

    public class  ViewHolder extends RecyclerView.ViewHolder
    {
        TextView makine_name;


        public ViewHolder(View itemView) {
            super(itemView);
            makine_name=itemView.findViewById(R.id.makine_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent =new Intent(mContext,Makine_Isletme_MainFrame.class);
                    intent.putExtra("Fragment","PUANTAJ");
                    intent.putExtra("Position",getAdapterPosition());

                    mContext.startActivity(intent);

                }
            });


        }

        public void setData(MakinePopupItem popupItem) {

            this.makine_name.setText(popupItem.getMakine_name());

        }
    }
}
