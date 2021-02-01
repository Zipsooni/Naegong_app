package com.example.naegong_app;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;


public class Rankingtab extends Fragment {

    private ListView listview;
    private Ranking_ListViewAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.ranking, container, false);
        adapter = new Ranking_ListViewAdapter();

        //listView 참조 및 adapter
        listview = view.findViewById(R.id.listview);
        listview.setAdapter(adapter);

        adapter.addItems("username", R.drawable.sticker, "12:00:00");

        adapter.notifyDataSetChanged(); //adapter의 변경을 알린다.
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }


}
