package com.epicodus.shoppinglist.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.shoppinglist.R;
import com.epicodus.shoppinglist.models.Item;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemDetailFragment extends Fragment {
    @Bind(R.id.itemImageView) ImageView mItemImageLabel;
    @Bind(R.id.itemNameTextView) TextView mItemNameLabel;
    @Bind(R.id.offerTypeTextView) TextView mOfferTypeLabel;
    @Bind(R.id.salePriceTextView) TextView mSalePriceLabel;
    @Bind(R.id.stockTextView) TextView mStockLabel;
    @Bind(R.id.itemIdTextView) TextView mItemIdLabel;
    @Bind(R.id.shortDescriptionTextView) TextView mShortDescriptionLabel;
    @Bind(R.id.saveItemButton) TextView mSaveItemButton;

    private Item mItem;

    public static ItemDetailFragment newInstance(Item item) {
        ItemDetailFragment itemDetailFragment = new ItemDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("item", Parcels.wrap(item));
        itemDetailFragment.setArguments(args);
        return itemDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mItem = Parcels.unwrap(getArguments().getParcelable("item"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext()).load(mItem.getMediumImage()).into(mItemImageLabel);

        mItemNameLabel.setText(mItem.getName());
        mOfferTypeLabel.setText(mItem.getOfferType());
        mSalePriceLabel.setText("$" + Double.toString(mItem.getSalePrice()));
        mStockLabel.setText(mItem.getStock());
        mItemIdLabel.setText("Item ID:" + mItem.getItemId());
        mShortDescriptionLabel.setText(mItem.getShortDescription());

        return view;
    }
}
