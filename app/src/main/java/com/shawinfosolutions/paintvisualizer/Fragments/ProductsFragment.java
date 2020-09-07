package com.shawinfosolutions.paintvisualizer.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.shawinfosolutions.paintvisualizer.Adapter.DecorativeAdapter;
import com.shawinfosolutions.paintvisualizer.Adapter.MyProjColorsAdapter;
import com.shawinfosolutions.paintvisualizer.Model.ColorData;
import com.shawinfosolutions.paintvisualizer.Model.ProductList;
import com.shawinfosolutions.paintvisualizer.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;

public class ProductsFragment extends Fragment {

    private GridView gridView;
    Context thiscontext;
    public Integer[] mThumbIds = {
            R.drawable.duco, R.drawable.paint_bucket,
            R.drawable.paint_bucket, R.drawable.paint_bucket,
            R.drawable.paint_bucket, R.drawable.paint_bucket,
            R.drawable.paint_bucket, R.drawable.paint_bucket,
            R.drawable.paint_bucket, R.drawable.paint_bucket
    };
    public String[] ImageName={"Duco Body Filler","Silk Viney Emulsion", "Autocolor","Economy Vesto Plastic",
            "Varnish Remover","Polyfillo", "Silk Viney Emulsion","Polyfillo", "Varnish Remover","Duco Body Filler"};
    public ProductsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_decorative, container, false);
        thiscontext=container.getContext();

        gridView=root.findViewById(R.id.grid_view);



        Bundle bundle = this.getArguments();
        String Pictures = bundle.getString("Products");
        Log.e("Pictures","==="+Pictures);
        try {
            ArrayList<ProductList> productLists = new ArrayList<>();
            productLists.clear();
            JSONArray jsonArray = new JSONArray(Pictures);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject explrObject = jsonArray.getJSONObject(i);
                ProductList productList = new
                        ProductList();

//                productList.setColorName(explrObject.getString("colorName"));
//                productList.setHexcodeVal((explrObject.getString("hexColorCode")));
                productList.setName(explrObject.getString("name"));
                productList.setImageLink(explrObject.getString("imageLink"));
                productList.setDescription(explrObject.getString("description"));
                productList.setUses(explrObject.getString("uses"));
                productList.setType(explrObject.getString("type"));
                productList.setColor(explrObject.getString("color"));
                productList.setFinish(explrObject.getString("finish"));
                productList.setRecommended(explrObject.getString("recommended"));
                productList.setMixingRatio(explrObject.getString("mixingRatio"));
                productLists.add(productList);


                // productLists.add(productList);
            }
//            MyProjColorsAdapter adapter = new MyProjColorsAdapter(getActivity(), productLists);
//            gridView.setAdapter(adapter);
            DecorativeAdapter adapter = new DecorativeAdapter(getActivity(),productLists,"myProj");
            gridView.setAdapter(adapter);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        //DecorativeAdapter adapter = new DecorativeAdapter(getActivity(),mThumbIds,ImageName);
       // gridView.setAdapter(adapter);
        return root;

    }

}
