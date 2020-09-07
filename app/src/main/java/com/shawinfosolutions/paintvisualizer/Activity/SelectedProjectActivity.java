package com.shawinfosolutions.paintvisualizer.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;
import com.shawinfosolutions.paintvisualizer.Adapter.ColourExplorerTabAdapter;
import com.shawinfosolutions.paintvisualizer.Adapter.SelectedProjectTabAdapter;
import com.shawinfosolutions.paintvisualizer.Model.MyProject;
import com.shawinfosolutions.paintvisualizer.Model.MyProjectItem;
import com.shawinfosolutions.paintvisualizer.Model.ProductList;
import com.shawinfosolutions.paintvisualizer.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class SelectedProjectActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    ImageView homeImg, productImg, projectImg, moreImg;
    private ActionBar actionbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_project_layout);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        tabLayout.addTab(tabLayout.newTab().setText("Gallery"));
        tabLayout.addTab(tabLayout.newTab().setText("Colour "));
        tabLayout.addTab(tabLayout.newTab().setText("Products "));
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);


        if(getIntent().getExtras() != null) {
            MyProjectItem user = (MyProjectItem) getIntent().getSerializableExtra("myProject");
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