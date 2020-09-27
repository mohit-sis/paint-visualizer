package com.shawinfosolutions.paintvisualizer.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.shawinfosolutions.paintvisualizer.Activity.SelectColorFromPalletActivity;
import com.shawinfosolutions.paintvisualizer.Activity.SelectedColorToImageActivity;
import com.shawinfosolutions.paintvisualizer.Adapter.ColorsAdapter;
import com.shawinfosolutions.paintvisualizer.Adapter.DecorativeAdapter;
import com.shawinfosolutions.paintvisualizer.Constants;
import com.shawinfosolutions.paintvisualizer.Model.ColorData;
import com.shawinfosolutions.paintvisualizer.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.fragment.app.Fragment;

import static com.android.volley.Request.Method.GET;

public class AllColorExplorerFragment extends Fragment {

    private GridView gridView;
    Context thiscontext;
    private ArrayList<Integer> itemsColor = new ArrayList<Integer>();
    ArrayList<ColorData> colorDataArrayList;
    private LinearLayout colorListLayout;
    private ArrayList<String> itemsColorName = new ArrayList<String>();
    private ColorsAdapter colorAdapter;
    private LinearLayout colorLayout;
    private LinearLayout baseColorLayout;
    private LinearLayout childProd, childColor;
    private String accessToken;
    public AllColorExplorerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_all_color_explorer, container, false);
        thiscontext=container.getContext();
        //baseColorLayout=root.findViewById(R.id.baseColorLayout);
        colorListLayout = root.findViewById(R.id.colorListLayout);

        SharedPreferences pref = getActivity().getSharedPreferences(Constants.PREF, Context.MODE_PRIVATE);

        accessToken = pref.getString("accessToken", null);
        Log.e("accessToken","==="+accessToken);
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                (GET, Constants.AllColors, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e("AllColorResponse", String.valueOf(response));
                        try {
                            //  JSONArray jsonArray = new JSONArray(response);
                            //baseColorLayout.removeAllViews();
                            colorListLayout.removeAllViews();
                            Log.e("colorResponseLength",String.valueOf(response.length()));
                            for(int i = 0; i < response.length(); i++){
                                JSONObject jresponse = response.getJSONObject(i);
                                String id = jresponse.getString("id");
                                String colorName = jresponse.getString("colorName");
                                String hexColorCode = jresponse.getString("hexColorCode");

//                                childProd = (LinearLayout) getLayoutInflater().inflate(R.layout.color_pallet_child, null);
//                                TextView colorValTxt = (TextView) childProd.findViewById(R.id.colorValTxt);
//                                TextView ColorVal = (TextView) childProd.findViewById(R.id.ColorVal);
//
//                                GradientDrawable bgShape = (GradientDrawable) colorValTxt.getBackground().getCurrent();
//                                bgShape.setColor(Color.parseColor(hexColorCode));
//                                bgShape.setStroke(1, Color.parseColor(hexColorCode));

//                                ColorVal.setText(colorName);
//                                baseColorLayout.addView(childProd);

                                childColor = (LinearLayout) getLayoutInflater().inflate(R.layout.color_child, null);
                                LinearLayout colorLayoutVal = (LinearLayout) childColor.findViewById(R.id.colorLayoutVal);
                                TextView colorNameTxt = (TextView) childColor.findViewById(R.id.colorNameTxt);

                                colorLayoutVal.setBackgroundColor(Color.parseColor(hexColorCode));
                                colorNameTxt.setText(colorName);

                                colorListLayout.addView(childColor);

                                childColor.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent(getContext(), SelectedColorToImageActivity.class);
                                        startActivity(intent);

                                    }
                                });

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("error", String.valueOf(error));

                        NetworkResponse networkResponse = error.networkResponse;
                        if (networkResponse != null && networkResponse.statusCode == 401) {
                        }
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                String token = accessToken;
                headers.put("Authorization", "Bearer " + token);
                return headers;
            }
        };
        Volley.newRequestQueue(getContext()).add(jsonObjectRequest);

        return root;

    }

}

