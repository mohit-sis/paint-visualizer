package com.shawinfosolutions.paintvisualizer.AsyncTask;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.NetworkError;
import com.android.volley.error.NoConnectionError;
import com.android.volley.error.ParseError;
import com.android.volley.error.ServerError;
import com.android.volley.error.TimeoutError;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.shawinfosolutions.paintvisualizer.Activity.MainActivity;
import com.shawinfosolutions.paintvisualizer.Activity.SignUpActivity;
import com.shawinfosolutions.paintvisualizer.Constants;
import com.shawinfosolutions.paintvisualizer.R;

import org.json.JSONException;
import org.json.JSONObject;

public class AsyncTaskSignIn extends AsyncTask<Void, Void, Void> {


    private ProgressDialog dialog;
    private Context context;
    private String loading_message = "Please wait...";
    String username;
    String password;
    String status, msg, accessToken, tokenType;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    public AsyncTaskSignIn(Context context, String username, String password) {
        this.context = context;
        this.username = username;
        this.password = password;


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
    protected Void doInBackground(Void... voids) {
        loginUser();
        return null;
    }

    private void loginUser() {
        {
            JSONObject object1 = new JSONObject();
            try {

                          /*  postParam.put("firstName", editTextfirst.getText().toString().trim());
                            postParam.put("lastName", editTextlast.getText().toString().trim());
                            postParam.put("email", editTextmail.getText().toString().trim());
                            postParam.put("username", editTextusername.getText().toString().trim());
                            postParam.put("password", editTextpassword.getText().toString().trim());*/
                object1.put("username", username);
                object1.put("password", password);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.d("TAG", "Login: " + object1);


            Volley.newRequestQueue(context).add(new JsonObjectRequest(Request.Method.POST, Constants.SignIn, object1,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.e("TAG", "onResponse: " + String.valueOf(response.toString()));

                            pref = context.getSharedPreferences(Constants.PREF, Context.MODE_PRIVATE); // 0 - for private mode
                            pref.edit().remove("accessToken").clear().commit();

                            try {
                                status = response.getString("status");
                                msg = response.getString("msg");
                                accessToken = response.getString("accessToken");
                                tokenType = response.getString("tokenType");
                                Log.e("status", "====" + status);
                                Log.e("msg", "====" + msg);


                                try {
                                    Log.e("status", "====11" + status);
                                    Log.e("msg", "====11" + msg);
                                    {
                                        final Dialog dialog = new Dialog(context);
                                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
                                        dialog.setContentView(
                                                R.layout.error_custom_dialog);
                                        TextView errorMsgTxt = (TextView) dialog.findViewById(R.id.errorMsgTxt);
                                        errorMsgTxt.setText(msg);
                                        Button btnOK = (Button) dialog.findViewById(R.id.btnOK);
                                        Button btnCancel = (Button) dialog.findViewById(R.id.btnCancel);

                                        // if button is clicked, close the custom dialog
                                        btnOK.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                dialog.dismiss();
                                                if (status.equalsIgnoreCase("success")) {
                                                    editor = pref.edit();
                                                    pref.edit().remove("accessToken").clear().commit();
                                                    editor.putString("accessToken", accessToken);

                                                    editor.commit();
                                                    editor.apply();
                                                    accessToken = pref.getString("accessToken", null);
                                                    Log.e("accessToken","==="+accessToken);
                                                    Intent intent = new Intent(context, MainActivity.class);
                                                    context.startActivity(intent);
                                                }
                                                else {
                                                    pref.edit().remove("accessToken").clear().commit();

                                                }


                                            }
                                        });
                                        btnCancel.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {

                                                dialog.dismiss();


                                            }
                                        });
                                        dialog.show();

                                    }


                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            // Intent intent = new Intent(context, LoginActivity.class);
                            // startActivity(intent);
                            //finish();
                            //  Toast.makeText(context, "" + response, Toast.LENGTH_SHORT).show();

                            // } else {
                            //   Toast.makeText(context, ""+response, Toast.LENGTH_SHORT).show();

                            // }
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
                        Toast.makeText(context, "Authantication Failure.Please Try Again", Toast.LENGTH_SHORT).show();
                    } else if (error instanceof ParseError) {
                        Toast.makeText(context, "Parse Error.Please Try Again", Toast.LENGTH_SHORT).show();
                    } else if (error instanceof TimeoutError) {
                        Toast.makeText(context, "TimeOut Error.Please Try Again", Toast.LENGTH_SHORT).show();
                    }
                }
            }));
        }
    }

    @Override

    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        dialog.dismiss();
    }

}


