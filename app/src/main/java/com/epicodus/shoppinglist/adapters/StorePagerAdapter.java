package com.epicodus.shoppinglist.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.epicodus.shoppinglist.models.Store;
import com.epicodus.shoppinglist.ui.StoreDetailFragment;

import java.util.ArrayList;

public class StorePagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Store> mStores;

    public StorePagerAdapter(FragmentManager fm, ArrayList<Store> stores) {
        super(fm);
        mStores = stores;
    }

    @Override
    public Fragment getItem(int position) {
        return StoreDetailFragment.newInstance(mStores.get(position));
    }

    @Override
    public int getCount() {
        return mStores.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mStores.get(position).getName();
    }
}
