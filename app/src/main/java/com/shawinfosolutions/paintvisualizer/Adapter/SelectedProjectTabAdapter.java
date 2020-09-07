package com.shawinfosolutions.paintvisualizer.Adapter;

import android.content.Context;
import android.os.Bundle;

import com.shawinfosolutions.paintvisualizer.Fragments.AllColorExplorerFragment;
import com.shawinfosolutions.paintvisualizer.Fragments.ColorFragment;
import com.shawinfosolutions.paintvisualizer.Fragments.GalleryFragment;
import com.shawinfosolutions.paintvisualizer.Fragments.MyColorExplorerFragment;
import com.shawinfosolutions.paintvisualizer.Fragments.ProductsFragment;
import com.shawinfosolutions.paintvisualizer.Model.MyProject;
import com.shawinfosolutions.paintvisualizer.Model.MyProjectItem;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class SelectedProjectTabAdapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;
    MyProjectItem user;
    public SelectedProjectTabAdapter(Context context, FragmentManager fm, int totalTabs, MyProjectItem user) {
        super(fm);
        this.myContext = context;
        this.user=user;
        this.totalTabs = totalTabs;


    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Bundle bundle = new Bundle();

                GalleryFragment decorFragment = new GalleryFragment();
                bundle.putString("Pictures", String.valueOf(user.getPictures()));
decorFragment.setArguments(bundle);
                return decorFragment;
            case 1:

                Bundle bundle1 = new Bundle();

                ColorFragment autoFragment = new ColorFragment();
                bundle1.putString("Colors", String.valueOf(user.getColors()));
                autoFragment.setArguments(bundle1);
                return autoFragment;
            case 2:
                Bundle bundle2 = new Bundle();

                ProductsFragment prodFragment = new ProductsFragment();
                bundle2.putString("Products", String.valueOf(user.getProducts()));
                prodFragment.setArguments(bundle2);

                return prodFragment;

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