package com.shawinfosolutions.paintvisualizer.Activity.MyProjects;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

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
import com.shawinfosolutions.paintvisualizer.Activity.MainActivity;
import com.shawinfosolutions.paintvisualizer.Activity.MoreOptionActivity;
import com.shawinfosolutions.paintvisualizer.Activity.OurProduct.OurProductActivity;
import com.shawinfosolutions.paintvisualizer.Adapter.MyProjectAdapter;
import com.shawinfosolutions.paintvisualizer.Constants;
import com.shawinfosolutions.paintvisualizer.IObserverAsyncTask;
import com.shawinfosolutions.paintvisualizer.Model.MyProject;
import com.shawinfosolutions.paintvisualizer.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.android.volley.Request.Method.GET;

public class MyProjectActivity extends AppCompatActivity {
    RecyclerView my_project_recycle;
    public Integer[] mThumbIds = {
            R.drawable.image, R.drawable.image,
            R.drawable.image, R.drawable.image,
            R.drawable.image, R.drawable.image,
            R.drawable.image, R.drawable.image,
            R.drawable.image, R.drawable.image
    };
    ImageView homeImg, productImg, projectImg, moreImg;
    public String[] ImageName={"Project Name","Project Name", "Project Name","Project Name", "Project Name","Project Name", "Project Name","Project Name", "Project Name","Project Name"};
    private ActionBar actionbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_project_activity);
        actionbar = getSupportActionBar();
        actionbar.setTitle("MY PROJECTS");
       // actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setDisplayShowHomeEnabled(true);
        my_project_recycle=findViewById(R.id.my_project_recycle);
        my_project_recycle.setLayoutManager(new LinearLayoutManager(this));

        AsyncTaskMyProject asyncTask=new AsyncTaskMyProject(this);
        asyncTask.execute();




        productImg = findViewById(R.id.productImg);
        homeImg = findViewById(R.id.homeImg);
        moreImg = findViewById(R.id.moreImg);

        projectImg = findViewById(R.id.projectImg);


        homeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeImg.setImageResource(R.drawable.home_blue);
                productImg.setImageResource(R.drawable.product_wh);
                projectImg.setImageResource(R.drawable.project_wh);
                moreImg.setImageResource(R.drawable.more_wh);
                Intent intent=new Intent(MyProjectActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        productImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeImg.setImageResource(R.drawable.home_wh);
                productImg.setImageResource(R.drawable.product_blue);
                projectImg.setImageResource(R.drawable.project_wh);
                moreImg.setImageResource(R.drawable.more_wh);
                Intent intent=new Intent(MyProjectActivity.this, OurProductActivity.class);
                startActivity(intent);
            }
        });
        projectImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeImg.setImageResource(R.drawable.home_wh);
                productImg.setImageResource(R.drawable.product_wh);
                projectImg.setImageResource(R.drawable.project_blue);
                moreImg.setImageResource(R.drawable.more_wh);
                Intent intent=new Intent(MyProjectActivity.this, MyProjectActivity.class);
                startActivity(intent);
            }
        });
        moreImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeImg.setImageResource(R.drawable.home_wh);
                productImg.setImageResource(R.drawable.product_wh);
                projectImg.setImageResource(R.drawable.project_wh);
                moreImg.setImageResource(R.drawable.more_blue);

                Intent intent=new Intent(MyProjectActivity.this, MoreOptionActivity.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private class AsyncTaskMyProject extends AsyncTask<Void, Void, String> {


        private ProgressDialog dialog;
        private Context context;
        private String loading_message = "Please wait...";
        String username;
        String password;
        String status, msg, accessToken, tokenType;
        private SharedPreferences pref;
        private SharedPreferences.Editor editor;
        private IObserverAsyncTask obserever;

        public AsyncTaskMyProject(Context context) {
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
            getMyProject();
            return null;
        }

        private void getMyProject() {
            SharedPreferences pref = context.getSharedPreferences(Constants.PREF, Context.MODE_PRIVATE);

            accessToken = pref.getString("accessToken", null);
            Log.e("accessToken","==="+accessToken);

            JSONObject object1 = new JSONObject();

            final ArrayList<MyProject> myProjectArrayList=new ArrayList<>();

            Volley.newRequestQueue(context).add(new JsonObjectRequest(GET, Constants.MyProject, object1,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            Log.e("myProjectTAG", "onResponse: " + String.valueOf(response.toString()));
                           // JSONObject jresponse = response.getJSONObject(i);

                           // ProductList productList=new ProductList();

                          //  productList.setName(jresponse.getString("name"));
                            try {
                                Log.e("responseLength", String.valueOf(response.length()));
                                //for (int i = 0; i < response.length(); i++) {

                                    //JSONObject jresponse = response.getJSONObject(i);

                                    String Title = response.getString("title");
                                    String imageLink = response.getString("imageLink");
                                    String colors = response.getString("colors");
                                    String pictures = response.getString("pictures");
                                    String products = response.getString("products");
                                    Log.e("TAG", "colors: " + colors);
                                    Log.e("TAG", "pictures: " + pictures);
                                    Log.e("TAG", "products: " + products);

//                                    JSONArray jsonArrayColor = jresponse.getJSONArray("colors");
//                                    Log.e("ColorSize", "" + jsonArrayColor.length());
//
//                                    JSONArray jsonArrayProducts = jresponse.getJSONArray("products");
//                                    Log.e("productsSize", "" + jsonArrayProducts.length());
//
//                                    JSONArray jsonArrayGallery = jresponse.getJSONArray("galleryImages");
//                                    Log.e("galleryImagesSize", "" + jsonArrayGallery .length());

                                    MyProject myProject = new MyProject();
                                    myProject.setTitle(Title);
                                    myProject.setImageLink(imageLink);
                                    myProject.setColors(colors);
                                    myProject.setPictures(pictures);
                                    myProject.setProducts(products);

                                    myProjectArrayList.add(myProject);

                                    MyProjectAdapter adapter = new MyProjectAdapter(MyProjectActivity.this, myProjectArrayList);
//                                    // Attach the adapter to the recyclerview to populate items
                                    my_project_recycle.setAdapter(adapter);
                                //}
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d("TAG", "onErrorResponse: " + error);
                                if (error instanceof NoConnectionError) {
                                    Toast.makeText(context, "No Internet Connection .Please try again", Toast.LENGTH_SHORT).show();
                                } else if (error instanceof NetworkError) {
                                    Toast.makeText(context, "Network Error.Please Try Again", Toast.LENGTH_SHORT).show();
                                } else if (error instanceof ServerError) {
                                    Toast.makeText(context, "Server Error.Please Try Again", Toast.LENGTH_SHORT).show();

                                } else if (error instanceof AuthFailureError) {
                                    Toast.makeText(context, "Authentication Failure.Please Try Again", Toast.LENGTH_SHORT).show();
                                } else if (error instanceof ParseError) {
                                    Toast.makeText(context, "Parse Error.Please Try Again", Toast.LENGTH_SHORT).show();
                                } else if (error instanceof TimeoutError) {
                                    Toast.makeText(context, "TimeOut Error.Please Try Again", Toast.LENGTH_SHORT).show();
                                }
                            }
                }
            )
            {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    String token = accessToken;
                    headers.put("Authorization", "Bearer " + token);
                    return headers;
                }
            });
           // Volley.newRequestQueue(context).add(jsonObjectRequest);




//            JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
//                    (GET, Constants.MyProject, null, new Response.Listener<JSONArray>() {
//                        @Override
//                        public void onResponse(JSONArray response) {
//                            Log.e("response", String.valueOf(response));
//
//                                  //  MyProjectAdapter adapter = new MyProjectAdapter(MyProjectActivity.this,mThumbIds,ImageName);
//                                    // Attach the adapter to the recyclerview to populate items
//                                   // my_project_recycle.setAdapter(adapter);
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//
//                        }
//
//                    }, new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                            Log.e("error", String.valueOf(error));
//
//                            NetworkResponse networkResponse = error.networkResponse;
//                            if (networkResponse != null && networkResponse.statusCode == 401) {
//                            }
//                        }
//                    }) {
//                @Override
//                public Map<String, String> getHeaders() throws AuthFailureError {
//                    HashMap<String, String> headers = new HashMap<String, String>();
//                    String token = accessToken;
//                    headers.put("Authorization", "Bearer " + token);
//                    return headers;
//                }
//            };
//            Volley.newRequestQueue(context).add(jsonObjectRequest);



        }

        @Override

        protected void onPostExecute(String response) {
            //  super.onPostExecute(response);

            dialog.dismiss();
        }

    }





}
