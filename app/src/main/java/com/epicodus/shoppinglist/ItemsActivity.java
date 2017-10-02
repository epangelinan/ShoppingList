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

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ItemsActivity extends AppCompatActivity {
    public static final String TAG = ItemsActivity.class.getSimpleName();

    @Bind(R.id.itemTextView) TextView mItemTextView;
    @Bind(R.id.listView) ListView mListView;

    private String[] items = new String[] {"Plaid Oblong Scarf", "Infinity Scarf", "Scarf w/Fringes", "Border Infinity Scarf", "Cowl Scarf with Fur", "Fancy Shawl Scarf"};

    private String[] availabilities = new String[] {"ONLINE_ONLY", "STORE_ONLY", "ONLINE_ONLY", "ONLINE_AND_STORE", "ONLINE_AND_STORE", "ONLINE_ONLY"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        ButterKnife.bind(this);

        ShoppingListArrayAdapter adapter = new ShoppingListArrayAdapter(this, android.R.layout.simple_list_item_1, items, availabilities);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = ((TextView)view).getText().toString();
                Toast.makeText(ItemsActivity.this, item, Toast.LENGTH_LONG).show();
            }
        });

        Intent intent = getIntent();
        String item = intent.getStringExtra("item");
        mItemTextView.setText("Here are all the items: " + item);

        getItems(item);
    }

    private void getItems(String item) {
        final WalmartService walmartService = new WalmartService();
        walmartService.findItems(item, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String jsonData = response.body().string();
                    Log.v(TAG, jsonData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
    }
}
