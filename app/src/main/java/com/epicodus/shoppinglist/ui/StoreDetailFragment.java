package com.epicodus.shoppinglist.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epicodus.shoppinglist.R;
import com.epicodus.shoppinglist.models.Store;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoreDetailFragment extends Fragment implements View.OnClickListener {
    @Bind(R.id.storeNameTextView) TextView mStoreNameLabel;
    @Bind(R.id.phoneTextView) TextView mPhoneLabel;
    @Bind(R.id.addressTextView) TextView mAddressLabel;

    private Store mStore;

    public static StoreDetailFragment newInstance(Store store) {
        StoreDetailFragment storeDetailFragment = new StoreDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("store", Parcels.wrap(store));
        storeDetailFragment.setArguments(args);
        return storeDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mStore = Parcels.unwrap(getArguments().getParcelable("store"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store_detail, container, false);
        ButterKnife.bind(this, view);

        mStoreNameLabel.setText(mStore.getName());
        mPhoneLabel.setText(mStore.getPhoneNumber());
        mAddressLabel.setText(mStore.getAddress());
        
        mPhoneLabel.setOnClickListener(this);
        mAddressLabel.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mPhoneLabel) {
            Intent phoneIntent = new Intent(Intent.ACTION_DIAL,
                    Uri.parse("tel:" + mStore.getPhoneNumber()));
            startActivity(phoneIntent);
        }
        if (v == mAddressLabel) {
            Intent mapIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("geo:" + mStore.getLatitude()
                            + "," + mStore.getLongitude()
                            + "?q=(" + mStore.getName() + ")"));
            startActivity(mapIntent);
        }
    }
}

