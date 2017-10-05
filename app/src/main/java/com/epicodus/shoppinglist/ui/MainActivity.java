package com.epicodus.shoppinglist.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.epicodus.shoppinglist.Constants;
import com.epicodus.shoppinglist.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

 //   private SharedPreferences mSharedPreferences;
 //   private SharedPreferences.Editor mEditor;

    private DatabaseReference mSearchedItemReference;
    private ValueEventListener mSearchedItemReferenceListener;

    @Bind(R.id.findItemsButton) Button mFindItemsButton;
    @Bind(R.id.searchItemEditText) EditText mSearchItemEditText;
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;
    @Bind(R.id.savedItemsButton) Button mSavedItemsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mSearchedItemReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_ITEM); //pinpoint location node

        mSearchedItemReferenceListener = mSearchedItemReference.addValueEventListener(new ValueEventListener() { //attach listener

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) { //something changed!
                for (DataSnapshot searchItemSnapshot : dataSnapshot.getChildren()) {
                    String searchItem = searchItemSnapshot.getValue().toString();
                    Log.d("Search items updated", "item: " + searchItem); //log
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { //update UI here if error occurred.

            }
        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

  //      mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
  //      mEditor = mSharedPreferences.edit();

        Typeface dancingScriptRegular = Typeface.createFromAsset(getAssets(), "fonts/dancingscriptregular.otf");
        mAppNameTextView.setTypeface(dancingScriptRegular);

        mFindItemsButton.setOnClickListener(this);
        mSavedItemsButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mFindItemsButton) {
            String searchItem = mSearchItemEditText.getText().toString();

            saveSearchItemToFirebase(searchItem);

 //           if(!(searchItem).equals("")) {
 //               addToSharedPreferences(searchItem);
 //           }

            Intent intent = new Intent(MainActivity.this, ItemListActivity.class);
            intent.putExtra("searchItem", searchItem);
            startActivity(intent);
        }
        if (v == mSavedItemsButton) {
            Intent intent = new Intent(MainActivity.this, SavedItemListActivity.class);
            startActivity(intent);
        }
    }

    public void saveSearchItemToFirebase(String searchItem) {
        mSearchedItemReference.push().setValue(searchItem);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSearchedItemReference.removeEventListener(mSearchedItemReferenceListener);
    }

//    private void addToSharedPreferences(String searchItem) {
//        mEditor.putString(Constants.PREFERENCES_SEARCH_ITEM_KEY, searchItem).apply();
//    }
}
