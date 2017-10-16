package com.epicodus.shoppinglist.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.shoppinglist.Constants;
import com.epicodus.shoppinglist.R;
import com.epicodus.shoppinglist.models.Item;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemDetailFragment extends Fragment implements View.OnClickListener{
    private static final int MAX_WIDTH = 400;
    private static final int MAX_HEIGHT = 300;

    private Item mItem;
    private ArrayList<Item> mItems;
    private int mPosition;
    private String mSource;

    @Bind(R.id.itemImageView) ImageView mItemImageLabel;
    @Bind(R.id.itemNameTextView) TextView mItemNameLabel;
    @Bind(R.id.offerTypeTextView) TextView mOfferTypeLabel;
    @Bind(R.id.salePriceTextView) TextView mSalePriceLabel;
    @Bind(R.id.stockTextView) TextView mStockLabel;
    @Bind(R.id.itemIdTextView) TextView mItemIdLabel;
    @Bind(R.id.shortDescriptionTextView) TextView mShortDescriptionLabel;
    @Bind(R.id.saveItemButton) TextView mSaveItemButton;
    @Bind(R.id.addToCartTextView) TextView mAddToCartLabel;



    public static ItemDetailFragment newInstance(ArrayList<Item> items, Integer position, String source) {
        ItemDetailFragment itemDetailFragment = new ItemDetailFragment();
        Bundle args = new Bundle();

        args.putParcelable(Constants.EXTRA_KEY_ITEMS, Parcels.wrap(items));
        args.putInt(Constants.EXTRA_KEY_POSITION, position);
        args.putString(Constants.KEY_SOURCE, source);

        itemDetailFragment.setArguments(args);
        return itemDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mItems = Parcels.unwrap(getArguments().getParcelable(Constants.EXTRA_KEY_ITEMS));
        mPosition = getArguments().getInt(Constants.EXTRA_KEY_POSITION);
        mItem = mItems.get(mPosition);
        mSource = getArguments().getString(Constants.KEY_SOURCE);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_detail, container, false);
        ButterKnife.bind(this, view);

        if (mSource.equals(Constants.SOURCE_SAVED)) {
            mSaveItemButton.setVisibility(View.GONE);
        } else {
            // This line of code should already exist. Make sure it now resides in this conditional:
            mSaveItemButton.setOnClickListener(this);
        }

        Picasso.with(view.getContext())
                .load(mItem.getMediumImage())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mItemImageLabel);

        mItemNameLabel.setText(mItem.getName());
        mOfferTypeLabel.setText(mItem.getOfferType());
        mSalePriceLabel.setText("$" + Double.toString(mItem.getSalePrice()));
        mStockLabel.setText(mItem.getStock());
        mItemIdLabel.setText("Item ID:" + mItem.getItemId());
        mShortDescriptionLabel.setText(mItem.getShortDescription());
        mAddToCartLabel.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mAddToCartLabel) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(mItem.getAddToCartUrl()));
            startActivity(webIntent);
        }
        if (v == mSaveItemButton) {
            DatabaseReference itemRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_ITEMS);
            itemRef.push().setValue(mItem);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
    }
}
