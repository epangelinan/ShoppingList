package com.epicodus.shoppinglist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ItemsActivity extends AppCompatActivity {
    @Bind(R.id.itemTextView) TextView mItemTextView;
    @Bind(R.id.listView) ListView mListView;

    private String[] items = new String[] {"Plaid Oblong Scarf", "Infinity Scarf", "Scarf w/Fringes", "Border Infinity Scarf", "Cowl Scarf with Fur", "Fancy Shawl Scarf"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        ButterKnife.bind(this);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, items);
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
    }
}
