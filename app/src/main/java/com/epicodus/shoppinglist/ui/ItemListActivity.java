package com.epicodus.shoppinglist.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
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
import com.epicodus.shoppinglist.util.OnItemSelectedListener;

import org.parceler.Parcels;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ItemListActivity extends AppCompatActivity implements OnItemSelectedListener {

    private Integer mPosition;
    ArrayList<Item> mItems;
    String mSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        if (savedInstanceState != null) {

            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                mPosition = savedInstanceState.getInt(Constants.EXTRA_KEY_POSITION);
                mItems = Parcels.unwrap(savedInstanceState.getParcelable(Constants.EXTRA_KEY_ITEMS));
                mSource = savedInstanceState.getString(Constants.KEY_SOURCE);

                if (mPosition != null && mItems != null) {
                    Intent intent = new Intent(this, ItemDetailActivity.class);
                    intent.putExtra(Constants.EXTRA_KEY_POSITION, mPosition);
                    intent.putExtra(Constants.EXTRA_KEY_ITEMS, Parcels.wrap(mItems));
                    intent.putExtra(Constants.KEY_SOURCE, mSource);
                    startActivity(intent);
                }
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (mPosition != null && mItems != null) {
            outState.putInt(Constants.EXTRA_KEY_POSITION, mPosition);
            outState.putParcelable(Constants.EXTRA_KEY_ITEMS, Parcels.wrap(mItems));
            outState.putString(Constants.KEY_SOURCE, mSource);
        }
    }

    @Override
    public void onItemSelected(Integer position, ArrayList<Item> items, String source) {
        mPosition = position;
        mItems = items;
        mSource = source;
    }
}
