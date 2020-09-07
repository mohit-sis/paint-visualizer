package com.shawinfosolutions.paintvisualizer.AsyncTask;

/*

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

    public AsyncTaskAllProducts(Context context, IObserverAsyncTask obserever,String username, String password) {
        this.context = context;
        this.username = username;
        this.password = password;
        this.obserever=obserever;


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
                (GET, Constants.AllProducts, null, new Response.Listener<JSONArray>() {
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
                            DecorativeAdapter adapter = new DecorativeAdapter(getActivity(),productLists);
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


*/
