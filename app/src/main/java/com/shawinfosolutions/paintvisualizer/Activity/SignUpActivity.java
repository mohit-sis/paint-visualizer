package com.shawinfosolutions.paintvisualizer.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.shawinfosolutions.paintvisualizer.AsyncTask.AsyncTaskSignIn;
import com.shawinfosolutions.paintvisualizer.AsyncTask.AsyncTaskSignUp;
import com.shawinfosolutions.paintvisualizer.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

//import com.facebook.FacebookSdk;
//import com.facebook.appevents.AppEventsLogger;

public class SignUpActivity extends Activity implements GoogleApiClient.OnConnectionFailedListener{
private LinearLayout signInBtnLay,SignInLayout,signUpLayBtn,signUpLayout;
private TextView signInTxt,signUpText,signInText,signUpTxt;
private Button signUpWithUsBtn,SignInBtn;
private EditText editTextUsername,editTextPass;

    private GoogleApiClient googleApiClient;
    private static final int RC_SIGN_IN = 1;
    SignInButton signUpByGoogle;

    private GoogleSignInClient mGoogleSignInClient;
    private static final String EMAIL = "email";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_layout);
        signInBtnLay=findViewById(R.id.signInBtnLay);
        signInTxt=findViewById(R.id.signInTxt);
        signUpText=findViewById(R.id.signUpText);
        signInText=findViewById(R.id.signInText);
        signUpTxt=findViewById(R.id.signUpTxt);
        signUpLayBtn=findViewById(R.id.signUpLayBtn);
        signUpLayout=findViewById(R.id.signUpLayout);
        signUpWithUsBtn=findViewById(R.id.signUpWithUsBtn);
        SignInLayout=findViewById(R.id.SignInLayout);
        editTextUsername=findViewById(R.id.editTextUsername);
        editTextPass=findViewById(R.id.editTextPass);
        SignInLayout.setVisibility(View.VISIBLE);
        signUpLayout.setVisibility(View.GONE);
      //  signInTxt.setTypeface(signInTxt.getTypeface(), Typeface.BOLD);
        SignInBtn=findViewById(R.id.SignInBtn);
      //  signUpTxt.setTypeface(signInTxt.getTypeface(), Typeface.NORMAL);
        signInText.setVisibility(View.GONE);
        signInTxt.setVisibility(View.VISIBLE);
        signInTxt.setTypeface(signInTxt.getTypeface(), Typeface.BOLD);
        SignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ValidateData()){

                    AsyncTaskSignIn asyncTask=new AsyncTaskSignIn(SignUpActivity.this,editTextUsername.getText().toString(),editTextPass.getText().toString());
                    asyncTask.execute();
//                    Intent intent=new Intent(SignUpActivity.this,MainActivity.class);
//                    startActivity(intent);
//                    finish();
                }

            }
        });
        signUpLayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInText.setVisibility(View.VISIBLE);
                signInTxt.setVisibility(View.GONE);

                signUpTxt.setVisibility(View.GONE);
                signUpText.setVisibility(View.VISIBLE);
                signUpText.setTypeface(signInTxt.getTypeface(), Typeface.BOLD);

                signUpLayout.setVisibility(View.VISIBLE);
                SignInLayout.setVisibility(View.GONE);

            }
        });
        signInBtnLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signInText.setVisibility(View.GONE);
                signInTxt.setVisibility(View.VISIBLE);

                signUpTxt.setVisibility(View.VISIBLE);
                signUpText.setVisibility(View.GONE);


                SignInLayout.setVisibility(View.VISIBLE);
                signUpLayout.setVisibility(View.GONE);

            }
        });


        signUpWithUsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignUpActivity.this,SignUpWithUsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Set the Google sign-in button.
        signUpByGoogle = findViewById(R.id.signUpWithGoogle);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // Create a GoogleApiClient instance
//        googleApiClient = new GoogleApiClient.Builder(this)
//                .enableAutoManage(this, this)
//                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
//                .build();

        signUpByGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = Auth.GoogleSignInApi.getSignInIntent(mGoogleSignInClient);
//                startActivityForResult(intent, RC_SIGN_IN);
                GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(SignUpActivity.this);

                if(account != null){
                    System.out.println("Account_info:"+account.toString());
                    String personEmail = account.getEmail();
                    Toast.makeText(getApplicationContext(),"You are already logged in with "+personEmail,Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(SignUpActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                    startActivityForResult(signInIntent, RC_SIGN_IN);
                }
            }
        });



        /*
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList(EMAIL));
        // If you are using in a fragment, call loginButton.setFragment(this);

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });

         */
    }

    private boolean ValidateData() {
        if(editTextUsername.getText().toString().equalsIgnoreCase("")){
            editTextUsername.setError("Please enter username");

        }
//        else if(!editTextUsername.getText().toString().trim().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")){
//            editTextUsername.setError("Please enter valid username");
//            return false;
//        }
        else if(editTextPass.getText().toString().equalsIgnoreCase("")){
            editTextPass.setError("Please enter password");
            return false;
        }
        return true;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {

            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            System.out.println("Google req_Result :"+result.toString());
            handleSignInResult(result);

        }
    }

    private void handleSignInResult(GoogleSignInResult result){
        if(result.isSuccess()){

            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
            if (acct != null) {
                String personName = acct.getDisplayName();
                String personGivenName = acct.getGivenName();
                String personFamilyName = acct.getFamilyName();
                String personEmail = acct.getEmail();
                String personId = acct.getId();
//                Uri personPhoto = acct.getPhotoUrl();

                System.out.println("name:"+personName +" GivenName:"+personGivenName+ " email:"+personEmail);
                Toast.makeText(getApplicationContext(),"Sign in with "+personEmail,Toast.LENGTH_LONG).show();
            }

            Intent intent=new Intent(SignUpActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(getApplicationContext(),"Google Sign up failed",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
