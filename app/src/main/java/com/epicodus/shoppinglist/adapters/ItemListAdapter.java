package com.epicodus.shoppinglist.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.shoppinglist.R;
import com.epicodus.shoppinglist.models.Item;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ItemViewHolder> {
    private ArrayList<Item> mItems = new ArrayList<>();
    private Context mContext;

    public ItemListAdapter(Context context, ArrayList<Item> items) {
        mContext = context;
        mItems = items;
    }

    @Override
    public ItemListAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_item, parent, false);
        ItemViewHolder viewHolder = new ItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ItemListAdapter.ItemViewHolder holder, int position) {
        holder.bindItem(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }



    public class ItemViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.itemImageView) ImageView mItemImageView;
        @Bind(R.id.itemNameTextView)  TextView mItemNameTextView;
        @Bind(R.id.salePriceTextView) TextView mSalePriceTextView;
        @Bind(R.id.offerTypeTextView) TextView mOfferTypeTextView;

        private Context mContext;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindItem(Item item) {
            Picasso.with(mContext).load(item.getMediumImage()).into(mItemImageView);
            mItemNameTextView.setText(item.getName());
            mSalePriceTextView.setText("$" + Double.toString(item.getSalePrice()));
            mOfferTypeTextView.setText(item.getOfferType());
        }
    }
}
