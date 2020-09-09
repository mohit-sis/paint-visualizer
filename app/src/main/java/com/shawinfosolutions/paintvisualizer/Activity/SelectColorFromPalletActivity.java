package com.shawinfosolutions.paintvisualizer.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.shawinfosolutions.paintvisualizer.Adapter.ColorsAdapter;
import com.shawinfosolutions.paintvisualizer.Constants;
import com.shawinfosolutions.paintvisualizer.Model.ColorData;
import com.shawinfosolutions.paintvisualizer.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.Nullable;

import static com.android.volley.Request.Method.GET;

public class SelectColorFromPalletActivity extends Activity {
    //  RecyclerView selectedColorList;
    private ArrayList<Integer> itemsColor = new ArrayList<Integer>();
    ArrayList<ColorData> colorDataArrayList;
    private LinearLayout colorListLayout;
    private ArrayList<String> itemsColorName = new ArrayList<String>();
    private ColorsAdapter colorAdapter;
    private LinearLayout colorLayout;
    private LinearLayout baseColorLayout;
    private LinearLayout childProd, childColor;
    private String accessToken;

    private ImageView crossBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_select_color_layout);
        //baseColorLayout=findViewById(R.id.baseColorLayout);
        //m
        colorListLayout = findViewById(R.id.colorListLayout);

        SharedPreferences pref = getSharedPreferences(Constants.PREF, Context.MODE_PRIVATE);

        accessToken = pref.getString("accessToken", null);
        Log.e("accessToken","==="+accessToken);
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                (GET, Constants.Color, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e("response", String.valueOf(response));
                        try {
                            //  JSONArray jsonArray = new JSONArray(response);
                            //baseColorLayout.removeAllViews();
                            //m
                            colorListLayout.removeAllViews();

                            for(int i = 0; i < response.length(); i++){
                                JSONObject jresponse = response.getJSONObject(i);
                                String id = jresponse.getString("id");
                                String colorName = jresponse.getString("colorName");
                                String hexColorCode = jresponse.getString("hexColorCode");

                                /*
                                childProd = (LinearLayout) getLayoutInflater().inflate(R.layout.color_pallet_child, null);
                                TextView colorValTxt = (TextView) childProd.findViewById(R.id.colorValTxt);
                                TextView ColorVal = (TextView) childProd.findViewById(R.id.ColorVal);

                                GradientDrawable bgShape = (GradientDrawable) colorValTxt.getBackground().getCurrent();
                                bgShape.setColor(Color.parseColor(hexColorCode));
                                bgShape.setStroke(1, Color.parseColor(hexColorCode));

                                ColorVal.setText(colorName);
                                baseColorLayout.addView(childProd);

                                 */

                                childColor = (LinearLayout) getLayoutInflater().inflate(R.layout.color_child, null);
                                LinearLayout colorLayoutVal = (LinearLayout) childColor.findViewById(R.id.colorLayoutVal);
                                TextView colorNameTxt = (TextView) childColor.findViewById(R.id.colorNameTxt);

                                colorLayoutVal.setBackgroundColor(Color.parseColor(hexColorCode));
                                colorNameTxt.setText(colorName.substring(0, 1).toUpperCase()+ colorName.substring(1).toLowerCase());

                                colorListLayout.addView(childColor);

                                childColor.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent(SelectColorFromPalletActivity.this, SelectedColorToImageActivity.class);
                                        startActivity(intent);
                                        finish();
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
        Volley.newRequestQueue(SelectColorFromPalletActivity.this).add(jsonObjectRequest);

        colorLayout = findViewById(R.id.colorLayout);
        colorLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectColorFromPalletActivity.this, SelectedColorToImageActivity.class);
                startActivity(intent);
                finish();
            }
        });

        crossBtn = findViewById(R.id.crossBtn);
        crossBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(SelectColorFromPalletActivity.this, PaintVisualizerActivity.class);
//                startActivity(intent);
//                finish();

                onBackPressed();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will

        switch (item.getItemId()) {
            case android.R.id.home:

                Intent intent = new Intent(SelectColorFromPalletActivity.this, VisualizerActivity.class);
                startActivity(intent);
                finish();

                //  finish();
                break;

        }
        return (super.onOptionsItemSelected(item));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SelectColorFromPalletActivity.this, VisualizerActivity.class);
        startActivity(intent);
        finish();
    }
}
