package com.epicodus.shoppinglist.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.shoppinglist.Constants;
import com.epicodus.shoppinglist.R;
import com.epicodus.shoppinglist.models.Item;
import com.epicodus.shoppinglist.ui.ItemDetailActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseItemViewHolder extends RecyclerView.ViewHolder {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    View mView;
    Context mContext;
    public ImageView mItemImageView;

    public FirebaseItemViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindItem(Item item) {
        mItemImageView = (ImageView) mView.findViewById(R.id.itemImageView);
        TextView itemNameTextView = (TextView) mView.findViewById(R.id.itemNameTextView);
        TextView offerTypeTextView = (TextView) mView.findViewById(R.id.offerTypeTextView);
        TextView salePriceTextView = (TextView) mView.findViewById(R.id.salePriceTextView);

        Picasso.with(mContext)
                .load(item.getMediumImage())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mItemImageView);

        itemNameTextView.setText(item.getName());
        offerTypeTextView.setText(item.getOfferType());
        salePriceTextView.setText("$" + Double.toString(item.getSalePrice()).format("%.2f", item.getSalePrice()));
    }
}
