package net.proys.proysrail.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import net.proys.proysrail.Items.FuelMainItem;
import net.proys.proysrail.Items.PuantajMainItem;
import net.proys.proysrail.R;

import java.util.ArrayList;

public class FuelMainAdapter extends RecyclerView.Adapter<FuelMainAdapter.ViewHolder> {
    ViewGroup viewGroup;
  EditText  popup_miktar;
    private Context mContext;
    private ArrayList<FuelMainItem> fuelMainItems;
    private LayoutInflater inflater;

    public FuelMainAdapter(Context mContext, ArrayList<FuelMainItem> fuelMainItems) {
        this.mContext = mContext;
        this.fuelMainItems = fuelMainItems;

        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.makine_fuel_row, parent, false);
         viewGroup= (ViewGroup) inflater.inflate(R.layout.popup_fuel_edit,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FuelMainItem fuelMainItem = fuelMainItems.get(position);
        if (position == (getItemCount() - 1))
            holder.setData(fuelMainItem, true);
        holder.setData(fuelMainItem);
        holder.fuel_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }


    @Override
    public int getItemCount() {
        return fuelMainItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        public TextView fuel_date, fuel_quantity;


        public ViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
            fuel_date = (TextView) view.findViewById(R.id.yakıt_tarih);
            fuel_quantity = (TextView) view.findViewById(R.id.yakıt_miktar);
        }


        public void setData(FuelMainItem fuelMainItem) {
            this.fuel_date.setText(fuelMainItem.getFuel_date());
            this.fuel_quantity.setText(fuelMainItem.getFuel_miktar());


        }

        public void setData(FuelMainItem fuelMainItem, Boolean last) {

            this.fuel_date.setTextColor(Color.parseColor("#444444"));
            this.fuel_quantity.setTextColor(Color.parseColor("#444444"));
            this.fuel_date.setText(fuelMainItem.getFuel_date());
            this.fuel_quantity.setText(fuelMainItem.getFuel_miktar());


        }


        @Override
        public void onClick(View view) {
            Toast.makeText(mContext, "Düzenlemek için uzun basınız", Toast.LENGTH_SHORT).show();
        }

        @Override
        public boolean onLongClick(View view) {

            showEditDialog(getAdapterPosition());




            return true;

        }

        private void showEditDialog(final int position) {

            View view=inflater.inflate(R.layout.popup_fuel_edit,viewGroup,false);
            popup_miktar=view.findViewById(R.id.fuel_miktar);
            popup_miktar.setText(fuelMainItems.get(position).getFuel_miktar().toString());
            AlertDialog.Builder builder=new AlertDialog.Builder(mContext);
            builder.setView(view);


            builder.setTitle("Yakıt Düzenleme");
            builder.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.setPositiveButton("Kaydet", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                  fuel_quantity.setText(popup_miktar.getText().toString());




                }
            });
            AlertDialog alertDialog = builder.create();

            alertDialog.show();

           }

    }

}
