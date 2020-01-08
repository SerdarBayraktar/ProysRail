package net.proys.proysrail.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.Toast;


import net.proys.proysrail.Adapters.PuantajMainAdapter;
import net.proys.proysrail.Items.PuantajMainItem;
import net.proys.proysrail.Puantaj_Detay_Ekle;
import net.proys.proysrail.R;

import java.util.ArrayList;


public class MakinePuantaj extends Fragment {
    private RecyclerView recyclerView;
    private PuantajMainAdapter adapter;
    private Button button;
    View view;
    ArrayList<PuantajMainItem> array;



    public MakinePuantaj() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    array=new ArrayList<>();
        array.add(new PuantajMainItem("Makine Başlangıç","4169"));
        array.add(new PuantajMainItem("Makine Başlangıç","4169"));
        array.add(new PuantajMainItem("Makine Başlangıç","4169"));
        array.add(new PuantajMainItem("Makine Başlangıç","4169"));
        array.add(new PuantajMainItem("İş verenin işi  durdurması","1"));
        array.add(new PuantajMainItem("Yer Teslim Problemleri","1"));
       array.add(new PuantajMainItem("Yer Teslim Problemleri","0"));
        array.add(new PuantajMainItem("Yer Teslim Problemleri","1"));
        array.add(new PuantajMainItem("Yer Teslim Problemleri","1"));
        array.add(new PuantajMainItem("Yer Teslim Problemleri","0"));
        array.add(new PuantajMainItem("Yer Teslim Problemleri","0"));
        array.add(new PuantajMainItem("Yer Teslim Problemleri","1"));
        array.add(new PuantajMainItem("Yer Teslim Problemleri","1"));
        array.add(new PuantajMainItem("Yer Teslim Problemleri","0"));

        view= inflater.inflate(R.layout.fragment_makine_puantaj, container, false);
        init();
        eventHandler();

        recyclerView.setAdapter(adapter);
        return view;
















    }

    private void eventHandler() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent =new Intent(getActivity(), Puantaj_Detay_Ekle.class);
               startActivity(intent);
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 )
                {
                    getActivity().findViewById(R.id.choose_machine).setVisibility(View.GONE);
                }
                else if(dy<0)
                {
                    getActivity().findViewById(R.id.choose_machine).setVisibility(View.VISIBLE);
                }
            }
        });


    }

    private void init() {

        button=view.findViewById(R.id.additem);
        button.setAllCaps(false);
        recyclerView= (RecyclerView) view.findViewById(R.id.listview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter=new PuantajMainAdapter(getActivity(),array);
    }


}
