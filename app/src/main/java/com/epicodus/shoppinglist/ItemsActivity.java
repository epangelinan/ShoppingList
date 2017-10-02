package com.epicodus.shoppinglist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ItemsActivity extends AppCompatActivity {
    private TextView mItemTextView;
    private ListView mListView;
    private String[] items = new String[] {"Plaid Oblong Scarf", "Infinity Scarf", "Scarf w/Fringes", "Border Infinity Scarf", "Cowl Scarf with Fur", "Fancy Shawl Scarf"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        mListView  = (ListView) findViewById(R.id.listView);
        mItemTextView = (TextView) findViewById(R.id.itemTextView);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, items);
        mListView.setAdapter(adapter);

        Intent intent = getIntent();
        String item = intent.getStringExtra("item");
        mItemTextView.setText("Here are all the items: " + item);
    }
}
