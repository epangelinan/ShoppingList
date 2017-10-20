package com.epicodus.shoppinglist.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.epicodus.shoppinglist.R;
import com.epicodus.shoppinglist.adapters.StoreListAdapter;
import com.epicodus.shoppinglist.models.Store;
import com.epicodus.shoppinglist.services.WalmartService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class StoreListActivity extends AppCompatActivity {
    public static final String TAG = StoreListActivity.class.getSimpleName();

    @Bind(R.id.recyclerView)RecyclerView mRecyclerView;
    private StoreListAdapter mAdapter;

    public ArrayList<Store> stores = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stores);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");

     //   mLocationTextView.setText("Here are all the stores near: " + location);
        getStores(location);
    }

    private void getStores(String location) {
        final WalmartService walmartService = new WalmartService();

        walmartService.findStores(location, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                stores = walmartService.processStoreResults(response);

                StoreListActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        mAdapter = new StoreListAdapter(getApplicationContext(), stores);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(StoreListActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }
}
