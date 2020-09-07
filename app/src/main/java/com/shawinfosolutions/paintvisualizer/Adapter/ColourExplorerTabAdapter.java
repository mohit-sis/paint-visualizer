package com.shawinfosolutions.paintvisualizer.Adapter;

import android.content.Context;

import com.shawinfosolutions.paintvisualizer.Fragments.AllColorExplorerFragment;
import com.shawinfosolutions.paintvisualizer.Fragments.AutomativeFragment;
import com.shawinfosolutions.paintvisualizer.Fragments.DecorativeFragment;
import com.shawinfosolutions.paintvisualizer.Fragments.IndustrialFragment;
import com.shawinfosolutions.paintvisualizer.Fragments.MyColorExplorerFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ColourExplorerTabAdapter  extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;

    public ColourExplorerTabAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                AllColorExplorerFragment decorFragment = new AllColorExplorerFragment();
                return decorFragment;
            case 1:
                MyColorExplorerFragment autoFragment = new MyColorExplorerFragment();
                return autoFragment;

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