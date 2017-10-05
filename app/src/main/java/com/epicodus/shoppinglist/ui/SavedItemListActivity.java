package com.epicodus.shoppinglist.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.epicodus.shoppinglist.Constants;
import com.epicodus.shoppinglist.R;
import com.epicodus.shoppinglist.adapters.FirebaseItemViewHolder;
import com.epicodus.shoppinglist.models.Item;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedItemListActivity extends AppCompatActivity {
    private DatabaseReference mItemReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_items);
        ButterKnife.bind(this);

        mItemReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_ITEMS);
        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Item, FirebaseItemViewHolder>
                (Item.class, R.layout.item_list_item, FirebaseItemViewHolder.class,
                        mItemReference) {

            @Override
            protected void populateViewHolder(FirebaseItemViewHolder viewHolder,
                                              Item model, int position) {
                viewHolder.bindItem(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}
