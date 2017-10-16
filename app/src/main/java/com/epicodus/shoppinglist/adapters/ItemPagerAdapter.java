package com.epicodus.shoppinglist.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.epicodus.shoppinglist.models.Item;
import com.epicodus.shoppinglist.ui.ItemDetailFragment;

import java.util.ArrayList;

public class ItemPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Item> mItems;
    private String mSource;

    public ItemPagerAdapter(FragmentManager fm, ArrayList<Item> items, String source) {
        super(fm);
        mItems = items;
        mSource = source;
    }

    @Override
    public Fragment getItem(int position) {
        return ItemDetailFragment.newInstance(mItems, position, mSource);
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mItems.get(position).getName();
    }
}
