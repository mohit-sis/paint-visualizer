package com.shawinfosolutions.paintvisualizer.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.shawinfosolutions.paintvisualizer.Activity.MyProjects.MyProjectActivity;
import com.shawinfosolutions.paintvisualizer.Activity.OurProduct.OurProductActivity;
import com.shawinfosolutions.paintvisualizer.Constants;
import com.shawinfosolutions.paintvisualizer.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class MoreOptionActivity extends AppCompatActivity {

    ImageView homeImg, productImg, projectImg, moreImg;
private LinearLayout myPrefLayout,faqLayout,feedbacklayout,videoLayout,storeLocatorLayout,contactUsLayout,logoutlayout;
    private ActionBar actionbar;

    private GoogleSignInOptions gso;
    private GoogleSignInClient mGoogleSignInClient;

    private SharedPreferences pref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_options_layout);
        actionbar = getSupportActionBar();
        actionbar.setTitle("MORE OPTIONS");
        // actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setDisplayShowHomeEnabled(true);
        productImg = findViewById(R.id.productImg);
        faqLayout=findViewById(R.id.faqLayout);
        homeImg = findViewById(R.id.homeImg);
        videoLayout=findViewById(R.id.videoLayout);
        moreImg = findViewById(R.id.moreImg);
        feedbacklayout=findViewById(R.id.feedbacklayout);
        myPrefLayout=findViewById(R.id.myPrefLayout);
        projectImg = findViewById(R.id.projectImg);
        storeLocatorLayout=findViewById(R.id.storeLocatorLayout);
        contactUsLayout = findViewById(R.id.contactUsLayout);
        logoutlayout = findViewById(R.id.logoutlayout);

        feedbacklayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MoreOptionActivity.this,FeedbackActvity.class);

                startActivity(intent);
            }
        });
        storeLocatorLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MoreOptionActivity.this,StoreLocatorActivity.class);

                startActivity(intent);
            }
        });
        contactUsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MoreOptionActivity.this,ContactUsActivity.class);
                startActivity(intent);
            }
        });
        videoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MoreOptionActivity.this,VideoActivity.class);
                startActivity(intent);
            }
        });
        myPrefLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MoreOptionActivity.this,MyPrefrenceActivity.class);
                startActivity(intent);
            }
        });
        faqLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MoreOptionActivity.this,FAQSActivity.class);
                startActivity(intent);
                finish();
            }
        });
        homeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeImg.setImageResource(R.drawable.home_blue);
                productImg.setImageResource(R.drawable.product_wh);
                projectImg.setImageResource(R.drawable.project_wh);
                moreImg.setImageResource(R.drawable.more_wh);
                Intent intent=new Intent(MoreOptionActivity.this, MainActivity.class);
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
                Intent intent=new Intent(MoreOptionActivity.this, OurProductActivity.class);
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
                Intent intent=new Intent(MoreOptionActivity.this, MyProjectActivity.class);
                startActivity(intent);
            }
        });

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        logoutlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(MoreOptionActivity.this);

                if(account != null){

                    String personEmail = account.getEmail();
                    System.out.println("Account_email:"+personEmail);
                    Toast.makeText(getApplicationContext(),"Logging out "+personEmail,Toast.LENGTH_LONG).show();

                    signOut();
                }else{

                    pref = MoreOptionActivity.this.getSharedPreferences(Constants.PREF, Context.MODE_PRIVATE); // 0 - for private mode
                    pref.edit().remove("accessToken").clear().commit();

                    Toast.makeText(getApplicationContext(),"Logging out... ",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(MoreOptionActivity.this,SignUpActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                        System.out.println("userLogout");
                        Intent intent=new Intent(MoreOptionActivity.this,SignUpActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
    }
}
