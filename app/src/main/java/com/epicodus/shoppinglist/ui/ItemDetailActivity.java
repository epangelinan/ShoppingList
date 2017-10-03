package com.epicodus.shoppinglist.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.epicodus.shoppinglist.R;
import com.epicodus.shoppinglist.adapters.ItemPagerAdapter;
import com.epicodus.shoppinglist.models.Item;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ItemDetailActivity extends AppCompatActivity {
    @Bind(R.id.viewPager) ViewPager mViewPager;
    private ItemPagerAdapter adapterViewPager;
    ArrayList<Item> mItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        ButterKnife.bind(this);

        mItems = Parcels.unwrap(getIntent().getParcelableExtra("items"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new ItemPagerAdapter(getSupportFragmentManager(), mItems);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}
