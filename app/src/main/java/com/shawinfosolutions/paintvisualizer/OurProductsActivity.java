/*
package com.shawinfosolutions.paintvisualizer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.Nullable;

import static com.android.volley.Request.Method.GET;

public class OurProductsActivity extends Activity {
    private LinearLayout out_prodLayout;
    private String accessToken;
    private LinearLayout childProd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.our_product_layout);
        out_prodLayout=findViewById(R.id.out_prodLayout);


        SharedPreferences sharedpreferences = getSharedPreferences("Visualizer", Context.MODE_PRIVATE);

        accessToken = sharedpreferences.getString("accessToken", null);

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                (GET, Constants.Video, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e("response", String.valueOf(response));

                        try {
                            //  JSONArray jsonArray = new JSONArray(response);
                            out_prodLayout.removeAllViews();

                            for(int i = 0; i < response.length(); i++){
                                JSONObject jresponse = response.getJSONObject(i);
                                String idcolor = jresponse.getString("id");
                                String videoUrl = jresponse.getString("videoUrl");
                                String titlename = jresponse.getString("title");
                                childProd = (LinearLayout) getLayoutInflater().inflate(R.layout.our_prod_video_child, null);
                                YouTubePlayerView    youtubePlayerView = (YouTubePlayerView) childProd.findViewById(R.id.youtubePlayerView);
                                TextView videoName = (TextView) childProd.findViewById(R.id.videoName);

//                                    colorLayoutVal.setBackgroundColor(Color.parseColor(hexColorCode));
                                videoName.setText(titlename);
                                playVideo(videoUrl, youtubePlayerView);
                                out_prodLayout.addView(childProd);

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
        Volley.newRequestQueue(OurProductsActivity.this).add(jsonObjectRequest);

    }
    public void playVideo(final String videoId, YouTubePlayerView youTubePlayerView) {
        //initialize youtube player view
        youTubePlayerView.initialize("YOUR API KEY HERE",
                new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                        YouTubePlayer youTubePlayer, boolean b) {
                        youTubePlayer.cueVideo(videoId);
                    }

                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                        YouTubeInitializationResult youTubeInitializationResult) {

                    }
                });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will

        switch (item.getItemId()) {
            case android.R.id.home:

                Intent intent = new Intent(OurProductsActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

                //  finish();
                break;

        }
        return (super.onOptionsItemSelected(item));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(OurProductsActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
*/
