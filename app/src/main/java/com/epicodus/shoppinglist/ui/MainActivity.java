package com.epicodus.shoppinglist.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.epicodus.shoppinglist.Constants;
import com.epicodus.shoppinglist.R;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Bind(R.id.findItemsButton) Button mFindItemsButton;
    @Bind(R.id.searchItemEditText) EditText mSearchItemEditText;
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        Typeface dancingScriptRegular = Typeface.createFromAsset(getAssets(), "fonts/dancingscriptregular.otf");
        mAppNameTextView.setTypeface(dancingScriptRegular);

        mFindItemsButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mFindItemsButton) {
            String searchItem = mSearchItemEditText.getText().toString();
            if(!(searchItem).equals("")) {
                addToSharedPreferences(searchItem);
            }
            Intent intent = new Intent(MainActivity.this, ItemListActivity.class);
            //intent.putExtra("searchItem", searchItem);
            startActivity(intent);
        }
    }

    private void addToSharedPreferences(String searchItem) {
        mEditor.putString(Constants.PREFERENCES_SEARCH_ITEM_KEY, searchItem).apply();
    }
}
