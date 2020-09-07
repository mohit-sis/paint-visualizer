package com.shawinfosolutions.paintvisualizer.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.shawinfosolutions.paintvisualizer.Activity.MyProjects.MyProjectActivity;
import com.shawinfosolutions.paintvisualizer.Activity.OurProduct.OurProductActivity;
import com.shawinfosolutions.paintvisualizer.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class MoreOptionActivity extends AppCompatActivity {

    ImageView homeImg, productImg, projectImg, moreImg;
private LinearLayout myPrefLayout,faqLayout,feedbacklayout,videoLayout,storeLocatorLayout;
    private ActionBar actionbar;

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
    }
}
