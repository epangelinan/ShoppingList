package com.epicodus.shoppinglist.ui;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.shoppinglist.Constants;
import com.epicodus.shoppinglist.R;
import com.epicodus.shoppinglist.adapters.ItemListAdapter;
import com.epicodus.shoppinglist.models.Item;
import com.epicodus.shoppinglist.services.WalmartService;
import com.epicodus.shoppinglist.util.OnItemSelectedListener;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemListFragment extends Fragment {
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    private ItemListAdapter mAdapter;
    public ArrayList<Item> mItems = new ArrayList<>();
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentAddress;
    private OnItemSelectedListener mOnItemSelectedListener;

    public ItemListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mEditor = mSharedPreferences.edit();

        setHasOptionsMenu(true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mOnItemSelectedListener = (OnItemSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + e.getMessage());
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        ButterKnife.bind(this, view);

        mRecentAddress = mSharedPreferences.getString(Constants.PREFERENCES_SEARCH_ITEM_KEY, null);

        if (mRecentAddress != null) {
            getItems(mRecentAddress);
        }

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_search, menu);

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
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public void getItems(String searchItem) {
        final WalmartService walmartService = new WalmartService();

        walmartService.findItems(searchItem, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mItems = walmartService.processResults(response);

                getActivity().runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        mAdapter = new ItemListAdapter(getActivity(), mItems, mOnItemSelectedListener);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
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
