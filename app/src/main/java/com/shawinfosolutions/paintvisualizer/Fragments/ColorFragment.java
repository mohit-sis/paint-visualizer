package com.shawinfosolutions.paintvisualizer.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.shawinfosolutions.paintvisualizer.Activity.MainActivity;
import com.shawinfosolutions.paintvisualizer.Adapter.ColorsAdapter;
import com.shawinfosolutions.paintvisualizer.Adapter.DecorativeAdapter;
import com.shawinfosolutions.paintvisualizer.Adapter.MyProjColorsAdapter;
import com.shawinfosolutions.paintvisualizer.Model.ColorData;
import com.shawinfosolutions.paintvisualizer.Model.ProductList;
import com.shawinfosolutions.paintvisualizer.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ColorFragment extends Fragment {

    private GridView grid_view;
    Context thiscontext;

    public ColorFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_my_color, container, false);
        grid_view=root.findViewById(R.id.grid_view);
        //grid_view.setLayoutManager(new LinearLayoutManager(getContext()));
        //grid_view.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true));

        thiscontext=container.getContext();
        Bundle bundle = this.getArguments();
        String Pictures = bundle.getString("Colors");
        Log.e("Pictures","==="+Pictures);
        try {
            ArrayList<ColorData> productLists = new ArrayList<>();
            productLists.clear();
            JSONArray jsonArray = new JSONArray(Pictures);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject explrObject = jsonArray.getJSONObject(i);
                ColorData productList = new
                        ColorData();

                productList.setColorName(explrObject.getString("colorName"));
                productList.setHexcodeVal((explrObject.getString("hexColorCode")));
                productLists.add(productList);
            }
            MyProjColorsAdapter adapter = new MyProjColorsAdapter(getActivity(), productLists);
            grid_view.setAdapter(adapter);
        }catch (Exception e){
            e.printStackTrace();
        }
        return root;

    }

}

