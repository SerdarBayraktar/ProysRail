package net.proys.proysrail.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import net.proys.proysrail.Adapters.FuelMainAdapter;
import net.proys.proysrail.Items.FuelMainItem;
import net.proys.proysrail.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MakineFuel extends Fragment {
    private RecyclerView recyclerView;
    private FuelMainAdapter adapter;


    public MakineFuel() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ArrayList<FuelMainItem> array=new ArrayList<>();
        array.add(new FuelMainItem("15.09.2019","114"));
        array.add(new FuelMainItem("15.09.2019","114"));
        array.add(new FuelMainItem("15.09.2019","114"));
        array.add(new FuelMainItem("15.09.2019","114"));

        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_makine_fuel, container, false);
        recyclerView= (RecyclerView) view.findViewById(R.id.listview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter=new FuelMainAdapter(getActivity(),array);

        recyclerView.setAdapter(adapter);

        return view;
    }

}
