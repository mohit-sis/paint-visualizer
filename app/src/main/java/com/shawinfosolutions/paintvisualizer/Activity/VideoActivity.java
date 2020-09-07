package com.shawinfosolutions.paintvisualizer.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.NetworkError;
import com.android.volley.error.NoConnectionError;
import com.android.volley.error.ParseError;
import com.android.volley.error.ServerError;
import com.android.volley.error.TimeoutError;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonArrayRequest;
import com.android.volley.request.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.shawinfosolutions.paintvisualizer.Activity.MyProjects.MyProjectActivity;
import com.shawinfosolutions.paintvisualizer.Adapter.MyProjectAdapter;
import com.shawinfosolutions.paintvisualizer.Config;
import com.shawinfosolutions.paintvisualizer.Constants;
import com.shawinfosolutions.paintvisualizer.IObserverAsyncTask;
import com.shawinfosolutions.paintvisualizer.Model.MyProject;
import com.shawinfosolutions.paintvisualizer.Model.MyProjectItem;
import com.shawinfosolutions.paintvisualizer.Model.VideoData;
import com.shawinfosolutions.paintvisualizer.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.android.volley.Request.Method.GET;

public class VideoActivity  extends YouTubeBaseActivity
         {
    private YouTubePlayerView youTubeView;
    private static final int RECOVERY_DIALOG_REQUEST = 1;
    private ImageView backBtn;
    private LinearLayout out_prodLayout;
             private LinearLayout childProd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_layout);
        out_prodLayout=findViewById(R.id.out_prodLayout);

        AsyncTaskVideo asyncTask=new AsyncTaskVideo(this);
        asyncTask.execute();



        backBtn=findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(VideoActivity.this,MoreOptionActivity.class);
                startActivity(intent);
            }
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(Config.DEVELOPER_KEY, (YouTubePlayer.OnInitializedListener) VideoActivity.this);
        }
    }

    private YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView) findViewById(R.id.youtubeview);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will

        switch (item.getItemId()) {
            case android.R.id.home:

                Intent intent = new Intent(VideoActivity.this, MoreOptionActivity.class);
                startActivity(intent);
                finish();

                //  finish();
                break;

        }
        return (super.onOptionsItemSelected(item));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(VideoActivity.this, MoreOptionActivity.class);
        startActivity(intent);
        finish();
    }
    private class AsyncTaskVideo extends AsyncTask<Void, Void, String> {


        private ProgressDialog dialog;
        private Context context;
        private String loading_message = "Please wait...";
        String username;
        String password;
        String status, msg, accessToken, tokenType;
        private SharedPreferences pref;
        private SharedPreferences.Editor editor;
        private IObserverAsyncTask obserever;

        public AsyncTaskVideo(Context context) {
            this.context = context;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(context);
            if (dialog != null && !loading_message.equalsIgnoreCase("")) {
                dialog.setMessage(loading_message);
                dialog.setCancelable(false);
                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                dialog.setProgress(0);
                dialog.setMax(100);

                dialog.show();
            }


        }

        @Override
        protected String doInBackground(Void... voids) {
            getVideoList();
            return null;
        }

        private void getVideoList() {
            SharedPreferences sharedpreferences =

        getSharedPreferences("Visualizer",Context.MODE_PRIVATE);

        accessToken =sharedpreferences.getString("accessToken",null);

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                (GET, Constants.Video, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e("response", String.valueOf(response));

                        try {
                            //  JSONArray jsonArray = new JSONArray(response);
                            out_prodLayout.removeAllViews();

                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jresponse = response.getJSONObject(i);
                                String idcolor = jresponse.getString("id");
                                String videoUrl = jresponse.getString("videoUrl");
                                String titlename = jresponse.getString("title");
                                childProd = (LinearLayout) getLayoutInflater().inflate(R.layout.our_prod_video_child, null);
                                YouTubePlayerView youtubePlayerView = (YouTubePlayerView) childProd.findViewById(R.id.youtubePlayerView);
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
        Volley.newRequestQueue(VideoActivity .this).

        add(jsonObjectRequest);
    }

        @Override

        protected void onPostExecute(String response) {
            //  super.onPostExecute(response);

            dialog.dismiss();
        }

    }
             public void playVideo(final String videoId, YouTubePlayerView youTubePlayerView) {
                 //initialize youtube player view
                 youTubePlayerView.initialize("YOUR API KEY HERE",
                         new YouTubePlayer.OnInitializedListener() {
                             @Override
                             public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                                 YouTubePlayer youTubePlayer, boolean b) {
                                 youTubePlayer.cueVideo("pI78lAM68mY");
                             }

                             @Override
                             public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                                 YouTubeInitializationResult youTubeInitializationResult) {

                             }
                         });
             }

         }
