package com.shawinfosolutions.paintvisualizer.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.shawinfosolutions.paintvisualizer.R;

import androidx.fragment.app.Fragment;

public class MyColorExplorerFragment extends Fragment {

    private GridView gridView;
    Context thiscontext;

    public MyColorExplorerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_my_color_explorer, container, false);
        thiscontext=container.getContext();


        return root;

    }

}
