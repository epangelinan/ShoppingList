package com.epicodus.shoppinglist;

import android.content.Context;
import android.widget.ArrayAdapter;

public class ShoppingListArrayAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mItems;
    private String[] mAvailabilities;

    public ShoppingListArrayAdapter(Context mContext, int resource, String[] mItems, String[] mAvailabilities) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mItems = mItems;
        this.mAvailabilities = mAvailabilities;
    }

    @Override
    public Object getItem(int position) {
        String item = mItems[position];
        String availability = mAvailabilities[position];
        return String.format("%s \nIs %s", item, availability);
    }

    @Override
    public int getCount() {
        return mItems.length;
    }
}
