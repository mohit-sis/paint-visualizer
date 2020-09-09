package com.shawinfosolutions.paintvisualizer.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.shawinfosolutions.paintvisualizer.Activity.MyProjects.MyProjectActivity;
import com.shawinfosolutions.paintvisualizer.Adapter.ColourExplorerTabAdapter;
import com.shawinfosolutions.paintvisualizer.Adapter.SelectedProjectTabAdapter;
import com.shawinfosolutions.paintvisualizer.Constants;
import com.shawinfosolutions.paintvisualizer.Model.MyProject;
import com.shawinfosolutions.paintvisualizer.Model.MyProjectItem;
import com.shawinfosolutions.paintvisualizer.Model.ProductList;
import com.shawinfosolutions.paintvisualizer.R;
import com.squareup.picasso.Picasso;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class SelectedProjectActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    ImageView homeImg, productImg, projectImg, moreImg, backBtn;
    TextView ProjectName;
    LinearLayout selectedProjectBannerLay;
    private ActionBar actionbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_project_layout);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        backBtn = findViewById(R.id.backBtn);
        ProjectName = findViewById(R.id.ProjectName);
        selectedProjectBannerLay = findViewById(R.id.selectedProjectBannerLay);

        tabLayout.addTab(tabLayout.newTab().setText("Gallery"));
        tabLayout.addTab(tabLayout.newTab().setText("Colour "));
        tabLayout.addTab(tabLayout.newTab().setText("Products "));
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SelectedProjectActivity.this, MyProjectActivity.class);
                startActivity(intent);
                finish();
            }
        });

        if(getIntent().getExtras() != null) {
            MyProjectItem user = (MyProjectItem) getIntent().getSerializableExtra("myProject");
            Log.e("selectedProjectName",user.getTitle());
            ProjectName.setText(user.getTitle());
            final SelectedProjectTabAdapter adapter = new SelectedProjectTabAdapter(this, getSupportFragmentManager(), tabLayout.getTabCount(),user);
            viewPager.setAdapter(adapter);
        }



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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will

        switch (item.getItemId()) {
            case android.R.id.home:

                Intent intent = new Intent(SelectedProjectActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

                //  finish();
                break;

        }
        return (super.onOptionsItemSelected(item));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SelectedProjectActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}