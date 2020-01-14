package net.proys.proysrail.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.IpPrefix;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import net.proys.proysrail.Get_Set;
import net.proys.proysrail.IP1ISCILIKPUANTAJ;
import net.proys.proysrail.Iscilik_Puantaj_Imalat;
import net.proys.proysrail.Items.İsciPuantajItem;
import net.proys.proysrail.R;
import net.proys.proysrail.SQLiteHelper;

import java.util.ArrayList;

public class İscilikPuantajAdapter  extends RecyclerView.Adapter<İscilikPuantajAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<İsciPuantajItem> puantajMainItems;
    private Context mContext;
    AlertDialog dialogCustom;
    private String isci;
    RecyclerView recyclerView1;
    String tarih;
    String bildiri_id;

    public İscilikPuantajAdapter(ArrayList<İsciPuantajItem> puantajMainItems, Context mContext,String tarih,String bildiri_id) {
        this.puantajMainItems = puantajMainItems;
        this.mContext = mContext;
        this.tarih = tarih;
        this.bildiri_id = bildiri_id;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.iscilik_puantaj_row,parent,false);

        AlertDialog.Builder builderSingle= new AlertDialog.Builder(mContext);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_expandable_list_item_1);
        arrayAdapter.add("Hardik");
        arrayAdapter.add("Hardik");
        arrayAdapter.add("Archit");
        arrayAdapter.add("Jignesh");

        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Get_Set veri = new Get_Set();
                TextView isci_txt = view1.findViewById(R.id.isci_adi);
                veri.setIsci(isci_txt.getText().toString());
            }
        });

        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String strName = arrayAdapter.getItem(which);
                AlertDialog.Builder builderInner = new AlertDialog.Builder(mContext);
                Toast.makeText(parent.getContext(), "asdasdsadadas", Toast.LENGTH_SHORT).show();
                builderInner.setMessage(strName);
                builderInner.setTitle("Your Selected Item is");
                builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int which) {
                        dialog.dismiss();
                    }
                });
                builderInner.show();
            }
        });
        // builderSingle.setView(R.layout.iscilik_puantaj_popup);
        // builderSingle.create().getWindow().setGravity(Gravity.BOTTOM);

        ///////////////////////////////////////////////////////////////////////////////////
        AlertDialog.Builder builderCustom=new AlertDialog.Builder(parent.getContext());
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.iscilik_puantaj_popup,null);
        recyclerView1 = view.findViewById(R.id.recycler);

        ArrayList<İsciPuantajItem> array1 = new ArrayList<>();
        İsciPuantajItem item=new İsciPuantajItem();
        item.setName("İşe Gelmedi");
        İsciPuantajItem item2=new İsciPuantajItem();
        item2.setName("Yıllık İzin");
        İsciPuantajItem item3=new İsciPuantajItem();
        item3.setName("Raporlu");
        İsciPuantajItem item4=new İsciPuantajItem();
        item4.setName("Mazeret İzni");
        İsciPuantajItem item5=new İsciPuantajItem();
        item5.setName("Gurbetçi İzni");

        array1.add(item);
        array1.add(item2);
        array1.add(item3);
        array1.add(item5);
        array1.add(item4);

        recyclerView1.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(parent.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView1.setLayoutManager(linearLayoutManager);

        İp1PopupAdapter adapter1=new İp1PopupAdapter(array1,parent.getContext(),tarih,isci,bildiri_id);
        Button button = view.findViewById(R.id.imalat_sec);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Iscilik_Puantaj_Imalat.class);
                mContext.startActivity(intent);
                //Toast.makeText(mContext, "toast", Toast.LENGTH_SHORT).show();
            }
        });


        recyclerView1.setAdapter(adapter1);

        builderCustom.setView(view);
        dialogCustom=builderCustom.create();
        return new ViewHolder(view1);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final İsciPuantajItem  item=puantajMainItems.get(position);
        final int positionlocal=position;
        holder.setData(item);
        if (item.getSaat().toString().equals("0.0")){
            holder.itemView.setBackgroundColor(mContext.getResources().getColor(R.color.verimsizlik_bg));
            final TextView isci_adi = holder.itemView.findViewById(R.id.isci_adi);
            isci_adi.setTextColor(mContext.getResources().getColor(R.color.white));
            final Get_Set veri = new Get_Set();
            //veri.setIsci(isci_adi.getText().toString());
            TextView calisma_saati = holder.itemView.findViewById(R.id.calisma_saati);
            calisma_saati.setTextColor(mContext.getResources().getColor(R.color.white));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogCustom.show();
                    veri.setIsci(puantajMainItems.get(positionlocal).getName());
                    Toast.makeText(mContext, "position"+positionlocal, Toast.LENGTH_SHORT).show();

                }

            });
        }else if (item.getSaat().toString().equals("999.0")){
            holder.itemView.setBackgroundColor(mContext.getResources().getColor(R.color.iscilik_puantaj_mazeret));
            final TextView isci_adi = holder.itemView.findViewById(R.id.isci_adi);
            isci_adi.setTextColor(mContext.getResources().getColor(R.color.white));
            TextView calisma_saati = holder.itemView.findViewById(R.id.calisma_saati);
            calisma_saati.setTextColor(mContext.getResources().getColor(R.color.white));
            calisma_saati.setText("İzin");
            final Get_Set veri = new Get_Set();
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogCustom.show();
                    veri.setIsci(puantajMainItems.get(positionlocal).getName());
                    Toast.makeText(mContext, "position"+positionlocal, Toast.LENGTH_SHORT).show();
                }
            });
        }else if (item.getKategori().equals("iscilik_puantaj")){
            holder.itemView.setBackgroundColor(mContext.getResources().getColor(R.color.iscilik_yesil));
            final TextView isci_adi = holder.itemView.findViewById(R.id.isci_adi);
            final Get_Set veri = new Get_Set();
            isci_adi.setTextColor(mContext.getResources().getColor(R.color.white));
            TextView calisma_saati = holder.itemView.findViewById(R.id.calisma_saati);
            calisma_saati.setTextColor(mContext.getResources().getColor(R.color.white));

        }else {
            final TextView isci_adi = holder.itemView.findViewById(R.id.isci_adi);
            final Get_Set veri = new Get_Set();
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    veri.setIsci(puantajMainItems.get(positionlocal).getName());
                    Toast.makeText(mContext, "position"+positionlocal, Toast.LENGTH_SHORT).show();
                    Button button1 = holder.itemView.findViewById(R.id.isim);
                    Get_Set veri = new Get_Set();
                    String isci = veri.getIsci();
                    button1.setText(veri.getIsci());
                }
            });
        }
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
