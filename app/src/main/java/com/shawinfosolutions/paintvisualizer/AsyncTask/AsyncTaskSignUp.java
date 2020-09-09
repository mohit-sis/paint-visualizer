package com.shawinfosolutions.paintvisualizer.AsyncTask;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
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
import com.shawinfosolutions.paintvisualizer.Activity.SignUpWithUsActivity;
import com.shawinfosolutions.paintvisualizer.Constants;
import com.shawinfosolutions.paintvisualizer.R;

import org.json.JSONException;
import org.json.JSONObject;

public class AsyncTaskSignUp  extends AsyncTask<Void, Void, Void>  {


    private ProgressDialog dialog;
    private Context context;
    private String loading_message="Please wait...";
    String username;
   String password; String companyname; String DOB; String PhnNo; String emailId;

    public  AsyncTaskSignUp(Context context, String username, String password, String companyname, String DOB, String PhnNo, String emailId) {
            this.context = context;
            this.username = username;
            this.companyname = companyname;
            this.password = password;
            this.DOB = DOB;
            this.PhnNo = PhnNo;
            this.emailId = emailId;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            try {
                dialog = new ProgressDialog(context);
                if (dialog != null && !loading_message.equalsIgnoreCase("")) {
                    dialog.setMessage(loading_message);
                    dialog.setCancelable(false);
                    dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    dialog.setProgress(0);
                    dialog.setMax(100);

                    dialog.show();
                }
            }catch (Exception e){
                e.printStackTrace();
            }


        }

        @Override
        protected Void doInBackground(Void... voids) {
            registerUser();
            return null;
        }

    private void registerUser() {
        {
            try {
                JSONObject object1 = new JSONObject();


                          /*  postParam.put("firstName", editTextfirst.getText().toString().trim());
                            postParam.put("lastName", editTextlast.getText().toString().trim());
                            postParam.put("email", editTextmail.getText().toString().trim());
                            postParam.put("username", editTextusername.getText().toString().trim());
                            postParam.put("password", editTextpassword.getText().toString().trim());*/
                object1.put("firstName", username);
                object1.put("lastName", username);
                object1.put("email", emailId);
                object1.put("username", username);
                object1.put("password", password);

                Log.d("TAG", "Login: " + object1);


                Volley.newRequestQueue(context).add(new JsonObjectRequest(Request.Method.POST, Constants.SignUp, object1,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.e("TAG", "onResponse: " + String.valueOf(response.toString()));

                                //  if (response.getString("status").equals("200")) {

                                // Intent intent = new Intent(context, LoginActivity.class);
                                // startActivity(intent);
                                //finish();
                                try {
                                    String email = response.getString("email");

                                    final Dialog dialog = new Dialog(context);
                                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
                                    dialog.setContentView(
                                            R.layout.error_custom_dialog);
                                    TextView errorMsgTxt = (TextView) dialog.findViewById(R.id.errorMsgTxt);
                                    errorMsgTxt.setText("User registered successfully, Please sign in with username: "+email);
                                    Button btnOK = (Button) dialog.findViewById(R.id.btnOK);
                                    Button btnCancel = (Button) dialog.findViewById(R.id.btnCancel);

                                    btnOK.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent intent = new Intent(context, SignUpActivity.class);
                                            context.startActivity(intent);
                                        }
                                    });
                                    btnCancel.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            dialog.dismiss();
                                        }
                                    });
                                    dialog.show();

                                    //Toast.makeText(context, "" + "User registered successfully with username: "+email, Toast.LENGTH_SHORT).show();



                                }catch (JSONException e) {
                                    e.printStackTrace();
                                }


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
                            Toast.makeText(context, "Authentication Failure.Please Try Again", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof ParseError) {
                            Toast.makeText(context, "Parse Error.Please Try Again", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof TimeoutError) {
                            Toast.makeText(context, "TimeOut Error.Please Try Again", Toast.LENGTH_SHORT).show();
                        }
                    }

                }));


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override

        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            try{

//                Intent intent=new Intent(context, SignUpActivity.class);
//                context.startActivity(intent);

//                if (MessageFlag.equalsIgnoreCase("F")) {
//                    Constant.errorDialog(MessageDes, activity);
//                } else if (MessageFlag.equalsIgnoreCase("S")) {
//                    // successDialog(MessageDes);
//                    MessageDes = "You will shortly receive mPIN via SMS on your registered Mobile Number.Kindly enter the same mPIN below and click on Update button";
//
//                    final Dialog dialog = new Dialog(activity);
//                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
//
//
//                    dialog.setContentView(R.layout.custom_dialog);
//                    // dialog.setTitle("Information");
//                    TextView alertText = (TextView) dialog.findViewById(R.id.alertText);
//                    alertText.setText(MessageDes);
//                    Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
//                    // if button is clicked, close the custom dialog
//                    dialogButton.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Constant.isInternetOn(activity);
//                            try {
//                                AsyncCallMSG asyncetask = new AsyncCallMSG(activity, "Please wait...");
//                                asyncetask.execute();
//                            }catch (Exception e){
//                                e.printStackTrace();
//                            }
//
//                            dialog.dismiss();
//
//
//                        }
//                    });
//                    dialog.show();
//
//                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }


