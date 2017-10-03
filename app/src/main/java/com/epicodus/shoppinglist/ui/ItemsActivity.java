package com.epicodus.shoppinglist.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.epicodus.shoppinglist.R;
import com.epicodus.shoppinglist.models.Item;
import com.epicodus.shoppinglist.services.WalmartService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ItemsActivity extends AppCompatActivity {
    public static final String TAG = ItemsActivity.class.getSimpleName();

    @Bind(R.id.searchItemTextView) TextView mSearchItemTextView;
    @Bind(R.id.listView) ListView mListView;

    public ArrayList<Item> mItems = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String searchItem = intent.getStringExtra("searchItem");

        mSearchItemTextView.setText("Here are all the items: " + searchItem);

        getItems(searchItem);
    }

    private void getItems(String searchItem) {
        final WalmartService walmartService = new WalmartService();

        walmartService.findItems(searchItem, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mItems = walmartService.processResults(response);
                ItemsActivity.this.runOnUiThread(new Runnable(){
                    @Override
                    public void run() {
                        String[] itemNames = new String[mItems.size()];
                        for (int i = 0; i < itemNames.length; i++) {
                            itemNames[i] = mItems.get(i).getName();
                        }

                        ArrayAdapter adapter = new ArrayAdapter(ItemsActivity.this, android.R.layout.simple_list_item_1, itemNames);
                            mListView.setAdapter(adapter);
                            for (Item item: mItems) {
                                Log.d(TAG, "Name: " + item.getName());
                                Log.d(TAG, "ItemId: " + item.getItemId());
                                Log.d(TAG, "SalePrice: " + Double.toString(item.getSalePrice()));
                                Log.d(TAG, "LongDescription: " + item.getLongDescription());
                                Log.d(TAG, "MediumImage: " + item.getMediumImage());
                                Log.d(TAG, "Stock: " + item.getStock());
                                Log.d(TAG, "OfferType: " + item.getOfferType());
                            }
                        }
                    });

                }

            });
        }

    }
