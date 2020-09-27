package com.shawinfosolutions.paintvisualizer.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.shawinfosolutions.paintvisualizer.Adapter.Pref_DisPagerAdapter;
import com.shawinfosolutions.paintvisualizer.Constants;
import com.shawinfosolutions.paintvisualizer.Model.ColorData;
import com.shawinfosolutions.paintvisualizer.Model.PrefDiscoveryData;
import com.shawinfosolutions.paintvisualizer.R;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.android.volley.Request.Method.GET;

public class MyPrefrenceActivity extends Activity {
    private ImageView backBtn;

    private String accessToken;
    private ImageView image1, nxtImgView;
    private LinearLayout basecolor;
    private LinearLayout childProd, childProdlay;
    private LinearLayout preference_child_layout, preference_parents_layout;
    private ArrayList<String> imagePathlist;
    private ArrayList<ColorData> colorDataList;
    private ViewPager viewPager;
    private Pref_DisPagerAdapter pref_disPagerAdapter;
    private TextView DescriptionTxt1,DescriptionTxt2;
    private LinearLayout selectedColorLay,openOptionLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_preference_layout);

        backBtn=findViewById(R.id.backBtn);
        selectedColorLay=findViewById(R.id.selectedColorLay);
        DescriptionTxt1=findViewById(R.id.DescriptionTxt1);
        openOptionLayout=findViewById(R.id.openOptionLayout);
        DescriptionTxt2=findViewById(R.id.DescriptionTxt2);
        openOptionLayout.setVisibility(View.GONE);

        selectedColorLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openOptionLayout.setVisibility(View.VISIBLE);
            }
        });
        //  DescriptionTxt1.setText("Lorem lpsum is simply dummy text of the printing and Contrary to popular belief,Lorem lpsum is not simply random text.");
        // DescriptionTxt2.setText("More");
        preference_child_layout = findViewById(R.id.preference_child_layout);
        preference_parents_layout = findViewById(R.id.preference_parents_layout);
        SharedPreferences sharedpreferences = getSharedPreferences(Constants.PREF, Context.MODE_PRIVATE);
        imagePathlist = new ArrayList<>();
        imagePathlist.clear();
        colorDataList = new ArrayList<>();
        colorDataList.clear();
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        accessToken = sharedpreferences.getString("accessToken", null);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MyPrefrenceActivity.this,MoreOptionActivity.class);
                startActivity(intent);
            }
        });

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                (GET, Constants.MyPreferences, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e("responsePreference", String.valueOf(response));
                        Log.e("responseLength: ", String.valueOf(response.length()));
                        try {
                            preference_parents_layout.removeAllViews();
                            colorDataList.clear();
                            imagePathlist.clear();
                            for (int i = 0; i < response.length(); i++) {
                                childProdlay = (LinearLayout) getLayoutInflater().inflate(R.layout.color_pref_child, null);
                                image1 = childProdlay.findViewById(R.id.image1);
                                nxtImgView = childProdlay.findViewById(R.id.nxtImgView);
                                basecolor = childProdlay.findViewById(R.id.basecolor);

                                JSONObject jresponse = response.getJSONObject(i);
                                String id = jresponse.getString("id");
                                String title = jresponse.getString("title");
                                String imageLink = jresponse.getString("imageLink");
                                imagePathlist.add(imageLink);
                                basecolor.removeAllViews();
                                JSONArray jsonArray = jresponse.getJSONArray("colors");
                                Log.e("size", "" + jsonArray.length());

                                //m
                                PrefDiscoveryData prefData = new PrefDiscoveryData();
                                prefData.setTitle(title);
                                prefData.setImageLink(imageLink);

                                //m
                                colorDataList.clear();

                                for (int j = 0; j < jsonArray.length(); j++) {
                                    JSONObject jcolorObj = jsonArray.getJSONObject(j);

                                    ColorData colorData = new ColorData();
                                    colorData.setColorName(jcolorObj.getString("colorName"));
                                    colorData.setHex(jcolorObj.getString("hex"));
                                    colorDataList.add(colorData);

                                    //m
                                    prefData.setColors(colorDataList);
                                }

                                Log.e("colorDataListSize",String.valueOf(colorDataList.size()));
                                Log.e("colorDataList",String.valueOf(colorDataList));

                                pref_disPagerAdapter = new Pref_DisPagerAdapter(MyPrefrenceActivity.this, imagePathlist, colorDataList, prefData);
                                viewPager.setAdapter(pref_disPagerAdapter);

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
                Log.e("Token","=="+token);
                headers.put("Authorization", "Bearer " + token);
                return headers;
            }
        };
        Volley.newRequestQueue(MyPrefrenceActivity.this).add(jsonObjectRequest);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will

        switch (item.getItemId()) {
            case android.R.id.home:

                Intent intent = new Intent(MyPrefrenceActivity.this, MoreOptionActivity.class);
                startActivity(intent);
                finish();

                //  finish();
                break;

        }
        return (super.onOptionsItemSelected(item));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MyPrefrenceActivity.this, MoreOptionActivity.class);
        startActivity(intent);
        finish();
    }

}
