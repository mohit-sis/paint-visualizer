package com.shawinfosolutions.paintvisualizer.Adapter;

import android.content.Context;

import com.shawinfosolutions.paintvisualizer.Fragments.AutomativeFragment;
import com.shawinfosolutions.paintvisualizer.Fragments.DecorativeFragment;
import com.shawinfosolutions.paintvisualizer.Fragments.IndustrialFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabAdapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;

    public TabAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                DecorativeFragment decorFragment = new DecorativeFragment();
                return decorFragment;
            case 1:
                AutomativeFragment autoFragment = new AutomativeFragment();
                return autoFragment;
            case 2:
                IndustrialFragment industrialFragment = new IndustrialFragment();
                return industrialFragment;
            default:
                return null;
        }
    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }
}