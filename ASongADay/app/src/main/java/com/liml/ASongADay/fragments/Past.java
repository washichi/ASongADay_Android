package com.liml.ASongADay.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liml.ASongADay.Adapters.RecyclerViewAdapter;
import com.liml.ASongADay.R;


public class Past extends Fragment{

    RecyclerView recycler;
    RecyclerView.Adapter recyclerViewAdapter;
    RecyclerView.LayoutManager recylerViewLayoutManager;

    String[] subjects =
            {
                    "T & Sugah - Stardust (ft. Miyoki)",
                    "Owsey - Dancing By The Dreamless Wave",
                    "Kygo feat. Parson James - Stole The Show",
                    "Bravado - Lorde ( Ekko & Sidetrack Bootleg)",
                    "Feint ft Koven - Take It In",
                    "Flowidus ft Samahra Eames - Medicate",
                    "Raise Spirit - Fallen Leaves",
                    "Fred V & Grafix - Ignite (feat. Amy J Pryce)",
                    "Token - Self Taught",
                    "Watsky - Tiny Glowing Screens, Pt. 3",
                    "Maduk - How Could You",
                    "North Base - Give You Up (feat. Mica)",
                    "Calyx & TeeBee - 1x1 - Long Gone",
                    "Oh Wonder - Technicolour Beat",
                    "Rameses B - Once Upon A Time"};

    public Past() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_two,container,false);
        recycler = (RecyclerView) root.findViewById(R.id.my_recycler_view);
        return root;

        //return inflater.inflate(R.layout.fragment_two, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler.setHasFixedSize(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recylerViewLayoutManager = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(recylerViewLayoutManager);
        recyclerViewAdapter = new RecyclerViewAdapter(getContext(), subjects);
        recycler.setAdapter(recyclerViewAdapter);
    }
    public static Past newInstance() {
        return new Past();
    }
}
