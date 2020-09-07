package com.shawinfosolutions.paintvisualizer.Activity.OurProduct;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;
import com.shawinfosolutions.paintvisualizer.Activity.MainActivity;
import com.shawinfosolutions.paintvisualizer.Activity.MoreOptionActivity;
import com.shawinfosolutions.paintvisualizer.Activity.MyProjects.MyProjectActivity;
import com.shawinfosolutions.paintvisualizer.Adapter.TabAdapter;
import com.shawinfosolutions.paintvisualizer.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class OurProductActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    ImageView homeImg, productImg, projectImg, moreImg;
    private ActionBar actionbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.our_prod_layout);
        actionbar = getSupportActionBar();
        actionbar.setTitle("OUR PRODUCT");
       // actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setDisplayShowHomeEnabled(true);
        tabLayout=(TabLayout)findViewById(R.id.tabLayout);
        viewPager=(ViewPager)findViewById(R.id.viewPager);

        tabLayout.addTab(tabLayout.newTab().setText("Decorative"));
        tabLayout.addTab(tabLayout.newTab().setText("Automative"));
        tabLayout.addTab(tabLayout.newTab().setText("Industrial"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        productImg = findViewById(R.id.productImg);
        homeImg = findViewById(R.id.homeImg);
        moreImg = findViewById(R.id.moreImg);

        projectImg = findViewById(R.id.projectImg);
        final TabAdapter adapter = new TabAdapter(this,getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        homeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeImg.setImageResource(R.drawable.home_blue);
                productImg.setImageResource(R.drawable.product_wh);
                projectImg.setImageResource(R.drawable.project_wh);
                moreImg.setImageResource(R.drawable.more_wh);
                Intent intent=new Intent(OurProductActivity.this, MainActivity.class);
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
                Intent intent=new Intent(OurProductActivity.this, OurProductActivity.class);
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
                Intent intent=new Intent(OurProductActivity.this, MyProjectActivity.class);
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

                Intent intent=new Intent(OurProductActivity.this, MoreOptionActivity.class);
                startActivity(intent);
            }
        });




        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}

