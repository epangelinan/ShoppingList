package com.epicodus.shoppinglist.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.epicodus.shoppinglist.Constants;
import com.epicodus.shoppinglist.R;
import com.epicodus.shoppinglist.adapters.ItemListAdapter;
import com.epicodus.shoppinglist.models.Item;
import com.epicodus.shoppinglist.services.WalmartService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ItemListActivity extends AppCompatActivity {
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentAddress;

   // public static final String TAG = ItemListActivity.class.getSimpleName();

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    private ItemListAdapter mAdapter;
    public ArrayList<Item> mItems = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String searchItem = intent.getStringExtra("searchItem");

        getItems(searchItem);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentAddress = mSharedPreferences.getString(Constants.PREFERENCES_SEARCH_ITEM_KEY, null);
        if (mRecentAddress != null) {
            getItems(mRecentAddress);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                addToSharedPreferences(query);
                getItems(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
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

                ItemListActivity.this.runOnUiThread(new Runnable(){

                    @Override
                    public void run() {
                        mAdapter = new ItemListAdapter(getApplicationContext(), mItems);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(ItemListActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });

            }

        });
    }

    private void addToSharedPreferences(String searchItem) {
        mEditor.putString(Constants.PREFERENCES_SEARCH_ITEM_KEY, searchItem).apply();
    }

}
