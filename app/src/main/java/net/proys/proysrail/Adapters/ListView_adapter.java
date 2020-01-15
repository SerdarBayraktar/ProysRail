package net.proys.proysrail.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import net.proys.proysrail.Get_Set;
import net.proys.proysrail.Iscilik_Puantaj_Imalat;
import net.proys.proysrail.Items.İsciPuantajItem;
import net.proys.proysrail.R;

import java.util.ArrayList;

public class ListView_adapter extends ArrayAdapter<String> {

    private LayoutInflater inflater;
    private ArrayList<İsciPuantajItem> puantajMainItems;
    private Activity mContext;
    AlertDialog dialogCustom;
    private String isci;
    RecyclerView recyclerView1;
    String tarih;
    String bildiri_id;
    String[] deneme;
    public ListView_adapter(Activity mContext, String tarih,String bildiri_id,ArrayList<İsciPuantajItem> puantajMainItems,String[] deneme) {
        super(mContext, R.layout.l2_is_gucu_row,deneme);
        // TODO Auto-generated constructor stub
        this.deneme = deneme;
        this.puantajMainItems = puantajMainItems;
        this.mContext = mContext;
        this.tarih = tarih;
        this.bildiri_id = bildiri_id;

    }

    public View getView(final int position, final View view, ViewGroup parent) {
        LayoutInflater inflater=mContext.getLayoutInflater();
        final View rowView=inflater.inflate(R.layout.iscilik_puantaj_row, null,true);//layout hatalı olabilir
        final Get_Set veri = new Get_Set();

        final TextView isci_adi = rowView.findViewById(R.id.isci_adi);
        //veri.setIsci(isci_adi.getText().toString());
        TextView calisma_saati = rowView.findViewById(R.id.calisma_saati);
        final Button button1 = rowView.findViewById(R.id.isim);

        final İsciPuantajItem  item=puantajMainItems.get(position);
        isci_adi.setText(item.getName());
        calisma_saati.setText(item.getSaat().toString());

            ////pop up
        AlertDialog.Builder builderSingle= new AlertDialog.Builder(mContext);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_expandable_list_item_1);
        arrayAdapter.add("Hardik");
        arrayAdapter.add("Hardik");
        arrayAdapter.add("Archit");
        arrayAdapter.add("Jignesh");

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Get_Set veri = new Get_Set();
                TextView isci_txt = rowView.findViewById(R.id.isci_adi);
                veri.setIsci(isci_txt.getText().toString());
            }
        });

        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String strName = arrayAdapter.getItem(which);
                AlertDialog.Builder builderInner = new AlertDialog.Builder(mContext);
                //Toast.makeText(parent.getContext(), "asdasdsadadas", Toast.LENGTH_SHORT).show();
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


        final AlertDialog.Builder builderCustom=new AlertDialog.Builder(mContext);
        View view1= LayoutInflater.from(mContext).inflate(R.layout.iscilik_puantaj_popup,null);
        recyclerView1 = view1.findViewById(R.id.recycler);

        ArrayList<İsciPuantajItem> array1 = new ArrayList<>();
        İsciPuantajItem item6=new İsciPuantajItem();
        item6.setName("İşe Gelmedi");
        İsciPuantajItem item2=new İsciPuantajItem();
        item2.setName("Yıllık İzin");
        İsciPuantajItem item3=new İsciPuantajItem();
        item3.setName("Raporlu");
        İsciPuantajItem item4=new İsciPuantajItem();
        item4.setName("Mazeret İzni");
        İsciPuantajItem item5=new İsciPuantajItem();
        item5.setName("Gurbetçi İzni");

        array1.add(item6);
        array1.add(item2);
        array1.add(item3);
        array1.add(item5);
        array1.add(item4);

        recyclerView1.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView1.setLayoutManager(linearLayoutManager);

        İp1PopupAdapter adapter1=new İp1PopupAdapter(array1,mContext,tarih,isci,bildiri_id);
        Button button = view1.findViewById(R.id.imalat_sec);
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

        //Pop p son
        if (item.getSaat().toString().equals("0.0")){
            rowView.setBackgroundColor(mContext.getResources().getColor(R.color.verimsizlik_bg));
            isci_adi.setTextColor(mContext.getResources().getColor(R.color.white));
            //veri.setIsci(isci_adi.getText().toString());
            calisma_saati.setTextColor(mContext.getResources().getColor(R.color.white));
            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogCustom.show();
                    veri.setIsci(puantajMainItems.get(position).getName());
                    Toast.makeText(mContext, "position"+position, Toast.LENGTH_SHORT).show();

                }

            });
        }else if (item.getSaat().toString().equals("999.0")){
            rowView.setBackgroundColor(mContext.getResources().getColor(R.color.iscilik_puantaj_mazeret));
            isci_adi.setTextColor(mContext.getResources().getColor(R.color.white));
            calisma_saati.setTextColor(mContext.getResources().getColor(R.color.white));
            calisma_saati.setText("İzin");
            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogCustom.show();

                    veri.setIsci(puantajMainItems.get(position).getName());
                    Toast.makeText(mContext, "position"+position, Toast.LENGTH_SHORT).show();
                }
            });
        }else if (item.getKategori().equals("iscilik_puantaj")){
            rowView.setBackgroundColor(mContext.getResources().getColor(R.color.iscilik_yesil));
            isci_adi.setTextColor(mContext.getResources().getColor(R.color.white));
            calisma_saati.setTextColor(mContext.getResources().getColor(R.color.white));

        }else {
            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    veri.setIsci(puantajMainItems.get(position).getName());
                    dialogCustom.show();
                    Toast.makeText(mContext, "position"+position, Toast.LENGTH_SHORT).show();

                    Get_Set veri = new Get_Set();
                    String isci = veri.getIsci();
                    button1.setText(veri.getIsci());
                }
            });
        }





        return rowView;

    }

}
