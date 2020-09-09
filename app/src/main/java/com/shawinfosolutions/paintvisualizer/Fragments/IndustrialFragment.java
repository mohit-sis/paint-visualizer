package com.shawinfosolutions.paintvisualizer.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.shawinfosolutions.paintvisualizer.Adapter.DecorativeAdapter;
import com.shawinfosolutions.paintvisualizer.Constants;
import com.shawinfosolutions.paintvisualizer.IObserverAsyncTask;
import com.shawinfosolutions.paintvisualizer.Model.ProductList;
import com.shawinfosolutions.paintvisualizer.R;

import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.android.volley.Request.Method.GET;

public class IndustrialFragment
        extends Fragment {

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
    private String accessToken;


    public IndustrialFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_home, container, false);

        View root = inflater.inflate(R.layout.fragment_decorative, container, false);
        thiscontext=container.getContext();

        gridView=root.findViewById(R.id.grid_view);
        IndustrialFragment.AsyncTaskAllProducts asyncTask=new IndustrialFragment.AsyncTaskAllProducts(getContext());
        asyncTask.execute();

        return root;
    }

    public class AsyncTaskAllProducts extends AsyncTask<Void, Void, String> {


        private ProgressDialog dialog;
        private Context context;
        private String loading_message = "Please wait...";
        String username;
        String password;
        String status, msg, accessToken, tokenType;
        private SharedPreferences pref;
        private SharedPreferences.Editor editor;
        private IObserverAsyncTask obserever;

        public AsyncTaskAllProducts(Context context) {
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
            getAllProducts();
            return null;
        }

        private void getAllProducts() {
            SharedPreferences pref = context.getSharedPreferences(Constants.PREF, Context.MODE_PRIVATE);

            accessToken = pref.getString("accessToken", null);
            Log.e("accessToken","==="+accessToken);
            JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                    (GET, Constants.IndustrialProducts, null, new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            Log.e("response", String.valueOf(response));
                            try {

                                ArrayList<ProductList> productLists=new ArrayList<>();
                                productLists.clear();
                                for(int i = 0; i < response.length(); i++){
                                    JSONObject jresponse = response.getJSONObject(i);

                                    ProductList productList=new ProductList();

                                    productList.setName(jresponse.getString("name"));
                                    productList.setImageLink(jresponse.getString("imageLink"));
                                    productList.setDescription(jresponse.getString("description"));
                                    productList.setUses(jresponse.getString("uses"));
                                    productList.setType(jresponse.getString("type"));
                                    productList.setColor(jresponse.getString("color"));
                                    productList.setFinish(jresponse.getString("finish"));
                                    productList.setRecommended(jresponse.getString("recommended"));
                                    productList.setMixingRatio(jresponse.getString("mixingRatio"));
                                    productLists.add(productList);


                                }
                                DecorativeAdapter adapter = new DecorativeAdapter(getActivity(),productLists,"Decor");
                                gridView.setAdapter(adapter);
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

            Volley.newRequestQueue(context).add(jsonObjectRequest);



        }

        @Override

        protected void onPostExecute(String response) {
            //  super.onPostExecute(response);

            dialog.dismiss();
        }

    }

}
