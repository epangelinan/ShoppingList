package com.epicodus.shoppinglist.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.shoppinglist.Constants;
import com.epicodus.shoppinglist.R;
import com.epicodus.shoppinglist.adapters.FirebaseItemListAdapter;
import com.epicodus.shoppinglist.adapters.FirebaseItemViewHolder;
import com.epicodus.shoppinglist.models.Item;
import com.epicodus.shoppinglist.util.SimpleItemTouchHelperCallback;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.Bind;

/**
 * A simple {@link Fragment} subclass.
 */
public class SavedItemListFragment extends Fragment implements OnStartDragListener {
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private FirebaseItemListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;

    public SavedItemListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saved_item_list, container, false);
        ButterKnife.bind(this, view);
        setUpFirebaseAdapter();
        return view;
    }

    private void setUpFirebaseAdapter() {

        Query query = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_CHILD_ITEMS)
                .orderByChild(Constants.FIREBASE_QUERY_INDEX);

        mFirebaseAdapter = new FirebaseItemListAdapter
                (Item.class, R.layout.item_list_item_drag, FirebaseItemViewHolder.class,
                        query, this, getActivity());

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mFirebaseAdapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}
