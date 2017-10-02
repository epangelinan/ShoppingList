package com.epicodus.shoppinglist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ItemsActivity extends AppCompatActivity {
    private TextView mItemTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        mItemTextView = (TextView) findViewById(R.id.itemTextView);
        Intent intent = getIntent();
        String item = intent.getStringExtra("item");
        mItemTextView.setText("Here are all the items: " + item);
    }
}
