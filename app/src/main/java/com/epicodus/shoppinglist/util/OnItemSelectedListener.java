package com.epicodus.shoppinglist.util;

import com.epicodus.shoppinglist.models.Item;

import java.util.ArrayList;

public interface OnItemSelectedListener {
    public void onItemSelected(Integer position, ArrayList<Item> items, String source);
}
