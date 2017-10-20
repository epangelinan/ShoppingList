package com.epicodus.shoppinglist.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epicodus.shoppinglist.R;
import com.epicodus.shoppinglist.models.Store;
import com.epicodus.shoppinglist.ui.StoreDetailActivity;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class StoreListAdapter extends RecyclerView.Adapter<StoreListAdapter.StoreViewHolder> {
    private ArrayList<Store> mStores = new ArrayList<>();
    private Context mContext;

    public StoreListAdapter(Context context, ArrayList<Store> stores) {
        mContext = context;
        mStores = stores;
    }

    @Override
    public StoreListAdapter.StoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_list_item, parent, false);
        StoreViewHolder viewHolder = new StoreViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(StoreListAdapter.StoreViewHolder holder, int position) {
        holder.bindStore(mStores.get(position));
    }

    @Override
    public int getItemCount() {
        return mStores.size();
    }

    public class StoreViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.storeNameTextView) TextView mStoreNameTextView;
        @Bind(R.id.streetAddressTextView) TextView mStreetAddressTextView;
        @Bind(R.id.cityTextView) TextView mCityTextView;
        @Bind(R.id.stateProvCodeTextView) TextView mStateProvCodeTextView;
        @Bind(R.id.zipTextView) TextView mZipTextView;


        private Context mContext;

        public StoreViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, StoreDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("stores", Parcels.wrap(mStores));
            mContext.startActivity(intent);
        }

        public void bindStore(Store store) {
            mStoreNameTextView.setText(store.getName());
            mStreetAddressTextView.setText(store.getStreetAddress());
            mCityTextView.setText(store.getCity());
            mStateProvCodeTextView.setText(store.getStateProvCode());
            mZipTextView.setText(store.getZip());
        }
    }
}
