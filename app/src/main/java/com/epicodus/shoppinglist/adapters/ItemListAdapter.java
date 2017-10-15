package com.epicodus.shoppinglist.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.shoppinglist.Constants;
import com.epicodus.shoppinglist.R;
import com.epicodus.shoppinglist.models.Item;
import com.epicodus.shoppinglist.ui.ItemDetailActivity;
import com.epicodus.shoppinglist.ui.ItemDetailFragment;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ItemViewHolder> {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

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



    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.itemImageView) ImageView mItemImageView;
        @Bind(R.id.itemNameTextView)  TextView mItemNameTextView;
        @Bind(R.id.salePriceTextView) TextView mSalePriceTextView;
        @Bind(R.id.offerTypeTextView) TextView mOfferTypeTextView;

        private Context mContext;
        private int mOrientation;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();


            // Determines the current orientation of the device:
            mOrientation = itemView.getResources().getConfiguration().orientation;

            // Checks if the recorded orientation matches Android's landscape configuration.
            // if so, we create a new DetailFragment to display in our special landscape layout:
            if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                createDetailFragment(0);
            }
            itemView.setOnClickListener(this);
        }

        public void bindItem(Item item) {
            Picasso.with(mContext)
                    .load(item.getMediumImage())
                    .resize(MAX_WIDTH, MAX_HEIGHT)
                    .centerCrop()
                    .into(mItemImageView);

            mItemNameTextView.setText(item.getName());
            mSalePriceTextView.setText("$" + Double.toString(item.getSalePrice()));
            mOfferTypeTextView.setText(item.getOfferType());
        }

        // Takes position of item in list as parameter:
        private void createDetailFragment(int position) {
            // Creates new ItemDetailFragment with the given position:
            ItemDetailFragment detailFragment = ItemDetailFragment.newInstance(mItems, position);
            // Gathers necessary components to replace the FrameLayout in the layout with the ItemDetailFragment:
            FragmentTransaction ft = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
            //  Replaces the FrameLayout with the ItemDetailFragment:
            ft.replace(R.id.itemDetailContainer, detailFragment);
            // Commits these changes:
            ft.commit();
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                createDetailFragment(itemPosition);
            } else {
                Intent intent = new Intent(mContext, ItemDetailActivity.class);
                intent.putExtra(Constants.EXTRA_KEY_POSITION, itemPosition);
                intent.putExtra(Constants.EXTRA_KEY_ITEMS, Parcels.wrap(mItems));
                mContext.startActivity(intent);
            }
        }
    }
}
