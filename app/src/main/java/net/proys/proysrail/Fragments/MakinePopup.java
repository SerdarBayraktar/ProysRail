package net.proys.proysrail.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.proys.proysrail.Adapters.PuantajMainAdapter;
import net.proys.proysrail.Adapters.makine_isletme_popup_adapter;
import net.proys.proysrail.Items.MakinePopupItem;
import net.proys.proysrail.R;

import java.util.ArrayList;


public class MakinePopup extends Fragment {
    private RecyclerView recyclerView;
    private makine_isletme_popup_adapter adapter;
    View view;
    ArrayList<MakinePopupItem> array;



    public MakinePopup() {

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        array=new ArrayList<>();
        array.add(new MakinePopupItem("Makine1"));
        array.add(new MakinePopupItem("Makine1"));
        array.add(new MakinePopupItem("Makine1"));
        array.add(new MakinePopupItem("Makine1"));
        array.add(new MakinePopupItem("Makine1"));
        array.add(new MakinePopupItem("Makine1"));
        array.add(new MakinePopupItem("Makine1"));
        array.add(new MakinePopupItem("Makine1"));

        array.add(new MakinePopupItem("Makine1"));

        view= inflater.inflate(R.layout.fragment_makine_popup, container, false);
            init();
        recyclerView.setAdapter(adapter);
        return view;
    }

    private void init() {
        recyclerView= (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter= new makine_isletme_popup_adapter(getActivity(),array);

    }

}
