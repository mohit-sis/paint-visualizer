package com.shawinfosolutions.paintvisualizer.Activity;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shawinfosolutions.paintvisualizer.Activity.MyProjects.MyProjectActivity;
import com.shawinfosolutions.paintvisualizer.Activity.OurProduct.OurProductActivity;
import com.shawinfosolutions.paintvisualizer.Adapter.CustomPagerAdapter;
import com.shawinfosolutions.paintvisualizer.R;

import java.util.ArrayList;

public class MainActivity extends Activity {

    ViewPager viewPager;
    ArrayList<Integer> arrayList;
    LinearLayout layout_dot;
    TextView[] dot;
    ImageView homeImg, productImg, projectImg, moreImg;
    private LinearLayout pref_discoveryLayout,paint_visualizerLayout,Color_matchLayout,colorExplorerLayout;
    private WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.main_actvity);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        pref_discoveryLayout=findViewById(R.id.pref_discoveryLayout);
        paint_visualizerLayout=findViewById(R.id.paint_visualizerLayout);
        productImg = findViewById(R.id.productImg);
        homeImg = findViewById(R.id.homeImg);
        moreImg = findViewById(R.id.moreImg);
        colorExplorerLayout=findViewById(R.id.colorExplorerLayout);
        projectImg = findViewById(R.id.projectImg);
        webView=findViewById(R.id.webView);
        layout_dot = (LinearLayout) findViewById(R.id.layout_dot);
        arrayList = new ArrayList<>();
        arrayList.add(R.drawable.image);
        arrayList.add(R.drawable.image);
        arrayList.add(R.drawable.image);
        arrayList.add(R.drawable.image);

        String summary = "<a class=\"twitter-timeline\" " +
                "href=\"https://twitter.com/crownpaints?ref_src=twsrc%5Etfw\">" +
                "Tweets by crownpaints</a><script async src=\"https://platform.twitter.com/widgets.js\"" +
                " charset=\"utf-8\"></script>";
//        String encodedHtml = Base64.encodeToString(summary.getBytes(),
//                Base64.NO_PADDING);
        Log.e("summary",""+summary);
        //webView.loadUrl("https://twitter.com/twitterdev/likes");
        // webView.loadData("https://twitter.com/crownpaints?lang=en");
        webView.loadData(summary, "text/html", "utf-8");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        CustomPagerAdapter pagerAdapter = new CustomPagerAdapter(getApplicationContext(), arrayList);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setPageMargin(20);
        addDot(0);
        // TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        // tabLayout.setupWithViewPager(pager, true);
////For Test
//        SharedPreferences sharedpreferences = getSharedPreferences(Constants.PREF, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedpreferences.edit();
//        editor.putString("accessToken", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0QG1haWwuY29tIiwiaWF0IjoxNTk0ODEwNjQ2LCJleHAiOjE1OTQ4OTcwNDZ9.2RWo2hJg3R-Uj6JgSTDubg-YdgKVnaKM9IZcRzlcpoPM2gcpCwca-AUi8YR-zWgzFkQ73k3Jnv9pdk-gLvxduQ");
//        editor.commit();
//        //////
        // whenever the page changes
        pref_discoveryLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, InspirationalTrendsActivity.class);
                startActivity(intent);
                finish();
            }
        });
        paint_visualizerLayout=findViewById(R.id.paint_visualizerLayout);
        paint_visualizerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, VisualizerActivity.class);
                startActivity(intent);
                finish();
            }
        });
        Color_matchLayout=findViewById(R.id.Color_matchLayout);
        Color_matchLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, ColorMatchFirstActivity.class);
                startActivity(intent);
                finish();
            }
        });
        colorExplorerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ColoExplorerActivity.class);
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
            }
        });
        productImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeImg.setImageResource(R.drawable.home_wh);
                productImg.setImageResource(R.drawable.product_blue);
                projectImg.setImageResource(R.drawable.project_wh);
                moreImg.setImageResource(R.drawable.more_wh);
                Intent intent=new Intent(MainActivity.this, OurProductActivity.class);
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
                Intent intent=new Intent(MainActivity.this, MyProjectActivity.class);
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

                Intent intent=new Intent(MainActivity.this,MoreOptionActivity.class);
                startActivity(intent);
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                addDot(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    public void addDot(int page_position) {
        dot = new TextView[arrayList.size()];
        layout_dot.removeAllViews();

        for (int i = 0; i < dot.length; i++) {
            ;
            dot[i] = new TextView(this);
            dot[i].setText(Html.fromHtml("&#9679;"));
            dot[i].setTextSize(20);
            dot[i].setTextColor(getResources().getColor(R.color.gray));
            layout_dot.addView(dot[i]);
        }
        //active dot
        dot[page_position].setTextColor(getResources().getColor(R.color.white));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will

        switch (item.getItemId()) {
            case android.R.id.home:

                Intent intent = new Intent(MainActivity.this, SplashScreen.class);
                startActivity(intent);
                finish();

                //  finish();
                break;

        }
        return (super.onOptionsItemSelected(item));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MainActivity.this, SplashScreen.class);
        startActivity(intent);
        finish();
    }
}


