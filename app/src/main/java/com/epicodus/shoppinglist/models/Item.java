package com.epicodus.shoppinglist.models;

import org.parceler.Parcel;

@Parcel
public class Item {
    private String mName;
    private String mItemId;
    private double mSalePrice;
    private String mShortDescription;
    private String mMediumImage;
    private String mStock;
    private String mOfferType;
    private String mAddToCartUrl;

    public Item() {}

    public Item(String name,
                String itemId,
                double salePrice,
                String shortDescription,
                String mediumImage,
                String stock,
                String offerType,
                String addToCartUrl) {

        this.mName = name;
        this.mItemId = itemId;
        this.mSalePrice = salePrice;
        this.mShortDescription = shortDescription;
        this.mMediumImage = mediumImage;
        this.mStock = stock;
        this.mOfferType = offerType;
        this.mAddToCartUrl = addToCartUrl;
    }

    public String getName() {
        return mName;
    }

    public String getItemId() {
        return mItemId;
    }

    public double getSalePrice() {
        return mSalePrice;
    }

    public String getShortDescription() {
        return mShortDescription;
    }

    public String getMediumImage() {
        return mMediumImage;
    }

    public String getStock() {
        return mStock;
    }

    public String getOfferType() {
        return mOfferType;
    }

    public String getAddToCartUrl() {
        return mAddToCartUrl;
    }
}
