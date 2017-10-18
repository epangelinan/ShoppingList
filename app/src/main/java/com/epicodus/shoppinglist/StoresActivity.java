package com.epicodus.shoppinglist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.shoppinglist.services.WalmartService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class StoresActivity extends AppCompatActivity {
    public static final String TAG = StoresActivity.class.getSimpleName();

    @Bind(R.id.locationTextView) TextView mLocationTextView;
    @Bind(R.id.listView) ListView mListView;

    public ArrayList<Store> stores = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stores);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");

        mLocationTextView.setText("Here are all the stores near: " + location);
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

                StoresActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        String[] storeNames = new String[stores.size()];
                        for (int i = 0; i < storeNames.length; i++) {
                            storeNames[i] = stores.get(i).getName();
                        }

                        ArrayAdapter adapter = new ArrayAdapter(StoresActivity.this,
                                android.R.layout.simple_list_item_1, storeNames);
                        mListView.setAdapter(adapter);

                        for (Store store : stores) {
                            Log.d(TAG, "name: " + store.getName());
                            Log.d(TAG, "country: " + store.getCountry());
                            Log.d(TAG, "streetAddress: " + store.getStreetAddress());
                            Log.d(TAG, "city: " + store.getCity());
                            Log.d(TAG, "stateProvCode: " + store.getStateProvCode());
                            Log.d(TAG, "zip: " + store.getZip());
                            Log.d(TAG, "phoneNumber: " + store.getPhoneNumber());
                        }
                    }

                });
            }
        });
    }
}
