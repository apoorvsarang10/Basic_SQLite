package com.example.assignment4_sqlite;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

public class AlertFragment extends DialogFragment {

    private ListView listView;

    public AlertFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.list_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = view.findViewById(R.id.listView);

        List<String> strings = Arrays.asList("Hello", "World", "Bye");
        ArrayAdapter<String> arrayAdapter
                = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, strings);

        listView.setAdapter(arrayAdapter);
    }
}
