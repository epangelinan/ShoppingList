package com.epicodus.shoppinglist.models;


public class Item {
    private String mName;
    private String mItemId;
    private double mSalePrice;
    private String mLongDescription;
    private String mMediumImage;
    private String mStock;
    private String mOfferType;

    public Item(String name,
                String itemId,
                double salePrice,
                String longDescription,
                String mediumImage,
                String stock,
                String offerType) {

        this.mName = name;
        this.mItemId = itemId;
        this.mSalePrice = salePrice;
        this.mLongDescription = longDescription;
        this.mMediumImage = mediumImage;
        this.mStock = stock;
        this.mOfferType = offerType;
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

    public String getLongDescription() {
        return mLongDescription;
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
}
