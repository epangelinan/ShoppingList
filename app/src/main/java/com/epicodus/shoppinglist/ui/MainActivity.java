package com.epicodus.shoppinglist.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.epicodus.shoppinglist.R;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.findItemsButton) Button mFindItemsButton;
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;
    @Bind(R.id.savedItemsButton) Button mSavedItemsButton;
    @Bind(R.id.findStoresButton) Button mFindStoresButton;

    public static final String TAG = MainActivity.class.getSimpleName();
    private EditText mLocationEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface dancingScriptRegular = Typeface.createFromAsset(getAssets(), "fonts/dancingscriptregular.otf");
        mAppNameTextView.setTypeface(dancingScriptRegular);

        mFindItemsButton.setOnClickListener(this);
        mSavedItemsButton.setOnClickListener(this);
        mFindStoresButton.setOnClickListener(this);
        mLocationEditText = (EditText) findViewById(R.id.locationEditText);

    }

    @Override
    public void onClick(View v) {
        if(v == mFindItemsButton) {
            Intent intent = new Intent(MainActivity.this, ItemListActivity.class);
            startActivity(intent);
        }
        if (v == mSavedItemsButton) {
            Intent intent = new Intent(MainActivity.this, SavedItemListActivity.class);
            startActivity(intent);
        }
        if (v == mFindStoresButton) {
            String location = mLocationEditText.getText().toString();
//            Log.d(TAG, location);
            Intent intent = new Intent(MainActivity.this, StoreListActivity.class);
            intent.putExtra("location", location);
            startActivity(intent);
        }
    }
}
