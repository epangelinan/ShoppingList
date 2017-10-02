package com.epicodus.shoppinglist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    private Button mFindItemsButton;
    private EditText mItemEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mItemEditText = (EditText) findViewById(R.id.itemEditText);

        mFindItemsButton = (Button) findViewById(R.id.findItemsButton);

        mFindItemsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = mItemEditText.getText().toString();
                Log.d(TAG, item);
                Intent intent = new Intent (MainActivity.this, ItemsActivity.class);
                startActivity(intent);
            }
        });
    }
}
