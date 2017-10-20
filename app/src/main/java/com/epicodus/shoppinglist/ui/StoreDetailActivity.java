package com.epicodus.shoppinglist.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.epicodus.shoppinglist.R;
import com.epicodus.shoppinglist.adapters.StorePagerAdapter;
import com.epicodus.shoppinglist.models.Store;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class StoreDetailActivity extends AppCompatActivity {
    @Bind(R.id.viewPager) ViewPager mViewPager;
    private StorePagerAdapter adapterViewPager;
    ArrayList<Store> mStores = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_detail);
        ButterKnife.bind(this);

        mStores = Parcels.unwrap(getIntent().getParcelableExtra("stores"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new StorePagerAdapter(getSupportFragmentManager(), mStores);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}
