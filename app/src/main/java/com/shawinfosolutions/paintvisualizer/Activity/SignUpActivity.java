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

import com.shawinfosolutions.paintvisualizer.AsyncTask.AsyncTaskSignIn;
import com.shawinfosolutions.paintvisualizer.AsyncTask.AsyncTaskSignUp;
import com.shawinfosolutions.paintvisualizer.R;

import androidx.annotation.Nullable;

public class SignUpActivity extends Activity {
private LinearLayout signInBtnLay,SignInLayout,signUpLayBtn,signUpLayout;
private TextView signInTxt,signUpText,signInText,signUpTxt;
private Button signUpWithUsBtn,SignInBtn;
private EditText editTextUsername,editTextPass;
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
    }

    private boolean ValidateData() {
        if(editTextUsername.getText().toString().equalsIgnoreCase("")){
            editTextUsername.setError("Please enter username");
            return false;
        }else if(editTextPass.getText().toString().equalsIgnoreCase("")){
            editTextPass.setError("Please enter password");
            return false;
        }
        return true;
    }
}
